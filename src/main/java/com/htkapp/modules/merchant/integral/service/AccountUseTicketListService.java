package com.htkapp.modules.merchant.integral.service;

import java.util.List;

import com.htkapp.modules.merchant.integral.entity.AccountUseTicketList;

public interface AccountUseTicketListService {
	//通过用户token以及店铺id查询用户名下的优惠券（可使用的优惠券）
	List<AccountUseTicketList> getListByTokenAndShopId(String token, int shopId);
	//通过用户token以及店铺id和优惠券创建时间来查询优惠券
	AccountUseTicketList getTicketListByTokenAndShopId(String token,String shopId);
	//通过用户token以及店铺id和优惠券创建时间来删除对应优惠券
	int delTicketListByTokenAndShopIdAndTime(String token,int shopId,String dataTime);
	//通过用户token以及店铺id和优惠券创建时间来修改对应优惠券的数量
	int updataTicketListByTokenAndShopId(String token,String dataId,int quantity);
}
