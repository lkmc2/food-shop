package com.lin.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.lin.dao.OrderStatusMapper;
import com.lin.dao.OrdersMapper;
import com.lin.dao.OrdersMapperCustom;
import com.lin.enums.OrderStatusEnum;
import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.OrderStatus;
import com.lin.pojo.Orders;
import com.lin.service.center.MyOrdersService;
import com.lin.utils.PagedGridResult;
import com.lin.vo.MyOrderVO;
import com.lin.vo.OrderStatusCountsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 我的订单实现类（用户中心使用）
 * @author lkmc2
 * @date 2020/3/16 21:46
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MyOrdersServiceImpl extends BaseService implements MyOrdersService {

    @Autowired
    private OrdersMapperCustom ordersMapperCustom;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("userId", userId);

        if (orderStatus != null) {
            paramMap.put("orderStatus", orderStatus);
        }

        PageHelper.startPage(page, pageSize);

        List<MyOrderVO> list = ordersMapperCustom.queryMyOrders(paramMap);

        return setterPagedGrid(list, page);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateDeliverOrderStatus(String orderId) {
        OrderStatus updateStatus = new OrderStatus();
        // 修改状态为商家发货
        updateStatus.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        updateStatus.setDeliverTime(new Date());

        Example example = new Example(OrderStatus.class);
        Example.Criteria criteria = example.createCriteria();
        // where order_id = '订单号'
        criteria.andEqualTo("orderId", orderId);
        // where order_status = 30
        criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);

        orderStatusMapper.updateByExampleSelective(updateStatus, example);
    }

    @Override
    public Orders queryMyOrder(String userId, String orderId) {
        Orders order = new Orders();
        order.setUserId(userId);
        order.setId(orderId);
        // 状态为未删除的订单
        order.setIsDelete(YesOrNoEnum.NO.type);

        return ordersMapper.selectOne(order);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean updateReceiveOrderStatus(String orderId) {
        OrderStatus updateOrder = new OrderStatus();
        updateOrder.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        updateOrder.setSuccessTime(new Date());

        Example example = new Example(OrderStatus.class);
        Example.Criteria criteria = example.createCriteria();
        // where order_id = '订单号'
        criteria.andEqualTo("orderId", orderId);
        // where order_status = '待收货'
        criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);

        int effectCount = orderStatusMapper.updateByExampleSelective(updateOrder, example);

        return effectCount == 1;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteOrder(String userId, String orderId) {
        Orders updateOrder = new Orders();
        updateOrder.setIsDelete(YesOrNoEnum.YES.type);
        updateOrder.setUpdateTime(new Date());

        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        // where id = '订单号'
        criteria.andEqualTo("id", orderId);
        // where user_id = '订单id'
        criteria.andEqualTo("userId", userId);

        int effectCount = ordersMapper.updateByExampleSelective(updateOrder, example);

        return effectCount == 1;
    }

    @Override
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("userId", userId);
        paramMap.put("orderStatus", OrderStatusEnum.WAIT_PAY.type);

        // 等待支付的订单数
        int waitPayCounts = ordersMapperCustom.getMyOrderStatusCounts(paramMap);

        paramMap.put("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);

        // 等待发货的订单数
        int waitDeliverCounts = ordersMapperCustom.getMyOrderStatusCounts(paramMap);

        paramMap.put("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);

        // 等待收货的订单数
        int waitReceiveCounts = ordersMapperCustom.getMyOrderStatusCounts(paramMap);

        paramMap.put("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);
        paramMap.put("isComment", YesOrNoEnum.NO.type);

        // 等待评论的订单数
        int waitCommentCounts = ordersMapperCustom.getMyOrderStatusCounts(paramMap);

        OrderStatusCountsVO countsVO = new OrderStatusCountsVO();
        countsVO.setWaitPayCounts(waitPayCounts);
        countsVO.setWaitDeliverCounts(waitDeliverCounts);
        countsVO.setWaitReceiveCounts(waitReceiveCounts);
        countsVO.setWaitCommentCounts(waitCommentCounts);

        return countsVO;
    }

    @Override
    public PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<OrderStatus> list = ordersMapperCustom.getMyOrderTrend(ImmutableMap.of("userId", userId));

        return setterPagedGrid(list, page);
    }

}
