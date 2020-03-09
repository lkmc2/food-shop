package com.lin.dao;

import com.lin.pojo.Category;

import java.util.List;

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
    List<Category> getSubCatList(Integer rootCatId);

}