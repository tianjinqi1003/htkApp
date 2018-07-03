package com.htkapp.modules.common.dao;


import com.htkapp.modules.common.entity.LoginUser;

/**
 * 用户	 商铺	员工
 */
public interface UserMessageMapper {

    /* ===================app用户接口开始======================== */
    //通过app用户名和密码查找用户信息
    LoginUser selectByUser(LoginUser loginUser);
    //通过token查找用户信息
    LoginUser selectByUserToken(String token);
    /* =====================app用户接口结束========================== */


    /* ======================商户接口开始============================== */
    //通过商户用户名和密码查找用户信息
    LoginUser selectByAccountShop(LoginUser loginUser);
    //通过token查找用户信息
    LoginUser selectByAccountShopToken(String token);
    /* ======================商户接口结束================================= */


    /* ========================管理员接口开始============================ */
    //通过管理员用户名和密码查找用户信息
    LoginUser selectByAdmin(LoginUser loginUser);
    //通过token查找用户信息
    LoginUser selectByAdminToken(String token);
    /* ==========================管理员接口结束=============================== */


}
