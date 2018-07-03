package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.ShopBulletinMapper;
import com.htkapp.modules.merchant.shop.entity.ShopBulletin;
import com.htkapp.modules.merchant.shop.service.ShopBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yinqilei on 17-6-27.
 * 店铺公告
 */
@Service
public class ShopBulletinServiceImpl implements ShopBulletinService {

    @Autowired
    private ShopBulletinMapper shopBulletinDao;

    /* ===================接口开始======================= */
    //根据店铺id查询店铺公告
    @Override
    public ShopBulletin getShopBulletinById(int shopId) throws Exception {
        try {
            ShopBulletin shopBulletin = shopBulletinDao.getShopBulletinByIdDAO(shopId);
            if (shopBulletin != null) {
                return shopBulletin;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }
    /* ===================接口结束======================= */


    /* ==================JSP页面接口开始======================= */
    //根据商户token查店铺的公告
    @Override
    public ShopBulletin getShopBulletinByToken(String accountShopToken) throws Exception {
        try {
            return shopBulletinDao.getShopBulletinByTokenDAO(accountShopToken);
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    /* ==================JSP页面接口结束======================= */

}
