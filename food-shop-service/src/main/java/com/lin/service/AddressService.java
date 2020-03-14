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

    /**
     * 用户修改地址
     * @param addressBO 用户修改地址
     */
    void updateUserAddress(AddressBO addressBO);

    /**
     * 根据用户 id 和 地址 id，删除对应的用户地址
     * @param userId 用户 id
     * @param addressId 地址 id
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 修改默认地址
     * @param userId 用户 id
     * @param addressId 地址 id
     */
    void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 根据用户 id 和 地址 id，查询具体的用户地址对象信息
     * @param userId 用户 id
     * @param addressId 地址 id
     */
    UserAddress queryUserAddress(String userId, String addressId);

}
