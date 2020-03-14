package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.lin.bo.ShopCartBO;
import com.lin.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品 Controller
 * @author lkmc2
 * @date 2020/3/14 12:15
 */
@Api(value = "购物车接口", tags = {"购物车的相关接口"})
@RestController
@RequestMapping("shopcart")
public class ShopCartController extends BaseController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    @PostMapping("/add")
    public JsonResult add(
            @RequestParam String userId,
            @RequestBody ShopCartBO shopCartBO,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (StrUtil.isBlank(userId)) {
            return JsonResult.errorMsg("");
        }

        System.out.println(shopCartBO);

        // todo：前端用户在登陆的情况下，添加商品到购物车，会同时在后端同步购物车到redis数据库

        return JsonResult.ok();
    }

}
