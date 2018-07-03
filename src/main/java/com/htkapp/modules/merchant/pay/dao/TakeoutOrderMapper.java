package com.htkapp.modules.merchant.pay.dao;

//外卖订单
public interface TakeoutOrderMapper {

    /* =========================接口开始=============================== */
    //更改催单状态
    int updateReminderStateByOrderIdDAO(int orderId, int stateId);
    //插入数据
    int insertReminderStateByOrderIdDAO(int orderId);

    /* ========================接口结束=============================== */
}
