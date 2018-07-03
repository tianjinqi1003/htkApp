package com.htkapp.modules.merchant.takeout.service;

import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.modules.merchant.takeout.dto.AddProductList;
import com.htkapp.modules.merchant.takeout.dto.PropertyList;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 外卖控制类Service
 */

public interface TakeoutService {


    /* ====================JSP页面接口开始========================== */
    //外卖发布首页
    void releaseIndex(Model model);
    //添加产品页面
    void addProduct(TakeoutProduct takeoutProduct, MultipartFile imgFile, StringBuffer label, AddProductList productList, PropertyList propertyList);
    //获取订单列表json数据
    TableResponseModel getOrderListJsonData(HttpServletRequest request);
    //获取订单评论列表json数据
    TableResponseModel getCommentListJsonData(HttpServletRequest request);
    //商品数量置满
    AjaxResponseModel filledUpProductInventoryById(Integer productId);
    //商品数量沽清
    AjaxResponseModel emptyProductInventoryById(Integer productId);
    //通过商户ID获取分类和商品接口
    AjaxResponseModel getCategoryAndProductByAccountShopId(int accountShopId, HttpServletRequest request);
    //删除分类接口
    AjaxResponseModel delCategoryById(int categoryId);
    //通过商户ID获取分类列表
    void getCategoryListById(Model model, HttpServletRequest request);
    //通过产品id查找出产品信息
    void getProductDetailByPID(Model model,int productId);
    //保存商品修改
    void saveProductEdit(TakeoutProduct takeoutProduct,MultipartFile imgFile, String label, PropertyList propertyList) throws Exception;
    //新订单确认接单接口
    AjaxResponseModel confirmTheOrderSuc(String orderNumber);
    //配送商品接口
    AjaxResponseModel itemsToShip(String orderNumber);
    
    //回复催单接口
    AjaxResponseModel replyMessage(AjaxRequestParams params);

    AjaxResponseModel takeOnProduct(Model model, String productIds);

    AjaxResponseModel takeOffProduct(Model model, String productIds);
    /* ====================JSP页面接口结束========================== */
}
