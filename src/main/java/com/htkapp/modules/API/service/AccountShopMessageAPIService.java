package com.htkapp.modules.API.service;

import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;

/**
 * Created by yinqilei on 17-7-5.
 */
public interface AccountShopMessageAPIService {

    /* ===============接口开始======================= */
    //商户app端登陆接口
    APIResponseModel appAccountShopLoginByUserName(String userName, String password, String role);
    //忘记密码 通过手机短信找回密码
    APIResponseModel forgetPasswordBySMS(String phone, String code, String password);
    //商户app端个人信息
    APIResponseModel getAppAccountShopData(String token);
    //改变商铺状态　
    APIResponseModel changeShopStateByToken(String token,Integer shopStateId);
    //商户已处理的外卖订单列表
    APIResponseModel getTakeoutOrderListByAccountShopToken(String token, Integer mark, int pageNumber);
    //商户未处理的外卖订单列表
    APIResponseModel getUntreatedTakeoutOrderList(String token, int pageNumber);
    //商户处理外卖订单(接单、拒单)
    APIResponseModel handlesTakeoutOrders(String token, String orderNumber, Integer stateId, String content);
    //商家app确认收货
    APIResponseModel confirmReceipt(String orderNumber, String token);
    //商家取消订单
    APIResponseModel cancelOrder(String orderNumber, String token);
    //配送按钮
    APIResponseModel deliveryOrder(APIRequestParams params);
     /* ===============接口结束======================= */
}
