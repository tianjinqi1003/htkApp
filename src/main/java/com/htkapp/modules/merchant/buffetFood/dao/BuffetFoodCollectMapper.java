package com.htkapp.modules.merchant.buffetFood.dao;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodCollect;

public interface BuffetFoodCollectMapper {

    //添加收藏商品
    int insertCollectDAO(BuffetFoodCollect collect);
    //查找用户收藏商品集合
    BuffetFoodCollect getCollectObjByIdDAO(int productId, String token);
    //删除收藏状态
    int deleteCollectByIdDAO(int productId, String token);
}
