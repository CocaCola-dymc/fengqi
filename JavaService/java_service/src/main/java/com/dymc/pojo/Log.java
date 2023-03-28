package com.dymc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data

public class Log {
    private Long id;
    private String username;
    private String combo;
    //@DateTimeFormat 作用于set方法表示为属性赋值，基于什么日期格式执行
    //@JsonFormat 作用于对象get方法，表示获取值时的一种格式
//    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @JsonFormat(pattern = "MM-dd  HH:mm",timezone = "GMT+8")
    private Date createdTime;
}
