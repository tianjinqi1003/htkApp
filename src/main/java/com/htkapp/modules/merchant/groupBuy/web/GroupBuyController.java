package com.htkapp.modules.merchant.groupBuy.web;


import com.htkapp.core.OtherUtils;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.groupBuy.dto.BuyPackageContentDto;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageProduct;
import com.htkapp.modules.merchant.groupBuy.service.GroupBuyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/merchant/groupBuy")
public class GroupBuyController {

    @Resource
    private GroupBuyService groupBuyService;

    private static String mGroupBuyDirectory = "merchant/";


    /* ==============================团购商品================================ */
    //编辑团购商品页面
    @RequestMapping(value = "/product/getGroupBuyDetailById", method = RequestMethod.GET)
    public String getGroupBuyDetailById(Model model, RequestParams params) {
        Map<String, Object> map = new HashMap<>();
        map.put("pro_mark", true);
        map.put("pro_mark_g", true);
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModel(model, map);
        params.setModel(model);
        groupBuyService.getGroupBuyDetailById(params);
        return mGroupBuyDirectory + "product_groupBuy_index_edit";
    }

    //编辑商品保存
    @RequestMapping(value = "/product/getGroupBuyDetailById", method = RequestMethod.POST)
    public String getGroupBuyDetailByIdPost(Model model, RequestParams params, BuyPackage buyPackage, BuyPackageContentDto buyPackageContentDto, MultipartFile imgFile){
        Map<String, Object> map = new HashMap<>();
        map.put("pro_mark", true);
        map.put("pro_mark_g", true);
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModel(model, map);
        params.setModel(model);
        try {
            groupBuyService.getGroupBuyDetailByIdPost(params, buyPackage, imgFile);
            return "redirect:/merchant/groupBuy/product/homePage";
        }catch (Exception e){
            return "redirect:/merchant/groupBuy/product/homePage";
        }
    }

    //发布团购商品页面
    @RequestMapping(value = "/product/addGroupBuyProduct", method = RequestMethod.GET)
    public String addGroupBuyProductPage(Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("pro_mark", true);
        map.put("pro_mark_g", true);
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModel(model, map);
        groupBuyService.issueGroupBuyProduct(model);
        return mGroupBuyDirectory + "product_groupBuy_index_add";
    }

    //发布团购商品页面
    @RequestMapping(value = "/product/addGroupBuyProduct", method = RequestMethod.POST)
    public String addGroupBuyProductPage(Model model, BuyPackage buyPackage, BuyPackageContentDto buyPackageContentDto, MultipartFile imgFile) {
        Map<String, Object> map = new HashMap<>();
        map.put("pro_mark", true);
        map.put("pro_mark_g", true);
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModel(model, map);
        try {
            groupBuyService.issueGroupBuyProductPost(model, buyPackage, buyPackageContentDto, imgFile);
            return "redirect:/merchant/groupBuy/product/homePage";
        } catch (Exception e) {
            return "redirect:/merchant/groupBuy/product/homePage";
        }
    }

    //添加商品页面
    @RequestMapping(value = "/product/addProductPage", method = RequestMethod.GET)
    public String addProductPage(Model model,
                                 @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("pro_mark", true);
        map.put("pro_mark_g", true);
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModel(model, map);
        groupBuyService.groupBuyAddProduct(model, pageNum);
        return mGroupBuyDirectory + "product_groupBuy_index_addProduct";
    }

    //确认添加商品到团购商品中
    @RequestMapping(value = "/product/addProductPage", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel addProductPage(BuyPackageProduct packageProduct) {
        groupBuyService.groupBuyAddProductOperate(packageProduct);
        return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
    }

    //删除添加后的商品
    @RequestMapping(value = "/product/deleteAddProduct")
    @ResponseBody
    public AjaxResponseModel deleteGroupBuyAddProduct(AjaxRequestParams params) {
        return groupBuyService.deleteGroupBuyAddProduct(params);
    }

    //删除团购商品
    @RequestMapping(value = "/product/deleteGroupBuyById")
    @ResponseBody
    public AjaxResponseModel deleteGroupBuyById(AjaxRequestParams params){
        return groupBuyService.deleteGroupBuyById(params);
    }

    /* ============================团购订单================================ */
    //订单查询页面
    @RequestMapping(value = "/getGroupBuyOrderQueryPage", method = RequestMethod.GET)
    public String getGroupBuyOrderQueryPage(Model model,RequestParams params,
                                            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("ord_mark", true);
        map.put("ord_mark_g", true);
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModel(model, map);
        //查询当前店铺下的所有团购订单，返回前台
        params.setModel(model);
        params.setPageNum(pageNum);
        groupBuyService.getGroupBuyOrderList(params);
        return mGroupBuyDirectory + "order_groupBuy_query";
    }

    //团购信息页面
    @RequestMapping(value = "/getGroupBuyItemMesPage", method = RequestMethod.GET)
    public String getGroupBuyItemMesPage(Model model, RequestParams params,
                                         @RequestParam(value = "pageNum", required = false, defaultValue = "1")Integer pageNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("ord_mark", true);
        map.put("ord_mark_g", true);
        map.put("date", new Date().getTime());
        OtherUtils.ReturnValByModel(model, map);
        params.setModel(model);
        params.setPageNum(pageNum);
        groupBuyService.getGroupBuyItemMesPage(params);
        return mGroupBuyDirectory + "order_groupBuy_item";
    }

    //团购确认消费按钮
    @RequestMapping("/enterConsumption")
    @ResponseBody
    public AjaxResponseModel enterConsumption(AjaxRequestParams params){
        return groupBuyService.enterConsumption(params);
    }

}
