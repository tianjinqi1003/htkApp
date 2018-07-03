package com.htkapp.modules.merchant.buffetFood.dao;

import com.htkapp.modules.merchant.buffetFood.dto.ReturnCommentList;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuffetFoodCommentMapper {


    /* ============================接口开始============================= */
    //根据产品id查询产品下的评论列表
    List<ReturnCommentList> getCommentListByProductIdDAO(@Param("shopId") int shopId, @Param("productId") int productId);

    /* ============================接口结束============================= */
}
