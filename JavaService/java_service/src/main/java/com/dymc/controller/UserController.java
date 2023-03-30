package com.dymc.controller;

import com.dymc.pojo.User;
import com.dymc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fengqi/")
public class UserController {

    @Autowired
    private UserService userService;

//    查询用户的所有信息
    @RequestMapping("doFindUsers")
    public List<User> doFindUsers(){
        return userService.findUsers();
    }

//  查询用户名是否存在数据库(注册前)
    @RequestMapping("doQueryUser")
    public List<User> doQueryUser(User user){
        return userService.queryUser(user);
    }

//    新增用户(注册)
    @RequestMapping("doInsertUser")
    public int doInsertUser(User user){
        return userService.insertUser(user);
    }

//    查询用户是否存在(登录)
    @RequestMapping("doQueryUsers")
    public List<User> doQueryUsers(User user){
        return userService.queryUsers(user);
    }

//    用户充值
    @RequestMapping("doDepositUser")
    public int doDepositUser(User user){
        return userService.depositUser(user);
    }

//    用户使用充电套餐
    @RequestMapping("doChargeUser")
    public int doChargeUser(User user){
        return userService.chargeUser(user);
    }

//    删除用户
    @RequestMapping("doDeleteUser")
    public int doDeleteUser(User user){
        return userService.deleteUser(user);
    }
}
