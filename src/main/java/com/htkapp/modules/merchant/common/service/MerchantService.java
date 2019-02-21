package com.htkapp.modules.merchant.common.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.RequestParams;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.AccountShopReplyComments;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinqilei on 17-7-13.
 */
public interface MerchantService {

    /* ==============接口开始================ */
    //异步验证商户账号是否存在
    AjaxResponseModel verifyMerchant(String phone);
    //异步验证用户输入手机号和验证码是否正确
    AjaxResponseModel verifyMobilePhoneAndCode(String phone, String code);
    //商户注册提交
    AjaxResponseModel submitRegisterBYyPost(AccountShop accountShop, RequestParams params);
    //找回密码确认身份成功后，更改密码接口
    AjaxResponseModel changePassword(String phone, String password, String code);
    //改变店铺状态接口
    AjaxResponseModel changeShopState(HttpServletRequest request, int stateId /*,int userId*/);
    //改变通知消息状态
    AjaxResponseModel changeNotificationMessageStatus(Integer id, Integer statusCode);
    /* ==============接口结束================ */


    /* ===========================JSP页面接口开始=============================== */
    //商户首页
    void getHomePage(RequestParams params);
    //外卖下历史订单查询
    void getOrderListByAIdAndMark(int mark, Model model, int pageNo, String startDate, String endDate);
    //外卖下实时订单查询
    void getTakeoutRealTimeOrderByCondition(Model model, int shopId, String startDate, String endDate, int statusCode, double longitude, double latitude);
    //查找商户通知中心消息
    void getNotifyDataByToken(Model model, String token, int pageNo, int status);
    //外卖评论页面列表
    void getTakeoutCommentListDataByToken(Model model, String accountShopToken, String starRating, String startTime, String endTime, int pageNum);
    //团购评论页面列表
    void getGroupBuyCommentListDataByToken(Model model, String accountShopToken, String starRating, String startTime, String endTime, int pageNum);
    //自助点餐评论页面列表
    void getBuffetFoodCommentListDataByToken(Model model, String accountShopToken, String starRating, String startTime, String endTime, int pageNum);
    //插入商户回复用户评论接口
    AjaxResponseModel replyMessageGiveUser(AccountShopReplyComments replyComments);
    //账单资金条件查找
    void getBillMoneyDataByCondition(Model model, String date, Integer type, Integer pageNum);
    //账单记录条件查找
    void getBillRecordListByCondition(Model model, String date, Integer type, Integer pageNum);
    //帐单记录详情条件查找
    void getBillRecordDetailByCondition(Model model, String date, Integer pageNum);
    //自助点餐订单查询未结算订单
    void getBuffetFoodOrderQueryResult(Model model, Integer pageNum);
    //自助点餐订单查询已结算订单
    void getBuffetFoodOrderDoneResult(Model model, String startTime, String endTime, Integer pageNum);
    //积分列表
    void integralListDateAndByCondition(Model model, Integer pageNum, String userPhone);
    //团购商品页面
    void groupBuyProductIndex(RequestParams params);
    //自助点餐新订单页面
    void buffetFoodNewOrder(RequestParams params);
    //门店信息
    void storeHomePage(RequestParams params);
    //获取门店座位信息
    void getSeatInfo(RequestParams params);
    //管理门店座位信息
    void manageSeatInfo(RequestParams params);
    //自助点餐订单处理(订单调整)
    void buffetFoodOrderEdit(RequestParams params);
    //自助点餐订单处理(催单)
    void buffetFoodOrderReminder(RequestParams params);
    //自助点餐订座处理
    void getSeatOrder(RequestParams params,String startTime,String endTime,Integer pageNum);
   
    
    /* ===========================JSP页面接口结束============================== */
}
