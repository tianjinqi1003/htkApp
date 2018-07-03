package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商家
 */

public interface AccountShopMapper {

    /* ======================接口开始========================== */
    //修改转账账户
    int changeBindedAccount(Integer id, String newAccount);
    //商户app端忘记密码，重置密码
    int changePasswordBySMSDAO(AccountShop accountShop);
    //通过token查询商户信息
    AccountShop selectByTokenDAO(String token);
    //改变账号登陆状态信息
    int changeAccountShopLoginStateDAO(AccountShop accountShop);
    //通过accountShopId查找商户信息
    AccountShop getAccountShopDataByIdDAO(int accountShopId);
    //通过手机号验证商户是否存在
    AccountShop getAccountShopByPhoneAndUserNameDAO(String phone);
    //改变商户呢称
    int changeAccountShopNickNameDAO(AccountShop accountShop);
    //找回密码验证身份成功后，根据手机号和密码重置密码
    int changePasswordDAO(@Param("phone") String phone, @Param("password") String password);
    //通过商户token查询商户的剩余使用时间
    AccountShop getUseTimeByTokenDAO(String token);
    //更改剩余时间
    int updateUseTimeByIdDAO(int userId, String time);

    /* =======================接口结束============================= */


    /* ====================JSP页面接口开始============================== */
    //通过条件获取商户列表
    List<AccountShop> getAccountShopListByConditionDAO(AccountShop accountShop);
    //获取商户列表
    List<AccountShop> getAccountShopListDAO();
    //注册新商户
    int registerAccountShopByPhoneDAO(AccountShop accountShop);
    //通过用户名和密码查找用户信息
    LoginUser getAccountShopByNameAndPwdDAO(String userName, String pwd);
    //修改密码
    int updatePasswordDAO(AccountShop accountShop);
    //修改手机号
    int updateShopPhoneDAO(AccountShop accountShop);

    AccountShop getAlipayAccount(Integer id);

    /* ========================JSP页面接品结束=============================== */
}