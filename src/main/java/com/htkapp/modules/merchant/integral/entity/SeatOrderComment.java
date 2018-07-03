package com.htkapp.modules.merchant.integral.entity;

import java.util.Date;

/**
 * 订座订单 评论类
 */
public class SeatOrderComment {

    private Integer id;   //主键id

    private Integer seatOrderId;  //订座订单表id

    private String seatOrderComment;   //订座单评论

    private String userToken;    //用户标识信息

    private Date gmtCreate;    //创建时间

    private Date gmtModified;  //最后修改时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatOrderId() {
        return seatOrderId;
    }

    public void setSeatOrderId(Integer seatOrderId) {
        this.seatOrderId = seatOrderId;
    }

    public String getSeatOrderComment() {
        return seatOrderComment;
    }

    public void setSeatOrderComment(String seatOrderComment) {
        this.seatOrderComment = seatOrderComment;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
