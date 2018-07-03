package com.htkapp.modules.merchant.shop.dao;

import com.htkapp.modules.merchant.shop.entity.ShopComment;

import java.util.List;

/**
 * 商铺评论
 */

public interface ShopCommentMapper {



    /*=============================================商家=============================================*/
    //通过商家ID查找商家下的评论列表
    List<ShopComment> findShopCommentByShopIdByPage(Integer shopId);
    //通过商家ID查找商家的评论数量
    int findTotalRows(Integer shopId);
}
