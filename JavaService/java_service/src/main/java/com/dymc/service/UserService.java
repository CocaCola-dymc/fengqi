package com.dymc.service;
import com.dymc.pojo.Admin;
import com.dymc.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> findUsers();
    List<User> queryUser(User user);
    int insertUser(User user);
    List<User> queryUsers(User user);
    int depositUser(User user);
    int chargeUser(User user);
    int deleteUser(User user);
}
