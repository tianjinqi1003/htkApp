package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.pay.dao.BillRecordMapper;
import com.htkapp.modules.merchant.pay.entity.BillRecord;
import com.htkapp.modules.merchant.pay.service.BillRecordService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

@Service
public class BillRecordServiceImpl implements BillRecordService {


    @Resource
    private BillRecordMapper billRecordDAO;

    /* ==================接口开始==================== */
    //根据类型获取用户的金额
    @Override
    public double getAmountToBeAccountedByType(String accountShopToken, int type) {
        try {
            Double result = billRecordDAO.getAmountToBeAccountedByTypeDAO(accountShopToken, type);
            return result == null ? 0 : result;
        } catch (Exception e) {
            return 0;
        }
    }

    //查找记录集合
    @Override
    public List<BillRecord> getBillRecordListByToken(String accountShopToken, String startTime, String endTime, Integer type, int pageNumber, int pageLimit) {
        try {
            String descStr = "gmt_create desc";
            List<BillRecord> resultList = billRecordDAO.getBillRecordListByTokenDAO(accountShopToken, startTime, endTime, type, descStr);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //插入记录
    @Override
    public void insertBillRecordByToken(BillRecord obj) throws RuntimeException {
        try {
            //取当天日期,查找是否已存在记录，没有则为1,存在则加1
            Date curDate = new Date();
            String startTime = format(DateUtil.beginOfDay(curDate), NORM_DATETIME_PATTERN);
            String endTime = format(DateUtil.endOfDay(curDate), NORM_DATETIME_PATTERN);
            //根据时间查找当天记录是否存在
            BillRecord billRecord = billRecordDAO.getBillRecordByDateAndTokenDAO(obj.getAccountShopToken(),startTime, endTime);
            if(billRecord != null){
                obj.setSerialNumber(billRecord.getSerialNumber() + 1);
            }else {
                obj.setSerialNumber(1);
            }
            int row = billRecordDAO.insertBillRecordByTokenDA0(obj);
            if (row <= 0) {
                throw new RuntimeException("插入记录失败");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //更改记录状态
    @Override
    public void changeRecordStatusByOrderNumber(String orderNumber, int status) throws Exception {
        try {
            int row = billRecordDAO.changeRecordStatusByOrderNumberDAO(orderNumber, status);
            if (row <= 0) {
                throw new Exception("更改状态失败");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //当天的实收入金额
    @Override
    public Double getTodayIncomeByDate(String accountShopToken, String startTime, String endTime) {
        try {
            Double result = billRecordDAO.getTodayIncomeByDateDAO(accountShopToken, startTime, endTime);
            return result == null ? 0.00 : result;
        } catch (Exception e) {
            return 0.00;
        }
    }

    //当天的订单收入
    @Override
    public Double getTodayOrderIncomeByDate(String accountShopToken, String startTime, String endTime) {
        try {
            Double result = billRecordDAO.getTodayOrderIncomeByDateDAO(accountShopToken, startTime, endTime);
            return result == null ? 0.00 : result;
        } catch (Exception e) {
            return 0.00;
        }
    }

    //当天的订单支出
    @Override
    public Double getSpendingOnOrderByDate(String accountShopToken, String startTime, String endTime) {
        try {
            Double result = billRecordDAO.getSpendingOnOrderByDateDAO(accountShopToken, startTime, endTime);
            return result == null ? 0.00 : result;
        } catch (Exception e) {
            return 0.00;
        }
    }

    //根据日期条件查找记录
    @Override
    public List<BillRecord> getBillRecordListByDate(String accountShopToken, String startTime, String endTime) {
        try {
            List<BillRecord> resultList = billRecordDAO.getBillRecordListByDateDAO(accountShopToken, startTime, endTime);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //取消订单要删除记录
    @Override
    public void deleteRecordByOrderNumberAndDate(String accountShopToken, String orderNumber) throws Exception {
        try {
            int row = billRecordDAO.deleteRecordByOrderNumberAndDateDAO(accountShopToken, orderNumber);
            if (row <= 0) {
                throw new Exception("删除记录失败");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateBillStatus(String accountShopToken, String orderNumber,String status) throws Exception {
        try {
            int row = billRecordDAO.updateBillStatus(accountShopToken, orderNumber,status);
            if (row <= 0) {
                throw new Exception("删除记录失败");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /* ==================接口结束==================== */
}
