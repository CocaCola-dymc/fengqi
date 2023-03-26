package com.dymc.service;

import com.dymc.mapper.AdminMapper;
import com.dymc.pojo.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private static final Logger Log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;


    @Override
    public List<Admin> queryAdmin(Admin admin) {
        List<Admin> list = adminMapper.queryAdmin(admin);
        return list;
    }
}
