package com.htkapp.modules.common.dao;

import java.util.Set;

//用户权限
public interface PermissionMapper {

    //通过用户id获取用户权限
    Set<String> getUserPermissionListByUserIdDAO(long userId);
}
