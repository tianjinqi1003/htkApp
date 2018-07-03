package com.htkapp.modules.merchant.shop.service;


import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.modules.merchant.shop.entity.ShopCategoryData;
import org.springframework.ui.Model;

import java.util.List;

public interface ShopInfoControllerService {

    /* ====================接口开始========================= */
    //获取商铺分类列表（商家注册页面）
    AjaxResponseModel getShopCategoryList();

    List<ShopCategoryData> getShopCategory();

    //获取店铺信息(店名、地址、分类、加入时间、剩余精确时间,店铺编号,店铺二维码等等.)
    void getShopMessageByShopEncryptToken(Model model);
    //店铺相册页面
    void getShopAlbumPage(RequestParams params);
    //店铺相册上传
    AjaxResponseModel uploadAlbumInterface(AjaxRequestParams params);
    //删除店铺相册接口
    AjaxResponseModel deleteAlbumById(AjaxRequestParams params);
    //修改密码
    AjaxResponseModel updatePassword(AjaxRequestParams params);
    //修改手机号
    AjaxResponseModel updateShopPhone(AjaxRequestParams params);
    //店铺头像上传修改
    AjaxResponseModel updateShopImg(AjaxRequestParams params);
    //营业时间修改
    AjaxResponseModel updateOpeningTime(AjaxRequestParams params);
    //订餐电话修改
    AjaxResponseModel updatePhone(AjaxRequestParams params);
    //店铺公告修改
    AjaxResponseModel updateIntro(AjaxRequestParams params);
    //店铺简介修改
    AjaxResponseModel updateDes(AjaxRequestParams params);
    //配送费
    AjaxResponseModel updateDeliveryFee(AjaxRequestParams params);
    /* ====================接口结束========================== */
}
