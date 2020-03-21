package com.lin.service.center;

import com.lin.pojo.Orders;
import com.lin.utils.PagedGridResult;
import com.lin.vo.OrderStatusCountsVO;

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

    /**
     * 查询我的订单
     * @param userId 用户 id
     * @param orderId 订单 id
     * @return 订单信息
     */
    Orders queryMyOrder(String userId, String orderId);

    /**
     * 更新订单状态 -> 确认收货
     * @param orderId 订单 id
     * @return 是否更新成功
     */
    boolean updateReceiveOrderStatus(String orderId);

    /**
     * 删除订单（逻辑删除）
     * @param userId 用户 id
     * @param orderId 订单 id
     * @return 是否删除成功
     */
    boolean deleteOrder(String userId, String orderId);

    /**
     * 查询用户订单数
     * @param userId 用户 id
     */
    OrderStatusCountsVO getOrderStatusCounts(String userId);

}
