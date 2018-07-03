package com.htkapp.modules.merchant.shop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by yinqilei on 17-3-2.
 */
public class ShopComment {

    private Integer shopCommentId;   //主键

    private String shopCommentContent;   //评论内容

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date shopCommentTime;     //评论时间

    private Integer shopCommentAccountId;   //评论用户ID

    private Integer shopCommentShopId;    //商店ID

    private String userName;   //评论用户名称

    private String shopName;  //商户名字

    public Integer getShopCommentId() {
        return shopCommentId;
    }

    public void setShopCommentId(Integer shopCommentId) {
        this.shopCommentId = shopCommentId;
    }

    public String getShopCommentContent() {
        return shopCommentContent;
    }

    public void setShopCommentContent(String shopCommentContent) {
        this.shopCommentContent = shopCommentContent;
    }

    public Date getShopCommentTime() {
        return shopCommentTime;
    }

    public void setShopCommentTime(Date shopCommentTime) {
        this.shopCommentTime = shopCommentTime;
    }

    public Integer getShopCommentAccountId() {
        return shopCommentAccountId;
    }

    public void setShopCommentAccountId(Integer shopCommentAccountId) {
        this.shopCommentAccountId = shopCommentAccountId;
    }

    public Integer getShopCommentShopId() {
        return shopCommentShopId;
    }

    public void setShopCommentShopId(Integer shopCommentShopId) {
        this.shopCommentShopId = shopCommentShopId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
