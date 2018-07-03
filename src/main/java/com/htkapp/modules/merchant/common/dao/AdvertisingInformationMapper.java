package com.htkapp.modules.merchant.common.dao;

import com.htkapp.modules.merchant.common.entity.AdvertisingInformation;

import java.util.List;

/**
 * Created by yinqilei on 17-6-29.
 *
 */

public interface AdvertisingInformationMapper {

    /* ================接口开始====================== */
    //根据团购商铺id获取广告列表
    List<AdvertisingInformation> getGroupBuyAdListDAO(Integer shopId);
    //获取首页广告列表(随机五个店铺广告信息)
    List<AdvertisingInformation> getRandomlyAdListDAO();




    /* ==================接口结束=========================== */
}
