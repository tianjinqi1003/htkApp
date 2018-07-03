package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.ShopMessage;

/**
 * Created by terabithia on 11/30/17.
 */
public interface ShopMessageMapper {

    //更改配送费
    int updateDeliverFeeDAO(ShopMessage shopMessage);

    int initShopMessage(int shopId);
}
