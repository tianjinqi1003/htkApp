package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.ShopBulletin;

/**
 * Created by yinqilei on 17-6-27.
 * 店铺公告
 */

public interface ShopBulletinMapper {

    /* ================接口开始====================== */
    //根据店铺id查询店铺公告
    ShopBulletin getShopBulletinByIdDAO(int shopId);

    /* ================接口结束====================== */

    /* ==================JSP页面接口开始======================= */
    //根据商户token查店铺的公告
    ShopBulletin getShopBulletinByTokenDAO(String accountShopToken);

    /* ==================JSP页面接口结束======================= */

}
