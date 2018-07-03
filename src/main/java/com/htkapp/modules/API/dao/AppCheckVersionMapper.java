package com.htkapp.modules.API.dao;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.API.entity.AppCheckVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yinqilei on 17-7-4.
 */
public interface AppCheckVersionMapper {

    /* =====================接口开始===================== */
    //获取回头客app用户端最新的版本信息
    AppCheckVersion getTheLatestVersionNumberDAO(String appId);

     /* =====================接口结束===================== */


    /* ========================jsp页面接口开始============================== */
    //根据appId获取版本列表
    List<AppCheckVersion> getVersionListByAppIdDAO(@Param("appId") String appId, @Param("orderDesc") String orderDesc);
    //根据id查找版本信息
    AjaxResponseModel getVersionDetailByIdDAO(Integer id);
    //通过id删除app版本信息
    AjaxResponseModel delVersionById(Integer id);
     /* ========================jsp页面接口结束============================= */
}
