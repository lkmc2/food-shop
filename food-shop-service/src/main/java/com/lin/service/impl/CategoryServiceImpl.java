package com.lin.service.impl;

import com.google.common.collect.ImmutableMap;
import com.lin.dao.CategoryMapper;
import com.lin.dao.CategoryMapperCustom;
import com.lin.enums.CategoryTypeEnum;
import com.lin.pojo.Category;
import com.lin.service.CategoryService;
import com.lin.vo.CategoryVO;
import com.lin.vo.NewItemsVO;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);

        // 查询条件构建器
        Example.Criteria criteria = example.createCriteria();
        // where type = '传入的类型'
        criteria.andEqualTo("type", CategoryTypeEnum.ROOT.type);

        return categoryMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<NewItemsVO> getSixNewItemLazy(Integer rootCatId) {
        return categoryMapperCustom.getSixNewItemLazy(ImmutableMap.of("rootCatId", rootCatId));
    }

}
