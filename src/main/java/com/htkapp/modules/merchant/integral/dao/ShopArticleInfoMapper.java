package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.ShopArticleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopArticleInfoMapper {

    //通过店铺id 获取资讯列表
    List<ShopArticleInfo> getShopArticleInfoByShopIdDAO(int shopId);
    //创建资讯
    int insertShopArticleInfoDAO(ShopArticleInfo shopArticleInfo);
    //根据id查询资讯内容
    ShopArticleInfo getShopArticleByIdDAO(int id);
    //通过商户id查询资讯列表
    List<ShopArticleInfo> getShopArticleInfoByIdDAO(@Param("accountShopId") int accountShopId, @Param("orderDesc") String orderDesc);
    //关闭资讯显示
    int updateArticleInfoShowStateDAO(int id, int stateId);
    //更改资讯
    int updateMesByIdDAO(ShopArticleInfo shopArticleInfo);
}
