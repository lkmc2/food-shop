package com.lin.service;

import com.lin.pojo.Items;
import com.lin.pojo.ItemsImg;
import com.lin.pojo.ItemsParam;
import com.lin.pojo.ItemsSpec;
import com.lin.utils.PagedGridResult;
import com.lin.vo.CommentLevelCountsVO;
import com.lin.vo.ShopCartVO;

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

    /**
     * 根据关键词搜索商品列表
     * @param keywords 关键词
     * @param sort 排序方式，k：默认排序，根据 name，c：根据销量排序， p：根据价格排序
     * @param page 页数
     * @param pageSize 每页条数
     * @return 商品列表（分页结果）
     */
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 根据分类 id 搜索商品列表
     * @param catId 分类 id
     * @param sort 排序方式，k：默认排序，根据 name，c：根据销量排序， p：根据价格排序
     * @param page 页数
     * @param pageSize 每页条数
     * @return 商品列表（分页结果）
     */
    PagedGridResult searchItemsByCatId(String catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据规格 id 查询最新的购物车中的商品数据（用于刷新渲染购物车中的商品数据）
     * @param specIds 规格 id ，使用英文逗号拼接
     * @return 购物车列表
     */
    List<ShopCartVO> queryItemsBySpecIds(String specIds);

    /**
     * 根据商品规格 id 获取规格对应的具体信息
     * @param specId 规格 id
     * @return 规格具体信息
     */
    ItemsSpec queryItemSpecById(String specId);

    /**
     * 根据商品 id 获取商品图片主图的 url
     * @param itemId 商品 id
     * @return 规格具体信息
     */
    String queryItemMainImgById(String itemId);
}
