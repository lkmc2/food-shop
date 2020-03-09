package com.lin.service;

import com.lin.pojo.Carousel;

import java.util.List;

/**
 * 轮播图服务
 * @author lkmc2
 * @date 2020/3/9 21:00
 */
public interface CarouselService {

    /**
     * 查询所有轮播图列表
     * @param isShow 是否展示轮播图
     * @return 轮播图列表
     */
    List<Carousel> queryAll(Integer isShow);

}
