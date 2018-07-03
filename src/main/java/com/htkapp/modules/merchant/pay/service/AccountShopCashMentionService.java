package com.htkapp.modules.merchant.pay.service;

import com.htkapp.modules.merchant.pay.entity.AccountShopCashMention;

public interface AccountShopCashMentionService {

    /* =====================接口开始========================= */
    //插入转账记录
    void insertTransfer(AccountShopCashMention cashMention) throws Exception;
    //根据交易订单号查看订单是否已提现
    boolean validationStatus(String orderNumber, String encryptToken, int cMark) throws Exception;
    /* =====================接口结束========================= */
}
