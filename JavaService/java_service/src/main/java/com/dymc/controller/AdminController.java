package com.dymc.controller;

import com.dymc.pojo.Admin;
import com.dymc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fengqi/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("doQueryAdmin")
    public List<Admin> doQueryAdmin(Admin admin){
        return adminService.queryAdmin(admin);
    }
}
