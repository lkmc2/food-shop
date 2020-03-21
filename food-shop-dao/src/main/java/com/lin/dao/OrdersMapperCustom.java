package com.lin.dao;

import com.lin.vo.MyOrderVO;

import java.util.List;
import java.util.Map;

/**
 * 订单 Dao（自定义）
 *
 * @author lkmc2
 * @date 2020/3/16 21:28
 */
public interface OrdersMapperCustom {

    /**
     * 查询我的订单
     *
     * @param paramMap 参数 Map
     * @return 我的订单列表
     */
    List<MyOrderVO> queryMyOrders(Map<String, Object> paramMap);

    /**
     * 根据订单状态获取用户订单数
     * @param paramMap 参数 Map
     * @return 订单数
     */
    int getMyOrderStatusCounts(Map<String, Object> paramMap);

}