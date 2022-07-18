package com.example.demo.Security.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 授权处理器，用于处理无权访问
 * 拒绝访问，无权限
 *
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AccessFailure implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        log.info("拒绝访问，无权限");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(ResponseResult.fail(ResponseResult.RespCode.UNAUTHORIZED)));
    }
}
