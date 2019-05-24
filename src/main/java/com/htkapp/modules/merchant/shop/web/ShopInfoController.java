package com.htkapp.modules.merchant.shop.web;

import com.htkapp.core.OtherUtils;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.modules.merchant.shop.service.ShopInfoControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.htkapp.modules.merchant.common.web.MerchantController.mDirectory;

/**
 * Created by yinqilei on 17-6-20.
 * 商铺控制类
 */
@Controller
@RequestMapping("/merchant/shopInfo")
public class ShopInfoController {

    @Resource
    private ShopInfoControllerService controllerService;



    //获取商铺分类列表（商家注册页面）
    @RequestMapping(value = "/getShopCategoryList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel getShopCategoryList(){
        return controllerService.getShopCategoryList();
    }

    //商铺信息－跳转到用户页面
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showShop(Model model) {
        controllerService.getShopMessageByShopEncryptToken(model);
        model.addAttribute("shopInfo",true);
        Map<String, Object> map = new HashMap<>();
        map.put("sto_mark", true);
        map.put("sto_mark_s", true);
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModel(model, map);
        return mDirectory + "shop_accountInfo";
    }

    //店铺相册页面
    @RequestMapping("/shopAlbumPage")
    public String shopAlbumPage(Model model, RequestParams params){
        Map<String, Object> map = new HashMap<>();
        map.put("sto_mark", true);
        map.put("sto_mark_s", true);
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModel(model, map);
        params.setModel(model);
        controllerService.getShopAlbumPage(params);
        return mDirectory + "shop_album";
    }

    //店铺相册上传
    @RequestMapping(value = "/uploadAlbumInterface", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel uploadAlbumInterface(AjaxRequestParams params){
        return controllerService.uploadAlbumInterface(params);
    }

    //删除店铺相册接口
    @RequestMapping(value = "/deleteAlbumById", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel deleteAlbumById(AjaxRequestParams params){
        return controllerService.deleteAlbumById(params);
    }

    //修改密码
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updatePassword(AjaxRequestParams params){
        return controllerService.updatePassword(params);
    }

    //修改手机号
    @RequestMapping(value = "/updateShopPhone", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updateShopPhone(AjaxRequestParams params){
        return controllerService.updateShopPhone(params);
    }

    //店铺头像上传修改
    @RequestMapping(value = "/updateShopImg", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updateShopImg(AjaxRequestParams params){
        return controllerService.updateShopImg(params);
    }

    //营业时间修改
    @RequestMapping(value = "/updateOpeningTime", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updateOpeningTime(AjaxRequestParams params){
        return controllerService.updateOpeningTime(params);
    }

    //订餐电话修改
    @RequestMapping(value = "/updatePhone", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updatePhone(AjaxRequestParams params){
        return controllerService.updatePhone(params);
    }

    //店铺公告修改
    @RequestMapping(value = "/updateIntro", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updateIntro(AjaxRequestParams params){
        return controllerService.updateIntro(params);
    }

    //店铺简介修改
    @RequestMapping(value = "/updateDes", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updateDes(AjaxRequestParams params){
        return controllerService.updateDes(params);
    }

    //配送费
    @RequestMapping(value = "/updateDeliveryFee", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updateDeliveryFee(AjaxRequestParams params){
        return controllerService.updateDeliveryFee(params);
    }
    
    //满多少元起送
    @RequestMapping(value = "/updateStartDeliveryPrice", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel updateStartDeliveryPrice(AjaxRequestParams params){
    	return controllerService.updateStartDeliveryPrice(params);
    }

}
