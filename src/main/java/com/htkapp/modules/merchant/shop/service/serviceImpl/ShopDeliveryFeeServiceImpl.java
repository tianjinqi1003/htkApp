package com.htkapp.modules.merchant.shop.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.ShopDeliveryFeeMapper;
import com.htkapp.modules.merchant.shop.entity.ShopDeliveryFee;
import com.htkapp.modules.merchant.shop.service.ShopDeliveryFeeService;

@Service
public class ShopDeliveryFeeServiceImpl implements ShopDeliveryFeeService {
	
	@Resource
	private ShopDeliveryFeeMapper shopDeliveryFeeDao;

	@Override
	public List<ShopDeliveryFee> getDataListByShopId(Integer shopId) {
		// TODO Auto-generated method stub
		
		return shopDeliveryFeeDao.getDataListByShopId(shopId);
	}

	@Override
	public void updateDeliverFee(ShopDeliveryFee shopDeliveryFee) {
		// TODO Auto-generated method stub
		int row=0;
		if(shopDeliveryFee.getId()==null) {
			row = shopDeliveryFeeDao.insertDeliverFeeDAO(shopDeliveryFee);
		}
		else {
			row = shopDeliveryFeeDao.updateDeliverFeeDAO(shopDeliveryFee);
		}
		
		if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
	}

}
