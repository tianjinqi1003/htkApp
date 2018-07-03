package com.htkapp.modules.common.service.ServiceImpl;

import com.htkapp.modules.common.dao.RoleMapper;
import com.htkapp.modules.common.entity.Role;
import com.htkapp.modules.common.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleDao;

    //通过id获取角色
    @Override
    public Set<String> getRoleListByUserId(long userId) {
        return roleDao.getRoleListByUserIdDAO(userId);
    }

    //获取所有店铺角色
    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = roleDao.getRoleListDAO();
        if(roleList != null && roleList.size() > 0){
            return roleList;
        }
        return null;
    }

    //通过用户id查找用户角色
    @Override
    public Set<String> getRoleListNameByUserId(long userId) {
        return roleDao.getRoleListNameByUserIdDAO(userId);
    }
}
