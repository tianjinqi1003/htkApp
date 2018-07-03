package com.htkapp.modules.merchant.pay.service;

import com.htkapp.modules.merchant.pay.dto.AllOrderList;
import com.htkapp.modules.merchant.pay.entity.OrderCommon;

import java.util.List;

public interface OrderCommonService {

    //联合查询外卖订单、团购订单、订座订单、自助点餐订单
    List<OrderCommon> getAllOrderList(String accountToken,int pageNumber, int pageLimit) throws Exception;
}
