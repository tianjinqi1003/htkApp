package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.ShopConsumptionActivities;

import java.util.List;

/**
 * Created by yinqilei on 17-6-27.
 * 店铺消费活动
 */

public interface ShopConsumptionActivitiesMapper {

    /* ================接口开始====================== */
    //根据店铺id查询店铺的所有消费活动内容
    List<ShopConsumptionActivities> getShopConsumptionActivityListByIdDAO(int shopId);



    /* ================接口结束====================== */

}
