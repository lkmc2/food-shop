package com.lin.service;

import com.lin.pojo.Items;
import com.lin.pojo.ItemsImg;
import com.lin.pojo.ItemsParam;
import com.lin.pojo.ItemsSpec;

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

}
