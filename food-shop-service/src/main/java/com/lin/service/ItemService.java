package com.lin.service;

import com.lin.pojo.Items;
import com.lin.pojo.ItemsImg;
import com.lin.pojo.ItemsParam;
import com.lin.pojo.ItemsSpec;
import com.lin.utils.PagedGridResult;
import com.lin.vo.CommentLevelCountsVO;

import java.util.List;

/**
 * 商品服务
 * @author lkmc2
 * @date 2020/3/10 22:47
 */
public interface ItemService {

    /**
     * 根据商品 id 查询商品详情
     * @param id 商品 id
     * @return 商品详情
     */
    Items queryItemById(String id);

    /**
     * 根据商品 id 查询商品图片列表
     * @param itemId 商品 id
     * @return 商品图片列表
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品 id 查询商品规格列表
     * @param itemId 商品 id
     * @return 商品规格列表
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品 id 查询商品参数
     * @param itemId 商品 id
     * @return 商品参数
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品 id 查询商品的评价等级数量
     * @param itemId 商品 id
     * @return 商品的评价等级数量
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 分页查询商品评论列表
     * @param itemId 商品 id
     * @param level 评价等级
     * @param page 页数
     * @param pageSize 每页条数
     * @return 商品评论列表（分页结果）
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

}
