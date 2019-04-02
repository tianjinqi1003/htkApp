package com.htkapp.modules.API.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.htkapp.core.MD5Utils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.config.FTPConfig;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.service.MerchantAppService;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.dto.Property;
import com.htkapp.modules.merchant.takeout.dto.PropertyList;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import com.htkapp.modules.merchant.takeout.service.TakeoutService;
import com.xiaoleilu.hutool.date.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/API/merchantAppAPI")
public class MerchantAppAPI {

	@Resource
	private MerchantAppService merchantAppService;
	@Resource
    private ShopServiceI shopService;
    @Resource
    private TakeoutService takeoutService;
    
    @RequestMapping(value = "/login")
    @ResponseBody
    public AjaxResponseModel merchantLogin(APIRequestParams params) {
    	
    	return merchantAppService.findByUserNamePwd(params.getUserName(), MD5Utils.MD5Encode(params.getPassword(), "UTF-8", true));
    }
	
	@RequestMapping(value="/getNewOrderList")
	@ResponseBody
	public APIResponseModel getNewOrderList(APIRequestParams params) throws Exception {
		
		String startDate = DateUtil.beginOfDay(new Date()).toString();
		//String startDate = "2019-03-01";
        String endDate = DateUtil.endOfDay(new Date()).toString();
		//此处是外卖，所以mark是0
		Shop shop = shopService.getShopByAccountShopIdAndMark(params.getUserId(), 0);
        return merchantAppService.getNewOrderList(shop.getShopId(), startDate, endDate, params.getStatusCode());
	}
	
	@RequestMapping(value="/getFinishedOrderList")
	@ResponseBody
	public APIResponseModel getFinishedOrderList(APIRequestParams params) throws Exception {
		
		String startDate = DateUtil.beginOfDay(new Date()).toString();
		//String startDate = "2019-03-01";
		String endDate = DateUtil.endOfDay(new Date()).toString();
		//此处是外卖，所以mark是0
		Shop shop = shopService.getShopByAccountShopIdAndMark(params.getUserId(), 0);
		return merchantAppService.getFinishedOrderList(shop.getShopId(), startDate, endDate, params.getStatusCode());
	}
	
	@RequestMapping(value="/confirmTheOrder")
	@ResponseBody
	public AjaxResponseModel confirmTheOrder(APIRequestParams params) {
		return takeoutService.confirmTheOrderSuc(params.getOrderNumber());
	}
	
	@RequestMapping(value="/confirmFinishedOrder")
	@ResponseBody
	public AjaxResponseModel confirmFinishedOrder(APIRequestParams params) {
		return merchantAppService.confirmFinishedOrder(params.getOrderNumber());
	}
	
	//获取外卖商品页面接口
    @RequestMapping(value = "/takeout/product/getProductData")
    @ResponseBody
    public AjaxResponseModel getProductById(APIRequestParams params) {
        try {
            if ("getData".equals(params.getActionName())) {
                //获取全部分类和商品
                return takeoutService.getCategoryAndProductByAccountShopId(params.getUserId(), null);
            } else {
                return null;
            }
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "失败");
        }
    }
    
    //外卖商品上架页面
    @RequestMapping(value = "/takeout/product/takeOn")
    @ResponseBody
    public AjaxResponseModel takeOn(APIRequestParams params) {
        try {
            return  takeoutService.takeOnProduct(null, params.getSelectedIds());
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "上架失败");
        }
    }
    
    //外卖商品下架页面
    @RequestMapping(value = "/takeout/product/takeOff")
    @ResponseBody
    public AjaxResponseModel takeOff(APIRequestParams params) {
        try {
            return takeoutService.takeOffProduct(null, params.getSelectedIds());
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "下架失败");
        }
    }
    
    //编辑商品页面
    @RequestMapping(value = "/takeout/product/editProduct")
    @ResponseBody
    public APIResponseModel editProduct(APIRequestParams params) {
        //根据产品id查找出商品详情
        return merchantAppService.getProductDetailByPID(params.getUserId(),params.getProductId());
    }
    
    //保存外卖商品修改接口
    @RequestMapping(value = "/takeout/product/saveProduct")
    @ResponseBody
    public APIResponseModel saveProduct(APIRequestParams params) {
        try {
	    	MultipartFile imgFile = params.getImgFile();
	    	JSONObject takeoutProductJO = JSONObject.fromObject(params.getTakeoutProductJOStr());
	    	TakeoutProduct product = (TakeoutProduct)JSONObject.toBean(takeoutProductJO, TakeoutProduct.class);
	    	
	    	String tppJAStr = params.getTakeoutProductPropertyJAStr();
	    	//String tppJAStr = "[{\"propertyE\":\"微辣\"}]";
	    	JSONArray tppJA = JSONArray.fromObject(tppJAStr);
	    	List<Property> propertyList = new ArrayList<>();
	    	for (Object tppObj : tppJA) {
		    	Property pro = new Property();
		    	JSONObject tppJO = (JSONObject)tppObj;
		    	pro.setPropertyE(tppJO.getString("propertyE"));
		    	propertyList.add(pro);
			}
			//String uploadUrl = FileUploadUtils.appUploadAvatarImg(imgFile, "shop/takeout/", FTPConfig.port_to);
		    //System.out.println("uploadUrl========"+uploadUrl);
	    	//TakeoutProduct product, MultipartFile imgFile, PropertyList propertyList
			takeoutService.saveProductEdit(product,imgFile,null,new PropertyList(propertyList));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new APIResponseModel(Globals.API_SUCCESS, "成功");
    }
}
