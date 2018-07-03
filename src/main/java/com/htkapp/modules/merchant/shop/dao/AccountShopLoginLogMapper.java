package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.AccountShopLoginLog;

/**
 * Created by yinqilei on 17-7-4.
 * 商户登陆日志
 */
public interface AccountShopLoginLogMapper {

    /* ==============接口开始================= */
    //插入商户本次登陆记录日志
    int insertCurrentLoginLogDAO(AccountShopLoginLog accountShopLoginLog);
    //插入商户本次退出记录日志
    int insertCurrentSignOutLogDAO(AccountShopLoginLog accountShopLoginLog);
    //查找商户最新的登陆时间
    String getCurrentLoginLogByTokenDAO(String accountShopToken);
    //查找商户上次登陆时间
    String getLastLoginLogByTokenDAO(String accountShopToken);

    /* =================接口结束====================== */
}
