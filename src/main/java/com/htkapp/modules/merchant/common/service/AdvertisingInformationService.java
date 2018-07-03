package com.htkapp.modules.merchant.common.service;

import com.htkapp.modules.merchant.common.entity.AdvertisingInformation;

import java.util.List;

/**
 * Created by yinqilei on 17-6-29.
 */

public interface AdvertisingInformationService {

    /* =================接口开始================== */
    //根据团购商铺id获取广告列表
    List<AdvertisingInformation> getGroupBuyAdList(Integer shopId) throws Exception;
    //获取首页广告列表(随机五个店铺广告信息)
    List<AdvertisingInformation> getRandomlyAdList() throws Exception;


    /* ==================接口结束======================= */
}
