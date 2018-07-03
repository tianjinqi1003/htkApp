package com.htkapp.modules.common.service;

import com.htkapp.modules.common.entity.AccountShopRole;

public interface AccountShopRoleService {

    //插入商户和角色信息
    void insertAccountShopRole(AccountShopRole accountShopRole);
    //根据商户id查找用户权限
    AccountShopRole getAccountShopRoleById(int id);
    //修改用户权限
    void updateAccountShopRole(AccountShopRole accountShopRole);
}
