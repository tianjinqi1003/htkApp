package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.dao.ShopArticleInfoMapper;
import com.htkapp.modules.merchant.integral.entity.ShopArticleInfo;
import com.htkapp.modules.merchant.integral.service.ShopArticleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopArticleInfoServiceImpl implements ShopArticleInfoService {

    @Resource
    private ShopArticleInfoMapper shopArticleInfoDao;

    //通过店铺id 获取资讯列表
    @Override
    public List<ShopArticleInfo> getShopArticleInfoByShopId(int shopId,int pageNumber, int pageLimit) {
        PageHelper.startPage(pageNumber, pageLimit);
        List<ShopArticleInfo> resultList = shopArticleInfoDao.getShopArticleInfoByShopIdDAO(shopId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //创建资讯
    @Override
    public void insertShopArticleInfoDAO(ShopArticleInfo shopArticleInfo) {
        int row = shopArticleInfoDao.insertShopArticleInfoDAO(shopArticleInfo);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //通过商户id查询资讯列表
    @Override
    public List<ShopArticleInfo> getShopArticleInfoById(int accountShopId, String orderDesc, int pageNum, int pageLimit) {
        PageHelper.startPage(pageNum,pageLimit);
        List<ShopArticleInfo> resultList = shopArticleInfoDao.getShopArticleInfoByIdDAO(accountShopId, orderDesc);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //根据aId查询内容
    @Override
    public ShopArticleInfo getShopArticleInfoById(int articleId) {
        return shopArticleInfoDao.getShopArticleByIdDAO(articleId);
    }

    @Override
    public void updateArticleInfoShowState(int id, int stateId) {
        int row = shopArticleInfoDao.updateArticleInfoShowStateDAO(id, stateId);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //更改资讯
    @Override
    public void updateMesById(ShopArticleInfo shopArticleInfo) {
        int row = shopArticleInfoDao.updateMesByIdDAO(shopArticleInfo);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }
}
