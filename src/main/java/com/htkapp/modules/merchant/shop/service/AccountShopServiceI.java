package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.shop.entity.AccountShop;

import java.util.List;


public interface AccountShopServiceI {

    /* =================接口开始=========================== */
    //商户app端忘记密码，重置密码
    boolean changePasswordBySMS(AccountShop accountShop) throws Exception;

    //通过token查询商户信息
    AccountShop selectByToken(String token) throws Exception;

    //改变账号登陆状态信息
    boolean changeAccountShopLoginState(AccountShop accountShop) throws RuntimeException;

    //通过accountShopId查找商户信息
    AccountShop getAccountShopDataById(int accountShopId) throws Exception;

    //通过手机号验证商户是否存在
    AccountShop getAccountShopByPhoneAndUserName(String phone) throws Exception;

    //改变商户呢称
    void changeAccountShopNickName(AccountShop accountShop) throws Exception;

    //找回密码验证身份成功后，根据手机号和密码重置密码
    void changePassword(String phone, String password) throws Exception;

    //通过商户token查询商户的剩余使用时间
    AccountShop getUseTimeByToken(String token) throws Exception;

    //修改第三方转账账户
    int changeBindedAccount(Integer id, String newAccount) throws Exception;

    //转账
    AccountShop getAlipayAccount(Integer id) throws Exception;
    /* =====================接品结束========================== */


    /* ====================JSP页面接口开始============================== */
    //通过条件获取商户列表
    List<AccountShop> getAccountShopListByCondition(AccountShop accountShop, int pageNo, int pageLimit) throws Exception;

    //获取商户列表
    List<AccountShop> getAccountShopList(int pageNo, int pageLimit);

    //注册新商户
    int registerAccountShopByPhone(AccountShop accountShop) throws Exception;

    //通过用户名和密码查找用户信息
    LoginUser getAccountShopByNameAndPwd(String userName, String pwd);

    //修改密码
    void updatePassword(AccountShop accountShop);

    //修改手机号
    void updateShopPhone(AccountShop accountShop);

    //更改剩余时间
    void updateUseTimeById(int userId, String time);
    /* ========================JSP页面接品结束=============================== */
}
