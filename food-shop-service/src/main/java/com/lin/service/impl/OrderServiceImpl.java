package com.lin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lin.bo.SubmitOrderBO;
import com.lin.dao.OrdersMapper;
import com.lin.enums.YesOrNoEnum;
import com.lin.pojo.Orders;
import com.lin.pojo.UserAddress;
import com.lin.service.AddressService;
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
    private AddressService addressService;

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

        // 费用信息
//        newOrder.setTotalAmount();
//        newOrder.setRealPayAmount();
        // 邮费
        newOrder.setPostAmount(0);

        newOrder.setPayMethod(payMethod);
        newOrder.setLeftMsg(leftMsg);

        newOrder.setIsComment(YesOrNoEnum.NO.type);
        newOrder.setIsDelete(YesOrNoEnum.NO.type);
        newOrder.setCreateTime(new Date());
        newOrder.setUpdateTime(new Date());

        // 2.循环根据 itemSpecIds 保存订单商品信息表
        // 3.保存订单状态表
    }

}
