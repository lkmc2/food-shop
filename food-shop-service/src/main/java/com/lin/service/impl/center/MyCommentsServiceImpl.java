package com.lin.service.impl.center;

import com.lin.dao.OrderItemsMapper;
import com.lin.pojo.OrderItems;
import com.lin.service.center.MyCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderId(orderId);

        return orderItemsMapper.select(orderItems);
    }

}
