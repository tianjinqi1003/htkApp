package com.htkapp.modules.merchant.common.service;

import com.htkapp.modules.merchant.common.dto.*;
import com.htkapp.modules.merchant.common.entity.ShopMessageComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yinqilei on 17-6-27.
 *
 */


public interface ShopMessageCommentService {

    /* =============接口开始================== */
    //通过传入的店铺id查找外卖下的评论
    List<ReturnCommentInfo> getTakeoutCommentById(int shopId, int pageNo, int pageLimit) throws Exception;
    //通过传入的用户评论的id查找商家回复的评论
    List<MerchantReplyInfo> getMerchantReplyListByUserCommentId(Integer userCommentId) throws Exception;
    //根据订单id查看评价详情
    ShopMessageComment viewReviewDetailsById(Integer orderId) throws Exception;
    //评价订单
    boolean commentOrderById(String token, Double commentsStars,String content, Integer orderId,Integer shopId) throws Exception;
    //查找套餐下的评论
    List<ShopMessageComment> getBuyPackageCommentList(int packageId, int mark) throws Exception;
    //通过店铺ID信息和标识查询总评论数量
    int getShopCommentCount(int shopId);
    //查询订单的评论状态
    int getCommentStatusByOrderId(int orderId) throws Exception;
    /* =============接口结束================== */

    /* =================JSP页面接口开始======================= */
    //获取外卖下的所有评论
    List<TakeoutCommentList> getTakeoutCommentListByShopId(int accountShopId, String starRating, String startTime, String endTime, int pageNo, int pageLimit) throws Exception;
    //获取团购下的所有评论
    List<GroupBuyCommentList> getGroupBuyCommentListByShopId(int accountShopId, int pageNo, int pageLimit) throws Exception;
    //根据商户token获取评论列表
    List<CommentListInfo> getCommentListByToken(String accountShopToken, int cMark,int comMark, int comStar, int pageNo, int pageLimit, String StartTime, String endTime)throws Exception;
    //统计一星到五星的数量
    int getStarCountByStarNum(String accountShopToken, int cMark, int comMark, int comStar);
    //根据时间段统计评论数量
    int getDateCountByDateVal(String accountShopToken, int cMark, int comMark, String startTime, String endTime);
    //通过传入星级，获取该星级的总值, comStar传入0则代表获取全部值
    double getStarValByStarNumDAO(String accountShopToken, int cMark, int comStar);

    /* =================JSP页面接口结束======================= */
}
