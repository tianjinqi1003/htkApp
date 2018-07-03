package com.htkapp.modules.admin.system.dao;

import com.htkapp.modules.admin.system.entity.AdminLoginLog;

/**
 * Created by yinqilei on 17-7-8.
 */
public interface AdminLoginLogMapper {

    /* ===================接口开始========================= */
    //插入管理员本次登陆记录日志
    int insertAdminCurrentLoginLogDAO(AdminLoginLog adminLoginLog);
    //插入管理员本次退出记录日志
    int insertAdminCurrentSignOutLogDAO(AdminLoginLog adminLoginLog);

    /* =======================接口结束========================== */
}
