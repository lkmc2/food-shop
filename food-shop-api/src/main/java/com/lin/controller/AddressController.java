package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.lin.bo.AddressBO;
import com.lin.pojo.UserAddress;
import com.lin.service.AddressService;
import com.lin.utils.JsonResult;
import com.lin.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "用户新增地址", notes = "用户新增地址")
    @PostMapping("/add")
    public JsonResult add(@RequestBody AddressBO addressBO) {
        // 检查地址是否正确
        JsonResult checkResult = checkAddress(addressBO);

        if (checkResult.getStatus() != 200) {
            return checkResult;
        }

        // 添加用户的新地址
        addressService.addNewUserAddress(addressBO);

        return JsonResult.ok();
    }

    /**
     * 检查地址是否正确
     * @param addressBO 地址信息
     * @return 地址检查结果
     */
    private JsonResult checkAddress(AddressBO addressBO) {
        String receiver = addressBO.getReceiver();

        if (StrUtil.isBlank(receiver)) {
            return JsonResult.errorMsg("收货人不能为空");
        }

        if (StrUtil.length(receiver) > 12) {
            return JsonResult.errorMsg("收货人名字不能太长");
        }

        String mobile = addressBO.getMobile();

        if (StrUtil.isBlank(mobile)) {
            return JsonResult.errorMsg("收货人手机号不能为空");
        }

        if (StrUtil.length(mobile) != 11) {
            return JsonResult.errorMsg("收货人手机号长度不正确");
        }

        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return JsonResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();

        if (StrUtil.isBlank(province) ||
                StrUtil.isBlank(city) ||
                StrUtil.isBlank(district) ||
                StrUtil.isBlank(detail)) {
            return JsonResult.errorMsg("收货地址信息不能为空");
        }

        return JsonResult.ok();
    }

}
