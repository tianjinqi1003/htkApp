package com.htkapp.modules.merchant.takeout.service.serviceImpl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.htkapp.core.LogUtil;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.dto.TakeoutCommentList;
import com.htkapp.modules.merchant.common.service.ShopMessageCommentService;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;
import com.htkapp.modules.merchant.pay.service.TakeoutOrderService;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.dto.*;
import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import com.htkapp.modules.merchant.takeout.service.TakeoutCategoryServiceI;
import com.htkapp.modules.merchant.takeout.service.TakeoutProductServiceI;
import com.htkapp.modules.merchant.takeout.service.TakeoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.LogManager;

import static com.htkapp.core.OtherUtils.*;

@Service
public class TakeoutServiceImpl implements TakeoutService {

    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private TakeoutCategoryServiceI takeoutCategoryService;
    @Resource
    private TakeoutProductServiceI takeoutProductService;
    @Resource
    private ShopServiceI shopService;
    @Resource
    private MoreMethodsUtils methodsUtils;
    @Resource
    private TakeoutOrderService takeoutOrderService;

    public static String mTakeoutDirectory = "merchant/takeout/";

    Class<? extends Object> cls = TakeoutServiceImpl.class;

    /* ====================JSP页面接口开始========================== */
    //外卖发布首页
    @Override
    public void releaseIndex(Model model) {
        try {
            Integer accountShopId = getLoginUserByRequest().getUserId();
            List<TakeoutCategory> categoryList = takeoutCategoryService.getTakeoutCategoryListByAccountShopId(accountShopId);
            List<TakeoutProduct> productList = takeoutProductService.getTakeoutProductListByAccountShopId(accountShopId);
            TakeoutRelease takeoutRelease = new TakeoutRelease();
            if (categoryList != null) {
                if (productList != null) {
                    for (TakeoutCategory each : categoryList) {
                        List<TakeoutProduct> products = new ArrayList<>();
                        for (TakeoutProduct every : productList) {
                            every.setImgUrl(OtherUtils.getRootDirectory() + every.getImgUrl());
                            takeoutRelease.setTakeoutCategory(each);
                            if (every.getCategoryId().equals(each.getId())) {
                                products.add(every);
                            }
                        }
                        takeoutRelease.setProductList(products);
                        int size = products.size();
                        int number = size / 3 == 0 ? 1 : size / 3 + size % 3 == 0 ? 0 : 1;
                        takeoutRelease.setProductSize(number);
                        break;
                    }
                    takeoutRelease.setCategoryList(categoryList);
                    model.addAttribute("data", takeoutRelease);
                } else {
                    model.addAttribute("data", null);
                }
            } else {
                model.addAttribute("data", null);
            }
        } catch (Exception e) {
            model.addAttribute("data", null);
            model.addAttribute("message", e.getMessage());
        }
    }

    //添加产品页面
    @Override
    public void addProduct(TakeoutProduct takeoutProduct, MultipartFile imgFile,
                           StringBuffer label, AddProductList productList, PropertyList propertyList) {
        //商品名称
        //店内分类
        //图片
        //描述
        //标签
        //价格与库存
        //属性
        //售卖时间(全时段售卖，　自定义时间售卖)
        //按照规格来添加商品(几个规格添加几个商品)
        try {
            //处理产品图片
            if (imgFile != null) {
                String uploadUrl = FileUploadUtils.appUploadAvatarImg(imgFile, "shop/takeout/");
                takeoutProduct.setImgUrl(uploadUrl);
            }
            String labels = String.valueOf(label);
            takeoutProduct.setLabel(labels);
            //产品属性集合
            Set<String> propertyLists = new HashSet<>();
            StringBuffer property = new StringBuffer();
            if (propertyList != null && propertyList.getPropertyList() != null) {
                for (Property every : propertyList.getPropertyList()) {
                    propertyLists.add(every.getPropertyE());
                }
                for (String ever : propertyLists) {
                    property.append(ever).append(",");
                }
            }
            LoginUser user = OtherUtils.getLoginUserByRequest();
            Shop shop = shopService.getShopIdByAccountShopId(user.getUserId(), takeoutProduct.getMark());
            if (productList != null && productList.getList().size() > 0) {
                for (ListProperty each : productList.getList()) {
                    takeoutProduct.setShopId(shop.getShopId());
                    takeoutProduct.setPrice(each.getPrice());
                    takeoutProduct.setPriceCanhe(each.getPriceCanhe());
                    takeoutProduct.setProperty(String.valueOf(property));
                    takeoutProduct.setInventory(each.getInventory());
                    takeoutProduct.setInventoryCount(each.getInventoryCount());
                    takeoutProductService.addTakeoutProduct(takeoutProduct);
                }
            } else {
                takeoutProduct.setShopId(shop.getShopId());
                takeoutProduct.setProperty(String.valueOf(property));
                takeoutProductService.addTakeoutProduct(takeoutProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error(cls, e.getMessage(), e);
        }
    }

    //获取订单列表json数据
    @Override
    public TableResponseModel getOrderListJsonData(HttpServletRequest request) {
        int pageNo = getPageByKey(request, "pageSize");
        int pageLimit = getPageByKey(request, "limit");
        //获取查询条件
        String orderNumber = request.getParameter("orderNumber");
        //获取查询按钮值
        String buttonVal = request.getParameter("buttonVal");
        int orderMark = OtherUtils.getMarkByButtonVal(buttonVal);
        if (StringUtils.isEmpty(orderNumber)) {
            orderNumber = null;
        }
        try {
            //全部订单列表加条件
            Integer accountShopId = getLoginUserByRequest().getUserId();
            List<OrderRecord> accountList = orderRecordService.getTakeoutOrderListByCondition(accountShopId, orderNumber, orderMark, pageNo, pageLimit);
            return new TableResponseModel<List<OrderRecord>>(new PageInfo<OrderRecord>(accountList).getPages(), accountList);
        } catch (Exception e) {
            return new TableResponseModel<Object>(0, null);
        }
    }


    //获取订单评论列表json数据
    @Override
    public TableResponseModel getCommentListJsonData(HttpServletRequest request) {
        int pageNo = getPageByKey(request, "pageSize");
        int pageLimit = getPageByKey(request, "limit");

        //获取查询条件
        String start = request.getParameter("start");  //开始时间
        String end = request.getParameter("end");  //结束时间
        String starRating = request.getParameter("starRating");  //星级
        try {
            Integer accountShopId = getLoginUserByRequest().getUserId();
//            List<TakeoutCommentList> resultList = shopMessageCommentService.getTakeoutCommentListByShopId(accountShopId, pageNo, pageLimit);
//            return new TableResponseModel<List<TakeoutCommentList>>(new PageInfo<TakeoutCommentList>(resultList).getPages(), resultList);
            return null;
        } catch (Exception e) {
            return new TableResponseModel<Object>(0, null);
        }
    }

    //商品数量置满
    @Override
    public AjaxResponseModel filledUpProductInventoryById(Integer productId) {
        if (productId != null) {
            try {
                Integer accountShopId = getLoginUserByRequest().getUserId();
                takeoutProductService.filledUpProductInventory(accountShopId, productId);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "成功");
            } catch (Exception e) {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
            }
        } else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    //商品数量沽清
    @Override
    public AjaxResponseModel emptyProductInventoryById(Integer productId) {
        if (productId != null) {
            try {
                Integer accountShopId = getLoginUserByRequest().getUserId();
                takeoutProductService.emptyProductInventory(accountShopId, productId);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "成功");
            } catch (Exception e) {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
            }
        } else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    //通过商户ID获取分类和商品接口
    @Override
    public AjaxResponseModel getCategoryAndProductByAccountShopId(int accountShopId, HttpServletRequest request) {
        //通过商户ID获取分类和商品;
        try {
            //获取分类
            List<TakeoutCategory> categoryList = takeoutCategoryService.getTakeoutCategoryListByAccountShopId(accountShopId);
            if (categoryList == null) {
                //分类为空，则返回
                return new AjaxResponseModel<>(Globals.API_SUCCESS, "分类列表为空", null);
            }
            List<ReturnCategoryAndProduct> data = new ArrayList<>();
            for (TakeoutCategory each : categoryList) {
                //循环分类集合，通过分类ID获取商品
                List<TakeoutProduct> productList = takeoutProductService.getTakeoutProductById(each.getId());
                if (productList != null) {
                    for (TakeoutProduct every : productList) {
                        every.setImgUrl(OtherUtils.getRootDirectory() + every.getImgUrl());
                    }
                }
                //添加分类和商品集合
                data.add(new ReturnCategoryAndProduct(each.getCategoryName(), each.getDescription(), each.getId(), productList));
            }
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", data);
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "捕获到异常", null);
        }
    }

    //删除分类接口
    @Override
    @Transactional
    public AjaxResponseModel delCategoryById(int categoryId) {
        //先删除分类下的商品，再删除分类
        try {
            takeoutProductService.delProductByCId(categoryId);
            takeoutCategoryService.delCategoryById(categoryId);
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "删除成功");
        } catch (Exception e) {
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "删除失败");
        }
    }

    //通过商户ID获取分类列表
    @Override
    public void getCategoryListById(Model model, HttpServletRequest request) {
        try {
            LoginUser user = OtherUtils.getLoginUserByRequest();
            List<TakeoutCategory> resultList = takeoutCategoryService.getTakeoutCategoryListByAccountShopId(user.getUserId());
            if (resultList == null) {
                return;
            }
            model.addAttribute("data", resultList);
        } catch (Exception e) {
            model.addAttribute("data", null);
        }
    }

    //通过产品id查找出产品信息
    @Override
    public void getProductDetailByPID(Model model, int productId) {
        //查找商品信息 model返回给前台
        try {
            LoginUser user = OtherUtils.getLoginUserByRequest();
            List<TakeoutCategory> resultList = takeoutCategoryService.getTakeoutCategoryListByAccountShopId(user.getUserId());
            if (resultList == null) {
                return;
            }
            model.addAttribute("data", resultList);
            TakeoutProduct takeoutProduct = takeoutProductService.getTakeoutProductById(user.getUserId(), productId);
            if (takeoutProduct != null) {
                takeoutProduct.setImgUrl(OtherUtils.getRootDirectory() + takeoutProduct.getImgUrl());
            }
            model.addAttribute("dataPro", takeoutProduct);
        } catch (Exception e) {
            model.addAttribute("dataPro", null);
        }
    }

    //保存商品修改
    @Override
    public void saveProductEdit(TakeoutProduct takeoutProduct, MultipartFile imgFile,String label, PropertyList propertyList) throws Exception {
        /**
         * @author 马鹏昊
         * @desc 只判断为空还不行要判断imgFile有没有信息（修改信息但是没修改图片的情况）
         */
        if (imgFile != null&&!imgFile.isEmpty()) {
            String uploadUrl = FileUploadUtils.appUploadAvatarImg(imgFile, "shop/takeout/");
            takeoutProduct.setImgUrl(uploadUrl);
        }
        StringBuffer property = new StringBuffer();
        Set<String> propertySet = new HashSet<>();
        if (propertyList != null && propertyList.getPropertyList() != null) {
            System.out.println("===");
            for (Property ever : propertyList.getPropertyList()) {
                propertySet.add(ever.getPropertyE());
            }
            for (String each : propertySet) {
                property.append(each).append(",");
            }
        }
        takeoutProduct.setProperty(String.valueOf(property));
        try {
            String desc = takeoutProduct.getDescription();
            desc = desc.replace("\r\n"," ");
            takeoutProduct.setDescription(desc);
            takeoutProductService.editProductById(takeoutProduct);
        } catch (Exception e) {
            throw new Exception("保存修改失败");
        }
    }

    //新订单确认接单接口
    @Override
    public AjaxResponseModel confirmTheOrderSuc(String orderNumber) {
        boolean result = orderRecordService.changeOrderStateByOrderNumber(orderNumber, Globals.DEFAULT_T_CONFIRM_ORDER);
        if (result) {
            return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "接单成功!,请等待刷新当前界面");
        }
        return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "接单失败!");
    }

    //配送商品接口
    @Override
    public AjaxResponseModel itemsToShip(String orderNumber) {
        boolean result = orderRecordService.changeOrderStateByOrderNumber(orderNumber, 3);
        if (result) {
            return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "配送中。。。。");
        }
        return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "改变订单配送失败!");
    }

    //回复催单接口
    @Override
    public AjaxResponseModel replyMessage(AjaxRequestParams params) {
        if (params != null && params.getOrderNumber() != null) {
            try {
                //查找订单
                OrderRecord orderRecord = orderRecordService.getOrderRecordByOrderNumber(params.getOrderNumber());
                if (orderRecord != null) {
                    //改变订单催单状态
                    takeoutOrderService.updateReminderStateByOrderId(orderRecord.getId(), 0);
                    Jpush.jPushMethod(orderRecord.getToken(), "商家正在处理中，请稍等。。", "ALERT");
                    return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
                } else {
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
                }
            } catch (Exception e) {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        } else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    @Override
    public AjaxResponseModel takeOnProduct(Model model, String selectedIds) {
        //查找商品信息 model返回给前台
        try {
            //转成int型id
            String[] idStrs = selectedIds.split(",");
            List<Integer> idInts = new ArrayList<>();
            for (String idStr : idStrs) {
                Integer i = Integer.parseInt(idStr);
                idInts.add(i);
            }
            return takeoutProductService.setProductTakeOn(idInts);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "上架失败");
        }
    }

    @Override
    public AjaxResponseModel takeOffProduct(Model model, String productIds) {
        try {
            //转成int型id
            String[] idStrs = productIds.split(",");
            List<Integer> idInts = new ArrayList<>();
            for (String idStr : idStrs) {
                Integer i = Integer.parseInt(idStr);
                idInts.add(i);
            }
            return takeoutProductService.setProductTakeOff(idInts);
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "下架失败");
        }
    }

    /* ====================JSP页面接口结束========================== */
}
