package com.htkapp.modules.merchant.common.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.common.dao.AccountShopHandleOrderLogMapper;
import com.htkapp.modules.merchant.common.entity.AccountShopHandleOrderLog;
import com.htkapp.modules.merchant.common.service.AccountShopHandleOrderLogService;
import jdk.nashorn.internal.objects.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinqilei on 17-7-10.
 */

@Service
public class AccountShopHandleOrderLogServiceImpl implements AccountShopHandleOrderLogService {

    @Autowired
    private AccountShopHandleOrderLogMapper accountShopHandleOrderLogDao;


    /* ============================接口开始============================== */
    //插入商户处理订单日志
    @Override
    public void insertAccountShopHandleOrderLog(AccountShopHandleOrderLog handleOrderLog) throws RuntimeException {
        try {
            int row = accountShopHandleOrderLogDao.insertAccountShopHandleOrderLogDAO(handleOrderLog);
            if( row == 0){
                throw new RuntimeException("处理异常");
            }
        }catch (Exception e){
            throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
        }
    }
    /* ==============================接口结束============================= */
}
