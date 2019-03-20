package com.htkapp.modules.merchant.shop.service;

import java.util.List;

import com.htkapp.modules.merchant.shop.entity.ShopDeliveryFee;

public interface ShopDeliveryFeeService {

	/**
	 * 根据店铺Id查询配送费
	 * @param shopId
	 * @return
	 */
	List<ShopDeliveryFee> getDataListByShopId(Integer shopId);

	void updateDeliverFee(ShopDeliveryFee shopDeliveryFee);

}
