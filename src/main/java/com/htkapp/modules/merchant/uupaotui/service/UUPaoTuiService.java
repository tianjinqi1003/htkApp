package com.htkapp.modules.merchant.uupaotui.service;

import org.json.JSONObject;

public interface UUPaoTuiService {
	
	/**
	 * 计算订单价格
	 * @param originID
	 * @param toAddress
	 * @return
	 */
	public JSONObject getOrderPrice(String originID, String toAddress);

	/**
	 * 发布订单
	 * @param priceToken
	 * @param orderPrice
	 * @param balancePaymoney
	 * @param receiverPhone
	 * @param pubUserMobile 
	 * @return
	 */
	public JSONObject addOrder(String priceToken, String orderPrice, String balancePaymoney, String receiverPhone, String pubUserMobile); 

}
