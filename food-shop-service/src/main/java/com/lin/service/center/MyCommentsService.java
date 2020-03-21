package com.lin.service.center;

import com.lin.pojo.OrderItems;

import java.util.List;

/**
 * 我的评论服务（用户中心使用）
 * @author lkmc2
 * @date 2020/3/21 11:16
 */
public interface MyCommentsService {

    /**
     * 根据订单 id 查询关联的商品
     * @param orderId 订单 id
     * @return 订单商品关联列表
     */
    List<OrderItems> queryPendingComment(String orderId);

}
