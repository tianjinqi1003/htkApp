package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.entity.OrderLog;

/**
 * Created by yinqilei on 17-6-29.
 */

public interface OrderLogMapper {

    /* ==================接口开始=================== */
    //插入订单日志
    int insertOrderLogDAO(OrderLog orderLog);



    /* ==================接口结束=================== */
}
