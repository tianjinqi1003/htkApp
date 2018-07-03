package com.htkapp.modules.admin.appVersionManage.service.serviceImpl;

import com.htkapp.core.OtherUtils;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.entity.AppCheckVersion;
import com.htkapp.modules.API.service.AppCheckVersionService;
import com.htkapp.modules.admin.appVersionManage.service.AppVersionManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AppVersionManageServiceImpl implements AppVersionManageService {

    @Resource
    private AppCheckVersionService appCheckVersionService;
    @Autowired(required = false)
    private HttpServletRequest request;

    //=======================接口开始============================
    //根据appId获取版本列表
    @Override
    public AjaxResponseModel getVersionList(String appId,int pageNumber) {
        try {
            int pageNo = Globals.DEFAULT_PAGE_NO;
            int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
            if(pageNumber > 1){
                pageNo = pageNumber;
            }
            if(StringUtils.isEmpty(appId)) appId = null;
            List<AppCheckVersion> resultList = appCheckVersionService.getVersionListByAppId(appId,pageNo,pageLimit);
            if(resultList != null){
                for (AppCheckVersion each : resultList){
                    each.setDownloadUrl(OtherUtils.getRootDirectory()+each.getDownloadUrl());
                }
            }
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION,"成功",resultList);
        }catch (Exception e){
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    //根据id查找版本信息
    @Override
    public AjaxResponseModel getVersionDetailById(String appId) {
        try {

        }catch (Exception e){

        }
        return null;
    }
    //========================接口结束===========================
}
