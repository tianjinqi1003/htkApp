package com.htkapp.modules.merchant.pay.service;

import com.htkapp.core.exception.order.OrderException;
import com.htkapp.modules.merchant.pay.dao.OrderProductMapper;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;

import java.util.List;

/**
 * Created by yinqilei on 17-6-29.
 */
public interface OrderProductService {

    /* ===============接口开始====================== */
    //插入关联订单的产品详情
    void insertProductInfoByOrder(OrderProduct orderProduct) throws OrderException;
    //通过订单id获得订单中的产品信息
    List<OrderProduct> getProductListByOrderId(int orderId) throws Exception;
    //通过订单id删除记录
    void deleteOrderProductByOrderId(int orderId);
    /* ====================接口结束========================== */

    /* =========================JSP接口开始============================== */
    //通过订单id获取订单中所购买的商品名
    List<String> getOrderProductNameByOrderId(long orderId);
    /* =========================JSP接口结束============================= */
}
