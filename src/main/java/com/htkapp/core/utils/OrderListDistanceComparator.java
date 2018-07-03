package com.htkapp.core.utils;

import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.shop.entity.Shop;

import java.util.Comparator;

/**
 * Created by yinqilei on 17-6-30.
 * 订单列表排序(最新的订单在最前面)
 */
public class OrderListDistanceComparator implements Comparator<OrderRecord> {

    @Override
    public int compare(OrderRecord c1, OrderRecord c2)
    {
        return c1.getId() < c2.getId() ? 0 : -1;
    }

}
