package com.htkapp.modules.common.service;

import java.util.Set;

public interface PermissionService {

    //通过用户id获取用户权限
    Set<String> getUserPermissionListByUserId(long userId);
}
