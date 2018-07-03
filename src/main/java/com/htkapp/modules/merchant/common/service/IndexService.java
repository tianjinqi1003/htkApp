package com.htkapp.modules.merchant.common.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.common.dto.IndexInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinqilei on 17-7-14.
 */
public interface IndexService {

    /* =================接口开始================== */
    //商户主页
    IndexInfo index(HttpServletRequest request);
    //首页店铺停止营业
    AjaxResponseModel stopShop(HttpServletRequest request, Integer shopStateId);
    /* =================接口结束=================== */
}
