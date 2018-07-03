package com.htkapp.modules.merchant.buffetFood.service;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProductTemporary;

import java.util.List;

public interface BuffetFoodOrderProductTemporaryService {

    /* =======================接口开始========================== */
    //插入数据
    void insertProductTemporary(BuffetFoodOrderProductTemporary temporary);
    //通过订单id查找数据
    List<BuffetFoodOrderProduct> getProductListById(int orderId);
    //通过订单id删除数据
    void deleteProductListById(int orderId);
    /* =======================接口结束========================== */
}
