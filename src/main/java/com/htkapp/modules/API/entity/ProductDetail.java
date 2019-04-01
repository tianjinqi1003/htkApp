package com.htkapp.modules.API.entity;

import java.util.List;

import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;

public class ProductDetail {

	List<TakeoutCategory> data;
	TakeoutProduct dataPro;
	public List<TakeoutCategory> getData() {
		return data;
	}
	public void setData(List<TakeoutCategory> data) {
		this.data = data;
	}
	public TakeoutProduct getDataPro() {
		return dataPro;
	}
	public void setDataPro(TakeoutProduct dataPro) {
		this.dataPro = dataPro;
	}
	
	public ProductDetail(List<TakeoutCategory> data,TakeoutProduct dataPro) {
		this.data=data;
		this.dataPro=dataPro;
	}
}
