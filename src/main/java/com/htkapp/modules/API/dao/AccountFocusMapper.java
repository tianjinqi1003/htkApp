package com.htkapp.modules.API.dao;

import com.htkapp.modules.API.entity.AccountFocus;
import com.htkapp.modules.merchant.shop.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户关注
 */

public interface AccountFocusMapper {


    /* =====================接口开始===================== */
    //通过用户ID查找收藏列表
    List<AccountFocus> getCollectListByTokenDAO(@Param("token") String token, @Param("mark") Integer mark);
    //app用户收藏店铺请求
    int collectionStoreDAO(@Param("token") String token, @Param("shopId") int shopId);
    //app用户取消收藏店铺
    int cancelTheStoreDAO(@Param("token") String token, @Param("shopId") int shopId);
    /* =====================接口结束===================== */



    /* ====================JSP页面接口开始====================== */
    //加入收藏关系
    int joinCollection(@Param("accountToken") String accountToken, @Param("shopId") Integer shopId);
    //检查是否已经收藏了店铺
    AccountFocus checkCollection(@Param("accountToken") String accountToken, @Param("shopId") Integer shopId);
    //根据手机号查找用户是否关注
    AccountFocus getAccountFocusByUserPhoneDAO(String userPhone, String accountShopToken);
    /* ====================JSP页面接口结束====================== */



}
