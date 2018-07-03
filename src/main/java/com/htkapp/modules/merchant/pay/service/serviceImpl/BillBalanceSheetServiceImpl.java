package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.OtherUtils;
import com.htkapp.modules.merchant.pay.dao.BillBalanceSheetMapper;
import com.htkapp.modules.merchant.pay.entity.BillBalanceSheet;
import com.htkapp.modules.merchant.pay.service.BillBalanceSheetService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static org.apache.commons.lang.time.DateFormatUtils.format;

@Service
public class BillBalanceSheetServiceImpl implements BillBalanceSheetService {

    @Resource
    private BillBalanceSheetMapper billBalanceSheetDAO;

    /* ========================接口开始========================= */
    //获取账户余额
    @Override
    public double getAccountBalance(String accountShopToken) {
        try {
            Double result = billBalanceSheetDAO.getAccountBalanceDAO(accountShopToken);
            return result == null ? 0 : result;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int updateAccountBalance(String accountShopToken, double newBalance) {
        try {
            int row = billBalanceSheetDAO.updateAccountBalance(accountShopToken,newBalance);
            return row;
        } catch (Exception e) {
            return 0;
        }
    }

    //查找收支记录
    @Override
    public List<BillBalanceSheet> getBalanceSheetRecordListByAccountShopToken(String token, String startTime, String endTime, Integer type, int pageNum, int pageLimit) {
        try {
            String orderDesc = "gmt_create desc";
            PageHelper.startPage(pageNum, pageLimit);
            List<BillBalanceSheet> resultList = billBalanceSheetDAO.getBalanceSheetRecordListByAccountShopTokenDAO(token, type, startTime, endTime, orderDesc);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //记录帐单支出、收入记录
    @Override
    public void keepRecordByAccountShopToken(BillBalanceSheet balanceSheet) throws Exception {
        //获取当天的起始和结束日期
        String startTime = format(DateUtil.beginOfDay(new Date()), NORM_DATETIME_PATTERN);
        String endTime = format(DateUtil.endOfDay(new Date()), NORM_DATETIME_PATTERN);
        //查找当天是否已存在记录了，已存在则修改已存在记录值,不存在则插入记录
        BillBalanceSheet result = billBalanceSheetDAO.getBalanceSheetRecordByDateDAO(balanceSheet.getAccountShopToken(), startTime, endTime);
        try {
            if (result == null) {
                //当天的记录不存在,总额从哪里获取
                BillBalanceSheet billBalanceSheet = billBalanceSheetDAO.getBalanceSheetRecordByLastDateDAO(balanceSheet.getAccountShopToken());
                if (billBalanceSheet == null) {
                    //没有记录,把当前要入账金额记为余额
                    balanceSheet.setBalance(balanceSheet.getSumAmount());
                } else {
                    balanceSheet.setBalance(billBalanceSheet.getBalance() + balanceSheet.getSumAmount());
                }
                int row = billBalanceSheetDAO.insetBalanceSheetRecordDAO(balanceSheet);
                if (row <= 0) {
                    throw new Exception("插入账单流水失败");
                }
            } else {
                //存在则修改值
                result.setSumAmount(balanceSheet.getSumAmount() + result.getSumAmount());
                result.setBalance(result.getBalance() + balanceSheet.getSumAmount());
                int row = billBalanceSheetDAO.updateBalanceSheetRecordByIdDAO(result);
                if (row <= 0) {
                    throw new Exception("追加账单流水失败");
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /* ========================接口结束========================= */
}
