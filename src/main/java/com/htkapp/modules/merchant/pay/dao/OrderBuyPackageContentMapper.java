package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.entity.OrderBuyPackageContent;

import java.util.List;

public interface OrderBuyPackageContentMapper {

    //插入团购订单套餐内商品信息
    int insertOrderBuyPackageContentByGroupBuyOrderDAO(OrderBuyPackageContent orderBuyPackageContent);

    //通过订单id查找团购套餐内产品
    List<OrderBuyPackageContent> getOrderBuyPackageContentListDAO(int orderId);
}
