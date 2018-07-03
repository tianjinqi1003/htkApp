package com.htkapp.modules.merchant.common.service;


import com.htkapp.modules.common.entity.LoginUser;

public interface UserServiceI {

    /* ===================接口开始===================== */
    //通过token和role 查找用户信息
    LoginUser checkToken(String token, String role) throws Exception;
    //通过用户名和密码 查找用户信息
    LoginUser checkUser(LoginUser loginUser) throws Exception;
    //通过用户名和密码、role　查找用户信息
    LoginUser checkUser(String userName, String password, String role);
    /* ====================接口结束========================= */

}