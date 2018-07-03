package com.htkapp.modules.merchant.pay.dto;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.integral.entity.SeatOrder;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;

import java.util.List;

public class AllOrderList {

//    private String orderNumber;  //订单号
//
//    private Double orderAmount;  //订单金额
//
//    private String receiptName;  //收货人姓名
//
//    private Integer orderState;  //订单状态  1:用户下单成功   2:商家接单成功   3:派送中   4:用户收货成功 5取消订单
//
//    private String receivingCall;  //收货电话
//
//    private String orderTime;   //下单时间
//
//    private Integer paymentMethod;   //0微信    1支付宝
//
//    private Integer sex;   //收货人性别
//
//    private String shippingAddress;  //收货地址
//
//    private Integer shopId;   //店铺id
//
//    private Integer mark;  //标记  0是外卖   1是团购

    private List<OrderRecord> orderRecordList;

    private List<SeatOrder> seatOrderList;

    private List<BuffetFoodOrder> buffetFoodOrderList;

    public List<OrderRecord> getOrderRecordList() {
        return orderRecordList;
    }

    public void setOrderRecordList(List<OrderRecord> orderRecordList) {
        this.orderRecordList = orderRecordList;
    }

    public List<SeatOrder> getSeatOrderList() {
        return seatOrderList;
    }

    public void setSeatOrderList(List<SeatOrder> seatOrderList) {
        this.seatOrderList = seatOrderList;
    }

    public List<BuffetFoodOrder> getBuffetFoodOrderList() {
        return buffetFoodOrderList;
    }

    public void setBuffetFoodOrderList(List<BuffetFoodOrder> buffetFoodOrderList) {
        this.buffetFoodOrderList = buffetFoodOrderList;
    }
}
