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


    @Override
    public List<User> findUser() {
        // TODO Auto-generated method stub
//        long start = System.currentTimeMillis();
        List<User> list = userMapper.findUser();
//        long end = System.currentTimeMillis();
//        Log.info("query time{}:" + (end - start));
        return list;
    }

}
