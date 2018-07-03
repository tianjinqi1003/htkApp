package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.modules.merchant.pay.dao.BillRecordStatisticsMapper;
import com.htkapp.modules.merchant.pay.entity.BillRecordStatistics;
import com.htkapp.modules.merchant.pay.service.BillRecordStatisticsService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

@Service
public class BillRecordStatisticsServiceImpl implements BillRecordStatisticsService {

    @Resource
    private BillRecordStatisticsMapper billRecordStatisticsDao;

    /* =========================接口开始========================== */
    @Override
    public BillRecordStatistics getBillRecordStatisticsByCurDate(String accountShopToken, String startTime, String endTime) {
        try {
            return billRecordStatisticsDao.getBillRecordStatisticsByCurDateDAO(accountShopToken, startTime, endTime);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void insertBillRecordStatisticsByToken(BillRecordStatistics statistics) throws Exception {
        try {
            int row = billRecordStatisticsDao.insertBillRecordStatisticsByTokenDAO(statistics);
            if (row <= 0) {
                throw new Exception("添加记录失败");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateBillRecordStatisticsById(BillRecordStatistics statistics) throws Exception {
        try {
            int row = billRecordStatisticsDao.updateBillRecordStatisticsByIdDAO(statistics);
            if (row <= 0) {
                throw new Exception("更新记录失败");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //添加记录
    @Override
    public void keepRecordByAccountShopToken(BillRecordStatistics statistics) throws RuntimeException {
        try {
            String startTime = format(DateUtil.beginOfDay(new Date()), NORM_DATETIME_PATTERN);
            String endTime = format(DateUtil.endOfDay(new Date()), NORM_DATETIME_PATTERN);
            BillRecordStatistics recordStatistics = billRecordStatisticsDao.getBillRecordStatisticsByCurDateDAO(statistics.getAccountShopToken(), startTime, endTime);
            if (recordStatistics == null) {
                int row = billRecordStatisticsDao.insertBillRecordStatisticsByTokenDAO(statistics);
                if (row <= 0) {
                    throw new Exception("插入记录失败");
                }
            } else {
                recordStatistics.setOrderIncome(statistics.getOrderIncome() + recordStatistics.getOrderIncome());
                recordStatistics.setAmount(statistics.getAmount() + recordStatistics.getAmount());
                int row = billRecordStatisticsDao.updateBillRecordStatisticsByIdDAO(recordStatistics);
                if (row <= 0) {
                    throw new RuntimeException("更新记录失败");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //取消订单后，减掉金额记录
    @Override
    public void updateBillRecordStatisticsByDate(String token, String startTime, String endTime, Double amount) throws Exception {
        try {
            int row = billRecordStatisticsDao.updateBillRecordStatisticsByDateDAO(token, startTime, endTime, amount);
            if (row <= 0) {
                throw new Exception("执行失败");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //通过时间条件查找数据
    @Override
    public List<BillRecordStatistics> getBillRecordStatisticsListByDate(String accountShopToken, String startTime, String endTime, int pageNumber, int pageLimit) throws Exception {
        try {
            String orderDesc = "bill_record_statistics.gmt_create desc";
            PageHelper.startPage(pageNumber, pageLimit);
            return billRecordStatisticsDao.getBillRecordStatisticsListByDateDAO(accountShopToken, startTime, endTime, orderDesc);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /* =========================接口结束============================ */
}
