package com.htkapp.modules.merchant.shop.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.shop.dao.ShopConsumptionActivitiesMapper;
import com.htkapp.modules.merchant.shop.entity.ShopConsumptionActivities;
import com.htkapp.modules.merchant.shop.service.ShopConsumptionActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yinqilei on 17-6-27.
 * 店铺消费活动
 */
@Service
public class ShopConsumptionActivitiesServiceImpl implements ShopConsumptionActivitiesService {

    @Autowired
    private ShopConsumptionActivitiesMapper shopConsumptionActivitiesDao;

    /* =================接口开始===================== */
    //根据店铺id查询店铺的所有消费活动内容
    @Override
    public List<ShopConsumptionActivities> getShopConsumptionActivityListById(int shopId) throws Exception {
        try {
           List<ShopConsumptionActivities> resultList = shopConsumptionActivitiesDao.getShopConsumptionActivityListByIdDAO(shopId);
           if(resultList != null && resultList.size() > 0){
               return resultList;
           }else {
               return null;
           }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }
    /* =================接口结束===================== */

}
