package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.ShopConsumptionActivities;

import java.util.List;

/**
 * Created by yinqilei on 17-6-27.
 * 店铺消费活动实体类
 */

public interface ShopConsumptionActivitiesService {

    /* ==================接口开始======================== */
    //根据店铺id查询店铺的所有消费活动内容
    List<ShopConsumptionActivities> getShopConsumptionActivityListById(int shopId) throws Exception;


    /* ==================接口结束======================== */
}
