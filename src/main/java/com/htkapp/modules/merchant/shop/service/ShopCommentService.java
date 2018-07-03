package com.htkapp.modules.merchant.shop.service;

import com.htkapp.modules.merchant.shop.entity.ShopComment;

import java.util.List;

/**
 * Created by yinqilei on 17-3-2.
 */
public interface ShopCommentService {



    /*=============================================商家=============================================*/
    //通过商家ID查找商家下的评论列表
    List<ShopComment> getShopCommentByShopIdByPage(int pageNo, int pageLimit, Integer shopId);
    //通过商家ID查找商家下的评论数量
    int findTotalRows(Integer shopId);

}
