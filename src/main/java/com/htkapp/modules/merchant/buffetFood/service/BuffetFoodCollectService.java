package com.htkapp.modules.merchant.buffetFood.service;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodCollect;

public interface BuffetFoodCollectService {

    //添加收藏商品
    void insertCollect(BuffetFoodCollect collect);
    //查找用户收藏商品集合
    int getCollectObjById(int productId, String token);
    //删除收藏状态
    void deleteCollectById(int productId, String token);
}
