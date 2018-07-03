package com.htkapp.modules.merchant.groupBuy.service.serviceImpl;

import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.groupBuy.dao.BuyPackageMapper;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yinqilei on 17-6-30.
 * 团购套餐
 */

@Service
public class BuyPackageServiceImpl implements BuyPackageService {

    @Resource
    private BuyPackageMapper buyPackageDao;

    /* ===================接口开始==================== */
    //获得团购店铺下的套餐列表
    @Override
    public List<BuyPackage> getGroupBuyListById(Integer shopId) throws Exception {
        try {
           List<BuyPackage> resultList = buyPackageDao.getGroupBuyListByIdDAO(shopId);
           if(resultList != null && resultList.size() > 0){
               return resultList;
           }
           return null;
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过套餐id查出套餐详情和店铺id
    @Override
    public BuyPackage getPackageInformation(int packageId) throws Exception {
        try {
            return buyPackageDao.getPackageInformationDAO(packageId);
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    /* ===================接口结束============================== */


    /* ===================JSP页面接口开始====================== */
    //通过商户id查询团购产品列表
    @Override
    public List<BuyPackage> getGroupBuyListByAccountShopId(int accountShopId) throws Exception {
        try {
            return buyPackageDao.getGroupBuyListByAccountShopIdDAO(accountShopId);
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //插入团购套餐
    @Override
    public int insertBuyPackage(BuyPackage buyPackage) {
        int row = buyPackageDao.insertBuyPackageDAO(buyPackage);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
        return  buyPackage.getId();
    }

    //通过套餐id删除商品
    @Override
    public void deleteBuyPackageById(int packageId, int shopId) {
        int row = buyPackageDao.deleteBuyPackageByIdDAO(packageId, shopId);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }

    //更改保存商品
    @Override
    public void updateBuyPackageById(BuyPackage buyPackage) {
        int row = buyPackageDao.updateBuyPackageByIdDAO(buyPackage);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    /* ==================JSP页面接口结束======================= */
}
