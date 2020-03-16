package com.lin.service.center;

import com.lin.utils.PagedGridResult;

/**
 * 我的订单服务（用户中心使用）
 * @author lkmc2
 * @date 2020/3/16 21:46
 */
public interface MyOrdersService {

    /**
     * 分页查询我的订单列表
     * @param userId 用户 id
     * @param orderStatus 订单状态带
     * @param page 当前页数
     * @param pageSize 每页显示条数
     * @return 我的订单列表
     */
    PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);

    /**
     * 将等待发货的订单的订单状态变更为商家发货
     * @param orderId 订单 id
     */
    void updateDeliverOrderStatus(String orderId);

}
