package com.lin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lin.bo.SubmitOrderBO;
import com.lin.dao.OrderItemsMapper;
import com.lin.dao.OrdersMapper;
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
    private AddressService addressService;

    @Autowired
    private ItemService itemService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();

        // 包邮费用设置为0
        int postAmount = 0;

        String orderId = sid.nextShort();

        UserAddress address = addressService.queryUserAddress(userId, addressId);

        // 1.新订单数据保存
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);

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

        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);

        newOrder.setIsComment(YesOrNoEnum.NO.type);
        newOrder.setIsDelete(YesOrNoEnum.NO.type);
        newOrder.setCreateTime(new Date());
        newOrder.setUpdateTime(new Date());

        // 2.循环根据 itemSpecIds 保存订单商品信息表
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

            // 2.2 根据规格 id ，获取商品信息以及商品图片
            String itemId = itemsSpec.getItemId();

            Items item = itemService.queryItemById(itemId);

            String imgUrl = itemService.queryItemMainImgById(itemId);

            // 2.3 循环保存子订单数据到数据库
            String subOrderId = sid.nextShort();

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

        // 费用信息
        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);

        ordersMapper.insert(newOrder);

        // 3.保存订单状态表
    }

}
