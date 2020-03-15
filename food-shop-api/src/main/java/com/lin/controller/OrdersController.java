package com.lin.controller;

import com.lin.bo.SubmitOrderBO;
import com.lin.enums.PayMethodEnum;
import com.lin.service.OrderService;
import com.lin.utils.CookieUtils;
import com.lin.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String orderId = orderService.createOrder(submitOrderBO);

        // 2.创建订单以后，移除购物车中已结算（已提交）的商品
        // todo：整合 redis 之后，完善购物车中的已结算商品清除，并且同步到前端的 cookie
        CookieUtils.setCookie(request, response, FOOD_SHOP_SHOP_CART, "", true);

        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据

        return JsonResult.ok(orderId);
    }

}
