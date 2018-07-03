package com.htkapp.modules.common.service.ServiceImpl;

import com.htkapp.modules.common.dao.PermissionMapper;
import com.htkapp.modules.common.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionDao;


    //通过用户id获取用户权限
    @Override
    public Set<String> getUserPermissionListByUserId(long userId) {
        return permissionDao.getUserPermissionListByUserIdDAO(userId);
    }
}
