package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.AccountShopLoginLogMapper;
import com.htkapp.modules.merchant.shop.entity.AccountShopLoginLog;
import com.htkapp.modules.merchant.shop.service.AccountShopLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinqilei on 17-7-4.
 */
@Service
public class AccountShopLoginLogServiceImpl implements AccountShopLoginLogService {

    @Autowired
    private AccountShopLoginLogMapper accountShopLoginLogDao;

    /* =====================接口开始======================== */
    //插入商户本次登陆记录日志
    //抛出运行期异常(调用方法加入事物管理，当捕获到运行异常会自动回滚事物)
    @Override
    public void insertCurrentLoginLog(AccountShopLoginLog accountShopLoginLog) throws RuntimeException {
        try {
            int row = accountShopLoginLogDao.insertCurrentLoginLogDAO(accountShopLoginLog);
            if (row <= 0) {
                throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
        }
    }

    //插入商户本次退出记录日志
    //抛出运行期异常(调用方法加入事物管理，当捕获到运行异常会自动回滚事物)
    @Override
    public void insertCurrentSignOutLog(AccountShopLoginLog accountShopLoginLog) throws RuntimeException {
        try {
            int row = accountShopLoginLogDao.insertCurrentSignOutLogDAO(accountShopLoginLog);
            if (row <= 0) {
                throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
            }
        } catch (Exception e) {
            throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查找商户最新的登陆时间
    @Override
    public String getCurrentLoginLogByToken(String accountShopToken) throws Exception {
        try {
            return accountShopLoginLogDao.getCurrentLoginLogByTokenDAO(accountShopToken);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查找商户上次登陆时间
    @Override
    public String getLastLoginLogByToken(String accountShopToken) throws Exception {
        try {
            return accountShopLoginLogDao.getLastLoginLogByTokenDAO(accountShopToken);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    /* ===================接口结束================================= */
}
