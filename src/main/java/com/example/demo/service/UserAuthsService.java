package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Auths;

import java.util.List;

public interface UserAuthsService extends IService<Auths> {
    List<String> userAuths(int userId);
}
