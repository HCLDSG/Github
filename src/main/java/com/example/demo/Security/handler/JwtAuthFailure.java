package com.example.demo.Security.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT认证处理器，处理认证失败
 * token认证失败，toke不存在或已失效
 *
 */
@Component
@Slf4j
public class JwtAuthFailure implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        log.info("认证失败，token不存在或已失效");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ResponseResult.fail(ResponseResult.RespCode.UNAUTHORIZED)));
    }
}
