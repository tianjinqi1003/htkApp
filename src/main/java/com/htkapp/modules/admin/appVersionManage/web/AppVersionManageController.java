package com.htkapp.modules.admin.appVersionManage.web;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.admin.appVersionManage.service.AppVersionManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/appVersionManage/")
public class AppVersionManageController {

    @Resource
    private AppVersionManageService versionManageService;

    //====================================page开始
    //app版本添加页面
    @RequestMapping("/versionAddPage")
    public String versionAddPage(Model model){
        model.addAttribute("appVersion", true);
        return "admin/version_add";
    }

    //app版本编辑页面
    @RequestMapping("/versionEditPage")
    public String versionEditPage(Model model){
        model.addAttribute("appVersion", true);
        return "admin/version_edit";
    }
    //====================================page结束


    //====================================方法开始
    //根据条件查询版本列表
    @RequestMapping("/getVersionList")
    @ResponseBody
    public AjaxResponseModel getVersionList(String appId,
                                                   @RequestParam(value = "pageNumber",required = false,defaultValue = "1") Integer pageNumber){
        return versionManageService.getVersionList(appId,pageNumber);
    }
    //===================================方法结束


    //根据id查找版本信息
    @RequestMapping("/getVersionDetailById")
    @ResponseBody
    public AjaxResponseModel getVersionDetailById(String id){
        return null;
    }

    //通过id删除app版本信息
    @RequestMapping("/delVersionById")
    public AjaxResponseModel delVersionById(Integer id){
        return null;
    }

    //app版本修改、保存方法
    @RequestMapping("/saveVersion")
    public AjaxResponseModel saveVersion(){
        return null;
    }




}
