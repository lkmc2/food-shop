package com.lin.service.center;

import com.lin.bo.center.CenterUserBO;
import com.lin.pojo.Users;

/**
 * 用户服务（用户中心使用）
 * @author lkmc2
 * @date 2020/3/15 20:56
 */
public interface CenterUserService {

    /**
     * 根据用户 id 查询用户信息
     * @param userId 用户 id
     * @return 用户信息
     */
    Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     * @param userId 用户 id
     * @param centerUserBO 用户信息
     * @return 用户信息
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);

}
