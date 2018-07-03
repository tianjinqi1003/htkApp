package com.htkapp.modules.common.dao;

import com.htkapp.modules.common.entity.AccountShopRole;

public interface AccountShopRoleMapper {

    //插入商户和角色信息
    int insertAccountShopRoleDAO(AccountShopRole accountShopRole);
    //根据商户id查找用户权限
    AccountShopRole getAccountShopRoleByIdDAO(int id);
    //修改用户权限
    int updateAccountShopRoleDAO(AccountShopRole accountShopRole);
}
