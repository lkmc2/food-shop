package com.lin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.lin.dao.*;
import com.lin.enums.CommentLevelEnum;
import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.*;
import com.lin.service.ItemService;
import com.lin.utils.DesensitizationUtil;
import com.lin.utils.PagedGridResult;
import com.lin.vo.CommentLevelCountsVO;
import com.lin.vo.ItemCommentVO;
import com.lin.vo.SearchItemsVO;
import com.lin.vo.ShopCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 商品服务实现类
 * @author lkmc2
 * @date 2020/3/10 22:47
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemCommentsMapper itemCommentsMapper;

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Override
    public Items queryItemById(String id) {
        return itemsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);

        // 查询条件构建器
        Example.Criteria criteria = example.createCriteria();
        // where item_no = '传入的商品id'
        criteria.andEqualTo("itemId", itemId);

        return itemsImgMapper.selectByExample(example);
    }

    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);

        // 查询条件构建器
        Example.Criteria criteria = example.createCriteria();
        // where item_no = '传入的商品id'
        criteria.andEqualTo("itemId", itemId);

        return itemsSpecMapper.selectByExample(example);
    }

    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);

        // 查询条件构建器
        Example.Criteria criteria = example.createCriteria();
        // where item_no = '传入的商品id'
        criteria.andEqualTo("itemId", itemId);

        return itemsParamMapper.selectOneByExample(example);
    }

    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        // 好评数
        Integer goodCounts = getCommentCounts(itemId, CommentLevelEnum.GOOD.type);
        // 中评数
        Integer normalCounts = getCommentCounts(itemId, CommentLevelEnum.NORMAL.type);
        // 差评数
        Integer badCounts = getCommentCounts(itemId, CommentLevelEnum.BAD.type);

        // 总评价数
        Integer totalCounts = goodCounts + normalCounts + badCounts;

        CommentLevelCountsVO commentLevelCountsVO = new CommentLevelCountsVO();
        commentLevelCountsVO.setGoodCounts(goodCounts);
        commentLevelCountsVO.setNormalCounts(normalCounts);
        commentLevelCountsVO.setBadCounts(badCounts);
        commentLevelCountsVO.setTotalCounts(totalCounts);

        return commentLevelCountsVO;
    }

    /**
     * 查询指定级别的商品评论条数
     * @param itemId 商品 id
     * @param level 评论级别，1：好评，2：中评，3：差评
     * @return 指定级别的商品评论条数
     */
    private Integer getCommentCounts(String itemId, Integer level) {
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);

        if (level != null) {
            condition.setCommentLevel(level);
        }

        return itemCommentsMapper.selectCount(condition);
    }

    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("itemId", itemId);
        paramMap.put("level", level);

        List<ItemCommentVO> list = itemsMapperCustom.queryItemComments(paramMap);

        for (ItemCommentVO vo : list) {
            // 对用户昵称进行脱敏加密
            vo.setNickname(DesensitizationUtil.commonDisplay(vo.getNickname()));
        }

        return setPagedGrid(list, page);
    }

    /**
     * 设置分页结果信息
     * @param list 查询结果列列表
     * @param page 当前页
     * @return 分页结果信息
     */
    private PagedGridResult setPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);

        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());

        return grid;
    }

    @Override
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("keywords", keywords);
        paramMap.put("sort", sort);

        PageHelper.startPage(page, pageSize);

        List<SearchItemsVO> list = itemsMapperCustom.searchItems(paramMap);

        return setPagedGrid(list, page);
    }

    @Override
    public PagedGridResult searchItemsByCatId(String catId, String sort, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("catId", catId);
        paramMap.put("sort", sort);

        PageHelper.startPage(page, pageSize);

        List<SearchItemsVO> list = itemsMapperCustom.searchItemsByCat(paramMap);

        return setPagedGrid(list, page);
    }

    @Override
    public List<ShopCartVO> queryItemsBySpecIds(String specIds) {
        List<String> specIdsList = StrUtil.split(specIds, ',');

        return itemsMapperCustom.queryItemsBySpecIds(specIdsList);
    }

    @Override
    public ItemsSpec queryItemSpecById(String specId) {
        return itemsSpecMapper.selectByPrimaryKey(specId);
    }

    @Override
    public String queryItemMainImgById(String itemId) {
        ItemsImg itemsImg = new ItemsImg();
        itemsImg.setItemId(itemId);
        itemsImg.setIsMain(YesOrNoEnum.YES.type);

        ItemsImg result = itemsImgMapper.selectOne(itemsImg);

        return result != null ? result.getUrl() : "";
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void decreaseItemSpecStock(String specId, int buyCounts) {
        int effectCount = itemsMapperCustom.decreaseItemSpecStock(specId, buyCounts);

        if (effectCount != 1) {
            throw new RuntimeException("订单创建失败，原因：库存不足！");
        }
    }

}
