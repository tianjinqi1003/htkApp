package com.htkapp.modules.merchant.common.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.common.entity.LoginUser;

/**
 * Created by yinqilei on 17-7-4.
 * 登陆逻辑service
 */
public interface LoginService {

    /* ==============方法接口开始=================== */
    //商户登陆
    AjaxResponseModel merchantLogin(LoginUser loginUser);
    //商户退出登陆（写入本次登陆的退出记录）
    AjaxResponseModel merchantSignOut(String sessionUserName);
    //管理员登陆
    AjaxResponseModel adminLogin(LoginUser loginUser);
    //管理员退出
    AjaxResponseModel adminSignOut(String sessionUserName);
    /* ==============方法接口结束=================== */
}
