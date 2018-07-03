package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.ShopSaverTicketRecord;

public interface ShopSaverTicketRecordMapper {

    //插入用户兑换优惠券记录
    int insertAccountExchangeRecordDAO(ShopSaverTicketRecord ticketRecord);
    //更新用户已存在的优惠券兑换记录
    int updateAccountExchangeRecordDao(Integer ticketId, String token, int shopId,int ticketQuantity);
    //通过用户token、优惠券id以及店铺id查询唯一的优惠券记录
    ShopSaverTicketRecord getShopSaverTicketRecordByTokenAndShopIdAndTicketId(Integer ticketId, String token, int shopId);
}
