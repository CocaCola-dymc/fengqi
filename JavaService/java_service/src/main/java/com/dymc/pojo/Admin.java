package com.dymc.pojo;

import lombok.Data;

import java.util.Date;

@Data
//@Accessors(chain = true)
public class Admin {
    private Long id;
    private String username;
    private String password;
}
