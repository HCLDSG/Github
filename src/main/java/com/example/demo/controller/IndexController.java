package com.example.demo.controller;

import com.example.demo.Security.jwt.JwtProvider;
import com.example.demo.entity.User;
import com.example.demo.service.UserAuthsService;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.SecurityUtils;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;



@Api(tags = "登录登出认证功能")
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class IndexController {

    private final JwtProvider jwtProvider;

    private final UserService userService;

    private final UserAuthsService userAuthsService;

    private final UserRoleService userRoleService;


    /**
     * 登录
     */
    @ApiOperation(value = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value="用户名",required=true,paramType="query"),
            @ApiImplicitParam(name="password",value="密码",required=true,paramType="query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 402, message = "账号或密码错误"),
    })
    @PostMapping(value = "/login")
    public ResponseResult<String> login(@RequestParam("userName") String userName,
                                        @RequestParam("password") String password) {

        User user = userService.getUser(userName);

        List<String> roles = userRoleService.userRole(user.getUserId());
        for (String roles1 : roles){
            log.info(roles1);
        }

        List<String> auths = userAuthsService.userAuths(user.getUserId());

        String token = jwtProvider.createToken(user.getUserId(), roles, auths);

        log.info(token);

        if (token == null && !SecurityUtils.passwordMatches(password, user.getUserPassword())) {
            return ResponseResult.loginFail("账号或密码错误");
        }else {
            return ResponseResult.ok(token);
        }

    }

    /**
     * 获取用户信息
     */
    @ApiOperation(value = "获取用户角色及权限")
    @GetMapping("/info")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAuthority('read')")
    public ResponseResult<HashMap<String, Object>> info() {
        return ResponseResult.ok(new HashMap<>(1) {
            {
                put("userId", SecurityUtils.getUserId());
                put("auths", SecurityUtils.getAuths());
            }
        });
    }


    @GetMapping("/test")
    @PreAuthorize("hasRole('xxx') or hasAuthority('aaa')")
    public ResponseResult<String> test() {
        return ResponseResult.ok();
    }

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('write')")
    public ResponseResult<String> test1() {
        return ResponseResult.ok();
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('read')")
    public ResponseResult<String> test2() {
        return ResponseResult.ok();
    }



}
