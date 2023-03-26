package com.dymc.mapper;

import com.dymc.pojo.Admin;

import java.util.List;

//@Mapper   //在主启动类里面添加
public interface AdminMapper {

//    查询管理员是否存在(登录)
//    @Select("select id from fq_admin where username=#{username} and password=#{password}")
    List<Admin> queryAdmin(Admin admin);
}
