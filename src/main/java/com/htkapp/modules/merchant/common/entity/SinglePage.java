package com.htkapp.modules.merchant.common.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 单页显示
 * @author Administrator
 *
 */
public class SinglePage {
	
	private Integer id;  //主键ID
	
	private String title;   //标题
	
	private String declaration;  //声明
	
	private String content;  //内容

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;  //活动开始时间

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;   //活动结束时间
	
	private String singleType;   //类型:积分消费，打折优惠

	private Integer shopId;   //店铺ID
	
	private Integer accountShopId;  //商户ID

	private Boolean longTerm;

	private String input1;

	private String input2;

	private String input3;
	
	private Date gmt_create;   //创建时间
	
	private Date gmt_modifiedn;  //最后修改时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSingleType() {
		return singleType;
	}

	public void setSingleType(String singleType) {
		this.singleType = singleType;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getAccountShopId() {
		return accountShopId;
	}

	public void setAccountShopId(Integer accountShopId) {
		this.accountShopId = accountShopId;
	}

	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}

	public Date getGmt_modifiedn() {
		return gmt_modifiedn;
	}

	public void setGmt_modifiedn(Date gmt_modifiedn) {
		this.gmt_modifiedn = gmt_modifiedn;
	}

	public String getInput1() {
		return input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public String getInput3() {
		return input3;
	}

	public void setInput3(String input3) {
		this.input3 = input3;
	}

	public Boolean getLongTerm() {
		return longTerm;
	}

	public void setLongTerm(Boolean longTerm) {
		this.longTerm = longTerm;
	}
}
