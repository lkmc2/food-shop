package com.lin.service.center;

import com.lin.bo.center.OrderItemsCommentBO;
import com.lin.pojo.OrderItems;
import com.lin.utils.PagedGridResult;

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

    /**
     * 保存用户的评论列表
     * @param orderId 订单 id
     * @param userId 用户 id
     * @param commentList 评论列表
     */
    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);

    /**
     * 分页查询我的评论列表
     * @param userId 用户 id
     * @param page 当前页数
     * @param pageSize 每页显示的条数
     * @return 我的评论列表
     */
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);

}
