package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.entity.OrderBuyPackage;

/**
 * Created by yinqilei on 17-7-1.
 * 团购订单关联的团购产品购买信息
 */

public interface OrderBuyPackageMapper {

    /* ===================接口开始==================== */
    //通过订单id查询购买过的套餐信息
    OrderBuyPackage getOrderPackageByIdDAO(int orderId);
    //插入团购订单的团购产品信息
    int insertBuyPackageDataByOrderIdDAO(OrderBuyPackage orderBuyPackage);
    //通过订单id删除记录
    int deleteOrderBuyPackageByOrderIdDAO(int orderId);
    /* ===================接口结束==================== */
}
