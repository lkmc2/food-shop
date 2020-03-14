package com.lin.service;

import com.lin.bo.AddressBO;
import com.lin.pojo.UserAddress;

import java.util.List;

/**
 * 地址服务
 * @author lkmc2
 * @date 2020/3/14 17:12
 */
public interface AddressService {

    /**
     * 根据用户 id 查询收货地址列表
     * @param userId 用户 id
     * @return 收货地址列表
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 用户新增地址
     * @param addressBO 地址信息
     */
    void addNewUserAddress(AddressBO addressBO);

}
