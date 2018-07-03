package com.htkapp.modules.API.service;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.merchant.integral.entity.SeatOrder;
import com.htkapp.modules.merchant.shop.entity.ShopSuggest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by yinqilei on 17-7-17.
 * 会员尊享平台api逻辑Service
 */
public interface IntegralAPIService {


    /* =============接口开始================= */
    //首页活动列表接口
    APIResponseModel getMemberHomeListData(APIRequestParams params);
    //会员平台－我的信息
    APIResponseModel getMemberAccountMes(APIRequestParams params);
    //我的－消费券列表
    APIResponseModel getAccountCouponList(APIRequestParams params);
    //我的－交易记录
    APIResponseModel getAccountTradingRecord(APIRequestParams params);
    //我的－我的预约
    APIResponseModel getAccountReserve(APIRequestParams params);
    //我的－积分兑换
    APIResponseModel getIntegralBuyData(APIRequestParams params);
    //加入会员页面二维码
    APIResponseModel getQrImgData(APIRequestParams params);
    //加入按钮操作请求
    APIResponseModel addMember(APIRequestParams params);
    //预定座位请求
    APIResponseModel addReserveRequest(APIRequestParams params, SeatOrder order);
    //平台－关于
    APIResponseModel getShopIntroduce(APIRequestParams params);
    //我的－积分记录
    APIResponseModel getAccountIntegralRecord(APIRequestParams params);
    //我的－积分兑换操作
    APIResponseModel redeemOperation(APIRequestParams params);
    //平台－建议
    APIResponseModel accountSuggestRequest(APIRequestParams params, ShopSuggest suggest);
    //资讯详情页
    void articleDetailPage(Integer aId, Model model);
    /* =============接口结束================= */
}
