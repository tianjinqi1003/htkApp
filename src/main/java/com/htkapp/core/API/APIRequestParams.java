package com.htkapp.core.API;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by yinqilei on 17-6-22.
 * api请求参数封装类
 */
public class APIRequestParams<T> {

    private String token;  //用户的唯一标识

    private String role;   //身份验证

    private Integer pageNumber;  //页数

    private String phone;   //手机号

    private String userName; //登陆用户名

    private String password; //登陆密码

    private String oldP;  //旧密码

    private String newP;  //新密码

    private Integer code;  //手机收到的验证码

    private short loginWay;  //登陆方式

    private MultipartFile avaImgFile;

    private String nickName;

    private String weChatToken;  //接入第三方微信登陆返回的标识

    private String qqToken;   //接入第三方qq登陆返回的标识

    private String avatarUrl;  //第三方头像url地址

    private String appIp;  //微信支付调起传入的appIp

    private Integer flag;  //标记

    private String content; //内容

    private int mark;  //区别：　0外卖　1团购 2自助点餐

    private String orderNumber;  //订单号

    private Integer id;  //主键id

    private Integer shopId;  //店铺id

    private String searchKey;  //关键字搜索商品

    private Integer productId;  //产品id

    private Integer categoryId; //分类id

    private Integer state;  //状态

    private Integer ticketId;  //优惠券id
    
    private String trueName;  //真实姓名

	private String cardID;  //身份证号
	
	private Integer riderId;

    public Integer getRiderId() {
		return riderId;
	}

	public void setRiderId(Integer riderId) {
		this.riderId = riderId;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldP() {
        return oldP;
    }

    public void setOldP(String oldP) {
        this.oldP = oldP;
    }

    public String getNewP() {
        return newP;
    }

    public void setNewP(String newP) {
        this.newP = newP;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public short getLoginWay() {
        return loginWay;
    }

    public void setLoginWay(short loginWay) {
        this.loginWay = loginWay;
    }

    public MultipartFile getAvaImgFile() {
        return avaImgFile;
    }

    public void setAvaImgFile(MultipartFile avaImgFile) {
        this.avaImgFile = avaImgFile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWeChatToken() {
        return weChatToken;
    }

    public void setWeChatToken(String weChatToken) {
        this.weChatToken = weChatToken;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getQqToken() {
        return qqToken;
    }

    public void setQqToken(String qqToken) {
        this.qqToken = qqToken;
    }

    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
    
    public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
}
