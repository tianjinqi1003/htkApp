package com.htkapp.modules.API.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.merchant.pay.entity.BillRecord;
import com.htkapp.modules.merchant.takeout.dto.AddProductList;
import com.htkapp.modules.merchant.takeout.dto.PropertyList;
import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;

public interface MerchantAppService {

	APIResponseModel getNewOrderList(Integer shopId, String startDate, String endDate, Integer statusCode);

	APIResponseModel getFinishedOrderList(int shopId, String startDate, String endDate, Integer statusCode);

	/**
	 * 确认已完成订单
	 * @param orderNumber
	 * @return
	 */
	AjaxResponseModel confirmFinishedOrder(String orderNumber);

	AjaxResponseModel findByUserNamePwd(String userName, String password);

	/**
	 * 根据产品id查找出商品详情
	 * @param userId 
	 * @param productId
	 * @return
	 */
	APIResponseModel getProductDetailByPID(Integer userId, Integer productId);

	List<TakeoutCategory> getTakeoutCategoryListByAccountShopId(Integer userId);

	/**
	 * 添加商品
	 * @param product
	 * @param imgFile
	 * @param label
	 * @param addProductList
	 * @param propertyList
	 * @param userId 
	 */
	void addTakeoutProduct(TakeoutProduct product, MultipartFile imgFile, String label, AddProductList addProductList,
			PropertyList propertyList, Integer userId) throws Exception;

	/**
	 * 查询账单记录
	 * @param token
	 * @return
	 */
	APIResponseModel getBillRecord(String token);

	/**
	 * 获得提现信息
	 * @param accountToken
	 * @return
	 */
	APIResponseModel getBalance(String accountToken);

}
