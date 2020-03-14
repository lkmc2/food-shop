package com.lin.controller;

import com.lin.bo.SubmitOrderBO;
import com.lin.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单 Controller
 * @author lkmc2
 * @date 2020/3/14 20:25
 */
@Api(value = "订单相关", tags = {"订单相关的Api接口"})
@RestController
@RequestMapping("orders")
public class OrdersController {

    @ApiOperation(value = "用户下单", notes = "用户下单")
    @PostMapping("/create")
    public JsonResult create(@RequestBody SubmitOrderBO submitOrderBO) {

        System.out.println(submitOrderBO);

        // 1.创建订单
        // 2.创建订单以后，移除购物车中已结算（已提交）的商品
        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据

        return JsonResult.ok();
    }

}
