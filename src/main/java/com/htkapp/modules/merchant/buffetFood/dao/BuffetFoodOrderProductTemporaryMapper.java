package com.htkapp.modules.merchant.buffetFood.dao;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProductTemporary;

import java.util.List;

public interface BuffetFoodOrderProductTemporaryMapper {

    /* ========================接口开始========================= */
    //插入数据
    int insertProductTemporaryDAO(BuffetFoodOrderProductTemporary temporary);
    //通过订单id查找数据
    List<BuffetFoodOrderProduct> getProductListByIdDAO(int orderId);
    //通过订单id删除数据
    int deleteProductListByIdDAO(int orderId);

    /* ========================接口结束========================= */
}
