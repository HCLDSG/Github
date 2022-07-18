package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 模拟数据库业务
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 模拟一个数据库用户
     * 账号 admin
     * 密码 123456
     */
//    private final static HashMap<String, User> USER_MAP = new LinkedHashMap<>() {
//        {
//            put("admin", new User()
//                    .setUserId(1)
//                    .setUsername("admin")
//                    .setPassword(SecurityUtils.passwordEncoder("123456"))
//                    // 角色以ROLE_前缀
//                    .setRoles(Arrays.asList("ROLE_ADMIN", "ROLE_USER"))
//                    // 权限
//                    .setAuths(Arrays.asList("read", "write"))
//            );
//        }
//    };


    public User getUser(String userName) {
        //获取当前用户信息
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        User user = this.userMapper.selectOne(wrapper);
        return user;
    }


}
