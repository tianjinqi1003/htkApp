package com.htkapp.modules.merchant.integral.entity;

import com.htkapp.modules.common.entity.CommonShopInfo;

import java.sql.Timestamp;

/**
 * 订座实体
 */
public class SeatOrder extends CommonShopInfo {

    private Integer id;  //主键

    private String orderNumber;  //订单号

    private String scheduledName;  //预定人名字

    private Integer seatCount;  //预订人数

    private String seatName;  //座位名

    private String scheduledTime;  //预定时间

    private String seatPhone;  //联系手机号

    private Integer shopId;   //店铺ID

    private String accountToken;   //用户token

    private String remarks;  //备注

    private Integer status;  //状态

    private Timestamp gmtCreate;  //创建时间

    private Timestamp gmtModified;  //最后修改时间

    private String orderTime; //订单下单时间

    private int mark;

    private long timeLeft;  //剩余时间

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public String getSeatPhone() {
        return seatPhone;
    }

    public void setSeatPhone(String seatPhone) {
        this.seatPhone = seatPhone;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getScheduledName() {
        return scheduledName;
    }

    public void setScheduledName(String scheduledName) {
        this.scheduledName = scheduledName;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

	@Override
	public String toString() {
		return "SeatOrder [id=" + id + ", orderNumber=" + orderNumber + ", scheduledName=" + scheduledName
				+ ", seatCount=" + seatCount + ", seatName=" + seatName + ", scheduledTime=" + scheduledTime
				+ ", seatPhone=" + seatPhone + ", shopId=" + shopId + ", accountToken=" + accountToken + ", remarks="
				+ remarks + ", status=" + status + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified
				+ ", orderTime=" + orderTime + ", mark=" + mark + ", timeLeft=" + timeLeft + "]";
	}
    
}
