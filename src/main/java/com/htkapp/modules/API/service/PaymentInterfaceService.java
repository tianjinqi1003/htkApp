package com.htkapp.modules.API.service;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by yinqilei on 17-6-29.
 *
 */
public interface PaymentInterfaceService {

    /* ===================接口开始================== */
    //调起支付接口
    APIResponseModel callUpPaymentInterfaceHTK(APIRequestParams params, OrderRecord orderRecord);
    //调起退款接口
    APIResponseModel callUpRefundInterfaceHTK(APIRequestParams params, String orderNumber);
    //自助点餐下单接口（未支付）
    APIResponseModel enterBuffetFoodSuccessfullyTransferred(BuffetFoodOrder order);
    //接微信服务端异步通知
    String weChatPayNotifyUrl();
    //接微信服务端异步通知
    String aliPayNotifyUrl(HttpServletRequest request);
    /* ===================接口结束================== */
}
