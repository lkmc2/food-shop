package com.lin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lin.bo.SubmitOrderBO;
import com.lin.dao.OrderItemsMapper;
import com.lin.dao.OrderStatusMapper;
import com.lin.dao.OrdersMapper;
import com.lin.enums.OrderStatusEnum;
import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.*;
import com.lin.service.AddressService;
import com.lin.service.ItemService;
import com.lin.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 订单服务实现类
 * @author lkmc2
 * @date 2020/3/14 20:40
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Sid sid;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ItemService itemService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public String createOrder(SubmitOrderBO submitOrderBO) {
        // 包邮费用设置为0
        int postAmount = 0;

        // 订单 id
        String orderId = sid.nextShort();

        // 1.新订单数据保存
        Orders newOrder = setOrderInfo(orderId, submitOrderBO, postAmount);

        // 2.循环根据 itemSpecIds 保存订单商品信息表
        String itemSpecIds = submitOrderBO.getItemSpecIds();

        String[] itemSpecIdArr = StrUtil.split(itemSpecIds, ",");

        // 商品原价累计
        int totalAmount = 0;

        // 优惠后的实际支付价格累计
        int realPayAmount = 0;

        for (String itemSpecId : itemSpecIdArr) {
            // todo：整合 redis 后，商品购买数量重新从 redis 的购物车中获取
            int buyCounts = 1;

            // 2.1 根据规格 id ，查询规格的具体信息，主要获取价格
            ItemsSpec itemsSpec = itemService.queryItemSpecById(itemSpecId);

            totalAmount += itemsSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemsSpec.getPriceDiscount() * buyCounts;

            // 2.2 循环保存子订单数据到数据库
            saveSubOrder(orderId, itemSpecId, buyCounts, itemsSpec);

            // 2.3 在用户提交订单以后，规格表中需要扣除库存
            itemService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        // 费用信息
        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);

        ordersMapper.insert(newOrder);

        // 3.保存订单状态表，设置状态为待付款
        saveOrderStatus(orderId);

        return orderId;
    }

    /**
     * 设置订单信息
     * @param submitOrderBO 订单信息BO
     * @param postAmount 包邮费用
     * @param orderId 订单 id
     * @return 订单信息
     */
    private Orders setOrderInfo(String orderId, SubmitOrderBO submitOrderBO, int postAmount) {
        // 用户地址信息
        UserAddress address = addressService.queryUserAddress(submitOrderBO.getUserId(), submitOrderBO.getAddressId());

        // 设置新订单信息
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(submitOrderBO.getUserId());

        // 收件人信息
        newOrder.setReceiverName(address.getReceiver());
        newOrder.setReceiverMobile(address.getMobile());
        newOrder.setReceiverAddress(StrUtil.format("{} {} {} {}",
                address.getProvince(),
                address.getCity(),
                address.getDistrict(),
                address.getDetail()));

        // 邮费
        newOrder.setPostAmount(postAmount);

        newOrder.setPayMethod(submitOrderBO.getPayMethod());
        newOrder.setLeftMsg(submitOrderBO.getLeftMsg());

        newOrder.setIsComment(YesOrNoEnum.NO.type);
        newOrder.setIsDelete(YesOrNoEnum.NO.type);
        newOrder.setCreateTime(new Date());
        newOrder.setUpdateTime(new Date());

        return newOrder;
    }

    /**
     * 保存子订单数据到数据库
     * @param orderId 订单 id
     * @param itemSpecId 商品规格 id
     * @param buyCounts 购买数量
     * @param itemsSpec 商品规格信息
     */
    private void saveSubOrder(String orderId, String itemSpecId, int buyCounts, ItemsSpec itemsSpec) {
        String itemId = itemsSpec.getItemId();

        // 根据规格 id ，获取商品信息以及商品图片
        Items item = itemService.queryItemById(itemId);
        String imgUrl = itemService.queryItemMainImgById(itemId);

        String subOrderId = sid.nextShort();

        // 保存子订单数据到数据库
        OrderItems subOrderItem = new OrderItems();
        subOrderItem.setId(subOrderId);
        subOrderItem.setOrderId(orderId);
        subOrderItem.setItemId(itemId);
        subOrderItem.setItemName(item.getItemName());
        subOrderItem.setItemImg(imgUrl);
        subOrderItem.setBuyCounts(buyCounts);
        subOrderItem.setItemSpecId(itemSpecId);
        subOrderItem.setItemSpecName(itemsSpec.getName());
        subOrderItem.setPrice(itemsSpec.getPriceDiscount());

        orderItemsMapper.insert(subOrderItem);
    }

    /**
     * 设置订单订单状态为待付款
     * @param orderId 订单 id
     */
    private void saveOrderStatus(String orderId) {
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreateTime(new Date());

        orderStatusMapper.insert(waitPayOrderStatus);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = new OrderStatus();
        paidStatus.setOrderId(orderId);
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setPayTime(new Date());

        orderStatusMapper.updateByPrimaryKeySelective(paidStatus);
    }

}
