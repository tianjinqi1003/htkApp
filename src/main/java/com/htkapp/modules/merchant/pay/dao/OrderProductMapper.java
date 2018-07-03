package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.entity.OrderProduct;

import java.util.List;

/**
 * Created by yinqilei on 17-6-29.
 *
 */
public interface OrderProductMapper {

    /* ================接口开始==================== */
    //插入关联订单的产品详情
    int insertProductInfoByOrderDAO(OrderProduct orderProduct);
    //通过订单id获得订单中的产品信息
    List<OrderProduct> getProductListByOrderIdDAO(int orderId);
    //通过订单id删除记录
    int deleteOrderProductByOrderIdDAO(int orderId);
    /* ====================接口结束======================= */



    /* ====================JSP接口开始======================= */
    //通过订单id获取订单中所购买的商品名
    List<String> getOrderProductNameByOrderIdDAO(long orderId);

    /* ====================JSP接口结束======================= */
}
