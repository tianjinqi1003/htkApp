package com.htkapp.modules.merchant.pay.service;

import com.htkapp.modules.merchant.pay.entity.OrderRefund;

public interface OrderRefundService {

    /* ===================接口开始==================== */
    //插入退款记录
    void insertRefund(OrderRefund orderRefund) throws Exception;

    /* ===================接口结束==================== */



    /* ===================JSP页面接口开始============================ */
    //查找当前退款单数量
    int getCurrentRefundOrderNumber(String accountShopToken) throws Exception;

    /* ====================JSP页面接口结束=============================== */
}
