package com.lin.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.lin.dao.OrderStatusMapper;
import com.lin.dao.OrdersMapperCustom;
import com.lin.enums.OrderStatusEnum;
import com.lin.pojo.OrderStatus;
import com.lin.service.center.MyOrdersService;
import com.lin.utils.PagedGridResult;
import com.lin.vo.MyOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 我的订单实现类（用户中心使用）
 * @author lkmc2
 * @date 2020/3/16 21:46
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MyOrdersServiceImpl implements MyOrdersService {

    @Autowired
    private OrdersMapperCustom ordersMapperCustom;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("userId", userId);

        if (orderStatus != null) {
            paramMap.put("orderStatus", orderStatus);
        }

        PageHelper.startPage(page, pageSize);

        List<MyOrderVO> list = ordersMapperCustom.queryMyOrders(paramMap);

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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateDeliverOrderStatus(String orderId) {
        OrderStatus updateStatus = new OrderStatus();
        // 修改状态为商家发货
        updateStatus.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        updateStatus.setDeliverTime(new Date());

        Example example = new Example(OrderStatus.class);
        Example.Criteria criteria = example.createCriteria();
        // where order_id = '订单号'
        criteria.andEqualTo("orderId", orderId);
        // where order_status = 30
        criteria.andEqualTo("orderStatus", OrderStatusEnum.WAIT_DELIVER.type);

        orderStatusMapper.updateByExampleSelective(updateStatus, example);
    }

}
