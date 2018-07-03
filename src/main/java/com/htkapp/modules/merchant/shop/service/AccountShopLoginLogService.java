package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.AccountShopLoginLog;

/**
 * Created by yinqilei on 17-7-4.
 */
public interface AccountShopLoginLogService {

    /* =====================接口开始============================ */
    //插入商户本次登陆记录日志
    void insertCurrentLoginLog(AccountShopLoginLog accountShopLoginLog) throws RuntimeException;
    //插入商户本次退出记录日志
    void insertCurrentSignOutLog(AccountShopLoginLog accountShopLoginLog) throws RuntimeException;
    //查找商户最新的登陆时间
    String getCurrentLoginLogByToken(String accountShopToken) throws Exception;
    //查找商户上次登陆时间
    String getLastLoginLogByToken(String accountShopToken) throws Exception;

    /* ======================接口结束================================== */
}
