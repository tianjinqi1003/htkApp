package com.htkapp.modules.merchant.pay.service;

import com.htkapp.core.exception.order.OrderException;
import com.htkapp.modules.merchant.pay.entity.OrderLog;

/**
 * Created by yinqilei on 17-6-29.
 */
public interface OrderLogService {

    /* ================接口开始====================== */
    //插入订单日志
    boolean insertOrderLog(OrderLog orderLog) throws OrderException;


    /* ================接口结束====================== */
}
