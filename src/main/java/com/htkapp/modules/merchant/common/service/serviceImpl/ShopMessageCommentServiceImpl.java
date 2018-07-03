package com.htkapp.modules.merchant.common.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.LogUtil;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.common.dao.ShopMessageCommentMapper;
import com.htkapp.modules.merchant.common.dto.*;
import com.htkapp.modules.merchant.common.entity.ShopMessageComment;
import com.htkapp.modules.merchant.common.service.ShopMessageCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yinqilei on 17-6-27.
 */

@Service
public class ShopMessageCommentServiceImpl implements ShopMessageCommentService {

    @Resource
    private ShopMessageCommentMapper shopMessageCommentDao;

    final Class<? extends Object> ele = ShopMessageCommentServiceImpl.class;

    /* =================接口开始====================== */
    //通过传入的店铺id查找外卖店铺下的评论
    @Override
    public List<ReturnCommentInfo> getTakeoutCommentById(int shopId, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<ReturnCommentInfo> resultList = shopMessageCommentDao.getTakeoutCommentByIdDAO(shopId);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    /**
     * 根据用户评论的id获取商家回复的评论
     * @param userCommentId
     * @return
     * @throws Exception
     */
    @Override
    public List<MerchantReplyInfo> getMerchantReplyListByUserCommentId(Integer userCommentId) throws Exception{
        try {
            List<MerchantReplyInfo> resultList = shopMessageCommentDao.getMerchantReplyListByUserCommentId(userCommentId);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单id查看评价详情
    @Override
    public ShopMessageComment viewReviewDetailsById(Integer orderId) throws Exception {
        try {
            ShopMessageComment shopMessageComment = shopMessageCommentDao.viewReviewDetailsByIdDAO(orderId);
            if (shopMessageComment != null) {
                return shopMessageComment;
            } else {
                return null;
            }
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //评价订单
    @Override
    public boolean commentOrderById(String token, Double commentsStars, String content, Integer orderId, Integer shopId) throws Exception {
        try {
            int row = shopMessageCommentDao.commentOrderByIdDAO(token, commentsStars, content, orderId, shopId);
            return row > 0;
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查找套餐下的评论
    @Override
    public List<ShopMessageComment> getBuyPackageCommentList(int packageId, int mark) throws Exception {
        try {
            return shopMessageCommentDao.getBuyPackageCommentListDAO(packageId, mark);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过店铺ID信息和标识查询总评论数量
    @Override
    public int getShopCommentCount(int shopId) {
        try {
            return shopMessageCommentDao.getShopCommentCountDAO(shopId);
        } catch (Exception e) {
            LogUtil.error(ele, "打印错误日志", e);
            return 0;
        }
    }

    //查询订单的评论状态
    @Override
    public int getCommentStatusByOrderId(int orderId) throws Exception {
        try {
            ShopMessageComment comment = shopMessageCommentDao.getCommentStatusByOrderIdDAO(orderId);
            if (comment != null) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /* =================接口结束====================== */


    /* ==================JSP页面接口开始======================== */
    //获取外卖下的所有评论
    @Override
    public List<TakeoutCommentList> getTakeoutCommentListByShopId(int accountShopId, String starRating, String startTime, String endTime, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<TakeoutCommentList> resultList = shopMessageCommentDao.getTakeoutCommentListByShopIdDAO(accountShopId, starRating, startTime, endTime);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //获取团购下的所有评论
    @Override
    public List<GroupBuyCommentList> getGroupBuyCommentListByShopId(int accountShopId, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<GroupBuyCommentList> resultList = shopMessageCommentDao.getGroupBuyCommentListByShopIdDAO(accountShopId, null, null, null);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据商户token获取评论列表
    @Override
    public List<CommentListInfo> getCommentListByToken(String accountShopToken, int cMark, int comMark, int comStar, int pageNo, int pageLimit, String startTime, String endTime) throws Exception {
        try {
            String orderDesc = "shop_message_comment.gmt_create desc";
            PageHelper.startPage(pageNo, pageLimit);
            List<CommentListInfo> resultList = shopMessageCommentDao.getCommentListByTokenDAO(accountShopToken, cMark, comMark, comStar, startTime, endTime, orderDesc);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //统计一星到五星的数量
    @Override
    public int getStarCountByStarNum(String accountShopToken, int cMark, int comMark, int comStar) {
        try {
            return shopMessageCommentDao.getStarCountByStarNumDAO(accountShopToken, cMark, comMark, comStar);
        } catch (Exception e) {
            return 0;
        }
    }

    //根据时间段统计评论数量
    @Override
    public int getDateCountByDateVal(String accountShopToken, int cMark, int comMark, String startTime, String endTime) {
        try {
            return shopMessageCommentDao.getDateCountByDateValDAO(accountShopToken, cMark, comMark, startTime, endTime);
        } catch (Exception e) {
            return 0;
        }
    }

    //通过传入星级，获取该星级的总值, comStar传入0则代表获取全部值
    @Override
    public double getStarValByStarNumDAO(String accountShopToken, int cMark, int comStar) {
        try {
            return shopMessageCommentDao.getStarValByStarNumDAO(accountShopToken, cMark, comStar);
        } catch (Exception e) {
            return 0;
        }
    }

    /* ==================JSP页面接口结束======================== */
}
