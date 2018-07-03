package com.htkapp.modules.merchant.integral.dao;

import java.util.List;

import com.htkapp.modules.merchant.integral.entity.AccountUseTicketList;

public interface AccountUseTicketListMapper {
	//通过用户token以及店铺id查询优惠券
	List<AccountUseTicketList> getTicketListByTokenAndShopIdDAO(String token, int shopId);
	//通过用户token以及店铺id和优惠券创建时间来查询优惠券
	AccountUseTicketList getTicketListByTokenAndShopId(String token,String dataId);
	//通过用户token以及店铺id和优惠券创建时间来删除对应优惠券
	int delTicketListByTokenAndShopId(String token,int shopId);
	//通过用户token以及店铺id和优惠券创建时间来修改对应优惠券的数量
	int updataTicketListByTokenAndShopIdAndTime(String token,String dataId,int quantity);
}
