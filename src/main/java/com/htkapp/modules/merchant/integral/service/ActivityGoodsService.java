package com.htkapp.modules.merchant.integral.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.merchant.integral.entity.ActivityGoods;
import com.htkapp.modules.merchant.integral.entity.Integral;

import java.util.List;


/**
 *
 */
public interface ActivityGoodsService {

    /* ====================接口开始======================= */
    //通过id查找活动商品对象
    ActivityGoods editPresentById(Integer id);

    /* ====================接口结束======================== */





    List<ActivityGoods> getPresentListByPageById(int pageNo, int limit, ActivityGoods activityGoods);

    int deletePresentById(Integer id);


    int insertPresent(ActivityGoods activityGoods) throws Exception;

    int updatePresentById(ActivityGoods activityGoods) throws Exception;

    AjaxResponseModel executeBuy(String[] activityId, Integral integral, int shopId, int userId) throws Exception;

    /*===================================app页面====================================*/
    List<ActivityGoods> getActivityListById(Integer shopId, Integer parentId);

    int updateActivityCountById(Integer id);

    /**
     * @param id 能过ID获得活动名称
     * @return
     */
    String getGoodsNameById(Integer id);

    /**
     * @param activity 兑换物品执行
     * @param id
     * @return
     */
//    AjaxResponseModel exchangeItems(ActivityGoods activity, int id);
}
