package com.lin.service;

import com.lin.bo.SubmitOrderBO;
import com.lin.vo.OrderVO;

/**
 * 订单服务
 * @author lkmc2
 * @date 2020/3/14 20:40
 */
public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param submitOrderBO 订单信息
     * @return 订单VO
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId 订单 id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

}
