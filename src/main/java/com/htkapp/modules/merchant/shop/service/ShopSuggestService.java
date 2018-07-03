package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.ShopSuggest;

public interface ShopSuggestService {

    //插入用户建议内容
    void insertSuggestContentByShopId(ShopSuggest suggest);
}
