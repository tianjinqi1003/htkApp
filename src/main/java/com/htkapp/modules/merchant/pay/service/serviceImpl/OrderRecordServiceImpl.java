package com.htkapp.modules.merchant.pay.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.LogUtil;
import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.exception.costom.NullDataException;
import com.htkapp.core.exception.order.OrderException;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.entity.AccountTicketList;
import com.htkapp.modules.merchant.integral.service.AccountTicketListService;
import com.htkapp.modules.merchant.pay.dao.OrderRecordMapper;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;
import static com.xiaoleilu.hutool.date.DateUtil.now;

/**
 * Created by yinqilei on 17-6-29.
 */
@Service
public class OrderRecordServiceImpl implements OrderRecordService {

    @Resource
    private OrderRecordMapper orderRecordDao;

    @Resource
    private AccountTicketListService ticketListService;

    Class<? extends Object> cls = OrderRecordServiceImpl.class;

    /* ================接口开始======================= */
    //支付成功后插入订单信息
    @Transactional
    @Override
    public void paymentSuccessfullyCreatedOrder(OrderRecord orderRecord) throws Exception {
        try {
            String startTime = format(DateUtil.beginOfDay(new Date()), NORM_DATETIME_PATTERN);
            String endTime = format(DateUtil.endOfDay(new Date()), NORM_DATETIME_PATTERN);
            //先根据当天日期查找是否有订单，已经有订单则获取序号、序号加1插入到数据库, 没有则插入记录、序号初始为1
            OrderRecord o = orderRecordDao.verifyOrderExitByDateDAO(orderRecord.getShopId(), startTime, endTime, orderRecord.getMark());
            Integer allNumberVal = orderRecordDao.getAllNumberByLimitDAO(orderRecord.getShopId()) == null ? 0 : orderRecordDao.getAllNumberByLimitDAO(orderRecord.getShopId());
            if (o != null) {
                //已插入记录
                Integer num = o.getNumber() + 1;
                orderRecord.setNumber(num);
            }
            orderRecord.setAllNumber(allNumberVal + 1);
            //这是插入order_record表记录
            int row = orderRecordDao.paymentSuccessfullyCreatedOrderDAO(orderRecord);
            if (row <= 0) {
                throw new Exception(Globals.CALL_DATABASE_ERROR);
            }

            //更新用户拥有的优惠券数量
            List<AccountTicketList> accountTicketLists = ticketListService.getTicketListByTokenAndCouponId(orderRecord.getToken(), orderRecord.getCouponId());
            //使用优惠券的情况下才操作
            if (accountTicketLists != null && accountTicketLists.size() > 0) {
                int newQuantity = 0;
                int nowQuantity = accountTicketLists.get(0).getQuantity();
                if (nowQuantity > 0)
                    newQuantity = nowQuantity - 1;

                ticketListService.updateTicketListByTokenAndCouponIdDAO(newQuantity, orderRecord.getToken(), orderRecord.getCouponId());
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //根据订单id查询订单信息
    @Override
    public OrderRecord getOrderRecordById(int orderId) throws Exception {
        try {
            OrderRecord orderRecord = orderRecordDao.getOrderRecordByIdDAO(orderId);
            if (orderRecord != null) {
                return orderRecord;
            } else {
                throw new NullDataException("查询订单失败");
            }
        } catch (NullDataException e1) {
            throw new NullDataException(e1.getMessage());
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单id查询订单信息列表
    @Override
    public List<OrderRecord> getOrderRecordListById(String token, Integer mark, int pageNo, int pageLimit, String orderByDesc) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<OrderRecord> result = orderRecordDao.getOrderRecordListByIdDAO(token, mark, orderByDesc);
            if (result != null && result.size() > 0) {
                return result;
            } else {
                return null;
            }
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单号查询订单信息
    @Override
    public OrderRecord getOrderRecordByOrderNumber(String orderNumber) throws Exception {
        try {
            return orderRecordDao.getOrderRecordByOrderNumberDAO(orderNumber);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单id查询订单信息
    @Override
    public OrderRecord getOrderRecordByOrderId(int orderId) throws Exception {
        try {
            return orderRecordDao.getOrderRecordByOrderIdDAO(orderId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //取消支付成功的订单并改变订单状态
    @Override
    @Transactional
    public boolean changeOrderStateByOrderNumber(String orderNumber, int orderState) throws OrderException {
        try {
            int row = orderRecordDao.changeOrderStateByOrderNumberDAO(orderNumber, orderState);
            return row > 0;
        } catch (OrderException e) {
            throw new OrderException(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过商铺id查询商铺下的所有订单
    @Override
    public List<OrderRecord> getOrderListByShopId(Integer shopId, Integer mark, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<OrderRecord> list = orderRecordDao.getOrderListByShopIdDAO(shopId, mark);
            if (list != null && list.size() > 0) {
                return list;
            } else {
                throw new NullDataException("当前店铺下没有评论");
            }
        } catch (NullDataException e1) {
            throw new NullDataException(e1.getMessage());
        } catch (Exception e2) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过商铺id和商铺类型mark　查询订单列表
    @Override
    public List<OrderRecord> getOrderListByShopIdAndMark(int shopId, int mark, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<OrderRecord> recordList = orderRecordDao.getOrderListByShopIdAndMarkDAO(shopId, mark);
            if (recordList != null && recordList.size() > 0) {
                return recordList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //商户处理外卖单 接单或拒单
    @Override
    public boolean handlesTakeoutOrder(int shopId, String orderNumber, int stateId) throws RuntimeException {
        try {
            int row = orderRecordDao.handlesTakeoutOrderDAO(shopId, orderNumber, stateId);
            return row > 0;
        } catch (Exception e) {
            throw new RuntimeException(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单号和商铺id验证订单信息
    @Override
    public OrderRecord verifyOrderInformation(int shopId, String orderNumber) throws Exception {
        try {
            return orderRecordDao.verifyOrderInformationDAO(shopId, orderNumber);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据条件查询已处理或未处理的团购订单和外卖订单列表
    @Override
    public List<OrderRecord> getAllProcessedOrderListByIdAndOrderState(int accountShopId, int mark, String orderDesc, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<OrderRecord> resultList = orderRecordDao.getAllProcessedOrderListByIdAndOrderStateDAO(accountShopId, mark, orderDesc);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查询已处理或未处理的团购订单
    @Override
    public List<OrderRecord> getGroupBuyOrderListByIdAndOrderState(int shopId, int mark, String orderDesc, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<OrderRecord> resultList = orderRecordDao.getGroupBuyOrderListByIdAndOrderStateDAO(shopId, mark, orderDesc);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查询已处理或未处理的外卖订单
    @Override
    public List<OrderRecord> getTakeoutOrderListByIdAndOrderState(int shopId, int mark, String orderDesc, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<OrderRecord> resultList = orderRecordDao.getTakeoutOrderListByIdAndOrderStateDAO(shopId, mark, orderDesc);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过订单号删除订单
    @Override
    public void deleteOrderByOrderNumber(String orderNumber, String token) {
        int row = orderRecordDao.deleteOrderByOrderNumberDAO(orderNumber, token);
        if (row <= 0) {
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }

    //根据订单号和店铺id查找订单
    @Override
    public OrderRecord getOrderRecordByTokenAndShopId(String orderNumber, int shopId) {
        return orderRecordDao.getOrderRecordByTokenAndShopIdDAO(orderNumber, shopId);
    }

    /* =================接口结束==================================== */


    /* ====================JSP页面接口开始========================= */
    //根据商户id和mark查询当天订单数量
    @Override
    public int getOrderListCountByAccountShopIdAndMark(int accountShopId, int mark, String time) throws Exception {
        try {
            return orderRecordDao.getOrderListCountByAccountShopIdAndMarkDAO(accountShopId, mark, time);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据商户id查询商户当天的收入金额
    @Override
    public Double getTodayIncomeByAccountShopId(int accountShopId, String time) throws Exception {
        try {
            Double result = orderRecordDao.getTodayIncomeByAccountShopIdDAO(accountShopId, time);
            if (result == null) {
                return 0D;
            } else {
                return result;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //商家外卖管理查找订单列表(全部列表、已处理、未处理)
    @Override
    public List<OrderRecord> getTakeoutOrderListByOrderState(int orderState, int accountShopId, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<OrderRecord> resultList = orderRecordDao.getTakeoutOrderListByOrderStateDAO(orderState, accountShopId);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //商家外卖管理查找全部订单列表加条件
    @Override
    public List<OrderRecord> getTakeoutOrderListByCondition(int accountShopId, String orderNumber, int orderMark, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<OrderRecord> orderRecordList = orderRecordDao.getTakeoutOrderListByConditionDAO(accountShopId, orderNumber, orderMark);
            if (orderRecordList != null && orderRecordList.size() > 0) {
                return orderRecordList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //商家团购管理查找订单列表(全部列表、已处理、未处理)
    @Override
    public List<OrderRecord> getGroupBuyOrderListByOrderState(int orderState, int accountShopId, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<OrderRecord> resultList = orderRecordDao.getGroupBuyOrderListByOrderStateDAO(orderState, accountShopId);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //商家团购管理查找全部订单列表加条件
    @Override
    public List<OrderRecord> getGroupBuyOrderListByCondition(int accountShopId, String orderNumber, int orderMark, int pageNo, int pageLimit) throws Exception {
        try {
            List<OrderRecord> resultList = orderRecordDao.getGroupBuyOrderListByConditionDAO(accountShopId, orderNumber, orderMark);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查找当前未接单数量
    @Override
    public int getNewOrderNumber(String accountShopToken) throws Exception {
        try {
            return orderRecordDao.getNewOrderNumberDAO(accountShopToken);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //昨日订单数
    @Override
    public int getOrderNumberByDate(String accountShopToken, String startTime, String endTime) throws Exception {
        try {
            return orderRecordDao.getOrderNumberByDate(accountShopToken, startTime, endTime);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //昨日营业额
    @Override
    public double getRevenueByDate(String accountShopToken, String startTime, String endTime) throws Exception {
        try {
            Double result = orderRecordDao.getRevenueByDate(accountShopToken, startTime, endTime);
            if (result == null) {
                return 0;
            } else {
                //数值太小显示不出营业额的原因
//                return result.shortValue();
                return result.doubleValue();
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查找外卖订单列表
    @Override
    public List<OrderRecord> getTakeoutOrderListByToken(String accountShopToken, int stateId, int pageNo, int pageLimit, String orderNumber) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            String orderDesc = "gmt_create desc";
            List<OrderRecord> resultList = orderRecordDao.getTakeoutOrderListByTokenDAO(accountShopToken, stateId, orderDesc, orderNumber);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //查找团购订单列表
    @Override
    public List<OrderRecord> getGroupBuyOrderListByToken(String accountShopToken, int stateId, int pageNo, int pageLimit, String orderNumber) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            String orderDesc = "gmt_create desc";
            List<OrderRecord> resultList = orderRecordDao.getGroupBuyOrderListByTokenDAO(accountShopToken, stateId, orderDesc, orderNumber);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单号查询订单信息
    @Override
    public OrderRecord getOrderDetailByOrderNumber(String accountShopToken, String orderNumber) throws Exception {
        try {
            return orderRecordDao.getOrderDetailByOrderNumberDAO(accountShopToken, orderNumber);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //外卖订单页面订单详情(筛选条件1：全部，订单序号，下单时间  筛选条件２：日期)
    @Override
    public List<OrderRecord> getOrderPageDataByCondition(int shopId, int mark, int pageNo, int pageLimit, String startDate, String endDate, int status) {
        PageHelper.startPage(pageNo, pageLimit);
        String orderDesc = "gmt_create desc";
        List<OrderRecord> resultList = orderRecordDao.getOrderPageDataByConditionDAO(shopId, mark, startDate, endDate, status, orderDesc);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    //外卖订单－实时订单条件查询
    @Override
    public List<OrderRecord> getTakeoutRealTimeOrderByCondition(int shopId, String startDate, String endDate, int statusCode) throws Exception {
        try {
            List<OrderRecord> resultList = orderRecordDao.getTakeoutRealTimeOrderByConditionDAO(shopId, startDate, endDate, statusCode);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            }
            return null;
        } catch (Exception e) {
            LogUtil.error(cls, e.getMessage(), e);
            throw new Exception(e.getMessage());
        }
    }

    //根据日期查找是否已有订单记录（用于插入序号）
    @Override
    public OrderRecord verifyOrderExitByDate(Integer shopId, String startTime, String endTime, int mark) {
        try {
            return orderRecordDao.verifyOrderExitByDateDAO(shopId, startTime, endTime, mark);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //根据时间统计订单数量
    @Override
    public int statisticalOrderQuantityByStateIdAndDate(Integer shopId, Integer stateId, String startTime, String endTime) {
        try {
            return orderRecordDao.statisticalOrderQuantityByStateIdAndDateDAO(shopId, stateId, startTime, endTime);
        } catch (Exception e) {
            return 0;
        }
    }

    //根据时间统计订单总收入
    @Override
    public Double statisticalIncomeByDate(Integer shopId, String startTime, String endTime) {
        try {
            Double result = orderRecordDao.statisticalIncomeByDateDAO(shopId, startTime, endTime);
            if (result == null) {
                return 0.00;
            } else {
                return result;
            }
        } catch (Exception e) {
            return 0.00;
        }
    }

    //根据时间和状态统计当前状态下的订单数量
    @Override
    public int statisticalQuantityByStateIdAndDate(Integer shopId, Integer stateId, String startTime, String endTime) {
        try {
            return orderRecordDao.statisticalQuantityByStateIdAndDateDAO(shopId, stateId, startTime, endTime);
        } catch (Exception e) {
            return 0;
        }
    }

    //根据商铺id和排序条件查询订单列表
    @Override
    public List<OrderRecord> getOrderRecordListByDescAndShopId(int shopId, String orderDesc, int pageNum, int pageLimit, String keyWord) {
        PageHelper.startPage(pageNum, pageLimit);
        List<OrderRecord> orderRecordList = orderRecordDao.getOrderRecordListByDescAndShopIdDAO(shopId, orderDesc, keyWord);
        if (orderRecordList != null && orderRecordList.size() > 0) {
            return orderRecordList;
        }
        return null;
    }

    @Override
    public OrderProduct getOrderProduct(Integer id) {
        OrderProduct orderProduct = orderRecordDao.getOrderProduct(id);
        return orderProduct;
    }

    @Override
    public int getOrderQuantities(Integer shopId, String dateStart, String dateEnd) {

        int row = orderRecordDao.getOrderHasDealedQuantities(shopId, dateStart, dateEnd);
        if (row <= 0)
            row = 0;
        return row;
    }

    /* ====================JSP页面接口结束========================= */
}
