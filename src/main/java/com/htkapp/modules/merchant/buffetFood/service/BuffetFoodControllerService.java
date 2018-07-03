package com.htkapp.modules.merchant.buffetFood.service;

import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProduct;
import com.htkapp.modules.merchant.takeout.dto.AddProductList;
import com.htkapp.modules.merchant.takeout.dto.PropertyList;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface BuffetFoodControllerService {

    /* =========================接口开始============================ */
    //添加产品页面
    void addProduct(BuffetFoodProduct product, MultipartFile imgFile);
    //通过商户ID获取分类和商品接口
    AjaxResponseModel getCategoryAndProductByAccountShopId(int accountShopId);
    //删除分类接口
    AjaxResponseModel delCategoryById(int categoryId);
    //通过商户ID获取分类列表
    void getCategoryListById(Model model);
    //通过产品id查找出产品信息
    void getProductDetailByPID(Model model,int productId);
    //保存商品修改
    void saveProductEdit(BuffetFoodProduct product, MultipartFile imgFile) throws Exception;
    //新订单下单接口
    AjaxResponseModel dealWithNewOrder(AjaxRequestParams params);
    //回复催单接口
    AjaxResponseModel replyReminder(AjaxRequestParams params);
    //确认调单
    AjaxResponseModel enterAdjust(AjaxRequestParams params);
   //打印自助点餐订单
//	AjaxResponseModel printOrder(AjaxRequestParams params, RequestParams Rparams, Integer state);
    /* =========================接口结束============================ */
}
