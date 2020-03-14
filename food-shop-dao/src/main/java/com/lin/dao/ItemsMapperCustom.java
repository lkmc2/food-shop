package com.lin.dao;

import com.lin.vo.ItemCommentVO;
import com.lin.vo.SearchItemsVO;

import java.util.List;
import java.util.Map;

/**
 * 商品表 Dao
 *
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
public interface ItemsMapperCustom {

    /**
     * 查询商品评论
     *
     * @param paramMap 参数 Map
     * @return 商品评论列表
     */
    List<ItemCommentVO> queryItemComments(Map<String, Object> paramMap);


    /**
     * 通过关键字查询商品搜索结果
     *
     * @param paramMap 参数 Map
     * @return 商品搜索结果列表
     */
    List<SearchItemsVO> searchItems(Map<String, Object> paramMap);

    /**
     * 查询分类 id 商品搜索结果
     *
     * @param paramMap 参数 Map
     * @return 商品搜索结果列表
     */
    List<SearchItemsVO> searchItemsByCat(Map<String, Object> paramMap);
}