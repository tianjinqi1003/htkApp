package com.htkapp.modules.admin.common.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.modules.admin.common.entity.Admin;
import org.springframework.ui.Model;


public interface AdminCommonControllerServiceI {

    /* ==================接口开始================== */
    //改变用户登陆状态
    void changeAdminLoginState(Admin admin) throws RuntimeException;
    //根据用户名和密码验证用户
    Admin getAccountByNameAndPwd(String userName, String pwd);
    /* ===================接口结束=================== */

    /* =======================JSP页面接口开始============================ */
    //用户管理页面数据
    void getManageUserData(Model model, int pageNum, RequestParams params);
    //注册申请列表
    void getRegisterApply(RequestParams params);
    //获取店铺角色列表
    AjaxResponseModel getPermissions();
    //确认店铺权限
    AjaxResponseModel enterPermissions(AjaxRequestParams params);
    //权限页面,查找商户列表
    void permissionPage(RequestParams params);
    /* =======================JSP页面接口结束============================ */
}
