package com.htkapp.modules.merchant.shop.dao;

import java.util.List;

import com.htkapp.modules.merchant.shop.entity.ShopDeliveryFee;

public interface ShopDeliveryFeeMapper {

	List<ShopDeliveryFee> getDataListByShopId(Integer shopId);

	int insertDeliverFeeDAO(ShopDeliveryFee shopDeliveryFee);

	int updateDeliverFeeDAO(ShopDeliveryFee shopDeliveryFee);

}
