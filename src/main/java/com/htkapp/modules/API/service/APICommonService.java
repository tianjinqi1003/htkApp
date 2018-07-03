package com.htkapp.modules.API.service;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.params.ServiceParams;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;

public interface APICommonService {

    //外卖支付流程
    void takeoutPaymentInterface(OrderRecord orderRecord) throws Exception;
    //团购支付流程
    void groupBuyPaymentInterface(OrderRecord orderRecord, String token) throws Exception;
    //取消订单的修改账单记录
    void updateBillData(ServiceParams serviceParams) throws Exception;
    //支付成功修改订单状态,记录账单
    void updateOrderByPayment(ServiceParams serviceParams);
    //支付成功订单的插入账单记录
    void insertBillData(ServiceParams serviceParams);
    //app自助点餐下单
    APIResponseModel insertBuffetFoodInitOrder(BuffetFoodOrder order) throws Exception;
}
