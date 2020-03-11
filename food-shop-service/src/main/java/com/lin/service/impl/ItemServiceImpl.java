package com.lin.service.impl;

import com.lin.dao.*;
import com.lin.enums.CommentLevelEnum;
import com.lin.pojo.*;
import com.lin.service.ItemService;
import com.lin.vo.CommentLevelCountsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 商品服务实现类
 * @author lkmc2
 * @date 2020/3/10 22:47
 */
@Service
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

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Items queryItemById(String id) {
        return itemsMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);

        // 查询条件构建器
        Example.Criteria criteria = example.createCriteria();
        // where item_no = '传入的商品id'
        criteria.andEqualTo("itemId", itemId);

        return itemsImgMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);

        // 查询条件构建器
        Example.Criteria criteria = example.createCriteria();
        // where item_no = '传入的商品id'
        criteria.andEqualTo("itemId", itemId);

        return itemsSpecMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);

        // 查询条件构建器
        Example.Criteria criteria = example.createCriteria();
        // where item_no = '传入的商品id'
        criteria.andEqualTo("itemId", itemId);

        return itemsParamMapper.selectOneByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
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

}
