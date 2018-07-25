package com.htkapp.modules.merchant.takeout.web;


import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.pay.service.OrderCommonService;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.dto.AddProductList;
import com.htkapp.modules.merchant.takeout.dto.PropertyList;
import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import com.htkapp.modules.merchant.takeout.service.TakeoutCategoryServiceI;
import com.htkapp.modules.merchant.takeout.service.TakeoutProductServiceI;
import com.htkapp.modules.merchant.takeout.service.TakeoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/merchant/takeout")
public class TakeoutController {

    @Autowired(required = false)
    private HttpServletRequest request;
    @Resource
    private TakeoutService takeoutService;
    @Resource
    private TakeoutCategoryServiceI takeoutCategoryService;
    @Resource
    private TakeoutProductServiceI takeoutProductService;

    public static String mTakeoutDirectory = "merchant/";


    //========================================================商品
    //新增外卖商品页面
    @RequestMapping(value = "/product/addProduct",method = RequestMethod.GET)
    public String addProduct(Model model, HttpServletRequest request) {
        //查找商铺下的所有分类，mode传值到页面
        takeoutService.getCategoryListById(model, request);
        return mTakeoutDirectory + "product_takeout_index_add";
    }

    //新增外卖商品post提交页面
    @RequestMapping(value = "/product/addProduct", method = RequestMethod.POST)
    public String addProduct(TakeoutProduct takeoutProduct, MultipartFile imgFile, String label1, String label2, String label3, AddProductList productList, PropertyList propertyList) {
        StringBuffer label = new StringBuffer();
        if (StringUtils.isNotEmpty(label1)) {
            label.append(label);
        }
        if (StringUtils.isNotEmpty(label2)) {
            label.append(label2);
        }
        if (StringUtils.isNotEmpty(label3)) {
            label.append(label3);
        }
        try {
			takeoutService.addProduct(takeoutProduct, imgFile, label, productList, propertyList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/merchant/takeout/product/homePage";
    }


    //外卖商品上架页面
    @RequestMapping(value = "/product/takeOn", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel takeOn(Model model, String selectedIds) {
        try {
            return  takeoutService.takeOnProduct(model, selectedIds);
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "下架失败");
        }
    }
    //外卖商品下架页面
    @RequestMapping(value = "/product/takeOff", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel takeOff(Model model, String selectedIds) {
        try {
                return takeoutService.takeOffProduct(model, selectedIds);
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "下架失败");
        }
    }


    //编辑商品页面
    @RequestMapping(value = "/product/editProduct", method = RequestMethod.GET)
    public String editProduct(Model model, Integer productId) {
        //根据产品id查找出商品详情
        takeoutService.getProductDetailByPID(model, productId);
        return mTakeoutDirectory + "product_takeout_index_edit";
    }

    //保存外卖商品修改接口
    @RequestMapping(value = "/product/saveProduct",method = RequestMethod.POST)
    public String saveProduct(TakeoutProduct product, MultipartFile imgFile,String label1, String label2, String label3, PropertyList propertyList) {
        try {
            StringBuffer label = new StringBuffer();
            if (StringUtils.isNotEmpty(label1)) {
                label.append(label);
            }
            if (StringUtils.isNotEmpty(label2)) {
                label.append(label2);
            }
            if (StringUtils.isNotEmpty(label3)) {
                label.append(label3);
            }
            takeoutService.saveProductEdit(product,imgFile,String.valueOf(label),propertyList);
            return mTakeoutDirectory + "product_takeout_index";
        } catch (Exception e) {
            return "redirect:editProduct?productId="+product.getId();
        }
    }

    //获取外卖商品页面接口
    @RequestMapping(value = "/product/getProductData", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseModel getProductById(String actionName, HttpServletRequest request) {
        try {
            LoginUser user = OtherUtils.getLoginUserByRequest();
            if (actionName.equals("getData")) {
                //获取全部分类和商品
                return takeoutService.getCategoryAndProductByAccountShopId(user.getUserId(), request);
            } else {
                return null;
            }
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "失败");
        }
    }

    //删除商品接口
    @RequestMapping("/product/delProductById")
    @ResponseBody
    public AjaxResponseModel delProductById(Integer productId) {
        try {
            takeoutProductService.delProductById(productId);
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESS_AND_JUMP_URL, "删除成功", "/merchant/takeout/index");
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
        }
    }

    //商品数量置满接口
    @RequestMapping("/product/filledUpProductInventoryById")
    @ResponseBody
    public AjaxResponseModel filledUpProductInventoryById(Integer productId) {
        return takeoutService.filledUpProductInventoryById(productId);
    }

    //商品数量沽清接口
    @RequestMapping("/product/emptyProductInventoryById")
    @ResponseBody
    public AjaxResponseModel emptyProductInventoryById(Integer productId) {
        return takeoutService.emptyProductInventoryById(productId);
    }

    //======================================================分类

    //删除分类接口
    @RequestMapping("/product/delCategoryById")
    @ResponseBody
    public AjaxResponseModel delCategoryById(Integer categoryId) {
        return takeoutService.delCategoryById(categoryId);
    }

    //添加分类接口
    @RequestMapping("/product/addCategory")
    @ResponseBody
    public AjaxResponseModel addCategory(String categoryName,int mark) {
        try {
            LoginUser user = OtherUtils.getLoginUserByRequest();
            takeoutCategoryService.addCategoryById(categoryName, user.getUserId(),mark);
            return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "添加成功");
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "添加失败");
        }
    }

    //修改分类获取数据接口
    @RequestMapping("/product/editCategory")
    @ResponseBody
    public AjaxResponseModel editCategory(int categoryId) {
        TakeoutCategory category = takeoutCategoryService.getCategoryById(categoryId);
        return new AjaxResponseModel<>(Globals.COMMON_SUCCESS_AND_REFRESH_CURRENT_PAGE, "成功", category);
    }

    //保存分类接口
    @RequestMapping("/product/saveCategory")
    @ResponseBody
    public AjaxResponseModel saveCategory(Integer categoryId, String categoryName) {
        try {
            TakeoutCategory category = new TakeoutCategory();
            category.setId(categoryId);
            category.setCategoryName(categoryName);
            takeoutCategoryService.editCategoryNameById(category);
            return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "保存成功");
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "保存失败");
        }
    }

    //======================================================订单

    //新订单确认接单接口
    @RequestMapping("/order/confirmTheOrder")
    @ResponseBody
    public AjaxResponseModel confirmTheOrder(String orderNumber){
        return takeoutService.confirmTheOrderSuc(orderNumber);
    }

    //配送商品接口
    @RequestMapping("/order/itemsToShip")
    @ResponseBody
    public AjaxResponseModel itemsToShip(String orderNumber){
        return takeoutService.itemsToShip(orderNumber);
    }
    //外招配送商品接口
    @RequestMapping("/order/needHelp")
    @ResponseBody
    public AjaxResponseModel needHelp(String orderNumber){
        return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "==成功调用方法==");
    }
    //回复催单接口
    @RequestMapping("/order/replyMessage")
    @ResponseBody
    public AjaxResponseModel replyMessage(AjaxRequestParams params){
        return takeoutService.replyMessage(params);
    }

    //======================================================评论

    //评论页面
    @RequestMapping("/comment")
    public String comment() {
        return mTakeoutDirectory + "comment";
    }

    //获取订单列表json数据接口
    @RequestMapping("/getOrderListJsonData")
    @ResponseBody
    public TableResponseModel getOrderListJsonData() {
        return takeoutService.getOrderListJsonData(request);
    }

    //获取订单评论列表json数据接口
    @RequestMapping("/getCommentListJsonData")
    @ResponseBody
    public TableResponseModel getCommentListJsonData() {
        return takeoutService.getCommentListJsonData(request);
    }
}
