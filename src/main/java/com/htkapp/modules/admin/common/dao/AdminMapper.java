package com.htkapp.modules.admin.common.dao;

import com.htkapp.modules.admin.common.entity.Admin;
import com.htkapp.modules.common.entity.LoginUser;

/**
 * 管理员
 */

public interface AdminMapper {

    /* =================接口开始==================== */
    //改变登陆状态
    int changeAdminLoginStateDAO(Admin admin);
    //根据用户名和密码验证用户
    LoginUser getAccountByNameAndPwdDAO(String userName, String pwd);
    /* =================接口结束====================== */

}