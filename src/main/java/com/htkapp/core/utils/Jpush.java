package com.htkapp.core.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.htkapp.core.config.JpushConfig;

/**
 * Created by yinqilei on 17-6-30.
 */
public class Jpush {

    public static void jPushMethod(String token,String pushContent, String c){
        JPushClient jpushClient = new JPushClient(JpushConfig.MASTER_SECRET, JpushConfig.APP_KEY, null, ClientConfig.getInstance());
        PushPayload payload = null;
        if(c.equals("ALERT")){
            //通知
            payload = buildPushObject_android_alias_alertWithTitle(token.replaceAll("-",""), pushContent,"");
        }else {
            //消息
            payload = buildPushObject_android_alias_mesWithTitle(token.replaceAll("-",""), pushContent);
        }
        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("用户推送"+result);

        } catch (APIConnectionException e) {
            System.out.println("用户推送"+e);
        } catch (APIRequestException e) {
            System.out.println("用户推送"+e.getErrorCode());
            System.out.println("用户推送"+e);
            System.out.println("用户推送"+e.getErrorMessage());
            System.out.println("用户推送"+e.getStatus());
        }
    }

    public static void jPushMethodToMerchant(String token, String pushContent,String c, String title){
        JPushClient jpushClient = new JPushClient(JpushConfig.M_MASTER_SECRET, JpushConfig.M_APP_KEY, null, ClientConfig.getInstance());
        PushPayload payload = null;
        if(c.equals("ALERT")){
            //通知
            payload = buildPushObject_android_alias_alertWithTitle(token.replaceAll("-",""), pushContent, title);
        }else {
            //消息
            payload = buildPushObject_android_alias_mesWithTitle(token.replaceAll("-",""), pushContent);
        }
        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("商家推送"+result);
        } catch (APIConnectionException e) {
            System.out.println("商家推送"+e);

        } catch (APIRequestException e) {
        	e.printStackTrace();
            System.out.println("商家推送"+e.getErrorCode());
            System.out.println("商家推送"+e);
            System.out.println("商家推送"+e.getErrorMessage());
            System.out.println("商家推送"+e.getStatus());
        }
    }
    
    public static void jPushMethodToMerchantApp(String token, String pushContent,String c, String title){
    	JPushClient jpushClient = new JPushClient(JpushConfig.M_APP_MASTER_SECRET, JpushConfig.M_APP_APP_KEY, null, ClientConfig.getInstance());
    	PushPayload payload = null;
    	if(c.equals("ALERT")){
    		//通知
    		payload = buildPushObject_android_alias_alertWithTitle(token.replaceAll("-",""), pushContent, title);
    	}else {
    		//消息
    		payload = buildPushObject_android_alias_mesWithTitle(token.replaceAll("-",""), pushContent);
    	}
    	try {
    		PushResult result = jpushClient.sendPush(payload);
    		System.out.println("商家App推送"+result);
    	} catch (APIConnectionException e) {
    		System.out.println("商家App推送"+e);
    		
    	} catch (APIRequestException e) {
    		e.printStackTrace();
    		System.out.println("商家App推送"+e.getErrorCode());
    		System.out.println("商家App推送"+e);
    		System.out.println("商家App推送"+e.getErrorMessage());
    		System.out.println("商家App推送"+e.getStatus());
    	}
    }


    //构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
    private static PushPayload buildPushObject_android_alias_alertWithTitle(String token, String pushContent, String title) {
//        Message message = Message.newBuilder().setMsgContent(pushContent)
//                .setTitle(title).build();
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(token))
                .setNotification(Notification.android(pushContent, Globals.JPUSH_APP_NAME + title, null))
                .build();
    }

    //构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 Mes，
    private static PushPayload buildPushObject_android_alias_mesWithTitle(String token, String pushContent) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias(token))
                .setMessage(Message.content(pushContent))
                .build();
    }
}
