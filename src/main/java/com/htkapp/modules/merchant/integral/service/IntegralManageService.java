package com.htkapp.modules.merchant.integral.service;

import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.modules.merchant.integral.entity.AccountSaverTicket;
import com.htkapp.modules.merchant.integral.entity.ShopArticleInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IntegralManageService {

    /* ======================接口开始============================ */
    //获取用户积分列表数据
    TableResponseModel getAccountIntegralListJsonDataById(HttpServletRequest request);
    /* ======================接口结束============================ */


    /* ====================JSP页面接口开始=========================== */
    //赠送或抵扣积分接口
    AjaxResponseModel presentOrDeductionIntegral(AjaxRequestParams params);
    //创建资讯
    AjaxResponseModel createNewActive(ShopArticleInfo articleInfo);
    //创建资讯图片上传
    AjaxResponseModel uploadMsgImg(MultipartFile file);
    //创建兑换活动
    AjaxResponseModel createExchangeActivity(AccountSaverTicket saverTicket, String startTime, String endTime);
    //未开始活动
    void getExitsActiveNoStartPage(RequestParams params);
    //进行中活动
    void getExitsActiveProcessPage(RequestParams params);
    //已停止活动
    void getExitsActiveStopPage(RequestParams params);
    //资讯
    void getExitsActiveMessagePage(RequestParams params);
    //作废活动接口
    AjaxResponseModel obsoleteActivity(AjaxRequestParams params);
    //资讯关闭显示接口
    AjaxResponseModel closeTheDisplay(AjaxRequestParams params);
    //开启活动
    AjaxResponseModel openActivity(AjaxRequestParams params);
    //修改资讯
    AjaxResponseModel updateMes(ShopArticleInfo shopArticleInfo);
    //查询座位订单号
    AjaxResponseModel getSeatInfo();
    //订座订单操作(安排座位)
    AjaxResponseModel updataSeatInfo(String seatName,String orderNumber);
    //查询座位订单中没有被处理的
    AjaxResponseModel getSeatInfoByStatus();
    //订座订单撤销操作
    AjaxResponseModel deleteSeatOrder(String selectedIds);
    /* ====================JSP页面接口结束========================== */

	String uploadNewsContentImg(MultipartFile file);

    AjaxResponseModel uploadMsgTitleImg(MultipartFile file);
}
