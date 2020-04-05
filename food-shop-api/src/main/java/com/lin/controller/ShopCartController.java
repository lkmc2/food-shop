package com.lin.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.lin.bo.ShopCartBO;
import com.lin.utils.JsonResult;
import com.lin.utils.JsonUtils;
import com.lin.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品 Controller
 * @author lkmc2
 * @date 2020/3/14 12:15
 */
@Api(value = "购物车接口", tags = {"购物车的相关接口"})
@RestController
@RequestMapping("shopcart")
public class ShopCartController extends BaseController {

    @Autowired
    private RedisOperator redisOperator;

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

        // 前端用户在登陆的情况下，添加商品到购物车，会同时在后端同步购物车到redis数据库
        // 需要判断当前购物车中包含已经存在的商品，如果存在则累加购买数量
        String shopCartJson = redisOperator.get(FOOD_SHOP_SHOP_CART + ":" + userId);

        List<ShopCartBO> shopCartList;

        if (StrUtil.isNotBlank(shopCartJson)) {
            // redis 中已经有购物车
            shopCartList = JsonUtils.jsonToList(shopCartJson, ShopCartBO.class);

            // 判断购物车中是有存在已有商品，有的话 counts 累加
            boolean isHaving = false;

            for (ShopCartBO shopCart : shopCartList) {
                String tempSpecId = shopCart.getSpecId();

                if (tempSpecId.equals(shopCartBO.getSpecId())) {
                    shopCart.setBuyCounts(shopCart.getBuyCounts() + shopCartBO.getBuyCounts());
                    isHaving = true;
                }
            }

            if (!isHaving) {
                shopCartList.add(shopCartBO);
            }
        } else {
            // redis 中没有该用户的购物车
            shopCartList = Lists.newArrayList();
            // 直接添加到购物车中
            shopCartList.add(shopCartBO);
        }

        // 覆盖现有 redis 中的购物车
        redisOperator.set(FOOD_SHOP_SHOP_CART + ":" + userId, JsonUtils.objectToJson(shopCartList));

        return JsonResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true),
        @ApiImplicitParam(name = "itemSpecId", value = "商品规格id", required = true)
    })
    @PostMapping("/del")
    public JsonResult del(
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (StrUtil.isBlank(userId) || StrUtil.isBlank(itemSpecId)) {
            return JsonResult.errorMsg("参数不能为空");
        }

        // 用户在页面删除购物车中的商品数据，如果此时用户已经登陆，则需要同步删除 redis 购物车中的数据
        String shopCartJson = redisOperator.get(FOOD_SHOP_SHOP_CART + ":" + userId);

        if (StrUtil.isNotBlank(shopCartJson)) {
            // redis 中已经有购物车
            List<ShopCartBO> shopCartList = JsonUtils.jsonToList(shopCartJson, ShopCartBO.class);

            // 判断购物车中是否存在已有商品，有的话则删除
            for (ShopCartBO shopCart : shopCartList) {
                String tempSpecId = shopCart.getSpecId();

                if (tempSpecId.equals(itemSpecId)) {
                    shopCartList.remove(shopCart);
                    break;
                }
            }

            // 覆盖现有 redis 中的购物车
            redisOperator.set(FOOD_SHOP_SHOP_CART + ":" + userId, JsonUtils.objectToJson(shopCartList));
        }

        return JsonResult.ok();
    }

}
