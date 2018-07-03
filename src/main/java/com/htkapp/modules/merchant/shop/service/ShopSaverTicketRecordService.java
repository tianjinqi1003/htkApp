package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.integral.entity.AccountTicketList;
import com.htkapp.modules.merchant.shop.entity.ShopSaverTicketRecord;

public interface ShopSaverTicketRecordService {

    //插入用户兑换优惠券记录
    void insertAccountExchangeRecord(ShopSaverTicketRecord ticketRecord);
    
    //更新用户兑换优惠券记录
    int updateAccountExchangeRecordDao(Integer ticketId, String token, int shopId,int ticketQuantity);
    //通过优惠券id、店铺id、用户token查询对应的优惠券
    ShopSaverTicketRecord getTicketByTokenAndShopIdAndTicketId(Integer ticketId,String token, int shopId);
}
