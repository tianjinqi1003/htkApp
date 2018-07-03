package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.entity.AccountShopCashMention;

public interface AccountShopCashMentionMapper {

    /* =====================接口开始========================= */
    //插入转账记录
    int insertTransferDAO(AccountShopCashMention cashMention);
    //根据交易订单号查看外卖订单和团购订单是否已提现
    AccountShopCashMention validationStatusDAO(String orderNumber, String encryptToken);
    //根据交易订单号查看自助订单是否已提现
    AccountShopCashMention validationStatusByBuffetFoodDAO(String orderNumber, String encryptToken);
    /* =====================接口结束========================= */
}
