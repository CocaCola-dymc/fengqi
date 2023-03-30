package com.dymc.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
//@Accessors(chain = true)
public class User {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private Double money;
    @JsonFormat(pattern = "MM-dd",timezone = "GMT+8")
    private Date createdTime;
}
