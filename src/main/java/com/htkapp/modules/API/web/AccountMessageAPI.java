package com.htkapp.modules.API.web;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.modules.API.entity.AppShippingAddress;
import com.htkapp.modules.API.service.AccountServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinqilei on 17-6-22.
 * 用户信息API
 */
@RestController
@RequestMapping("/API/AccountMessage")
public class AccountMessageAPI {

    @Resource
    private AccountServiceI accountService;

    //获取手机验证码
    @RequestMapping(value = "/sendSms/{phone}")
    public APIResponseModel sendSMS(@PathVariable("phone") String phone) {
        return accountService.sendSMS(phone);
    }

    //通过手机号注册用户
    @RequestMapping("/registerByPhone")
    public APIResponseModel registerByPhone(String phone, String valCode, String password) {
        return accountService.registerByPhone(phone, valCode, password);
    }

    //登陆通过手机号和验证码登陆
    @RequestMapping("/appAccountLoginByCode")
    public APIResponseModel appAccountLoginByCode(APIRequestParams params) {
        return accountService.appAccountLoginByCode(params.getPhone(), String.valueOf(params.getCode()));
    }

    //登陆通过用户名和密码登陆
    @RequestMapping("/appAccountLoginByUserName")
    public APIResponseModel appAccountLoginByUserName(APIRequestParams params) {
        return accountService.appAccountLoginByUserName(params.getUserName(), params.getPassword(), params.getRole(), params.getLoginWay());
    }


    //用户信息
    @RequestMapping("/getAppAccountData")
    public APIResponseModel getAppAccountData(APIRequestParams params) {
        return accountService.getAppAccountData(params.getToken());
    }

    //修改密码
    @RequestMapping("/changeAppAccountPassword")
    public APIResponseModel changeAppAccountPassword(APIRequestParams params) {
        return accountService.changeAppAccountPassword(params.getToken(), params.getOldP(), params.getNewP());
    }


    //修改头像
    @RequestMapping("/changeAvaImg")
    public APIResponseModel changeAvaImg(APIRequestParams params) {
        return accountService.changeAvaImg(params.getAvaImgFile(), params.getToken());
    }

    //修改呢称
    @RequestMapping("/changeNickName")
    public APIResponseModel changeNickName(APIRequestParams params) {
        return accountService.changeAppNickName(params.getToken(), params.getNickName());
    }

    //获取用户外卖收货地址列表
    @RequestMapping("/getShippingAddressList")
    public APIResponseModel getAccountShippingAddressList(APIRequestParams params) {
        return accountService.getAccountShippingAddressList(params.getToken());
    }


    //添加用户收货地址
    @RequestMapping("/addAccountShippingAddress")
    public APIResponseModel addAccountShippingAddress(AppShippingAddress appShippingAddress) {
        return accountService.addAccountShippingAddress(appShippingAddress);
    }

    //修改用户收货地址
    @RequestMapping("/changeAccountShippingAddress")
    public APIResponseModel changeAccountShippingAddress(AppShippingAddress appShippingAddress) {
        return accountService.changeAccountShippingAddress(appShippingAddress);
    }

    //app用户删除外卖收货地址
    @RequestMapping("/deleteAccountShippingAddressById")
    public APIResponseModel deleteAccountShippingAddressById(String token, Integer addressId){
        return accountService.deleteAccountShippingAddressById(token,addressId);
    }

    //收藏店铺和取消收藏店铺
    @RequestMapping("/collectionStore")
        public APIResponseModel collectionStore(String token, Integer shopId, Boolean colStatus) {
        return accountService.collectionStore(token, shopId, colStatus);
    }

    //根据用户的token获取用户的收藏店铺列表
    @RequestMapping("/getCollectionListByToken")
    public APIResponseModel getCollectionListByToken(String token,
                                                 @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
        return accountService.getCollectionListByToken(token,pageNumber);
    }

    //忘记密码 通过手机短信找回密码
    @RequestMapping("/forgetPasswordBySMS")
    public APIResponseModel forgetPasswordBySMS(APIRequestParams params){
        return accountService.forgetPasswordBySMS(String.valueOf(params.getPhone()),String.valueOf(params.getCode()),params.getPassword());
    }

    //微信登陆调起接口
    @RequestMapping("/loginByWeChat")
    public APIResponseModel loginByWeChat(String weChatToken){
        return accountService.loginByWeChat(weChatToken);
    }

    //qq登陆调起接口
    @RequestMapping("/loginByQq")
    public APIResponseModel loginByQq(String qqToken){
        return accountService.loginByQq(qqToken);
    }

    //微信登陆未绑定用户调起接口
    @RequestMapping("/weChatLoginCallUpInterface")
    public APIResponseModel weChatLoginCallUpInterface(APIRequestParams params){
        return accountService.weChatLoginCallUpInterface(params);
    }

    //qq登陆未绑定用户调起接口
    @RequestMapping("/qqLoginCallUpInterface")
    public APIResponseModel qqLoginCallUpInterface(APIRequestParams params){
        return accountService.qqLoginCallUpInterface(params);
    }

    //用户确认收货接口
    @RequestMapping("/enterReceipt")
    public APIResponseModel enterReceipt(APIRequestParams params,String orderNumber, String token){
        return accountService.enterReceipt(params,orderNumber, token);
    }

    //解绑第三方账号
    @RequestMapping("/unbindThirdPartyAccount")
    public APIResponseModel unbindThirdPartyAccount(APIRequestParams params){
        return accountService.unbindThirdPartyAccount(params);
    }


}
