package com.example.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.JwtUserDetail;
import com.example.demo.entity.User;
import com.example.demo.entity.Auths;
import com.example.demo.entity.Role;
import com.example.demo.mapper.UserAuthsMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserAuthsMapper userAuthsMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null){
            throw new RuntimeException("请输入用户名");
        }

        //获取当前用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username);
        User user = userMapper.selectOne(wrapper);

        log.info(username);

        //获取用户角色
        List<String> roles = new ArrayList<>();
        QueryWrapper<Role> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_id",user.getUserId());
        List<Role> userRole = this.userRoleMapper.selectList(wrapper1);
        for (Role userRole1 : userRole){
            roles.add("ROLE_" + userRole1.getUserRole());

            log.info(userRole1.getUserRole());
        }

        //获取用户权限
        List<String> auths = new ArrayList<>();
        QueryWrapper<Auths> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("user_id",user.getUserId());
        List<Auths> userAuths = this.userAuthsMapper.selectList(wrapper2);
        for (Auths userAuths1 : userAuths){
            roles.add(userAuths1.getUserAuths());

            log.info(userAuths1.getUserAuths());
        }

        return new JwtUserDetail(user.getUserName(), user.getUserPassword(), roles, auths);
    }
}
