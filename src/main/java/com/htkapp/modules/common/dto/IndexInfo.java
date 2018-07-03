package com.htkapp.modules.common.dto;

/**
 * Created by yinqilei on 17-7-14.
 * 返回的商户主页数据封装类
 */

public class IndexInfo {

    //本次登陆时间
    //上次退出时间
    //剩余使用时间

    //待处理订单(新订单,异常订单,退单)
    //待处理反馈(未回复差评,未回复评价)
    //今日总览(昨日订单总数，昨日营业额)

    //会员尊享平台(正在进行活动数量)

    private String currentLoginTime;  //本次登陆时间

    private String lastExitTime;   //上次退出时间

    private String useRemainingTime;  //使用剩余时间

    private Integer newOrderNumber;  //新订单数量

    private Integer exceptionOrderNumber;  //异常订单数量

    private Integer returnOrderNumber;  //退单数量

    private Integer badCommentNumber;  //未回复差评数量

    private Integer noCommentNumber;  //未回复评价数量

    private Integer yesterdayOrderCount;  //昨日订单数量

    private String yesterdayRevenue; //昨日营业额

    private Integer todayOrderCount;

    private String todayRevenue;

    private Integer shopState; //店铺状态

    private String shopName;  //店铺名

    private Integer memberActiveNumber;  //会员尊享平台活动数量


    private Integer hasBeenCreatedActives;  //已创建的积分兑换活动数量（所有的）,不包括咨讯数量

    private Integer nowActives;  //已创建的且在有效期内的积分兑换活动数量

    public Integer getHasBeenCreatedActives() {
        return hasBeenCreatedActives;
    }

    public void setHasBeenCreatedActives(Integer hasBeenCreatedActives) {
        this.hasBeenCreatedActives = hasBeenCreatedActives;
    }

    public Integer getNowActives() {
        return nowActives;
    }

    public void setNowActives(Integer nowActives) {
        this.nowActives = nowActives;
    }

    public IndexInfo() {
    }

    public IndexInfo(String currentLoginTime, String lastExitTime, String useRemainingTime,
                     Integer newOrderNumber, Integer exceptionOrderNumber, Integer returnOrderNumber,
                     Integer badCommentNumber, Integer noCommentNumber, Integer yesterdayOrderCount,
                     String yesterdayRevenue, Integer memberActiveNumber,Integer todayOrderCount, String todayRevenue,Integer hasBeenCreatedActives,Integer nowActives) {
        this.currentLoginTime = currentLoginTime;
        this.lastExitTime = lastExitTime;
        this.useRemainingTime = useRemainingTime;
        this.newOrderNumber = newOrderNumber;
        this.exceptionOrderNumber = exceptionOrderNumber;
        this.returnOrderNumber = returnOrderNumber;
        this.badCommentNumber = badCommentNumber;
        this.noCommentNumber = noCommentNumber;
        this.yesterdayOrderCount = yesterdayOrderCount;
        this.yesterdayRevenue = yesterdayRevenue;
        this.memberActiveNumber = memberActiveNumber;
        this.todayOrderCount = todayOrderCount;
        this.todayRevenue = todayRevenue;
        this.hasBeenCreatedActives = hasBeenCreatedActives;
        this.nowActives = nowActives;
    }

    public String getCurrentLoginTime() {
        return currentLoginTime;
    }

    public void setCurrentLoginTime(String currentLoginTime) {
        this.currentLoginTime = currentLoginTime;
    }

    public String getLastExitTime() {
        return lastExitTime;
    }

    public void setLastExitTime(String lastExitTime) {
        this.lastExitTime = lastExitTime;
    }

    public String getUseRemainingTime() {
        return useRemainingTime;
    }

    public void setUseRemainingTime(String useRemainingTime) {
        this.useRemainingTime = useRemainingTime;
    }

    public Integer getNewOrderNumber() {
        return newOrderNumber;
    }

    public void setNewOrderNumber(Integer newOrderNumber) {
        this.newOrderNumber = newOrderNumber;
    }

    public Integer getExceptionOrderNumber() {
        return exceptionOrderNumber;
    }

    public void setExceptionOrderNumber(Integer exceptionOrderNumber) {
        this.exceptionOrderNumber = exceptionOrderNumber;
    }

    public Integer getReturnOrderNumber() {
        return returnOrderNumber;
    }

    public void setReturnOrderNumber(Integer returnOrderNumber) {
        this.returnOrderNumber = returnOrderNumber;
    }

    public Integer getBadCommentNumber() {
        return badCommentNumber;
    }

    public void setBadCommentNumber(Integer badCommentNumber) {
        this.badCommentNumber = badCommentNumber;
    }

    public Integer getNoCommentNumber() {
        return noCommentNumber;
    }

    public void setNoCommentNumber(Integer noCommentNumber) {
        this.noCommentNumber = noCommentNumber;
    }

    public Integer getYesterdayOrderCount() {
        return yesterdayOrderCount;
    }

    public void setYesterdayOrderCount(Integer yesterdayOrderCount) {
        this.yesterdayOrderCount = yesterdayOrderCount;
    }

    public String getYesterdayRevenue() {
        return yesterdayRevenue;
    }

    public void setYesterdayRevenue(String yesterdayRevenue) {
        this.yesterdayRevenue = yesterdayRevenue;
    }

    public Integer getMemberActiveNumber() {
        return memberActiveNumber;
    }

    public void setMemberActiveNumber(Integer memberActiveNumber) {
        this.memberActiveNumber = memberActiveNumber;
    }

    public Integer getTodayOrderCount() {
        return todayOrderCount;
    }

    public void setTodayOrderCount(Integer todayOrderCount) {
        this.todayOrderCount = todayOrderCount;
    }

    public String getTodayRevenue() {
        return todayRevenue;
    }

    public void setTodayRevenue(String todayRevenue) {
        this.todayRevenue = todayRevenue;
    }

    public Integer getShopState() {
        return shopState;
    }

    public void setShopState(Integer shopState) {
        this.shopState = shopState;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
