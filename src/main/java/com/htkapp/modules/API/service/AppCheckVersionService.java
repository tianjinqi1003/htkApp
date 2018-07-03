package com.htkapp.modules.API.service;

import com.htkapp.modules.API.entity.AppCheckVersion;

import java.util.List;

/**
 * Created by yinqilei on 17-7-4.
 */
public interface AppCheckVersionService {

    /* =====================接口开始===================== */
    //获取回头客app用户端最新的版本信息
    AppCheckVersion getTheLatestVersionNumber(String appId) throws Exception;

     /* =====================接口结束===================== */


     /* =====================jsp页面接口开始========================== */
     //根据appId获取版本列表
     List<AppCheckVersion> getVersionListByAppId(String appId, int pageNo, int pageLimit);
     /* =====================jsp页面接口中开始=========================== */
}
