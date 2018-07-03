package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.ShopSuggest;

public interface ShopSuggestMapper {

    //插入用户建议内容
    int insertSuggestContentByShopIdDAO(ShopSuggest suggest);
}
