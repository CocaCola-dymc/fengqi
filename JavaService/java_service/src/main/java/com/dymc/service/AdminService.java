package com.dymc.service;
import com.dymc.pojo.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> queryAdmin(Admin admin);
}
