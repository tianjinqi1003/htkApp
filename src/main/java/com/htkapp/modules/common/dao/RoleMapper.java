package com.htkapp.modules.common.dao;

import com.htkapp.modules.common.entity.Role;

import java.util.List;
import java.util.Set;

//用户角色
public interface RoleMapper {

    //通过用户id查找用户角色
    Set<String> getRoleListByUserIdDAO(long userId);
    //通过用户id查找用户角色
    Set<String> getRoleListNameByUserIdDAO(long userId);
    //获取所有店铺角色
    List<Role> getRoleListDAO();
}
