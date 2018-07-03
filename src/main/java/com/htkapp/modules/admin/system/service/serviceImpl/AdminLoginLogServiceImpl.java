package com.htkapp.modules.admin.system.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.admin.system.dao.AdminLoginLogMapper;
import com.htkapp.modules.admin.system.entity.AdminLoginLog;
import com.htkapp.modules.admin.system.service.AdminLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinqilei on 17-7-8.
 *
 */
@Service
public class AdminLoginLogServiceImpl implements AdminLoginLogService {

    @Autowired
    private AdminLoginLogMapper adminLoginLogDao;

    /* ====================接口开始========================== */
    //插入管理员本次登陆记录日志
    @Override
    public void insertAdminCurrentLoginLog(AdminLoginLog adminLoginLog) throws RuntimeException {
        try {
            int row = adminLoginLogDao.insertAdminCurrentLoginLogDAO(adminLoginLog);
            if(row <= 0){
                throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
            }
        }catch (RuntimeException e){
            throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
        }
    }

    //插入管理员本次退出记录日志
    @Override
    public void insertAdminCurrentSignOutLog(AdminLoginLog adminLoginLog) throws RuntimeException {
        try {
            int row = adminLoginLogDao.insertAdminCurrentSignOutLogDAO(adminLoginLog);
            if(row <= 0){
                throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
            }
        }catch (RuntimeException e){
            throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
        }
    }
    /*  =======================接口结束=========================== */
}
