package com.lin.dao;

import com.lin.bo.center.OrderItemsCommentBO;
import com.lin.vo.MyCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品评价表 Dao（自定义）
 *
 * @author lkmc2
 * @date 2020/3/21 20:57
 */
public interface ItemCommentsMapperCustom {

    /**
     * 保存评论列表
     * @param userId      用户 id
     * @param commentList 评论列表
     */
    void saveComments(@Param("userId") String userId, @Param("commentList") List<OrderItemsCommentBO> commentList);

    /**
     * 查询我的评论列表
     * @param paramMap 参数 Map
     * @return 评论列表
     */
    List<MyCommentVO> queryMyComments(Map<String, Object> paramMap);

}