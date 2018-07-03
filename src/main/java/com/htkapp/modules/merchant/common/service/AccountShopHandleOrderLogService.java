package com.htkapp.modules.merchant.common.service;

import com.htkapp.modules.merchant.common.entity.AccountShopHandleOrderLog;

/**
 * Created by yinqilei on 17-7-10.
 *
 */
public interface AccountShopHandleOrderLogService {

    /* ===================接口开始===================== */
    //插入商户处理订单日志
    void insertAccountShopHandleOrderLog(AccountShopHandleOrderLog handleOrderLog) throws RuntimeException;


    /* ====================接口结束====================== */
}
