package com.lin.controller;

/**
 * 公共逻辑 Controller
 * @author lkmc2
 * @date 2020/3/10 22:51
 */
public class BaseController {

    /** 购物车在 cookie 中的名称 **/
    protected static final String FOOD_SHOP_SHOP_CART = "shopcart";

    /** 每页显示条数 **/
    protected static final int PAGE_SIZE = 20;

    /** 每页评论显示条数 **/
    protected static final int COMMENT_PAGE_SIZE = 10;

    /** 通知商户支付成功的 url ，微信支付成功 -> 支付中心 -> food-shop **/
    protected static final String PAY_RETURN_URL = "http://localhost:8088/orders/notifyMerchantOrderPaid";

}
