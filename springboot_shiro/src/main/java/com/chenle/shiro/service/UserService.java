package com.chenle.shiro.service;

import com.chenle.shiro.entity.User;

import java.util.List;

public interface UserService {
    //用户登录
    User getUserInfoByName(String name);
    List<String> getUserRoleInfo(String principal);
    //获取用户角色的权限信息
    public List<String> getUserPermissionInfo(List<String> roles);
}