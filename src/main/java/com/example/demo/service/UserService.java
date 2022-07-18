package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User;

public interface UserService extends IService<User> {

    /**
     * 获取用户
     *
     * @param userName 账号
     * @return user
     */
    User getUser(String userName);

}
