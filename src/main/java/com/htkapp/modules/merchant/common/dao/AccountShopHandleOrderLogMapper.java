package com.htkapp.modules.merchant.common.dao;

import com.htkapp.modules.merchant.common.entity.AccountShopHandleOrderLog;

/**
 * Created by yinqilei on 17-7-10.
 * 商户处量订单日志
 */
public interface AccountShopHandleOrderLogMapper {

    /* =================接口开始=================== */
    //插入商户处理订单日志
    int insertAccountShopHandleOrderLogDAO(AccountShopHandleOrderLog handleOrderLog);

    /* ==================接口结束================== */
}
