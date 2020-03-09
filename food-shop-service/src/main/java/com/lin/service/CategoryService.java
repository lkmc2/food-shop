package com.lin.service;

import com.lin.pojo.Category;

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

}
