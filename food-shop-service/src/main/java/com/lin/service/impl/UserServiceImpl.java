package com.lin.service.impl;

import com.lin.dao.UsersMapper;
import com.lin.pojo.Users;
import com.lin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户服务实现类
 * @author lkmc2
 * @date 2020/3/7 21:54
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

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

}
