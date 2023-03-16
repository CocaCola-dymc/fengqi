package com.dymc.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
//@Accessors(chain = true)
public class User {
    private Long id;
    private String username;
    private String password;
    private Date createdTime;
}
