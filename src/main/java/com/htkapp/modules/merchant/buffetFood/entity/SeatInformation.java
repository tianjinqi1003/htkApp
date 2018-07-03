package com.htkapp.modules.merchant.buffetFood.entity;

/**
 * 自助点餐座位信息
 */
public class SeatInformation {

    private Integer id;

    private String seatName;

    private Integer numberSeat;  //座位人数

    private Integer shopId;  //商铺id

    private Integer seatStatus;   //座位状态
    
    private BuffetFoodOrder bfo; //座位相关的订单
    
    private String useSeatTime;//使用座位的时间

    public String getUseSeatTime() {
		return useSeatTime;
	}

	public void setUseSeatTime(String useSeatTime) {
		this.useSeatTime = useSeatTime;
	}

	public BuffetFoodOrder getBfo() {
		return bfo;
	}

	public void setBfo(BuffetFoodOrder bfo) {
		this.bfo = bfo;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public Integer getNumberSeat() {
        return numberSeat;
    }

    public void setNumberSeat(Integer numberSeat) {
        this.numberSeat = numberSeat;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(Integer seatStatus) {
        this.seatStatus = seatStatus;
    }
}
