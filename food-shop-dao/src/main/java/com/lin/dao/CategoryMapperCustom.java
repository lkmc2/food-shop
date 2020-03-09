package com.lin.dao;

import com.lin.vo.CategoryVO;
import com.lin.vo.NewItemsVO;

import java.util.List;
import java.util.Map;

/**
 * 商品分类 Dao（自定义）
 * @author lkmc2
 * @date 2020/3/7 20:57
 */
public interface CategoryMapperCustom {

    /**
     * 通过一级分类 id 查询二级和三级分类信息
     * @param rootCatId 一级分类 id
     * @return 二级和三级分类信息列表
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的 6 条最新商品数据
     * @param paramMap 参数 Map
     * @return 一级分类下的 6 条最新商品数据列表
     */
    List<NewItemsVO> getSixNewItemLazy(Map<String, Object> paramMap);

}