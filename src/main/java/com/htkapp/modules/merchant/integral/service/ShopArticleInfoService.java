package com.htkapp.modules.merchant.integral.service;

import com.htkapp.modules.merchant.integral.entity.ShopArticleInfo;

import java.util.List;

public interface ShopArticleInfoService {

    //通过店铺id 获取资讯列表
    List<ShopArticleInfo> getShopArticleInfoByShopId(int shopId, int pageNumber, int pageLimit);
    //创建资讯
    void insertShopArticleInfoDAO(ShopArticleInfo shopArticleInfo);
    //通过商户id查询资讯列表
    List<ShopArticleInfo> getShopArticleInfoById(int accountShopId, String orderDesc, int pageNum, int pageLimit);
    //根据aId查询内容
    ShopArticleInfo getShopArticleInfoById(int articleId);
    //关闭资讯显示
    void updateArticleInfoShowState(int id, int stateId);
    //更改资讯
    void updateMesById(ShopArticleInfo shopArticleInfo);
}
