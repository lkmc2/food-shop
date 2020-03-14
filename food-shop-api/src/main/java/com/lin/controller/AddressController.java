package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.lin.pojo.UserAddress;
import com.lin.service.AddressService;
import com.lin.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 地址 Controller
 * @author lkmc2
 * @date 2020/3/14 17:07
 */
@Api(value = "地址相关", tags = {"地址相关的Api接口"})
@RestController
@RequestMapping("address")
public class AddressController {

    /*
    用户在确认订单页面，可以针对收货地址做如下操作：
    1.查询用户的所有收货地址列表
    2.新增收货地址
    3.删除收货地址
    4.修改收货地址
    5.设置默认地址
     */

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "根据用户id查询收货地址列表", notes = "根据用户id查询收货地址列表")
    @PostMapping("/list")
    public JsonResult list(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {

        if (StrUtil.isBlank(userId)) {
            return JsonResult.errorMsg("");
        }

        List<UserAddress> list = addressService.queryAll(userId);

        return JsonResult.ok(list);
    }

}
