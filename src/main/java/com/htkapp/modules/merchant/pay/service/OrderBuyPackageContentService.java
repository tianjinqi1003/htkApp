package com.htkapp.modules.merchant.pay.service;

import com.htkapp.modules.merchant.pay.entity.OrderBuyPackageContent;

import java.util.List;

public interface OrderBuyPackageContentService {

    //插入团购订单套餐内商品信息
    void insertOrderBuyPackageContentByGroupBuyOrder(OrderBuyPackageContent orderBuyPackageContent);
    //通过订单id查找团购套餐内产品
    List<OrderBuyPackageContent> getOrderBuyPackageContentList(int orderId);

}
