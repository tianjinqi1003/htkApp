package com.htkapp.modules.admin.permissionManage.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;

/**
 * Created by terabithia on 1/3/18.
 */
public interface PermissionConService {

    //获取全部的权限列表接口
    AjaxResponseModel getPermissionsList();

    //权限修改确认接口
    AjaxResponseModel updatePermissionByMerchantId(AjaxRequestParams params);

    //使用时间修改确认接口
    AjaxResponseModel updateUseTimeByMerchantId(AjaxRequestParams params);

}
