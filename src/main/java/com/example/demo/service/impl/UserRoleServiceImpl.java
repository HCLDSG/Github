package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Role;
import com.example.demo.mapper.UserRoleMapper;
import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, Role> implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    public List<String> userRole(int userId){
        //获取用户角色
        List<String> roles = new ArrayList<>();
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Role> userRole = this.userRoleMapper.selectList(wrapper);
        for (Role userRole1 : userRole){
            roles.add("ROLE_" + userRole1.getUserRole());
        }
        return roles;
    }
}
