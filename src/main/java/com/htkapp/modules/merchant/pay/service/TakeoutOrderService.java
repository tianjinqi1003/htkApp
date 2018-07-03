package com.htkapp.modules.merchant.pay.service;

public interface TakeoutOrderService {

    /* =========================接口开始=============================== */
    //更改催单状态
    void updateReminderStateByOrderId(int orderId, int stateId) throws Exception;
    //插入数据
    void insertReminderStateByOrderId(int orderId) throws Exception;

    /* ========================接口结束=============================== */
}
