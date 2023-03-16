package com.dymc.mapper;

import com.dymc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

//@Mapper   //在主启动类里面添加
public interface UserMapper {

    @Select("select id,username,password,createdTime from fq_user")
    List<User> findUser();
}
