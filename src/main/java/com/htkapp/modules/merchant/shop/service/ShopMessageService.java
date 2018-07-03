package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.ShopMessage;

/**
 * Created by terabithia on 11/30/17.
 */
public interface ShopMessageService {

    //更改配送费
    void updateDeliverFee(ShopMessage shopMessage);
}
