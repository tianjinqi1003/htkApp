package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.exception.BuyException;
import com.htkapp.core.exception.InsertRecordException;
import com.htkapp.core.exception.MinusPointsException;
import com.htkapp.core.exception.ReduceInventoryException;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.merchant.integral.dao.IntegralMapper;
import com.htkapp.modules.merchant.integral.dao.IntegralSpendingLogMapper;
import com.htkapp.modules.merchant.integral.dao.ActivityGoodsMapper;
import com.htkapp.modules.merchant.integral.entity.ActivityGoods;
import com.htkapp.modules.merchant.integral.entity.Integral;
import com.htkapp.modules.merchant.integral.entity.IntegralSpendingLog;
import com.htkapp.modules.merchant.integral.service.ActivityGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

@Service
public class ActivityGoodsServiceImpl implements ActivityGoodsService {

    @Resource
    private ActivityGoodsMapper presentDao;
    @Resource
    private IntegralMapper integralDao;
    @Resource
    private IntegralSpendingLogMapper integralSpendingLogDao;


    /* =========================接口开始============================== */
    //通过ID查找积分兑换活动信息
    @Override
    public ActivityGoods editPresentById(Integer id) {
        return presentDao.editPresentById(id);
    }

    /* =========================接口结束============================== */

    /**
     * @param pageNo   通过商户ID获得积分兑换活动列表
     * @param limit
     * @param activityGoods
     * @return
     */
    @Override
    public List<ActivityGoods> getPresentListByPageById(int pageNo, int limit, ActivityGoods activityGoods) {
        PageHelper.startPage(pageNo, limit);
        return presentDao.findPresentListByPageById(activityGoods);
    }

    /**
     * @param id 通过ID删除积分兑换活动
     * @return
     */
    @Override
    public int deletePresentById(Integer id) {
        int row = presentDao.deletePresentById(id);
        return row == 0 ? 0 : 1;
    }


    /**
     * @param activityGoods 插入积分兑换活动信息
     * @return
     */
    @Override
    public int insertPresent(ActivityGoods activityGoods) throws Exception {
        int count = presentDao.selectActivityCount(activityGoods.getParentId());
        int row = 0;
        if (count < 6) {
            row = presentDao.insertPresentById(activityGoods);
        } else {
            throw new Exception("每个活动下的实物不能超过6个!");
        }
        return row;
    }

    /**
     * @param activityGoods 通过ID修改积分兑换活动信息
     * @return
     */
    @Override
    @Transactional
    public int updatePresentById(ActivityGoods activityGoods) throws Exception {
        int count = presentDao.selectActivityCount(activityGoods.getParentId());
        int row = 0;
        if (count < 6) {
            row = presentDao.updatePresentById(activityGoods);
        } else if (count == 6) {
            row = presentDao.updatePresentById(activityGoods);
            int updateCount = presentDao.selectActivityCount(activityGoods.getParentId());
            if (updateCount > 6) {
                throw new RuntimeException("不能超过6个!");
            }
        } else {
            throw new Exception("每个活动下的实物不能超过6个!");
        }
        return row;
    }

    @Override
    @Transactional
    public AjaxResponseModel executeBuy(String[] activityId, Integral integral, int shopId, int userId)
            throws ReduceInventoryException, MinusPointsException, InsertRecordException {
        int result = 0;
        //执行逻辑：减库存 + 记录购买行为
        try {
            //减库存
            int count = 0;
            for (String every : activityId) {
                count += presentDao.updateActivityCountById(Integer.parseInt(every));
            }
            if (count == activityId.length) {
                //减用户积分
//                int row = integralDao.updateIntegralByAccountIdAndShopId(integral);
                int row = 0;
                if (row <= 0) {
                    throw new MinusPointsException("减积分异常！");
                } else {
                    //插入购买记录
                    for (String every : activityId) {
                        //加入积分消费信息到消费表中
                        IntegralSpendingLog integralSpendingLog = new IntegralSpendingLog();
                        int value = (presentDao.editPresentById(Integer.parseInt(every))).getIntegral();
                        String goodsName = presentDao.findGoodsNameById(Integer.parseInt(every));
                        integralSpendingLog.setGoodsCount(1); //每次只能兑换一件实物  默认为1
                        integralSpendingLog.setGoodsName(goodsName);  //存入活动名称
                        integralSpendingLog.setSpendingIntegral(value);  //存入本次消费的积分数
                        integralSpendingLog.setShopId(shopId);   //存入商铺ID
                        integralSpendingLog.setAccountId(userId);  //存入用户ID
                        integralSpendingLog.setActivityId(Integer.parseInt(every));  //存入活动ID
                        integralSpendingLog.setSpendingType("p");   //消费类型
                        integralSpendingLog.setSpendingDescription("兑换实物");  //消费说明
                        result += integralSpendingLogDao.insertById(integralSpendingLog);
                    }
                }
            } else {
                throw new ReduceInventoryException("减库存异常！");
            }
            if (result == activityId.length) {
                return new AjaxResponseModel(0, "购买成功！");
            } else {
                throw new InsertRecordException("插入记录异常！");
            }

        } catch (ReduceInventoryException e1) {
            throw e1;
        } catch (MinusPointsException e2) {
            throw e2;
        } catch (InsertRecordException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new BuyException("buy inner error:" + e4.getMessage());
        }
    }

    /*===================================app页面====================================*/

    /**
     * @param shopId 通过ID查找店铺下的所有活动
     * @return
     */
    @Override
    public List<ActivityGoods> getActivityListById(Integer shopId, Integer parentId) {
        return presentDao.findActivityListById(shopId, parentId);
    }

    /**
     * @param id 通过activityId 更新实物数量
     * @return
     */
    @Override
    public int updateActivityCountById(Integer id) {
        return presentDao.updateActivityCountById(id);
    }

    /**
     * @param id 能过ID获得活动名称
     * @return
     */
    @Override
    public String getGoodsNameById(Integer id) {
        return presentDao.findGoodsNameById(id);
    }

    /**
     * @param activityGoods 兑换物品执行
     * @param id
     * @return
     */
//    @Override
//    @Transactional
//    public ResponseModel exchangeItems(ActivityGoods activityGoods, int id) {
//
//        //执行兑换逻辑：减库存 + 记录兑换行为
//
//        return null;
//    }
}
