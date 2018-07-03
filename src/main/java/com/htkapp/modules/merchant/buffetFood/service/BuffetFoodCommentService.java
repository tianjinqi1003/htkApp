package com.htkapp.modules.merchant.buffetFood.service;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnCommentList;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodComment;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;

import java.util.List;

public interface BuffetFoodCommentService {


    /* ============================接口开始============================== */
    //根据产品id查询产品下的评论列表
    List<ReturnCommentList> getCommentListByProductId(int shopId, int productId, int pageNumber, int pageLimit);
    /* ============================接口结束================================ */
}
