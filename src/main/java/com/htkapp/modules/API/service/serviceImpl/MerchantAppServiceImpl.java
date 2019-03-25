package com.htkapp.modules.API.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.service.MerchantAppService;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.OrderProductService;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;

@Service
public class MerchantAppServiceImpl implements MerchantAppService {

	@Resource
	private OrderRecordService orderRecordService;
	@Resource
	private OrderProductService orderProductService;

	@Override
	public APIResponseModel getNewOrderList(Integer shopId, String startDate, String endDate, Integer statusCode) {
		// TODO Auto-generated method stub
		
		try {
			List<OrderRecord> resultList = orderRecordService.getTakeoutRealTimeOrderByCondition(shopId, startDate, endDate, statusCode);
			if (resultList != null) {
				for (OrderRecord each : resultList) {
					List<OrderProduct> productList = orderProductService.getProductListByOrderId(each.getId());
					each.setProductLists(productList);
				}
				return new APIResponseModel<List<OrderRecord>>(Globals.API_SUCCESS, "查询新订单成功", resultList);
			}
			else {
				return new APIResponseModel<List<OrderRecord>>(Globals.API_FAIL, "查询新订单失败", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new APIResponseModel<List<OrderRecord>>(Globals.API_FAIL, "查询新订单失败", null);
		}
	}

}
