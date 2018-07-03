package com.htkapp.modules.API.service;

import com.htkapp.core.dto.APIResponseModel;

/**
 * Created by yinqilei on 17-7-4.
 */
public interface AppAPIService {


    /* ===================接口开始======================= */
    //检查app版本号
    APIResponseModel checkAppUpdate(String appId, String versionNumber);
    //获取用户通知中心最新消息
    APIResponseModel getNoticeCenterByToken(String token, Integer pageNumber);
    /* ======================接口结束======================== */
}
