package com.htkapp.modules.API.service;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;

public interface MerchantAppService {

	APIResponseModel getNewOrderList(Integer shopId, String startDate, String endDate, Integer statusCode);

	APIResponseModel getFinishedOrderList(Integer shopId, String startDate, String endDate, Integer statusCode);

	/**
	 * 确认已完成订单
	 * @param orderNumber
	 * @return
	 */
	AjaxResponseModel confirmFinishedOrder(String orderNumber);

}
