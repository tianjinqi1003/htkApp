package com.htkapp.modules.merchant.buffetFood.entity;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.merchant.integral.entity.AccountUseTicketList;

import java.sql.Date;
import java.util.List;

/**
 * 自助点餐订单实体表
 */

public class BuffetFoodOrder {

    private Integer id;

    private String orderNumber;  //订单号  No

    private Double orderAmount;  //订单金额

    private Double discountAmount;  //抵扣金额

    private String shopAddress; //地址  No

    private String orderBody;  //订单内容 No

    private String seatName;   //座位号

    private Integer paymentMethod;   //0微信    1支付宝

    private Integer orderState;  //订单状态  1:下单成功

    private List<BuffetFoodOrderProduct> productLists; //产品集合

    private String jsonProductList;  //产品集合json

    private String token;  //app用户token

    private Integer shopId;   //店铺id
    
    private String orderTime;   //下单时间
    
    private String orderHandle;

    private String shopName;  //店铺名
    
    private String logoUrl;   //店铺图片url

    private String adjustOrderJson;   //调整订单的数据

    private String adjustOrderProductJson;  //调整订单的产品数据

    private String remark;  //备注

    private Integer payState; //支付状态

    private Integer serialNumber;  //当天的序号

    private Integer allSerialNumber;  //总列表的序号

    private Integer discountCouponId;  //优惠券id

    private String minute;  //提交分钟数

    private int sum;
    
    private int adjustState;//调单状态
    
    
    private List<AccountUseTicketList> ticketList;//用户名下的优惠券
    
    private String tempSeatName;//临时座位名称
    

	public String getTempSeatName() {
		return tempSeatName;
	}

	public void setTempSeatName(String tempSeatName) {
		this.tempSeatName = tempSeatName;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public List<AccountUseTicketList> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<AccountUseTicketList> ticketList) {
		this.ticketList = ticketList;
	}

	public int getAdjustState() {
		return adjustState;
	}

	public void setAdjustState(int adjustState) {
		this.adjustState = adjustState;
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

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderBody() {
        return orderBody;
    }

    public void setOrderBody(String orderBody) {
        this.orderBody = orderBody;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public List<BuffetFoodOrderProduct> getProductLists() {
        return productLists;
    }

    public void setProductLists(List<BuffetFoodOrderProduct> productLists) {
        this.productLists = productLists;
    }

    public String getJsonProductList() {
        return jsonProductList;
    }

    public void setJsonProductList(String jsonProductList) {
        if (StringUtils.isNotEmpty(jsonProductList)) {
            Gson gson = new Gson();
            productLists = gson.fromJson(jsonProductList, new TypeToken<List<BuffetFoodOrderProduct>>() {
            }.getType());
        }
        this.jsonProductList = jsonProductList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getOrderHandle() {
        return orderHandle = "查看详情";
    }

    public void setOrderHandle(String orderHandle) {
        this.orderHandle = orderHandle;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getAllSerialNumber() {
        return allSerialNumber;
    }

    public void setAllSerialNumber(Integer allSerialNumber) {
        this.allSerialNumber = allSerialNumber;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getAdjustOrderJson() {
        return adjustOrderJson;
    }

    public void setAdjustOrderJson(String adjustOrderJson) {
        this.adjustOrderJson = adjustOrderJson;
    }

    public String getAdjustOrderProductJson() {
        return adjustOrderProductJson;
    }

    public void setAdjustOrderProductJson(String adjustOrderProductJson) {
        this.adjustOrderProductJson = adjustOrderProductJson;
    }

    public Integer getDiscountCouponId() {
        return discountCouponId;
    }

    public void setDiscountCouponId(Integer discountCouponId) {
        this.discountCouponId = discountCouponId;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

	@Override
	public String toString() {
		return "BuffetFoodOrder [id=" + id + ", orderNumber=" + orderNumber + ", orderAmount=" + orderAmount
				+ ", discountAmount=" + discountAmount + ", shopAddress=" + shopAddress + ", orderBody=" + orderBody
				+ ", seatName=" + seatName + ", paymentMethod=" + paymentMethod + ", orderState=" + orderState
				+ ", productLists=" + productLists + ", jsonProductList=" + jsonProductList + ", token=" + token
				+ ", shopId=" + shopId + ", orderTime=" + orderTime + ", orderHandle=" + orderHandle + ", shopName="
				+ shopName + ", logoUrl=" + logoUrl + ", adjustOrderJson=" + adjustOrderJson
				+ ", adjustOrderProductJson=" + adjustOrderProductJson + ", remark=" + remark + ", payState=" + payState
				+ ", serialNumber=" + serialNumber + ", allSerialNumber=" + allSerialNumber + ", discountCouponId="
				+ discountCouponId + ", minute=" + minute + ", sum=" + sum + ", adjustState=" + adjustState
				+ ", ticketList=" + ticketList + "]";
	}
	
}
