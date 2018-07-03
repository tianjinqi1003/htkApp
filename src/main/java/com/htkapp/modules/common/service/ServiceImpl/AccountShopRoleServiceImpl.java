package com.htkapp.modules.common.service.ServiceImpl;

import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.common.dao.AccountShopRoleMapper;
import com.htkapp.modules.common.entity.AccountShopRole;
import com.htkapp.modules.common.service.AccountShopRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountShopRoleServiceImpl implements AccountShopRoleService {

    @Resource
    private AccountShopRoleMapper accountShopRoleDao;

    //插入商户和角色信息
    @Override
    public void insertAccountShopRole(AccountShopRole accountShopRole) {
        int row = accountShopRoleDao.insertAccountShopRoleDAO(accountShopRole);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //根据商户id查找用户权限
    @Override
    public AccountShopRole getAccountShopRoleById(int id) {
        return accountShopRoleDao.getAccountShopRoleByIdDAO(id);
    }

    //修改用户权限
    @Override
    public void updateAccountShopRole(AccountShopRole accountShopRole) {
        int row = accountShopRoleDao.updateAccountShopRoleDAO(accountShopRole);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }
}
