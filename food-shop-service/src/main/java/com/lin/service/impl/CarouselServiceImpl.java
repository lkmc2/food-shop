package com.lin.service.impl;

import com.lin.dao.CarouselMapper;
import com.lin.pojo.Carousel;
import com.lin.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 轮播图服务实现类
 * @author lkmc2
 * @date 2020/3/7 21:54
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;


    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        // order by sort
        example.orderBy("sort").desc();

        // 查询条件构建器
        Example.Criteria criteria = example.createCriteria();
        // where is_show = '传入的展示状态'
        criteria.andEqualTo("isShow", isShow);

        List<Carousel> result = carouselMapper.selectByExample(example);

        return null;
    }

}
