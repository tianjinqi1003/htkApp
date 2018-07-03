package com.htkapp.modules.merchant.groupBuy.service;

import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.modules.merchant.groupBuy.dto.BuyPackageContentDto;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageProduct;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface GroupBuyService {


    /* =====================接口开始======================= */

    /* =====================接口结束======================= */


    /* ====================JSP页面接口开始======================== */
    //团购添加商品页面
    void groupBuyAddProduct(Model model,int pageNum);
    //团购添加商品操作
    void groupBuyAddProductOperate(BuyPackageProduct packageProduct);
    //发布团购页面
    void issueGroupBuyProduct(Model model);
    //发布团购操作Post
    void issueGroupBuyProductPost(Model model, BuyPackage buyPackage, BuyPackageContentDto buyPackageContentDto, MultipartFile imgFile) throws Exception;
    //删除添加到团购的商品
    AjaxResponseModel deleteGroupBuyAddProduct(AjaxRequestParams params);
    //查询店铺下的团购订单列表
    void getGroupBuyOrderList(RequestParams params);
    //团购信息页面
    void getGroupBuyItemMesPage(RequestParams params);
    //团购确认消费按钮
    AjaxResponseModel enterConsumption(AjaxRequestParams params);
    //团购商品编辑数据
    void getGroupBuyDetailById(RequestParams params);
    //删除团购商品
    AjaxResponseModel deleteGroupBuyById(AjaxRequestParams params);
    //编辑商品保存
    void getGroupBuyDetailByIdPost(RequestParams params, BuyPackage buyPackage, MultipartFile imgFile) throws Exception;
    /* ====================JSP页面接口结束======================== */
}
