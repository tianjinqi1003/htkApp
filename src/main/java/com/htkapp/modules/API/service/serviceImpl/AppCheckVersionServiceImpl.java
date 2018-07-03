package com.htkapp.modules.API.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dao.AppCheckVersionMapper;
import com.htkapp.modules.API.entity.AppCheckVersion;
import com.htkapp.modules.API.service.AppCheckVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yinqilei on 17-7-4.
 */
@Service
public class AppCheckVersionServiceImpl implements AppCheckVersionService {

    @Autowired
    private AppCheckVersionMapper appCheckVersionDao;


    /* =====================接口开始===================== */
    //获取回头客app用户端最新的版本信息
    @Override
    public AppCheckVersion getTheLatestVersionNumber(String appId) throws Exception {
        try {
            return appCheckVersionDao.getTheLatestVersionNumberDAO(appId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }
     /* =====================接口结束===================== */


    /* ======================jsp页面接口开始========================== */
    //根据appId获取版本列表
    @Override
    public List<AppCheckVersion> getVersionListByAppId(String appId, int pageNo, int pageLimit) {
        String orderDesc = "id desc";
        PageHelper.startPage(pageNo,pageLimit);
        List<AppCheckVersion> resultList = appCheckVersionDao.getVersionListByAppIdDAO(appId,orderDesc);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }
     /* ======================jsp页面接口结束=========================== */

}
