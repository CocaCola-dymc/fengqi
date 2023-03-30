package com.dymc.mapper;

import com.dymc.pojo.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogMapper {
//    插入用户充电记录
//    @Insert("insert into fq_log (id,username,combo,createdTime) values (null,#{username},#{combo},now())")
    int insertLog(Log log);

//    用户查询自己的充电记录
//    @Select("select username,combo,createdTime from fq_log where username=#{username}")
    List<Log> queryLog(Log log);

//    @Select("select username,combo,createdTime from fq_log")
    List<Log> queryLogs(Log log);
}
