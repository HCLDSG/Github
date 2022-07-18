package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Role;

import java.util.List;

public interface UserRoleService extends IService<Role> {
    List<String> userRole(int userId);
}
