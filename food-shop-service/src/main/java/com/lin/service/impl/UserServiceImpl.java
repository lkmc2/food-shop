package com.lin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.lin.bo.UserBO;
import com.lin.dao.UsersMapper;
import com.lin.enums.SexEnum;
import com.lin.pojo.Users;
import com.lin.service.UserService;
import com.lin.utils.Md5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * 用户服务实现类
 * @author lkmc2
 * @date 2020/3/7 21:54
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    /** 用户头像地址 **/
    private static final String USER_FACE_URL = "https://c-ssl.duitang.com/uploads/item/201704/10/20170410095843_SEvMy.jpeg";

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(Users.class);

        // 查询条件构建器
        Example.Criteria criteria = userExample.createCriteria();
        // where username = '传入的用户名'
        criteria.andEqualTo("username", username);

        Users result = usersMapper.selectOneByExample(userExample);

        return result != null;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Users createUser(UserBO userBO) {
        String userId = sid.nextShort();

        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        user.setPassword(Md5Utils.getMd5Str(userBO.getPassword()));

        // 默认用户昵称和用户名一致
        user.setNickname(userBO.getUsername());
        // 默认头像
        user.setFace(USER_FACE_URL);
        // 默认生日
        user.setBirthday(DateUtil.parse("1970-01-01"));
        // 默认性别为保密
        user.setSex(SexEnum.SECRET.type);

        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        // 保存用户到数据库
        usersMapper.insert(user);

        return user;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);

        // 查询条件构建器
        Example.Criteria criteria = userExample.createCriteria();
        // where username = '传入的用户名'
        criteria.andEqualTo("username", username);
        // and password = '传入的密码'
        criteria.andEqualTo("password", password);

        return usersMapper.selectOneByExample(userExample);
    }

}
