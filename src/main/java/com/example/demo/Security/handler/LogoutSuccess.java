package com.example.demo.Security.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 退出登录处理器，处理退出登录成功
 * 退出登录
 *
 */
@Slf4j
@Component
public class LogoutSuccess extends SimpleUrlLogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ResponseResult.ok().setMessage("退出登录成功！")));
    }
}
