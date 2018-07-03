package com.htkapp.modules.merchant.groupBuy.service.serviceImpl;

import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.groupBuy.dao.BuyPackageContentMapper;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageContent;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuyPackageContentServiceImpl implements BuyPackageContentService {

    @Resource
    private BuyPackageContentMapper buyPackageContentDao;

    /* ==========================接口开始================================ */
    //插入套餐详情内容(套餐内商品)
    @Override
    public void insertPackageItem(BuyPackageContent buyPackageContent) {
        int row = buyPackageContentDao.insertPackageItemDAO(buyPackageContent);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //通过packageId 获取套餐内商品
    @Override
    public List<BuyPackageContent> getPackageItemListById(int packageId) {
        List<BuyPackageContent> buyPackageContentList = buyPackageContentDao.getPackageItemListByIdDAO(packageId);
        if(buyPackageContentList != null && buyPackageContentList.size() > 0){
            return buyPackageContentList;
        }
        return null;
    }

    //通过packageId删除套餐内商品
    @Override
    public void deletePackageContentListById(int packageId) {
        int row = buyPackageContentDao.deletePackageContentListByIdDAO(packageId);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }

    /* ==========================接口结束============================== */
}
