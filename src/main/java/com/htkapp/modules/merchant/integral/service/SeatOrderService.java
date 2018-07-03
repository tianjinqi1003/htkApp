package com.htkapp.modules.merchant.integral.service;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.API.dto.SeatOrderDetail;
import com.htkapp.modules.merchant.integral.entity.SeatOrder;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.directwebremoting.guice.RequestParameters;

/**
 *
 */
public interface SeatOrderService {

    /* =====================接口开始===================== */
    //根据用户token查找用户下的订座订单列表和店铺信息
    List<SeatOrder> getSeatOrderListByTokenAndShopId(String token, int shopId, int pageNumber, int pageLimit);
    //通过订单号查询订单详情
    SeatOrder getSeatOrderByOrderNumber(String orderNumber);
    //插入座位预定
    void insertSeatOrderByToken(SeatOrder order);
    //根据店铺号查询店铺名下所有的订座信息
    List<SeatOrder> getSeatOrderListByShopId(String shopId,String startTime,String endTime);
    //订座订单操作(安排座位)
    int updataSeatInfo(String seatName,String orderNumber);
  //根据店铺号以及状态码查询店铺名下未处理的订单
    List<SeatOrder> getSeatOrderListByShopIdAndStatus(String shopId);
    //根据订单号撤销对应的订座订单
    int deleteSeatOrder(List<String> idInts);
    //根据订单号改变订座订单的状态
    int updataSeatInfoStatus(List<String> idInts);
    
    /* =====================接口结束===================== */

    /* ===================JSP接口开始========================= */
    //插入座位订单
    int insert(SeatOrder seatOrder);
    //根据订座订单号查询订座详情
    SeatOrderDetail getSeatOrderDetail(String orderNumber, String token);
    /* ===================JSP接口结束========================= */
}
