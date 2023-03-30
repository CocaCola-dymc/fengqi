package com.dymc.mapper;

import com.dymc.pojo.User;
import com.dymc.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper   //在主启动类里面添加
public interface UserMapper {

//    查询用户所有信息
//    @Select("select id,username,password,phone,createdTime from fq_user")
    List<User> findUsers();

//     查询用户名是否存在数据库(注册前)
//    @Select("select id,username,password,phone,createdTime from fq_user")
    List<User> queryUser(User user);

//    新增用户(注册)
//    @Insert("insert into fq_user(id,username,password,phone,createdTime) values (null,#{username},#{password},#{phone},now())")
    int insertUser(User user);

//    查询用户是否存在(登录)
//    @Select("select id from fq_user where username=#{username} and password=#{password}")
    List<User> queryUsers(User user);

//    用户充值
//    @Update("update fq_user set money = money + #{money} where id=#{id}")
    int depositUser(User user);

//    用户使用充电套餐
//    @Update("update fq_user set money = money - #{money} where id=#{id}")
    int chargeUser(User user);

//    删除用户
//    @Delete("delete from fq_user where username=#{username}")
    int deleteUser(User user);
}
