package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.lin.bo.ShopCartBO;
import com.lin.bo.SubmitOrderBO;
import com.lin.enums.OrderStatusEnum;
import com.lin.enums.PayMethodEnum;
import com.lin.pojo.OrderStatus;
import com.lin.service.OrderService;
import com.lin.utils.CookieUtils;
import com.lin.utils.JsonResult;
import com.lin.utils.JsonUtils;
import com.lin.utils.RedisOperator;
import com.lin.vo.MerchantOrdersVO;
import com.lin.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单 Controller
 * @author lkmc2
 * @date 2020/3/14 20:25
 */
@Api(value = "订单相关", tags = {"订单相关的Api接口"})
@RestController
@RequestMapping("orders")
public class OrdersController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "用户下单", notes = "用户下单")
    @PostMapping("/create")
    public JsonResult create(@RequestBody SubmitOrderBO submitOrderBO,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        // 支付方式
        Integer payMethod = submitOrderBO.getPayMethod();

        if (!payMethod.equals(PayMethodEnum.WEIXIN.type)
            && !payMethod.equals(PayMethodEnum.ALIPAY.type)) {
            return JsonResult.errorMsg("支付方式不支持！");
        }

//        System.out.println(submitOrderBO);

        // redis 缓存的购物车列表
        String shopCartJson = redisOperator.get(FOOD_SHOP_SHOP_CART + ":" + submitOrderBO.getUserId());

        if (StrUtil.isNotBlank(shopCartJson)) {
            return JsonResult.errorMsg("购物车数据不正确");
        }

        // 购物车列表
        List<ShopCartBO> shopCartList = JsonUtils.jsonToList(shopCartJson, ShopCartBO.class);

        // 1.创建订单
        OrderVO orderVO = orderService.createOrder(shopCartList, submitOrderBO);

        // 订单 id
        String orderId = orderVO.getOrderId();


        // 2.创建订单以后，移除购物车中已结算（已提交）的商品
        // todo：整合 redis 之后，完善购物车中的已结算商品清除，并且同步到前端的 cookie
        CookieUtils.setCookie(request, response, FOOD_SHOP_SHOP_CART, "", true);

        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        // 商户订单 VO
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        // 设置通知商户支付成功的 url
        merchantOrdersVO.setReturnUrl(PAY_RETURN_URL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("userId", "jack");
        headers.add("password", "123456");

        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<JsonResult> responseEntity = restTemplate.postForEntity(PAYMENT_URL, entity, JsonResult.class);

        JsonResult paymentResult = responseEntity.getBody();

        Preconditions.checkNotNull(paymentResult, "支付中心响应结果不能为空");

        if (paymentResult.getStatus() != HttpStatus.OK.value()) {
            return JsonResult.errorMsg("支付中心订单创建失败，请联系管理员！");
        }

        return JsonResult.ok(orderId);
    }

    @ApiOperation(value = "通知商户订单已支付", notes = "通知商户订单已支付")
    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(
            @ApiParam(name = "merchantOrderId", value = "商户订单id", required = true)
            @RequestParam String merchantOrderId) {

        // 修改订单状态为：已付款，待发货
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);

        return HttpStatus.OK.value();
    }

    @ApiOperation(value = "获取订单支付信息", notes = "获取订单支付信息")
    @PostMapping("/getPaidOrderInfo")
    public JsonResult getPaidOrderInfo(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) {

        if (StrUtil.isBlank(orderId)) {
            return JsonResult.errorMsg("");
        }

        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);

        return JsonResult.ok(orderStatus);
    }

}
