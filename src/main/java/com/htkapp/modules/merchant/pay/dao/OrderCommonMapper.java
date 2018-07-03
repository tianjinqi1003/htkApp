package com.htkapp.modules.merchant.pay.dao;

import com.htkapp.modules.merchant.pay.dto.AllOrderList;
import com.htkapp.modules.merchant.pay.entity.OrderCommon;

import java.util.List;

public interface OrderCommonMapper {

    //联合查询外卖订单、团购订单、订座订单、自助点餐订单数量
//    List<OrderCommon> getAllOrderListCountDAO(String accountToken, String orderBy);

    //联合查询外卖订单、团购订单、订座订单、自助点餐订单
    List<OrderCommon> getAllOrderListDAO(String accountToken,int pageNumber, int pageLimit);
}
