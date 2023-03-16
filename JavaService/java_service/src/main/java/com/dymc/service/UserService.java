package com.dymc.service;
import com.dymc.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> findUser();
}
