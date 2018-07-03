package com.htkapp.modules.admin.accountManage.web;

import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.admin.accountManage.service.AccountManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinqilei on 17-7-10.
 * 账号管理
 */

@Controller
@RequestMapping("/admin/accountManage/")
public class AccountManageController {

    @Autowired(required = false)
    private HttpServletRequest request;
    @Autowired
    private AccountManageService accountManageService;


    //===================================page开始
    //商户添加界面
    @RequestMapping("/addMerchant")
    public String addMerchant() {
        return "admin/accountManage/merchantManage/add";
    }

    //==================================page结束

    @RequestMapping("/getAppAccountJsonData")
    @ResponseBody
    public AjaxResponseModel getAppAccountJsonData() {
        return null;
    }

    //获取用户管理表格json数据
    @RequestMapping("/getUserManageJsonData")
    @ResponseBody
    public TableResponseModel getUserManageJsonData() {
        return accountManageService.getUserManageJsonData(request);
    }

    //获取商户管理表格json数据
    @RequestMapping("/getMerchantManageJsonData")
    @ResponseBody
    public TableResponseModel getMerchantManageJsonData() {
        return accountManageService.getMerchantManageJsonData(request);
    }
}
