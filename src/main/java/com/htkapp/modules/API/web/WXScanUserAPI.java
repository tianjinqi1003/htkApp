package com.htkapp.modules.API.web;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLInitializationException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.service.WXScanUserService;

@Controller
@RequestMapping("/API/wxScanUserAPI")
public class WXScanUserAPI {
	
	@Resource
	private WXScanUserService wxScanUserService;
	
	@RequestMapping(value="/jscode2session")
	public JSONObject jscode2session(String appID, String secret, String jsCode) {
		
		JSONObject jo=null;
		try {
			BasicHttpClientConnectionManager connManager;
			connManager = new BasicHttpClientConnectionManager(
			        RegistryBuilder.<ConnectionSocketFactory>create()
			                .register("http", PlainConnectionSocketFactory.getSocketFactory())
			                .register("https", SSLConnectionSocketFactory.getSocketFactory())
			                .build(),
			        null,
			        null,
			        null
			);
			HttpClient httpClient = HttpClientBuilder.create()
			        .setConnectionManager(connManager)
			        .build();

			System.out.println("appID====="+appID);
			System.out.println("secret====="+secret);
			System.out.println("jsCode====="+jsCode);
			HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/sns/jscode2session?appid="+appID+"&secret="+secret+"&js_code="+jsCode+"&grant_type=authorization_code");

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(6000).build();
			httpPost.setConfig(requestConfig);

			String data="";
			StringEntity postEntity = new StringEntity(data, "UTF-8");
			httpPost.addHeader("Content-Type", "text/xml");
			httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 ");  // TODO: 很重要，用来检测 sdk 的使用情况，要不要加上商户信息？
			httpPost.setEntity(postEntity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			//System.out.println("==========="+EntityUtils.toString(httpEntity, "UTF-8"));
			String joStr = EntityUtils.toString(httpEntity, "UTF-8");
			System.out.println("joStr====="+joStr);
			jo = JSONObject.parseObject(joStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo;
	}
	
	@RequestMapping(value="/login")
	@ResponseBody
	public APIResponseModel login(String appID, String secret, String code) {
		
		/*
		appID="wxabae78128214e23b";
		secret="f39c986468559a017529597a74da9ad5";
		code="061SN23W1O42jZ0wOf0W1mnY2W1SN239";
		*/
		JSONObject jo = jscode2session(appID,secret,code);
		if("40013".equals(jo.getString("errcode"))) {
			System.out.println("errcode====="+jo.getString("errmsg"));
			return new APIResponseModel<String>(Globals.API_FAIL, jo.getString("errmsg"));
		}
		else if("40029".equals(jo.getString("errcode"))) {
			System.out.println("errcode====="+jo.getString("errmsg"));
			return new APIResponseModel<String>(Globals.API_FAIL, jo.getString("errmsg"));
		}
		else if("40163".equals(jo.getString("errcode"))) {
			System.out.println("errcode====="+jo.getString("errmsg"));
			return new APIResponseModel<String>(Globals.API_FAIL, jo.getString("errmsg"));
		}
		else {
			String openid = jo.getString("openid");
			System.out.println("openid====="+openid);
			return wxScanUserService.login(openid);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("{'session_key':'CveRfVRdx8t7CbtwT9tNdQ==','openid':'ozw7j5Bm6sw5OiFHLk2fHB8uyTnU'}".replaceAll("'", "\\\'"));
	}

}
