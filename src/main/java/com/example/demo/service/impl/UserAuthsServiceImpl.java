package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Auths;
import com.example.demo.mapper.UserAuthsMapper;
import com.example.demo.service.UserAuthsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthsServiceImpl extends ServiceImpl<UserAuthsMapper, Auths> implements UserAuthsService {

    @Autowired
    UserAuthsMapper userAuthsMapper;

    public List<String> userAuths(int userId){
        //获取用户权限
        List<String> auths = new ArrayList<>();
        QueryWrapper<Auths> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Auths> userAuths = this.userAuthsMapper.selectList(wrapper);
        for (Auths userAuths1 : userAuths){
            auths.add(userAuths1.getUserAuths());
        }
        return auths;
    }
}
