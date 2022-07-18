package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JwtUserDetail implements UserDetails {

    private Integer userId;

    private String username;

    private String password;

    private List<String> roles;    //角色,以ROLE_开头

    private List<String> auths;    //权限

    public JwtUserDetail(String username, String password, List<String> roles, List<String> auths) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.auths = auths;
    }

    //getAuthorities获取用户包含的权限，返回权限集合，权限是一个继承了GrantedAuthority的对象；
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
        if (!roles.isEmpty()&&!auths.isEmpty()){

            List<GrantedAuthority> authorities = new ArrayList<>();

            //获取用户角色
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }

            //获取用户权限
            for (String auth : auths) {
                authorities.add(new SimpleGrantedAuthority(auth));
            }

            return authorities;
        }
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
