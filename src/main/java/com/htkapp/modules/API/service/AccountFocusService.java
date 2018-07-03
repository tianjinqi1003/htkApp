package com.htkapp.modules.API.service;


import com.htkapp.modules.API.entity.AccountFocus;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface AccountFocusService {


    /* ================接口开始======================= */
    //通过用户token查找收藏列表分页
    List<AccountFocus> getCollectListByTokenByPage(String token, int pageNo, int pageLimit) throws Exception;
    //通过用户ID查找收藏列表
    List<AccountFocus> getCollectListByToken(String token) throws Exception;
    //app用户收藏店铺
    boolean collectionStore(String token, int shopId) throws Exception;
    //app用户取消收藏店铺
    boolean cancelTheStore(String token, int shopId) throws Exception;
    //通过用户token获取关注的店铺列表id
    Set<Integer> getCollectionShopIdListByToken(String token, int mark);
    /* ================接口结束======================= */

    /* ====================JSP页面接口开始====================== */
    //收藏店铺(通过用户ID和店铺ID加入关系)
    void joinCollectionByAccountIdAndShopId(String accountToken, Integer shopId);
    //检查是否已经收藏了店铺
    int checkCollectionByAccountIdAndShopId(String accountToken, Integer shopId);
    //根据手机号查找用户是否关注
    AccountFocus getAccountFocusByUserPhone(String userPhone, String accountShopToken);
    //加入收藏(加入收藏前检查用户是否是第一次加入,如果不是第一次加入,则找回积分值，如果是第一次加入则创建积分值记录)
    int checkStateAndJoinMemberByToken(String token, int shopId);
    /* ====================JSP页面接口结束====================== */

}
