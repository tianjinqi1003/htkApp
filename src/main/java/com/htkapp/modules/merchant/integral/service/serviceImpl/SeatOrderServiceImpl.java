package com.htkapp.modules.merchant.integral.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.core.curdException.InsertException;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dto.SeatOrderDetail;
import com.htkapp.modules.merchant.integral.dao.SeatOrderMapper;
import com.htkapp.modules.merchant.integral.entity.SeatOrder;
import com.htkapp.modules.merchant.integral.service.SeatOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SeatOrderServiceImpl implements SeatOrderService {

    @Resource
    private SeatOrderMapper seatOrderDao;

    /* =====================接口开始========================== */
    //根据用户token查找用户下的订座订单列表和店铺信息
    @Override
    public List<SeatOrder> getSeatOrderListByTokenAndShopId(String token, int shopId, int pageNumber, int pageLimit) {
        String orderBy = "gmt_create desc";
        PageHelper.startPage(pageNumber, pageLimit);
        List<SeatOrder> resultList = seatOrderDao.getSeatOrderListByTokenAndShopIdDAO(token,shopId, orderBy);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }
    //通过订单号查询订单详情
    @Override
    public SeatOrder getSeatOrderByOrderNumber(String orderNumber) {
        return seatOrderDao.getSeatOrderByOrderNumberDAO(orderNumber);
    }

    //插入座位预定
    @Override
    public void insertSeatOrderByToken(SeatOrder order) {
        int row = seatOrderDao.insertSeatOrderByTokenDAO(order);
        if(row <= 0){
            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
        }
    }

    //根据订座订单号查询订座详情
    @Override
    public SeatOrderDetail getSeatOrderDetail(String orderNumber, String token) {
        return seatOrderDao.getSeatOrderDetailDAO(orderNumber, token);
    }

    /* =====================接口结束======================== */


    /* =====================JSP接口开始========================= */
    //插入座位订单
    @Override
    public int insert(SeatOrder seatOrder) {
        return seatOrderDao.insertSeatOrderData(seatOrder);
    }
    /* =====================JSP接口结束========================= */
    //查询店铺名下的所有预定订单
	@Override
	public List<SeatOrder> getSeatOrderListByShopId(String shopId,String startTime,String endTime) {
		List<SeatOrder> list=seatOrderDao.getSeatOrderListByShopId(shopId,startTime,endTime);
		if(list.size()<=0) {
			return list;
		}
		return list;
	}
	@Override
	public int updataSeatInfo(String seatName, String orderNumber) {
		int row=seatOrderDao.updataSeatInfo(seatName, orderNumber);
		 if(row <= 0){
	            throw new InsertException(Globals.DEFAULT_EXCEPTION_INSERT_FAILED);
	        }
		 return row;
	}
	@Override
	public List<SeatOrder> getSeatOrderListByShopIdAndStatus(String shopId) {
		int status=0;
		List<SeatOrder> list=seatOrderDao.getSeatOrderListByShopIdAndStatus(shopId, status);
		if(list.size()<=0) {
			return list;
		}
		return list;
	}
	@Override
	public int deleteSeatOrder(List<String> idInts) {
		int a =seatOrderDao.deleteSeatOrder(idInts);
		return a;
	}
	@Override
	public int updataSeatInfoStatus(List<String> idInts) {
		int a =seatOrderDao.updataSeatInfoStatus(idInts);
		return a;
	}

}
