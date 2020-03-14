package com.lin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.lin.bo.AddressBO;
import com.lin.dao.UserAddressMapper;
import com.lin.pojo.UserAddress;
import com.lin.service.AddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 地址服务实现类
 * @author lkmc2
 * @date 2020/3/14 17:12
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class AddressServiceImpl implements AddressService {

    @Autowired
    private Sid sid;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> queryAll(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);

        return userAddressMapper.select(userAddress);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void addNewUserAddress(AddressBO addressBO) {
        // 是否默认地址，0：否，1：是
        int isDefault = 0;

        // 1.判断当前用户是否存在地址，如果没有，则新增为‘默认地址’
        List<UserAddress> addressList = this.queryAll(addressBO.getUserId());

        if (CollUtil.isEmpty(addressList)) {
            isDefault = 1;
        }

        String addressId = sid.nextShort();

        // 2.保存地址到数据库
        UserAddress newAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, newAddress);

        newAddress.setId(addressId);
        newAddress.setIsDefault(isDefault);
        newAddress.setCreateTime(new Date());
        newAddress.setUpdateTime(new Date());

        userAddressMapper.insert(newAddress);
    }

}
