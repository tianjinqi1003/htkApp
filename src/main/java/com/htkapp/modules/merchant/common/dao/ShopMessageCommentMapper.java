package com.htkapp.modules.merchant.common.dao;

import com.htkapp.modules.merchant.common.dto.*;
import com.htkapp.modules.merchant.common.entity.ShopMessageComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单评论
 */

public interface ShopMessageCommentMapper {

    /* ====================接口开始======================= */
    //通过传入的店铺id查找外卖下的评论
    List<ReturnCommentInfo> getTakeoutCommentByIdDAO(int shopId);
    //根据订单id查看评价详情
    ShopMessageComment viewReviewDetailsByIdDAO(Integer orderId);
    //评价订单
    int commentOrderByIdDAO(@Param("token") String token, @Param("commentsStars") Double commentsStars, @Param("content") String content, @Param("orderId") Integer orderId, @Param("shopId") Integer shopId);
    //查找套餐下的评论
    List<ShopMessageComment> getBuyPackageCommentListDAO(@Param("packageId") int packageId, @Param("mark") int mark);
    //通过店铺信息和标识查询总评论数量
    int getShopCommentCountDAO(int shopId);
    //查询店铺下的总评分数
    double getTheStoreScoreByShopIdDAO(int shopId);
    //查询订单的评论状态
    ShopMessageComment getCommentStatusByOrderIdDAO(int orderId);
    /* ====================接口开始======================= */

    /* ===================JSP页面接口开始========================= */
    //获取外卖下的所有评论
    List<TakeoutCommentList> getTakeoutCommentListByShopIdDAO(@Param("accountShopId") int accountShopId, @Param("starRating") String starRating, @Param("start") String start, @Param("end") String end);
    //获取团购下的所有评论
    List<GroupBuyCommentList> getGroupBuyCommentListByShopIdDAO(@Param("accountShopId") int accountShopId, @Param("starRating") String starRating, @Param("start") String start, @Param("end") String end);
    //根据商户token获取评论列表
    List<CommentListInfo> getCommentListByTokenDAO(@Param("accountShopToken") String accountShopToken, @Param("cMark") int cMark, @Param("comMark") int comMark, @Param("comStar") int comStar, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("orderDesc") String orderDesc);
    //统计一星到五星的数量
    int getStarCountByStarNumDAO(@Param("accountShopToken") String accountShopToken, @Param("cMark") int cMark, @Param("comMark") int comMark, @Param("comStar") int comStar);
    //根据时间段统计评论数量
    int getDateCountByDateValDAO(@Param("accountShopToken") String accountShopToken, @Param("cMark") int cMark, @Param("comMark") int comMark, @Param("startTime") String startTime, @Param("endTime") String endTime);
    //通过传入星级，获取该星级的总值, comStar传入0则代表获取全部值
    double getStarValByStarNumDAO(@Param("accountShopToken") String accountShopToken, @Param("cMark") int cMark, @Param("comStar") int comStar);

    List<MerchantReplyInfo> getMerchantReplyListByUserCommentId(Integer userCommentId);
    /* ===================JSP页面接口结束========================= */
}