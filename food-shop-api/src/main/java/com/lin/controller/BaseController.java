package com.lin.controller;

import cn.hutool.core.util.IdUtil;
import com.lin.pojo.Orders;
import com.lin.pojo.Users;
import com.lin.service.center.MyOrdersService;
import com.lin.utils.JsonResult;
import com.lin.utils.RedisOperator;
import com.lin.vo.UsersVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

    /** 通用分页条数 **/
    protected static final int COMMON_PAGE_SIZE = 10;

    /** Redis 用户会话 **/
    protected static final String REDIS_USER_TOKEN = "redis_user_token";

    /** 支付中心的调用地址 **/
    protected static final String PAYMENT_URL = "http://localhost:8088/payment/createMerchantOrder";

    /** 通知商户支付成功的 url ，微信支付成功 -> 支付中心 -> food-shop **/
    protected static final String PAY_RETURN_URL = "http://localhost:8088/orders/notifyMerchantOrderPaid";

    /** 用户上传头像的地址 **/
    protected static final String IMAGE_USER_FACE_LOCATION = "E:\\UploadWorkplace\\FoodShowPicUpload\\faces";

    @Autowired
    protected MyOrdersService myOrdersService;

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 用户验证用户和订单是否有关联关系，避免非法用户调用
     * @param userId 用户 id
     * @param orderId 订单 id
     * @return 验证结果
     */
    protected JsonResult checkUserOrder(String userId, String orderId) {
        Orders order = myOrdersService.queryMyOrder(userId, orderId);

        if (order == null) {
            return JsonResult.errorMsg("订单不存在");
        }

        return JsonResult.ok(order);
    }

    /**
     * 设置用户信息的敏感字段为空
     * @param user 用户信息
     */
    protected void setNullProperty(Users user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        user.setBirthday(null);
    }

    /**
     * 转换用户对象为用户 VO 对象，并保存用户会话到 redis 中
     * @param users 用户对象
     * @return 用户 VO 对象
     */
    protected UsersVO convertUsersVO(Users users) {
        // 实现用户的 redis 会话，保存会话到 redis
        String uniqueToken = IdUtil.simpleUUID();
        redisOperator.set(REDIS_USER_TOKEN + ":" + users.getId(), uniqueToken);

        UsersVO usersVO = new UsersVO();
        BeanUtils.copyProperties(users, usersVO);
        usersVO.setUserUniqueToken(uniqueToken);

        return usersVO;
    }

}
