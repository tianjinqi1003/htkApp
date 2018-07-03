package com.htkapp.modules.API.service;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.entity.AppShippingAddress;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface AccountServiceI {


    /* =====================接口的逻辑处理方法========================== */
    //通过手机号注册账号(逻辑处理方法)
    APIResponseModel registerByPhone(String phone, String valCode, String password);
    //通过传入的手机号生成验证码，并以短信发送给该手机号(逻辑处理方法)
    APIResponseModel sendSMS(String phone);
    //通过手机号和短信验证码登陆
    APIResponseModel appAccountLoginByCode(String phone, String valCode);
    //通过用户名和密码登陆
    APIResponseModel appAccountLoginByUserName(String userName, String password, String role,short loginWay);
    //通过app用户token查找用户信息
    APIResponseModel getAppAccountData(String token);
    //修改app用户密码
    APIResponseModel changeAppAccountPassword(String token, String oldP, String newP);
    //app用户修改头像
    APIResponseModel changeAvaImg(MultipartFile avaImgFile, String token);
    //app修改呢称(只能修改一次)
    APIResponseModel changeAppNickName(String token, String nickName);
    //获取用户的外卖收货地址
    APIResponseModel getAccountShippingAddressList(String token);
    //添加用户外卖收货地址
    APIResponseModel addAccountShippingAddress(AppShippingAddress shippingAddress);
    //修改用户外卖收货地址
    APIResponseModel changeAccountShippingAddress(AppShippingAddress shippingAddress);
    //app用户收藏店铺
    APIResponseModel collectionStore(String token, Integer shopId, Boolean colStatus);
    //根据app用户的token获取用户的收藏列表
    APIResponseModel getCollectionListByToken(String token, int pageNumber);
    //app用户删除外卖收货地址
    APIResponseModel deleteAccountShippingAddressById(String token, Integer addressId);
    //忘记密码（通过短信重置密码）
    APIResponseModel forgetPasswordBySMS(String token, String code, String password);
    //微信登陆调起接口
    APIResponseModel loginByWeChat(String weChatToken);
    //qq登陆调起接口
    APIResponseModel loginByQq(String qqToken);
    //微信登陆未绑定用户调起接口
    APIResponseModel weChatLoginCallUpInterface(APIRequestParams params);
    //qq登陆未绑定用户调起接口
    APIResponseModel qqLoginCallUpInterface(APIRequestParams params);
    //用户确认收货接口
    APIResponseModel enterReceipt(APIRequestParams params,String orderNumber, String token);

    /*=====================接口开始============================*/
    //注册新用户
    APIResponseModel insertAccount(Account account) throws Exception;
    //查找当前要注册用户是否已经注册(判断是否是重复注册)
    Boolean selectByPhone(String phone) throws Exception;
    //通过手机号查找用户信息
    Account selectDataByPhone(String phone) throws Exception;
    //通过token查找用户信息
    Account selectByToken(String token) throws Exception;
    //通过token和oldPassword验证用户的修改密码请求
    Account verifyAppAccountPassword(String token,String oldP) throws Exception;
    //根据用户token查询出用户id,用户头像，用户呢称
    Account getTakeoutCommentAccountMessageByToken(String token) throws Exception;
    //忘记密码 通过短信改密码
    boolean changePasswordByForgetPasswordBySMS(Account account) throws Exception;
    //根据订单号和标识查询购买用户信息
    Account getAccountDataByOrderNumberAndMark(String orderNumber, int mark) throws Exception;
    //验证微信登陆是否已绑定用户
    Account verifyLoginByWeChatToken(String weChatToken) throws Exception;
    //验证qq登陆是否已绑定用户
    Account verifyLoginByQqToken(String qqToken) throws Exception;
    //更新用户的第三方登陆token
    void changeThirdPartyToken(String thirdPartyToken, Integer mark, String accountToken) throws Exception;
    //解绑第三方账号
    APIResponseModel unbindThirdPartyAccount(APIRequestParams params);
    /*=====================接口结束============================*/


    /*=========================jsp页面接口开始============================*/
    //通过条件查找用户列表
    List<Account> getAccountListByCondition(Account account, int pageNo, int pageLimit) throws Exception;
    //查找用户列表(无条件)
    List<Account> getAccountList(int pageNo, int pageLimit) throws Exception;

    /* =======================jsp页面接口结束============================= */

//    //关注商家
//    boolean attentionShop(Integer accountId, Integer shopId);
//
//    //通过用户ID查找用户的收货地址
////    List<AppShippingAddress> getAddressByAccountId(int pageNo, int pageLimit, Integer accountId);
//
//    //通过用户ID查找用户信息
//    Account findAccountById(Integer accountId);
//
//    //通过用户ID修改用户呢称
//    boolean editAccountNickName(Integer accountId, String nickName);
//
//
//    List<Account> findByPage(int pageNo, int pageLimit, Account account);
//
//    Account findByPk(int parseInt);
//
//    int update(Account account);
//
//    int deleteByIds(String[] accountIds) throws Exception;
//
//    void updatePassword(Account account) throws Exception;
//
//    boolean checkAccountPassword(Account account);
//
//    //通过ID查找用户名
//    String getAccountNameById(int id);
}
