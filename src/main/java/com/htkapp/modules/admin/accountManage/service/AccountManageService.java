package com.htkapp.modules.admin.accountManage.service;

import com.htkapp.core.dto.TableResponseModel;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinqilei on 17-7-10.
 */
public interface AccountManageService {


    /* ===================接口开始===================== */
    //获取用户管理页面的Json的数据
    TableResponseModel getUserManageJsonData(HttpServletRequest request);
    //获取商户管理表格json数据
    TableResponseModel getMerchantManageJsonData(HttpServletRequest request);
    
    /* ===================接口结束====================== */


}
