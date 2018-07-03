package com.htkapp.modules.common.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;

/**
 * Created by yinqilei on 17-7-13.
 * common控制类
 */
public interface CommonService {

    /* ===============接口开始================= */
    //通过手机号获取验证码
    AjaxResponseModel getPhoneVerificationCode(String phone);

    /* ===============接品结束================== */
}
