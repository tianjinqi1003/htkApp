package com.htkapp.modules.admin.appVersionManage.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;

public interface AppVersionManageService {

    //=======================接口开始============================
    //根据appId获取版本列表
    AjaxResponseModel getVersionList(String appId,int pageNumber);
    //根据id查找版本信息
    AjaxResponseModel getVersionDetailById(String id);
    //========================接口结束==========================
}
