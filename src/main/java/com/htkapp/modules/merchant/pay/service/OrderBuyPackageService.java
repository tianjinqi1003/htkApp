package com.htkapp.modules.merchant.pay.service;

import com.htkapp.core.exception.order.OrderException;
import com.htkapp.modules.merchant.pay.entity.OrderBuyPackage;

/**
 * Created by yinqilei on 17-7-1.
 */
public interface OrderBuyPackageService {

    /* ===================接口开始===================== */
    //通过订单id查询购买过的套餐信息
    OrderBuyPackage getOrderPackageById(int orderId) throws Exception;
    //插入团购订单的团购产品信息
    void insertBuyPackageDataByOrderId(OrderBuyPackage orderBuyPackage) throws Exception;
    //通过订单id删除记录
    void deleteOrderBuyPackageByOrderId(int orderId);
    /* ===================接口开始===================== */
}
