package com.htkapp.modules.API.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.htkapp.core.MD5Utils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.config.AlipayConfig;
import com.htkapp.core.config.FTPConfig;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.OrderNumGen;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.service.MerchantAppService;
import com.htkapp.modules.API.service.PaymentInterfaceService;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.pay.entity.BillRecord;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.dto.AddProductList;
import com.htkapp.modules.merchant.takeout.dto.ListProperty;
import com.htkapp.modules.merchant.takeout.dto.Property;
import com.htkapp.modules.merchant.takeout.dto.PropertyList;
import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import com.htkapp.modules.merchant.takeout.service.TakeoutCategoryServiceI;
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
    @Resource
    private PaymentInterfaceService paymentInterfaceService;
    
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

    //调起退款
    @RequestMapping(value = "/callUpRefundInterface")
    @ResponseBody
    public APIResponseModel callUpRefundInterface(APIRequestParams params) {
    	params.setToken(params.getAccountToken());
        return paymentInterfaceService.callUpRefundInterfaceHTK(params, params.getOrderNumber());
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
    
    /**
     * 保存外卖商品修改接口
     * @param params
     * @return
     */
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
	    	List<Property> propertyList = new ArrayList<Property>();
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
	        return new APIResponseModel(Globals.API_FAIL, "失败");
		}
        return new APIResponseModel(Globals.API_SUCCESS, "成功");
    }
    
    /**
     * 新增外卖商品
     * @param params
     * @return
     */
    @RequestMapping(value = "/takeout/product/addProduct")
    @ResponseBody
    public APIResponseModel addProduct(APIRequestParams params) {
    	MultipartFile imgFile = params.getImgFile();
    	JSONObject takeoutProductJO = JSONObject.fromObject(params.getTakeoutProductJOStr());
    	TakeoutProduct product = (TakeoutProduct)JSONObject.toBean(takeoutProductJO, TakeoutProduct.class);

    	String tppJAStr = params.getTakeoutProductPropertyJAStr();
    	JSONArray tppJA = JSONArray.fromObject(tppJAStr);
    	List<Property> propertyList = new ArrayList<>();
    	for (Object tppObj : tppJA) {
	    	Property pro = new Property();
	    	JSONObject tppJO = (JSONObject)tppObj;
	    	pro.setPropertyE(tppJO.getString("propertyE"));
	    	propertyList.add(pro);
		}
    	try {
    		merchantAppService.addTakeoutProduct(product, imgFile, null, new PropertyList(propertyList),params.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            return new APIResponseModel(Globals.API_FAIL, "失败");
		}
    	return new APIResponseModel(Globals.API_SUCCESS, "成功");
    }
    
    @RequestMapping(value = "/takeout/getCategoryListById")
    @ResponseBody
    public APIResponseModel getCategoryListById(APIRequestParams params) {
		
    	List<TakeoutCategory> resultList = merchantAppService.getTakeoutCategoryListByAccountShopId(params.getUserId());
        if (resultList == null) {
            return new APIResponseModel(Globals.API_FAIL, "失败");
        }
    	return new APIResponseModel(Globals.API_SUCCESS, "成功",resultList);
	}
    
    /**
     * 查询账单记录
     * @param params
     * @return
     */
    @RequestMapping(value = "/getBillRecord")
    @ResponseBody
    public APIResponseModel getBillRecord(APIRequestParams params) {
    	
    	return merchantAppService.getBillRecord(params.getAccountToken());//这里必须接收accountToken参数，要是接收token参数，在app端就被过滤掉了，接收不到
    }

    /**
     * 获得提现信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/getBalance")
    @ResponseBody
    public APIResponseModel getBalance(APIRequestParams params) {
		
    	return merchantAppService.getBalance(params.getAccountToken());
	}
    
    /**
     * 提现(转账)
     *
     * @return
     * @author 马鹏昊
     */
    @RequestMapping(value = "/withdraw")
    @ResponseBody
    public AjaxResponseModel withdraw(APIRequestParams params) {
        try {
            AccountShop accountShop = merchantAppService.getAlipayAccount(params.getUserId());

            String money = params.getMoney();
            float moneyF = Float.parseFloat(money);
            //扣除手续费千分之六
            float realMoney= moneyF*(1f-0.006f);
            DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            String p = decimalFormat.format(realMoney);//format 返回的是字符串

            if (accountShop != null) {

                AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, "json",
                        AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
                AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
                String out_trade_no = OrderNumGen.next().toString();
                if (TextUtils.isEmpty(out_trade_no)){
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "订单号为空");
                }
                if (TextUtils.isEmpty(accountShop.getAlipayAccountType())){
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "第三方账户类型payee_type为空");
                }
                if (TextUtils.isEmpty(accountShop.getAlipayAccount())){
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "第三方账户类型payee_account为空");
                }
                request.setBizContent("{" +
                        "\"out_biz_no\":" + "\""+out_trade_no+"\"," +
                        "\"payee_type\":" + "\""+accountShop.getAlipayAccountType() + "\"," +
                        "\"payee_account\":" +"\""+ accountShop.getAlipayAccount() + "\"," +
                        "\"amount\":" +"\""+ p +  "\"," +
                        "\"payer_show_name\":\"青岛华凌科技有限公司\"," +
                        //收款方真实姓名（如果传了该值则会校验真实姓名和收款方账户是否一致）
//                        "\"payee_real_name\":" +"\""+ accountShop.getUserName() +"\"," +
//                    "\"remark\":\"转账备注\"" +
                        "\"remark\":\"商家提现\"" +
                        "}");
                AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
                if (response.isSuccess()) {
                    System.out.println("调用成功");
//                    callAplipayQuery(alipayClient,out_trade_no);

                    //修改数据库里的商家账户余额(减去的是后台输入的提现金额)
                    double oldBalance = merchantAppService.getAccountBalance(accountShop.getToken());
                    double newBalance = oldBalance - Double.valueOf(money);
                    int row = merchantAppService.updateAccountBalance(accountShop.getToken(), newBalance);
                    return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "转账成功");
                } else {
                    System.out.println("调用失败");
//                    callAplipayQuery(alipayClient,out_trade_no);
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, response.getSubMsg());
                }
            }
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "转账失败");
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
        }
    }
    
    /**
     * 推送消息或通知
     * @param params
     * @return
     */
    @RequestMapping(value = "/sendNotification")
    @ResponseBody
    public APIResponseModel sendNotification(APIRequestParams params) {

    	Map<String, String> extras = new HashMap<String, String>();
    	extras.put("actionName", params.getActionName());
    	//Jpush.jPushMethodToMerchantApp("f8030831-996d-4a42-8ac3-df1b3793de19", "自助点餐订单下单成功", "ALERT", "商家接单app");
    	Jpush.jPushMethodToMerchantApp(params.getMobilePhone(), params.getContent(), "ALERT", params.getTitle(),extras);//这里为了方便，token用phone代替，每个商家由phone区分开，和用token做标识推送一样
    	return new APIResponseModel(Globals.API_SUCCESS, "成功");
	}
}
