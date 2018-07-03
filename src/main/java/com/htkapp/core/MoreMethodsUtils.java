package com.htkapp.core;

import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.merchant.common.entity.AccountShopNotice;
import com.htkapp.modules.merchant.common.service.AccountShopNoticeService;

import javax.annotation.Resource;

import static com.htkapp.core.OtherUtils.pushByGoEasy;

public class MoreMethodsUtils {

    @Resource
    private AccountShopNoticeService shopNoticeService;


    //goEasy推送消息方法(插入消息到数据库并推送到前端页面)
    public void pushMesToManagePage(PushMesEntity pushMesEntity) throws Exception {
        if (pushMesEntity == null) {
            throw new Exception(Globals.PARAMETER_CANNOT_BE_EMPTY);
        }
        try {
            //插入消息
            AccountShopNotice merchantNotice = new AccountShopNotice();
            merchantNotice.setTitle(pushMesEntity.getNTitle());
            merchantNotice.setMsgCategory(pushMesEntity.getNMsgCategory());
            merchantNotice.setMsgContent(pushMesEntity.getNMsgContent());
            merchantNotice.setAccountShopToken(pushMesEntity.getNToken());
            shopNoticeService.insertNoticeByToken(merchantNotice);
            //推送到前端页面
            pushByGoEasy(pushMesEntity.getClassifyId(), pushMesEntity.getStatusCode(), pushMesEntity.getMessage(), pushMesEntity.getAccountShopId());
        } catch (Exception e) {
            throw new Exception(Globals.BACK_CODE_THROW_EXCEPTION);
        }
    }

    //同时推送给商家和用户消息(极光推送)
    public void jPushToMerAndAccount(String aToken, String aContent,String aMes, String mToken, String mContent, String mMes, int i) throws Exception{
        if(aContent == null || aToken == null || mToken == null || mContent == null){
            throw new Exception(Globals.PARAMETER_CANNOT_BE_EMPTY);
        }
        try {
            if(i == 0){
                //单独推送给用户app
                Jpush.jPushMethod(aToken,aContent,"ALERT");
                Jpush.jPushMethod(aToken,aMes,"");
            }else if(i == 1 && StringUtils.isNotEmpty(mContent) && StringUtils.isNotEmpty(mToken)) {
                //单独推送给商家app
                Jpush.jPushMethodToMerchant(mToken,mContent,"ALERT", "商家版");
                Jpush.jPushMethodToMerchant(mToken,mMes,"","");
            }else {
                Jpush.jPushMethod(aToken,aContent,"ALERT");
                Jpush.jPushMethodToMerchant(mToken,mContent,"ALERT", "商家版");
                Jpush.jPushMethod(aToken,aMes,"");
                Jpush.jPushMethodToMerchant(mToken,mMes,"","");
            }
        }catch (Exception e){
            throw new Exception(Globals.BACK_CODE_THROW_EXCEPTION);
        }
    }

}
