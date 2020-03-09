package com.lin.service;

import com.lin.pojo.Category;
import com.lin.vo.CategoryVO;

import java.util.List;

/**
 * 分类服务
 * @author lkmc2
 * @date 2020/3/9 21:00
 */
public interface CategoryService {

    /**
     * 查询所有一级分类
     * @return 一级分类列表
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类 id 查询二级和三级分类列表
     * @param rootCatId 一级分类 id
     * @return 二级和三级分类列表
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

}
