package com.htkapp.modules.merchant.common.dto;

import com.htkapp.modules.merchant.pay.entity.OrderBuyPackage;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;

import java.util.List;

/**
 * 团购和外卖评论列表
 */

public class CommentListInfo {

    private Integer id;  //评论表id

    private String content;  //内容

    private double commentsStars;  //星级

    private String orderNumber;  //订单号

    private String commentTime;  //评论时间

    private String productName; //商品名

    private double price;  //价格

    private int quantity;  //数量

    private long orderId;  //订单表id

    private String replyContent; //回复内容

    private String productNameStr;  //商品名字符串

    private List<OrderProduct> productList;

    private OrderBuyPackage orderBuyPackage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getCommentsStars() {
        return commentsStars;
    }

    public void setCommentsStars(double commentsStars) {
        this.commentsStars = commentsStars;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public List<OrderProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderProduct> productList) {
        this.productList = productList;
    }

    public OrderBuyPackage getOrderBuyPackage() {
        return orderBuyPackage;
    }

    public void setOrderBuyPackage(OrderBuyPackage orderBuyPackage) {
        this.orderBuyPackage = orderBuyPackage;
    }

    public String getProductNameStr() {
        return productNameStr;
    }

    public void setProductNameStr(String productNameStr) {
        this.productNameStr = productNameStr;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
