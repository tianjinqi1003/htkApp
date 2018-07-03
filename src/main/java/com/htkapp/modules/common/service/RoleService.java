package com.htkapp.modules.common.service;

import com.htkapp.modules.common.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    //通过用户id查找用户角色
    Set<String> getRoleListByUserId(long userId);
    //获取所有店铺角色
    List<Role> getRoleList();
    //通过用户id查找用户角色
    Set<String> getRoleListNameByUserId(long userId);

}
