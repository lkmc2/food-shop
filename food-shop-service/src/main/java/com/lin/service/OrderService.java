package com.lin.service;

import com.lin.bo.SubmitOrderBO;

/**
 * 订单服务
 * @author lkmc2
 * @date 2020/3/14 20:40
 */
public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param submitOrderBO 订单信息
     * @return 订单 id
     */
    String createOrder(SubmitOrderBO submitOrderBO);

}
