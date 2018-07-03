package com.htkapp.modules.admin.system.service;

import com.htkapp.modules.admin.system.entity.AdminLoginLog;

/**
 * Created by yinqilei on 17-7-8.
 */
public interface AdminLoginLogService {


    /* ==================接口开始======================== */
    //插入管理员本次登陆记录日志
    void insertAdminCurrentLoginLog(AdminLoginLog adminLoginLog) throws RuntimeException;
    //插入管理员本次退出记录日志
    void insertAdminCurrentSignOutLog(AdminLoginLog adminLoginLog) throws RuntimeException;
    /* =======================接口结束======================== */
}
