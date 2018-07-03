package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.entity.OrderRefund;

public interface OrderRefundMapper {

    /* ===================接口开始==================== */
    //插入退款记录
    int insertRefundDAO(OrderRefund orderRefund);

    /* ===================接口结束==================== */


    /* ===================JSP页面接口开始==================== */
    //查找当前退款单数量
    int getCurrentRefundOrderNumberDAO(String accountShopToken);



    /* ===================JSP页面接口结束==================== */
}
