package com.lin.service;

import com.lin.bo.ShopCartBO;
import com.lin.bo.SubmitOrderBO;
import com.lin.pojo.OrderStatus;
import com.lin.vo.OrderVO;

import java.util.List;

/**
 * 订单服务
 * @author lkmc2
 * @date 2020/3/14 20:40
 */
public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param shopCartList 购物车列表
     * @param submitOrderBO 订单信息
     * @return 订单VO
     */
    OrderVO createOrder(List<ShopCartBO> shopCartList, SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId 订单 id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     * @param orderId 订单 id
     * @return 订单状态
     */
    OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时未支付订单
     */
    void closeOrder();

}
