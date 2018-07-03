package com.htkapp.modules.admin.permissionManage.web;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.modules.admin.permissionManage.service.PermissionConService;
import com.htkapp.modules.common.service.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by terabithia on 12/31/17.
 */
@Controller
@RequestMapping(value = "/admin/permission")
public class PermissionController {

    @Resource
    private PermissionConService permissionConService;

    //获取全部的权限列表接口
    @RequestMapping(value = "/getPermissionsList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel getPermissionsList() {
        return permissionConService.getPermissionsList();
    }


    //权限修改确认接口
    @RequestMapping(value = "/updatePermissionByMerchantId", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updatePermissionByMerchantId(AjaxRequestParams params) {
        return permissionConService.updatePermissionByMerchantId(params);
    }

    //使用时间修改确认接口
    @RequestMapping(value = "/updateUseTimeByMerchantId", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updateUseTimeByMerchantId(AjaxRequestParams params) {
        return permissionConService.updateUseTimeByMerchantId(params);
    }

}
