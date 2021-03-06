package com.lin.service;

import com.lin.bo.UserBO;
import com.lin.pojo.Users;

/**
 * 用户服务
 * @author lkmc2
 * @date 2020/3/7 21:53
 */
public interface UserService {

    /**
     * 查询用户名是否存在
     * @param username 用户名
     * @return 用户名是否存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBO 用户信息
     * @return 新用户
     */
    Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登陆
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    Users queryUserForLogin(String username, String password);

}
