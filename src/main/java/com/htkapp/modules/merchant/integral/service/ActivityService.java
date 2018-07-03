package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.entity.Activity;

import java.util.Date;
import java.util.List;

public interface ActivityService {


    /* =================appHtml页面接口开始=================== */
    //查询出商家下的所有正在进行的活动 并分页
    List<Activity> getListByIdByDate(int pageNo, int pageLimit, int shopId, Date nowTime) throws Exception;
    //通过活动id查找活动对象
    Activity getActivityById(int activeId);
    /* =================appHtml页面接口结束=================== */
//
//    /**
//     * @return 通过商铺ID查找集合数据并分页
//     */
//    List<Activity> getListByPageById(int pageSize, int limit, int id);
//
//    /**
//     * @param id     通过活动ID和商铺ID查找活动对象
//     * @param shopId
//     * @return
//     */
//    Activity getById(int id, int shopId);
//
//    /**
//     * @param activityParent 通过主键ID和商铺ID更改
//     * @return
//     */
////    ResponseModel updateByIdS(Activity activityParent);
//
//    /**
//     * @param activityParent 通过商铺ID插入数据
//     * @return
//     */
////    ResponseModel saveByIdS(Activity activityParent);
//
//    /**
//     * @param id     通过主键ID和商铺ID删除活动
//     * @param shopId
//     * @return
//     */
////    ResponseModel deleteByIdS(int id, int shopId);
//
//    /**
//     * @param shopId 通过商铺ID查询商铺下活动集合
//     * @return
//     */
//    List<Activity> getListById(int shopId);


}
