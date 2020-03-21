package com.lin.service.impl.center;

import com.lin.bo.center.OrderItemsCommentBO;
import com.lin.dao.ItemCommentsMapperCustom;
import com.lin.dao.OrderItemsMapper;
import com.lin.dao.OrderStatusMapper;
import com.lin.dao.OrdersMapper;
import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.OrderItems;
import com.lin.pojo.OrderStatus;
import com.lin.pojo.Orders;
import com.lin.service.center.MyCommentsService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 我的评论服务实现类（用户中心使用）
 * @author lkmc2
 * @date 2020/3/21 11:16
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MyCommentsServiceImpl implements MyCommentsService {

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private ItemCommentsMapperCustom itemCommentsMapperCustom;

    @Autowired
    private Sid sid;

    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(orderId);

        return orderItemsMapper.select(orderItems);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList) {
        // 1.保存评价到订单评价表 items_comments
        for (OrderItemsCommentBO commentBO : commentList) {
            commentBO.setCommentId(sid.nextShort());
        }

        // 保存评论列表
        itemCommentsMapperCustom.saveComments(userId, commentList);


        // 2.修改订单表的是否评论字段为已评论 orders
        Orders order = new Orders();
        order.setId(orderId);
        order.setIsComment(YesOrNoEnum.YES.type);
        ordersMapper.updateByPrimaryKeySelective(order);


        // 3.修改订单状态表的留言时间 order_status
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

}
