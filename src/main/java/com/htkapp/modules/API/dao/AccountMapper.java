package com.htkapp.modules.API.dao;

import java.util.List;

import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.entity.AppShippingAddress;
import org.apache.ibatis.annotations.Param;

/**
 * 用户
 */

public interface AccountMapper {


    /*===================接口开始======================*/
    //注册app新用户
    int insertAccountDAO(Account account);
    //通过手机查找app用户对象(注册前查找，防止重复注册)
    Account selectByPhoneDAO(String phone);
    //通过app_token获得用户信息
    Account selectByTokenDAO(String token);
    //修改密码
    int changePasswordDAO(Account account);
    //验证新密码和旧密码是否一致
    Account verifyAppAccountPasswordDAO(@Param("token") String token,@Param("oldP") String oldP);
    //登陆成功后更改用户的登陆方式默认值
    void changeAppAccountLoginWayDAO(@Param("token") String token, @Param("loginWay") short loginWay);
    //根据app用户token获取用户最近记录到数据库中的登陆方式
    Short getAppAccountLoginWayDAO(String token);
    //修改注册完成的app用户的称呢(把返回主键值赋值)
    void changeAppAccountNickNameDAO(@Param("token") String token, @Param("nickName") String nickName);
    //当第一次更改过密码，就更改密码是否更改过的状态为true
    void changePasswordStatusDAO(String token);
    //更改用户头像url
    void changeAppAccountAvaUrlDAO(@Param("token") String token, @Param("avaUrl") String avaUrl);
    //更改app用户的呢称（只能修改一次,修改时改变状态）
    void changeAppAccountNickNameAndStateDAO(@Param("token") String token, @Param("nickName") String nickName);
    //根据用户token查询出用户id,用户头像，用户呢称
    Account getTakeoutCommentAccountMessageByTokenDAO(String token);
    //忘记密码 通过短信改密码
    int changePasswordByForgetPasswordBySMSDAO(Account account);
    //根据订单号和标识查询购买用户信息
    Account getAccountDataByOrderNumberAndMarkDAO(@Param("orderNumber") String orderNumber, @Param("mark") int mark);
    //验证微信登陆是否已绑定用户
    Account verifyLoginByWeChatTokenDAO(String weChatToken);
    //验证qq登陆是否已绑定用户
    Account verifyLoginByQqTokenDAO(String qqToken);
    //更改用户的第三方登陆token
    int changeThirdPartyTokenDAO(@Param("thirdPartyToken") String thirdPartyToken, @Param("mark") Integer mark, @Param("accountToken") String accountToken);
    /*===================接口结束======================*/


    /* ==================jsp页面接口开始========================== */
    //通过条件查找用户列表
    List<Account> getAccountListByConditionDAO(Account account);
    //查找用户列表(无条件)
    List<Account> getAccountListDAO();



    /* =======================jsp页面接口开始=========================== */
//    //通过商铺ID关注商铺
//    int attentionShopByShopId(@Param("accountId") Integer accountId, @Param("shopId") Integer shopId);
//
//    //通过用户ID查找用户信息
//    Account findAccountById(@Param("accountId") Integer accountId);
//
//    //通过用户ID修改用户呢称
//    int editAccountNickNameById(@Param("accountId") Integer accountId, @Param("nickName") String nickName);
//
//    int deleteByPrimaryKey(Integer accountId);
//
//    Account selectByPrimaryKey(Integer accountId);
//
//
//    int insertSelective(Account record);
//
//
//    int updateByPrimaryKeySelective(Account record);
//
//    int updateByPrimaryKey(Account record);
//
//    //查找所有用户并分页显示
//    List<Account> findByPage(Account account);
//
//
//    //检查用户输入的旧密码是否正确
//    Account checkAccountShopPassword(Account account);
//
//    //通过ID查找用户名
//    Account findById(@Param("id") int id);

}