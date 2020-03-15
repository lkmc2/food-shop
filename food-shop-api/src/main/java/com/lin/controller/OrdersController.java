package com.lin.controller;

import com.lin.bo.SubmitOrderBO;
import com.lin.enums.OrderStatusEnum;
import com.lin.enums.PayMethodEnum;
import com.lin.service.OrderService;
import com.lin.utils.CookieUtils;
import com.lin.utils.JsonResult;
import com.lin.vo.MerchantOrdersVO;
import com.lin.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        System.out.println(submitOrderBO);

        // 1.创建订单
        OrderVO orderVO = orderService.createOrder(submitOrderBO);

        // 订单 id
        String orderId = orderVO.getOrderId();

        // 商户订单 VO
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        // 设置通知商户支付成功的 url
        merchantOrdersVO.setReturnUrl(PAY_RETURN_URL);

        // 2.创建订单以后，移除购物车中已结算（已提交）的商品
        // todo：整合 redis 之后，完善购物车中的已结算商品清除，并且同步到前端的 cookie
        CookieUtils.setCookie(request, response, FOOD_SHOP_SHOP_CART, "", true);

        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据

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

}
