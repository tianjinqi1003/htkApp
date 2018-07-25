package com.htkapp.modules.merchant.groupBuy.service.serviceImpl;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.config.FTPConfig;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.core.shiro.common.utils.LoggerUtils;
import com.htkapp.core.shiro.common.utils.StringUtils;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProduct;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodProductService;
import com.htkapp.modules.merchant.common.dto.GroupBuyCommentList;
import com.htkapp.modules.merchant.common.service.ShopMessageCommentService;
import com.htkapp.modules.merchant.groupBuy.dto.BuyPackageContentDto;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageContent;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageProduct;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageContentService;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageProductService;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageService;
import com.htkapp.modules.merchant.groupBuy.service.GroupBuyService;
import com.htkapp.modules.merchant.pay.entity.OrderBuyPackage;
import com.htkapp.modules.merchant.pay.entity.OrderBuyPackageContent;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.OrderBuyPackageContentService;
import com.htkapp.modules.merchant.pay.service.OrderBuyPackageService;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import com.htkapp.modules.merchant.takeout.service.TakeoutProductServiceI;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static com.htkapp.core.OtherUtils.getMarkByButtonVal;
import static com.htkapp.core.OtherUtils.getPageByKey;
import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_MINUTE_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

@Service
public class GroupBuyServiceImpl implements GroupBuyService {

    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private ShopMessageCommentService shopMessageCommentService;
    @Resource
    private TakeoutProductServiceI takeoutProductService;
    @Resource
    private BuffetFoodProductService buffetFoodProductService;
    @Resource
    private BuyPackageProductService buyPackageProductService;
    @Resource
    private ShopServiceI shopService;
    @Resource
    private BuyPackageService buyPackageService;
    @Resource
    private BuyPackageContentService buyPackageContentService;
    @Resource
    private OrderBuyPackageService orderBuyPackageService;
    @Resource
    private OrderBuyPackageContentService orderBuyPackageContentService;
    @Resource
    private MoreMethodsUtils methodsUtils;


    /* =======================接口开始=========================== */


    //团购添加商品页面
    @Override
    public void groupBuyAddProduct(Model model, int pageNum) {
        try {
            LoginUser user = OtherUtils.getLoginUserByRequest();
            int pageNumber = Globals.DEFAULT_PAGE_NO;
            int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
            if(pageNum > 1){
                pageNumber = pageNum;
            }
            //外卖商铺id
            Shop tShop = shopService.getShopDataByAccountShopIdAndMark(user.getUserId(),Globals.DEFAULT_T_SHOP);
            //查找当前商铺下未加入商品列表
            //根据商户信息获取外卖商品  只查询未添加的
            List<TakeoutProduct> takeoutProductList = takeoutProductService.getNotInGroupBuyProductListData(tShop.getShopId());
            //根据商户信息获取自助商品  只查询未添加的
            List<BuffetFoodProduct> buffetFoodProductList = buffetFoodProductService.getNotInGroupBuyProductListData(tShop.getShopId());
            Shop shop = shopService.getShopDataByAccountShopIdAndMark(user.getUserId(), Globals.DEFAULT_G_SHOP);
            if(shop == null){
                //团购商铺不存在
                model.addAttribute("takeoutProductList", null);
                model.addAttribute("buffetFoodProductList", null);
            }else {
                List<BuyPackageProduct> buyPackageProductList = buyPackageProductService.getBuyPackageProductListByShopId(shop.getShopId(), pageNumber, pageLimit);
                PageInfo pageInfo = new PageInfo<>(buyPackageProductList);
                model.addAttribute("takeoutProductList", takeoutProductList);
                model.addAttribute("buffetFoodProductList", buffetFoodProductList);
                model.addAttribute("buyPackageProductList", buyPackageProductList);
                model.addAttribute("page", pageInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /* =======================接口结束=========================== */

    /* ========================JSP页面接口开始============================= */
    //团购添加商品操作
    @Override
    public void groupBuyAddProductOperate(BuyPackageProduct packageProduct) {
        try {
            //通过
            LoginUser user = OtherUtils.getLoginUserByRequest();
            Shop shop = shopService.getShopDataByAccountShopIdAndMark(user.getUserId(), Globals.DEFAULT_G_SHOP);
            packageProduct.setShopId(shop.getShopId());
            buyPackageProductService.insertBuyPackageProductByShopId(packageProduct);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //发布团购页面
    @Override
    public void issueGroupBuyProduct(Model model) {
        //查询添加到团购下的商品
        try {
            LoginUser user = OtherUtils.getLoginUserByRequest();
            Shop shop = shopService.getShopDataByAccountShopIdAndMark(user.getUserId(),Globals.DEFAULT_G_SHOP);
            List<BuyPackageProduct> resultList = buyPackageProductService.getProductListById(shop.getShopId());
            model.addAttribute("productList", resultList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //删除添加到团购的商品
    @Override
    public AjaxResponseModel deleteGroupBuyAddProduct(AjaxRequestParams params) {
        try {
            if(params != null && params.getId() != null){
                buyPackageProductService.deleteAddProductById(params.getId());
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }else {
                return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
            }
        }catch (Exception e){
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED,e.getMessage());
        }
    }

    //发布团购操作Post
    @Override
    @Transactional
    public void issueGroupBuyProductPost(Model model, BuyPackage buyPackage, BuyPackageContentDto buyPackageContentDto, MultipartFile imgFile) throws Exception {
        //发布团购操作
        try {
            LoginUser user = OtherUtils.getLoginUserByRequest();
            Shop shop = shopService.getShopDataByAccountShopIdAndMark(user.getUserId(),Globals.DEFAULT_G_SHOP);
            //处理图片
            String uploadImgUrl = FileUploadUtils.appUploadAvatarImg(imgFile, "shop/groupBuy/", FTPConfig.port_to);
            buyPackage.setImgUrl(uploadImgUrl);
            buyPackage.setShopId(shop.getShopId());
            if(buyPackage.getValidityTime() == null){
//                buyPackage.setValidityTime(format(DateUtil.offsetMonth(new Date(),1), NORM_DATETIME_MINUTE_PATTERN));
                //截取字符串
                if(buyPackage.getUsageTime() != null){
                    //截取字符串
                    int pos = buyPackage.getUsageTime().indexOf("到");
                    String endTime = buyPackage.getUsageTime().substring(0,pos);
                    buyPackage.setValidityTime(endTime.trim());
                }
            }
            //拿到团购产品总价格、总门市价格
            if(buyPackageContentDto != null){
                double totalPrice = 0.00;
                double totalRetailPrice = 0.00;
                for (BuyPackageContent each : buyPackageContentDto.getList()){
                    totalRetailPrice += each.getOriginalCost();
                    totalPrice += each.getPrice();
                }
                buyPackage.setPrice(totalPrice);
                buyPackage.setRetailPrice(totalRetailPrice);
            }else {
                throw new Exception("团购内没有商品");
            }
            //插入套餐
            int packageId = buyPackageService.insertBuyPackage(buyPackage);
            //插入套餐内产品列表
            for (BuyPackageContent each : buyPackageContentDto.getList()) {
                each.setPackageId(packageId);
                buyPackageContentService.insertPackageItem(each);
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e.getMessage());
        }
    }

    //查询店铺下的团购订单列表
    @Override
    public void getGroupBuyOrderList(RequestParams params) {
        if(params != null && params.getModel() != null && params.getPageNum() != null){
            //查询数据
            try {
                Model model = params.getModel();
                LoginUser user = OtherUtils.getLoginUserByRequest();
                //通过商户id, 团购商户标识，获取商户
                Shop shop = shopService.getShopDataByAccountShopIdAndMark(user.getUserId(), 1);
                if(shop != null){
                    //查询订单列表
                    String orderDesc = "gmt_create desc";
                    int pageNumber = Globals.DEFAULT_PAGE_NO;
                    int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
                    if(params.getPageNum() > 1){
                        pageNumber = params.getPageNum();
                    }
                    String keyWord = null;
                    if(params.getKeyWord() != null && StringUtils.isNotEmpty(params.getKeyWord())){
                        keyWord = params.getKeyWord();
                    }
                    List<OrderRecord> orderRecordList = orderRecordService.getOrderRecordListByDescAndShopId(shop.getShopId(),orderDesc,pageNumber,pageLimit, keyWord);
                    if(orderRecordList != null){
                        //查询订单的产品详情
                        for (OrderRecord each : orderRecordList){
                            //根据团购订单id查询订单内详情
                            OrderBuyPackage orderBuyPackage = orderBuyPackageService.getOrderPackageById(each.getId());
                            //查找当前订单套餐中的产品信息
                            List<OrderBuyPackageContent> resultList = orderBuyPackageContentService.getOrderBuyPackageContentList(orderBuyPackage.getOrderId());
                            each.setOrderBuyPackageContentList(resultList);
                        }
                        PageInfo pageInfo = new PageInfo<>(orderRecordList);
                        model.addAttribute("pageInfo", pageInfo);
                    }
                    model.addAttribute("data", orderRecordList);
                }
                return;
            }catch (Exception e){
                LoggerUtils.fmtError(getClass(), e, "团购订单列表获取异常"+e.getMessage());
            }
        }else {
            return;
        }
    }


    //团购信息页面
    @Override
    public void getGroupBuyItemMesPage(RequestParams params) {
        if(params != null){
            try {
                //查询店铺下的团购商品显示
                LoginUser user = OtherUtils.getLoginUserByRequest();
                Shop shop = shopService.getShopDataByAccountShopIdAndMark(user.getUserId(), 1);
                if (shop != null) {
                    List<BuyPackage> resultList = buyPackageService.getGroupBuyListById(shop.getShopId());
                    if(resultList != null){
                        Gson gson = new Gson();
                        for (BuyPackage each : resultList){
                            each.setImgUrl(OtherUtils.getRootDirectory() + each.getImgUrl());
                            each.setJsonStr(gson.toJson(each));
                        }
                        PageInfo pageInfo = new PageInfo<>(resultList);
                        Model model = params.getModel();
                        model.addAttribute("data", resultList);
                        model.addAttribute("pageInfo", pageInfo);
                        System.out.println("团购信息页面");
                    }
                }
            } catch (Exception e) {
                return;
            }
        }
    }

    //团购确认消费按钮
    @Override
    public AjaxResponseModel enterConsumption(AjaxRequestParams params) {
        if(params != null && params.getOrderNumber() != null){
            try {
                LoginUser user = OtherUtils.getLoginUserByRequest();
                OrderRecord order = orderRecordService.getOrderRecordByOrderNumber(params.getOrderNumber());
                //改变订单状态,推送消息
                boolean result = orderRecordService.changeOrderStateByOrderNumber(params.getOrderNumber(), 11);
                if (result){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderNumber", order.getOrderNumber());
                    jsonObject.put("orderState", order.getOrderState());
                    jsonObject.put("orderId", order.getId());
                    methodsUtils.jPushToMerAndAccount(order.getToken(), "团购订单已消费", jsonObject.toJSONString(), user.getToken(), "用户团购订单已消费", jsonObject.toJSONString(), 2);
                    //推送消息到页面
                    methodsUtils.pushMesToManagePage(new PushMesEntity("团购订单消息", "g", "团购订单被消费了", user.getToken(), 'g', order.getOrderState(), "您有一个的团购订单消息", user.getUserId()));
                    return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
                }else {
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
                }
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    //团购商品编辑数据
    @Override
    public void getGroupBuyDetailById(RequestParams params) {
        if(params != null && params.getModel() != null && params.getId() != null){
            //根据id查询商品信息
            try {
                BuyPackage buyPackage = buyPackageService.getPackageInformation(params.getId());
                if(buyPackage != null){
                    buyPackage.setImgUrl(OtherUtils.getRootDirectory()+buyPackage.getImgUrl());
                    //查询团购下的商品
                    List<BuyPackageContent> resultList = buyPackageContentService.getPackageItemListById(buyPackage.getId());
                    buyPackage.setContentList(resultList);
                }
                Model model = params.getModel();
                model.addAttribute("data", buyPackage);
            }catch (Exception e){
                return;
            }
        }
    }

    //删除团购商品
    @Override
    public AjaxResponseModel deleteGroupBuyById(AjaxRequestParams params) {
        if(params != null && params.getId() != null){
            //根据商铺id和团购商品id删除团购商品和团购商品下的商品
            try {
                LoginUser user = OtherUtils.getLoginUserByRequest();
                Shop shop = shopService.getShopMessageById(user.getUserId(), 1);
                buyPackageService.deleteBuyPackageById(params.getId(), shop.getShopId());
                buyPackageContentService.deletePackageContentListById(params.getId());
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    //编辑商品保存
    @Override
    public void getGroupBuyDetailByIdPost(RequestParams params, BuyPackage buyPackage, MultipartFile imgFile) throws Exception {
        if(params != null && buyPackage != null){
            try {
                LoginUser user = OtherUtils.getLoginUserByRequest();
                Shop shop = shopService.getShopDataByAccountShopIdAndMark(user.getUserId(),Globals.DEFAULT_G_SHOP);
                if(imgFile.getSize() > 0){
                    //处理图片
                    String uploadImgUrl = FileUploadUtils.appUploadAvatarImg(imgFile, "shop/groupBuy/", FTPConfig.port_to);
                    buyPackage.setImgUrl(uploadImgUrl);
                }
                buyPackage.setShopId(shop.getShopId());
                buyPackageService.updateBuyPackageById(buyPackage);
            }catch (Exception e){
                throw new Exception("编辑保存失败");
            }
        }
    }

    /* ========================JSP页面接口结束============================== */
}
