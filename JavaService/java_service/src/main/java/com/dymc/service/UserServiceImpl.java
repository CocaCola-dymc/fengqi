package com.dymc.service;

import com.dymc.mapper.UserMapper;
import com.dymc.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger Log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    //    查询用户的所有信息
    @Override
    public List<User> findUsers() {
        List<User> list = userMapper.findUsers();
        return list;
    }

//  查询用户名是否存在数据库(注册前)
    @Override
    public List<User> queryUser(User user) {
        List<User> list = userMapper.queryUser(user);
        return list;
    }

    //    新增用户(注册)
    @Override
    public int insertUser(User user) {
        int rows = userMapper.insertUser(user);
        return rows;
    }

    //    查询用户是否存在(登录)
    @Override
    public List<User> queryUsers(User user) {
        List<User> list = userMapper.queryUsers(user);
        return list;
    }

    //    用户充值
    @Override
    public int depositUser(User user) {
        int rows = userMapper.depositUser(user);
        return rows;
    }

    //    用户使用充电套餐
    @Override
    public int chargeUser(User user) {
        int rows = userMapper.chargeUser(user);
        return rows;
    }

    //    删除用户
    @Override
    public int deleteUser(User user) {
        int rows = userMapper.deleteUser(user);
        return rows;
    }
}
