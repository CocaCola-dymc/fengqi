package com.dymc.controller;

import com.dymc.pojo.User;
import com.dymc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("doFindUser")
    public List<User> doFindUser(){
        return userService.findUser();
    }
}
