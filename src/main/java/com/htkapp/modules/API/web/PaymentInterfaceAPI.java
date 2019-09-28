package com.htkapp.modules.API.web;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.shiro.common.utils.LoggerUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.service.PaymentInterfaceService;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinqilei on 17-6-29.
 * app支付接口
 */

@RestController
@RequestMapping("/API/paymentInterfaceAPI")
public class PaymentInterfaceAPI {

    @Resource
    private PaymentInterfaceService paymentInterfaceService;

    //调起支付
    @RequestMapping("/callUpPaymentInterface")
    public APIResponseModel callUpPaymentInterface(APIRequestParams params, OrderRecord orderRecord) {
    	LoggerUtils.fmtDebug(getClass(), "调起支付宝支付.........");
        return paymentInterfaceService.callUpPaymentInterfaceHTK(params, orderRecord);
    }

    //调起退款
    @RequestMapping("/callUpRefundInterface")
    public APIResponseModel callUpRefundInterface(APIRequestParams params, String orderNumber) {
        return paymentInterfaceService.callUpRefundInterfaceHTK(params, orderNumber);
    }

    //自助点餐确认订单　写入订单和记录到数据库中
    @RequestMapping("/enterBuffetFoodSuccessfullyTransferred")
    public APIResponseModel enterBuffetFoodSuccessfullyTransferred(BuffetFoodOrder order) {
        return paymentInterfaceService.enterBuffetFoodSuccessfullyTransferred(order);
    }

    //接微信服务端异步通知
    @RequestMapping("/weChatPayNotifyInterface")
    public String weChatPayNotifyUrl() {
        return paymentInterfaceService.weChatPayNotifyUrl();
    }

    //接收支付宝服务端异步通知
    @RequestMapping("/aliPayNotifyInterface")
    @ResponseBody
    public String aliPayNotifyUrl(HttpServletRequest request) {
    	LoggerUtils.fmtDebug(getClass(), "支付宝支付回调.........");
        return paymentInterfaceService.aliPayNotifyUrl(request);
    }

}
