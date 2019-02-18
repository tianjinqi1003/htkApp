package com.htkapp.modules.merchant.uupaotui.service.serviceImpl;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.htkapp.modules.merchant.uupaotui.service.UUPaoTuiService;
import com.uupaotui.openapi.ApiConfig;
import com.uupaotui.openapi.Dictionary;
import com.uupaotui.openapi.Response;
import com.uupaotui.openapi.UUCommonFun;
import com.uupaotui.openapi.UUHttpRequestHelper;

@Service
public class UUPaoTuiServiceImpl implements UUPaoTuiService {

	@Override
	public JSONObject getOrderPrice(String originID, String toAddress) {
		// TODO Auto-generated method stub
		
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("origin_id", originID);
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		//mydic.Add("from_address", "青岛市北区连云港路33号万达商务楼B座821");
		mydic.Add("from_address", "青岛市黄岛新区双珠路288号东方金石大厦807");
		//mydic.Add("from_usernote", "万达商务楼B座821");
		//mydic.Add("to_address", "中原路与嵩山路交叉处绿城广场");
		mydic.Add("to_address", toAddress);
		//mydic.Add("to_usernote", "北门");
		//mydic.Add("to_usernote", "万达商务楼A座821");
		mydic.Add("city_name", "青岛市");
		mydic.Add("county_name", "市北区");
		//mydic.Add("from_lng", "113.71742");
		mydic.Add("from_lng", "0");
		//mydic.Add("from_lat", "34.767995");
		mydic.Add("from_lat", "0");
		//mydic.Add("to_lng", "113.638827");
		mydic.Add("to_lng", "0");
		//mydic.Add("to_lat", "34.753592");
		mydic.Add("to_lat", "0");
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.GetOrderPriceUrl, mydic);
		Response.Write(result);
		return new JSONObject(result);
	}

	@Override
	public JSONObject addOrder(String priceToken, String orderPrice, String balancePaymoney, String receiverPhone, String pubUserMobile) {
		// TODO Auto-generated method stub
		
		Dictionary<String, String> mydic = new Dictionary<String, String>();
		mydic.Add("appid", ApiConfig.AppID);
		mydic.Add("nonce_str", UUCommonFun.NewGuid());
		mydic.Add("timestamp", UUCommonFun.getTimeStamp());
		mydic.Add("openid", ApiConfig.OpenID);
		//mydic.Add("price_token", "71ad18b12fd84eb985fe117b1497e7cc");
		mydic.Add("price_token", priceToken);
		//mydic.Add("order_price", "25.40");
		mydic.Add("order_price", orderPrice);//total_money
		//mydic.Add("balance_paymoney", "15.40");
		mydic.Add("balance_paymoney", balancePaymoney);//need_paymoney
		mydic.Add("receiver", "张三");
		mydic.Add("receiver_phone", receiverPhone);
		mydic.Add("note", "请尽快取件");
		mydic.Add("callback_url", "http://www.uupt.com");
		mydic.Add("push_type", "2");
		mydic.Add("special_type", "0");
		mydic.Add("callme_withtake", "1");
		mydic.Add("pubUserMobile", pubUserMobile);
		mydic.Add("sign", UUCommonFun.CreateMd5Sign(mydic, ApiConfig.AppSecret));
		String result = UUHttpRequestHelper.HttpPost(ApiConfig.AddOrderUrl, mydic);
		System.out.println(result);
		return new JSONObject(result);
	}
	
}
