package com.htkapp.modules.common.dto;

/**
 * Created by yinqilei on 17-7-4.
 * 通知中心数据返回封装类
 */
public class ReturnNoticeCenterData {

    private String noticeTitle;  //通知标题

    private String noticeContent; //通知内容

    private String noticeTime;  //

    private Integer orderId;  //关联的订单id

    private Integer shopId;  //店铺id

    private Integer mark; //标识

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }
}
