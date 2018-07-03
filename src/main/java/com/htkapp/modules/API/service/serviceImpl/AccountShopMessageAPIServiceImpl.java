package com.htkapp.modules.API.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.MD5Utils;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.dto.ReturnAllProcessedOrderList;
import com.htkapp.modules.API.dto.ReturnProcessedGroupBuyOrder;
import com.htkapp.modules.API.dto.ReturnProcessedTakeoutOrder;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.service.AccountServiceI;
import com.htkapp.modules.API.service.AccountShopMessageAPIService;
import com.htkapp.modules.API.service.SMSBaseServiceI;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.common.entity.NoticeCenter;
import com.htkapp.modules.common.service.NoticeCenterService;
import com.htkapp.modules.merchant.common.entity.AccountShopHandleOrderLog;
import com.htkapp.modules.merchant.common.service.AccountShopHandleOrderLogService;
import com.htkapp.modules.merchant.common.service.UserServiceI;
import com.htkapp.modules.merchant.pay.entity.OrderBuyPackage;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.OrderBuyPackageService;
import com.htkapp.modules.merchant.pay.service.OrderProductService;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;
import com.htkapp.modules.merchant.pay.service.TakeoutOrderService;
import com.htkapp.modules.merchant.shop.dto.AppAccountShopData;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.xiaoleilu.hutool.db.sql.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinqilei on 17-7-5.
 */
@Service
public class AccountShopMessageAPIServiceImpl implements AccountShopMessageAPIService {

    @Resource
    private UserServiceI userService;
    @Resource
    private SMSBaseServiceI smsService;
    @Resource
    private NoticeCenterService noticeCenterService;
    @Resource
    private OrderProductService orderProductService;
    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private ShopServiceI shopService;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private AccountShopHandleOrderLogService handleOrderLogService;
    @Resource
    private MoreMethodsUtils methodsUtils;

    /* ========================接口开始============================ */
    //商户app端登陆接口
    @Override
    public APIResponseModel appAccountShopLoginByUserName(String userName, String password, String role) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(role)) {
            return new APIResponseModel(Globals.API_REQUEST_BAD, "请求参数错误");
        } else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password) && StringUtils.isNotEmpty(role)) {
            try {
                LoginUser loginUser = new LoginUser()
                        .setUserName(userName)
                        .setPassword(MD5Utils.getMD5Encode(password))
                        .setRole(role);
                LoginUser enterUser = userService.checkUser(loginUser);
                if (enterUser == null) {
                    return new APIResponseModel(Globals.API_FAIL, "用户登陆失败");
                } else {
                    return new APIResponseModel<String>(Globals.API_SUCCESS, "用户登陆成功", enterUser.getToken());
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD, "请求参数错误");
        }
    }

    //忘记密码 通过手机短信找回密码
    @Override
    public APIResponseModel forgetPasswordBySMS(String phone, String code, String password) {
        try {
            if (phone != null && StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(password)) {
                try {
                    String valCodeDB = smsService.findValCodeByPhone(String.valueOf(phone));
                    if (StringUtils.isEmpty(valCodeDB) || !valCodeDB.equals(code)) {
                        return new APIResponseModel(Globals.API_REQUEST_BAD, "很抱歉，验证码输入不正确，请重新输入！");
                    } else if (StringUtils.isNotEmpty(valCodeDB) && valCodeDB.equals(code)) {
                        //记录下修改密码行为
                        AccountShop accountShop = new AccountShop();
                        accountShop.setUserName(password);
                        accountShop.setPassword(MD5Utils.getMD5Encode(password));
                        try {
                            boolean result = accountShopService.changePasswordBySMS(accountShop);
                            if (result) {
                                return new APIResponseModel(Globals.API_SUCCESS, "重置密码成功");
                            } else {
                                return new APIResponseModel(Globals.API_SUCCESS, "重置密码失败");
                            }
                        } catch (Exception e) {
                            return new APIResponseModel(Globals.API_FAIL);
                        }
                    } else {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                }
            } else {
                return new APIResponseModel(Globals.API_REQUEST_BAD);
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }


    //商户app端个人信息
    @Override
    public APIResponseModel getAppAccountShopData(String token) {
        try {
            AccountShop accountShop = accountShopService.selectByToken(token);
            if (accountShop == null) {
                return new APIResponseModel(Globals.API_FAIL, "获取个人信息失败");
            } else {
                AppAccountShopData appAccountShopData = new AppAccountShopData();
                appAccountShopData.setToken(token);
                appAccountShopData.setAvatarImg(OtherUtils.getRootDirectory() + accountShop.getAvatarImg());
                appAccountShopData.setManagerName(accountShop.getNickName());
                try {
                    Shop shop = shopService.getShopByAccountShopId(accountShop.getId());
                    if (shop != null) {
                        appAccountShopData.setShopState(shop.getState());
                        appAccountShopData.setCustomerServicePhone(shop.getPhone());
                        return new APIResponseModel<AppAccountShopData>(Globals.API_SUCCESS, "查找用户信息成功", appAccountShopData);
                    } else {
                        return new APIResponseModel(Globals.API_FAIL, "获取个人信息失败");
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL);
                }
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }

    //改变商铺营业状态
    @Override
    public APIResponseModel changeShopStateByToken(String token, Integer shopStateId) {
        if (StringUtils.isNotEmpty(token) && shopStateId != null) {
            try {
                AccountShop accountShop = accountShopService.selectByToken(token);
                if (accountShop != null) {
                    try {
                        boolean result = shopService.changeShopStateById(accountShop.getId(), shopStateId);
                        if (result) {
                            String tips = "";
                            if (shopStateId == 0) {
                                tips = "当前店铺已调整为休息状态";
                            } else if (shopStateId == 1) {
                                tips = "当前店铺已调整为营业状态";
                            }
                            return new APIResponseModel(Globals.API_SUCCESS, tips);
                        } else {
                            return new APIResponseModel(Globals.API_FAIL);
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                    }
                } else {
                    return new APIResponseModel(Globals.API_FAIL);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //获取已处理订单总列表(团购、外卖)
//    @Override
//    public APIResponseModel getAllProcessedOrderList(String token, int pageNumber) {
//        //根据商户token查询商户下的外卖店铺和团购店铺
//        //如果有外卖和团购店铺　再根据店铺id查询店铺下的订单数据
//        //根据查询到的订单列表　再筛选出是否已处理订单
//        //把查询到的数据按时间排序，返回数据
//        try {
//            AccountShop accountShop = accountShopService.selectByToken(token);
//            if (accountShop != null && accountShop.getId() != null) {
//                //根据商户id查询店铺信息
//                try {
//                    ReturnAllProcessedOrderList returnAllProcessedOrderList = new ReturnAllProcessedOrderList();
//                    List<ReturnProcessedGroupBuyOrder> groupBuyOrderLists = new ArrayList<>();
//                    List<ReturnProcessedTakeoutOrder> takeoutOrderLists = new ArrayList<>();
//                    int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
//                    int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
//                    if (pageNumber > 1) {
//                        pageNo = pageNumber;
//                    }
//                    String orderDesc = "gmt_create desc ";
//                    List<OrderRecord> resultList = orderRecordService.getAllProcessedOrderListByIdAndOrderState(accountShop.getId(), 1, orderDesc, pageNo, pageLimit);
//                    for (OrderRecord each : resultList) {
//                        if (each.getMark() == 0) {
//                            //外卖
//                            //传入商铺id，商铺类型mark  查询订单
//                            try {
//                                //9.外卖  {订单号，下单时间，收货人姓名，收货地址，收货人电话 ，
//                                // 收货人经纬度 ，价格 ，   送达状态， 送达时间 ，送货人姓名，送货人电话 ， 下单列表}
//                                ReturnProcessedTakeoutOrder returnProcessedTakeoutOrder = new ReturnProcessedTakeoutOrder();
//                                List<OrderProduct> orderProductList = orderProductService.getProductListByOrderId(each.getId());
//                                if (orderProductList != null) {
//                                    String deliveryTime = "2017-07-06 12:00:00.0";
//                                    String time = deliveryTime.substring(0, deliveryTime.length() - 5);
//                                    System.out.println(time.substring(10));
//                                    returnProcessedTakeoutOrder.setProductList(orderProductList);
//                                    returnProcessedTakeoutOrder.setDeliveryPhone("18888888888");
//                                    returnProcessedTakeoutOrder.setDeliveryTime(time.substring(10));
//                                    returnProcessedTakeoutOrder.setLatitude(each.getLatitude());
//                                    returnProcessedTakeoutOrder.setLongitude(each.getLongitude());
//                                    returnProcessedTakeoutOrder.setOrderAmount(each.getOrderAmount());
//                                    returnProcessedTakeoutOrder.setOrderNumber(each.getOrderNumber());  //订单号
//                                    returnProcessedTakeoutOrder.setOrderState(each.getOrderState());
//                                    returnProcessedTakeoutOrder.setShipperName("林梅梅");
//                                    returnProcessedTakeoutOrder.setReceiptName(each.getReceiptName()); //收货人姓名
//                                    returnProcessedTakeoutOrder.setOrderTime(each.getOrderTime());  //下单时间
//                                    returnProcessedTakeoutOrder.setReceivingCall(each.getReceivingCall());
//                                    returnProcessedTakeoutOrder.setShippingAddress(each.getShippingAddress());
//                                    returnProcessedTakeoutOrder.setMark(each.getMark());
//                                    returnProcessedTakeoutOrder.setOrderState(each.getOrderState());
//                                    takeoutOrderLists.add(returnProcessedTakeoutOrder);
//                                } else {
//                                    continue;
//                                }
//                                returnAllProcessedOrderList.setProcessedTakeoutOrderList(takeoutOrderLists);
//                            } catch (Exception e) {
//                                return new APIResponseModel(Globals.API_FAIL);
//                            }
//                        } else if (each.getMark() == 1) {
//                            //团购
//                            //8.团购 List{订单号，下单时间，价格  ， 套餐名， 套餐图片}
//                            //团购订单状态:10未使用   11使用　12取消订单  13订单完成　
//                            try {
//                                ReturnProcessedGroupBuyOrder processedGroupBuyOrder = new ReturnProcessedGroupBuyOrder();
//                                OrderBuyPackage orderBuyPackage = orderBuyPackageService.getOrderPackageById(each.getId());
//                                if (orderBuyPackage != null) {
//                                    processedGroupBuyOrder.setPackageName(orderBuyPackage.getPackageName());
//                                    processedGroupBuyOrder.setOrderAmount(each.getOrderAmount());
//                                    processedGroupBuyOrder.setOrderNumber(each.getOrderNumber());
//                                    processedGroupBuyOrder.setOrderTime(each.getOrderTime());
//                                    processedGroupBuyOrder.setMark(each.getMark());
//                                    processedGroupBuyOrder.setLogoUrl(OtherUtils.getRootDirectory() + orderBuyPackage.getLogoUrl());  //套餐图片
//                                    processedGroupBuyOrder.setOrderState(each.getOrderState());
//                                    groupBuyOrderLists.add(processedGroupBuyOrder);
//                                    System.out.println("总列表－团购");
//                                }
//                                returnAllProcessedOrderList.setProcessedGroupBuyOrderList(groupBuyOrderLists);
//                            } catch (Exception e) {
//                                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                            }
//                        } else {
//                            return new APIResponseModel(Globals.API_FAIL);
//                        }
//                    }
//                    List list = new ArrayList();
//                    if (takeoutOrderLists != null && groupBuyOrderLists != null) {
//                        for (ReturnProcessedTakeoutOrder each : takeoutOrderLists) {
//                            list.add(each);
//                        }
//                        for (ReturnProcessedGroupBuyOrder each : groupBuyOrderLists) {
//                            list.add(each);
//                        }
//                        return new APIResponseModel<List>(Globals.API_SUCCESS, "成功", list);
//                    } else {
//                        return new APIResponseModel<ReturnAllProcessedOrderList>(Globals.API_FAIL);
//                    }
//                } catch (Exception e) {
//                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                }
//            } else {
//                return new APIResponseModel(Globals.API_FAIL);
//            }
//        } catch (Exception e) {
//            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//        }
//    }

    //商户已处理的团购订单列表
//    @Override
//    public APIResponseModel getGroupBuyOrderListByAccountShopToken(String token, Integer mark, int pageNumber) {
//        if (mark != null) {
//            try {
//                AccountShop accountShop = accountShopService.selectByToken(token);
//                if (accountShop != null && accountShop.getId() != null) {
//                    try {
//                        Shop shop = shopService.getShopByAccountShopIdAndMark(accountShop.getId(), mark);
//                        int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
//                        int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
//                        if (pageNumber > 1) {
//                            pageNo = pageNumber;
//                        }
//                        String orderDesc = "gmt_create desc ";
//                        try {
//                            List<OrderRecord> groupBuyOrderList = orderRecordService.getGroupBuyOrderListByIdAndOrderState(shop.getShopId(), 1, orderDesc, pageNo, pageLimit);
//                            if (groupBuyOrderList != null) {
//                                try {
//                                    List<ReturnProcessedGroupBuyOrder> groupBuyOrderLists = new ArrayList<>();
//                                    for (OrderRecord eachGroupBuy : groupBuyOrderList) {
//                                        ReturnProcessedGroupBuyOrder processedGroupBuyOrder = new ReturnProcessedGroupBuyOrder();
//                                        OrderBuyPackage orderBuyPackage = orderBuyPackageService.getOrderPackageById(eachGroupBuy.getId());
//                                        if (orderBuyPackage != null) {
//                                            processedGroupBuyOrder.setPackageName(orderBuyPackage.getPackageName());
//                                            processedGroupBuyOrder.setOrderAmount(eachGroupBuy.getOrderAmount());
//                                            processedGroupBuyOrder.setOrderNumber(eachGroupBuy.getOrderNumber());
//                                            processedGroupBuyOrder.setOrderTime(eachGroupBuy.getOrderTime());
//                                            processedGroupBuyOrder.setMark(eachGroupBuy.getMark());
//                                            processedGroupBuyOrder.setLogoUrl(OtherUtils.getRootDirectory() + orderBuyPackage.getLogoUrl());
//                                            processedGroupBuyOrder.setOrderState(eachGroupBuy.getOrderState());
//                                            groupBuyOrderLists.add(processedGroupBuyOrder);
//                                        }
//                                    }
//                                    return new APIResponseModel<List<ReturnProcessedGroupBuyOrder>>(Globals.API_SUCCESS, "成功", groupBuyOrderLists);
//                                } catch (Exception e) {
//                                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                                }
//                            } else {
//                                return new APIResponseModel(Globals.API_FAIL);
//                            }
//                        } catch (Exception e) {
//                            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                        }
//                    } catch (Exception e) {
//                        return new APIResponseModel(Globals.API_FAIL);
//                    }
//                } else {
//                    return new APIResponseModel(Globals.API_FAIL);
//                }
//            } catch (Exception e) {
//                return new APIResponseModel(Globals.API_FAIL);
//            }
//        } else {
//            return new APIResponseModel(Globals.API_REQUEST_BAD);
//        }
//    }

    //商户已处理的外卖订单列表
    @Override
    public APIResponseModel getTakeoutOrderListByAccountShopToken(String token, Integer mark, int pageNumber) {
        if (mark != null) {
            try {
                AccountShop accountShop = accountShopService.selectByToken(token);
                if (accountShop != null && accountShop.getId() != null) {
                    try {
                        Shop shop = shopService.getShopByAccountShopIdAndMark(accountShop.getId(), mark);
                        int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
                        int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
                        if (pageNumber > 1) {
                            pageNo = pageNumber;
                        }
                        String orderDesc = "gmt_create desc ";
                        try {
                            //9.外卖  {订单号，下单时间，收货人姓名，收货地址，收货人电话 ，
                            // 收货人经纬度 ，价格 ，   送达状态， 送达时间 ，送货人姓名，送货人电话 ， 下单列表}
                            List<OrderRecord> takeoutOrderList = orderRecordService.getTakeoutOrderListByIdAndOrderState(shop.getShopId(), 1, orderDesc, pageNo, pageLimit);
                            if (takeoutOrderList != null) {
                                List<ReturnProcessedTakeoutOrder> takeoutOrderLists = new ArrayList<>();
                                for (OrderRecord eachTakeout : takeoutOrderList) {
                                    ReturnProcessedTakeoutOrder returnProcessedTakeoutOrder = new ReturnProcessedTakeoutOrder();
                                    List<OrderProduct> orderProductList = orderProductService.getProductListByOrderId(eachTakeout.getId());
                                    if (orderProductList != null) {
                                        String time = eachTakeout.getModifiedTime().substring(0, eachTakeout.getModifiedTime().length() - 5);
                                        returnProcessedTakeoutOrder.setProductList(orderProductList);
                                        returnProcessedTakeoutOrder.setDeliveryPhone(eachTakeout.getReceivingCall());
                                        returnProcessedTakeoutOrder.setDeliveryTime(time.substring(10));
                                        returnProcessedTakeoutOrder.setLatitude(eachTakeout.getLatitude());
                                        returnProcessedTakeoutOrder.setLongitude(eachTakeout.getLongitude());
                                        returnProcessedTakeoutOrder.setOrderAmount(eachTakeout.getOrderAmount());
                                        returnProcessedTakeoutOrder.setOrderNumber(eachTakeout.getOrderNumber());  //订单号
                                        returnProcessedTakeoutOrder.setOrderState(eachTakeout.getOrderState());
                                        returnProcessedTakeoutOrder.setShipperName(eachTakeout.getReceiptName());
                                        returnProcessedTakeoutOrder.setReceiptName(eachTakeout.getReceiptName()); //收货人姓名
                                        returnProcessedTakeoutOrder.setOrderTime(eachTakeout.getOrderTime());  //下单时间
                                        returnProcessedTakeoutOrder.setReceivingCall(eachTakeout.getReceivingCall());
                                        returnProcessedTakeoutOrder.setShippingAddress(eachTakeout.getShippingAddress());
                                        returnProcessedTakeoutOrder.setMark(mark);
                                        returnProcessedTakeoutOrder.setOrderState(eachTakeout.getOrderState());
                                        takeoutOrderLists.add(returnProcessedTakeoutOrder);
                                    }
                                }
                                return new APIResponseModel<List<ReturnProcessedTakeoutOrder>>(Globals.API_SUCCESS, "成功", takeoutOrderLists);
                            } else {
                                return new APIResponseModel<>(Globals.API_SUCCESS,"没有数据",null);
                            }
                        } catch (Exception e) {
                            return new APIResponseModel(Globals.API_FAIL);
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                    }
                } else {
                    return new APIResponseModel(Globals.API_FAIL);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //商户已处理的订座订单列表
//    @Override
//    public APIResponseModel getSeatOrderListByAccountShopToken(String token, int pageNumber) {
//        return null;
//    }

    //商户未处理的订单总列表
//    @Override
//    public APIResponseModel getAllUntreatedOrderList(String token, int pageNumber) {
//        try {
//            AccountShop accountShop = accountShopService.selectByToken(token);
//            if (accountShop != null && accountShop.getId() != null) {
//                //根据商户id查询店铺信息
//                try {
//                    ReturnAllProcessedOrderList returnAllProcessedOrderList = new ReturnAllProcessedOrderList();
//                    List<ReturnProcessedGroupBuyOrder> groupBuyOrderLists = new ArrayList<>();
//                    List<ReturnProcessedTakeoutOrder> takeoutOrderLists = new ArrayList<>();
//                    int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
//                    int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
//                    if (pageNumber > 1) {
//                        pageNo = pageNumber;
//                    }
//                    String orderDesc = "gmt_create desc ";
//                    List<OrderRecord> resultList = orderRecordService.getAllProcessedOrderListByIdAndOrderState(accountShop.getId(), 0, orderDesc, pageNo, pageLimit);
//                    for (OrderRecord each : resultList) {
//                        if (each.getMark() == 0) {
//                            //外卖
//                            //传入商铺id，商铺类型mark  查询订单
//                            try {
//                                //9.外卖  {订单号，下单时间，收货人姓名，收货地址，收货人电话 ，
//                                // 收货人经纬度 ，价格 ，   送达状态， 送达时间 ，送货人姓名，送货人电话 ， 下单列表}
//                                //外卖订单完成状态是9
//                                ReturnProcessedTakeoutOrder returnProcessedTakeoutOrder = new ReturnProcessedTakeoutOrder();
//                                List<OrderProduct> orderProductList = orderProductService.getProductListByOrderId(each.getId());
//                                if (orderProductList != null) {
//                                    String deliveryTime = "2017-07-06 12:00:00.0";
//                                    String time = deliveryTime.substring(0, deliveryTime.length() - 5);
//                                    System.out.println(time.substring(10));
//                                    returnProcessedTakeoutOrder.setProductList(orderProductList);
//                                    returnProcessedTakeoutOrder.setDeliveryPhone("18888888888");
//                                    returnProcessedTakeoutOrder.setDeliveryTime(time.substring(10));
//                                    returnProcessedTakeoutOrder.setLatitude(each.getLatitude());
//                                    returnProcessedTakeoutOrder.setLongitude(each.getLongitude());
//                                    returnProcessedTakeoutOrder.setOrderAmount(each.getOrderAmount());
//                                    returnProcessedTakeoutOrder.setOrderNumber(each.getOrderNumber());  //订单号
//                                    returnProcessedTakeoutOrder.setOrderState(each.getOrderState());
//                                    returnProcessedTakeoutOrder.setShipperName("林梅梅");
//                                    returnProcessedTakeoutOrder.setReceiptName(each.getReceiptName()); //收货人姓名
//                                    returnProcessedTakeoutOrder.setOrderTime(each.getOrderTime());  //下单时间
//                                    returnProcessedTakeoutOrder.setReceivingCall(each.getReceivingCall());
//                                    returnProcessedTakeoutOrder.setShippingAddress(each.getShippingAddress());
//                                    returnProcessedTakeoutOrder.setMark(each.getMark());
//                                    returnProcessedTakeoutOrder.setOrderState(each.getOrderState());
//                                    takeoutOrderLists.add(returnProcessedTakeoutOrder);
//                                    System.out.println("未处理外卖订单列表");
//                                } else {
//                                    continue;
//                                }
//                                returnAllProcessedOrderList.setProcessedTakeoutOrderList(takeoutOrderLists);
//                            } catch (Exception e) {
//                                return new APIResponseModel(Globals.API_FAIL);
//                            }
//                        } else if (each.getMark() == 1) {
//                            //团购
//                            //8.团购 List{订单号，下单时间，价格  ， 套餐名， 套餐图片}
//                            //团购订单状态:10未使用   11使用　12取消订单  13订单完成　
//                            try {
//                                //未使用
//                                ReturnProcessedGroupBuyOrder processedGroupBuyOrder = new ReturnProcessedGroupBuyOrder();
//                                OrderBuyPackage orderBuyPackage = orderBuyPackageService.getOrderPackageById(each.getId());
//                                if (orderBuyPackage != null) {
//                                    processedGroupBuyOrder.setPackageName(orderBuyPackage.getPackageName());
//                                    processedGroupBuyOrder.setOrderAmount(each.getOrderAmount());
//                                    processedGroupBuyOrder.setOrderNumber(each.getOrderNumber());
//                                    processedGroupBuyOrder.setOrderTime(each.getOrderTime());
//                                    processedGroupBuyOrder.setMark(each.getMark());
//                                    processedGroupBuyOrder.setLogoUrl(OtherUtils.getRootDirectory() + orderBuyPackage.getLogoUrl());  //套餐图片
//                                    processedGroupBuyOrder.setOrderState(each.getOrderState());
//                                    processedGroupBuyOrder.setPhone(each.getReceivingCall());
//                                    groupBuyOrderLists.add(processedGroupBuyOrder);
//                                    System.out.println("未处理总列表－团购");
//                                    returnAllProcessedOrderList.setProcessedGroupBuyOrderList(groupBuyOrderLists);
//                                } else {
//                                    returnAllProcessedOrderList.setProcessedGroupBuyOrderList(null);
//                                }
//                            } catch (Exception e) {
//                                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                            }
//                        } else {
//                            return new APIResponseModel(Globals.API_FAIL);
//                        }
//                    }
//                    List list = new ArrayList();
//                    if (takeoutOrderLists != null && groupBuyOrderLists != null) {
//                        for (ReturnProcessedTakeoutOrder each : takeoutOrderLists) {
//                            list.add(each);
//                        }
//                        for (ReturnProcessedGroupBuyOrder each : groupBuyOrderLists) {
//                            list.add(each);
//                        }
//                        System.out.println("-----");
//                        return new APIResponseModel<List>(Globals.API_SUCCESS, "成功", list);
//                    } else {
//                        return new APIResponseModel<ReturnAllProcessedOrderList>(Globals.API_FAIL);
//                    }
//                } catch (Exception e) {
//                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                }
//            } else {
//                return new APIResponseModel(Globals.API_FAIL);
//            }
//        } catch (Exception e) {
//            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//        }
//    }

    //商户未处理的外卖订单列表
    @Override
    public APIResponseModel getUntreatedTakeoutOrderList(String token, int pageNumber) {
        int mark = 0;
        try {
            AccountShop accountShop = accountShopService.selectByToken(token);
            if (accountShop != null && accountShop.getId() != null) {
                try {
                    Shop shop = shopService.getShopByAccountShopIdAndMark(accountShop.getId(), mark);
                    int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
                    int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
                    if (pageNumber > 1) {
                        pageNo = pageNumber;
                    }
                    String orderDesc = "gmt_create desc ";
                    try {
                        //9.外卖  {订单号，下单时间，收货人姓名，收货地址，收货人电话 ，
                        // 收货人经纬度 ，价格 ，   送达状态， 送达时间 ，送货人姓名，送货人电话 ， 下单列表}
                        List<OrderRecord> takeoutOrderList = orderRecordService.getTakeoutOrderListByIdAndOrderState(shop.getShopId(), 0, orderDesc, pageNo, pageLimit);
                        if (takeoutOrderList != null) {
                            List<ReturnProcessedTakeoutOrder> takeoutOrderLists = new ArrayList<>();
                            for (OrderRecord eachTakeout : takeoutOrderList) {
                                //外卖订单码１代表用户下单成功
                                ReturnProcessedTakeoutOrder returnProcessedTakeoutOrder = new ReturnProcessedTakeoutOrder();
                                List<OrderProduct> orderProductList = orderProductService.getProductListByOrderId(eachTakeout.getId());
                                if (orderProductList != null) {
                                    String deliveryTime = "2017-07-06 12:00:00.0";
                                    String time = deliveryTime.substring(0, deliveryTime.length() - 5);
                                    System.out.println(time.substring(10));
                                    returnProcessedTakeoutOrder.setProductList(orderProductList);
                                    returnProcessedTakeoutOrder.setDeliveryPhone("18888885888");
                                    returnProcessedTakeoutOrder.setDeliveryTime(time.substring(10));
                                    returnProcessedTakeoutOrder.setLatitude(eachTakeout.getLatitude());
                                    returnProcessedTakeoutOrder.setLongitude(eachTakeout.getLongitude());
                                    returnProcessedTakeoutOrder.setOrderAmount(eachTakeout.getOrderAmount());
                                    returnProcessedTakeoutOrder.setOrderNumber(eachTakeout.getOrderNumber());  //订单号
                                    returnProcessedTakeoutOrder.setOrderState(eachTakeout.getOrderState());
                                    returnProcessedTakeoutOrder.setShipperName("林梅梅");
                                    returnProcessedTakeoutOrder.setReceiptName(eachTakeout.getReceiptName()); //收货人姓名
                                    returnProcessedTakeoutOrder.setOrderTime(eachTakeout.getOrderTime());  //下单时间
                                    returnProcessedTakeoutOrder.setReceivingCall(eachTakeout.getReceivingCall());
                                    returnProcessedTakeoutOrder.setShippingAddress(eachTakeout.getShippingAddress());
                                    returnProcessedTakeoutOrder.setMark(mark);
                                    returnProcessedTakeoutOrder.setOrderState(eachTakeout.getOrderState());
                                    takeoutOrderLists.add(returnProcessedTakeoutOrder);
                                    System.out.println("未处理外卖订单");
                                }
                            }
                            return new APIResponseModel<List<ReturnProcessedTakeoutOrder>>(Globals.API_SUCCESS, "成功", takeoutOrderLists);
                        } else {
                            return new APIResponseModel<>(Globals.API_SUCCESS,"没有数据", null);
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                }
            } else {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }

    //商户未处理的团购订单列表(加手机号数据)
//    @Override
//    public APIResponseModel getUntreatedGroupBuyOrderList(String token, int pageNumber) {
//        int mark = 1;
//        try {
//            AccountShop accountShop = accountShopService.selectByToken(token);
//            if (accountShop != null && accountShop.getId() != null) {
//                try {
//                    Shop shop = shopService.getShopByAccountShopIdAndMark(accountShop.getId(), mark);
//                    int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
//                    int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
//                    if (pageNumber > 1) {
//                        pageNo = pageNumber;
//                    }
//                    String orderDesc = "gmt_create desc ";
//                    try {
//                        List<OrderRecord> groupBuyOrderList = orderRecordService.getGroupBuyOrderListByIdAndOrderState(shop.getShopId(), 0, orderDesc, pageNo, pageLimit);
//                        if (groupBuyOrderList != null) {
//                            try {
//                                List<ReturnProcessedGroupBuyOrder> groupBuyOrderLists = new ArrayList<>();
//                                for (OrderRecord eachGroupBuy : groupBuyOrderList) {
//                                    //状态码10代表用户还没有消费
//                                    ReturnProcessedGroupBuyOrder processedGroupBuyOrder = new ReturnProcessedGroupBuyOrder();
//                                    OrderBuyPackage orderBuyPackage = orderBuyPackageService.getOrderPackageById(eachGroupBuy.getId());
//                                    if (orderBuyPackage != null) {
//                                        processedGroupBuyOrder.setPackageName(orderBuyPackage.getPackageName());
//                                        processedGroupBuyOrder.setOrderAmount(eachGroupBuy.getOrderAmount());
//                                        processedGroupBuyOrder.setOrderNumber(eachGroupBuy.getOrderNumber());
//                                        processedGroupBuyOrder.setOrderTime(eachGroupBuy.getOrderTime());
//                                        processedGroupBuyOrder.setMark(eachGroupBuy.getMark());
//                                        processedGroupBuyOrder.setLogoUrl(OtherUtils.getRootDirectory() + orderBuyPackage.getLogoUrl());
//                                        processedGroupBuyOrder.setOrderState(eachGroupBuy.getOrderState());
//                                        processedGroupBuyOrder.setPhone(eachGroupBuy.getReceivingCall());
//                                        groupBuyOrderLists.add(processedGroupBuyOrder);
//                                        System.out.println("未处理团购订单");
//                                    }
//                                }
//                                return new APIResponseModel<List<ReturnProcessedGroupBuyOrder>>(Globals.API_SUCCESS, "成功", groupBuyOrderLists);
//                            } catch (Exception e) {
//                                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                            }
//                        } else {
//                            return new APIResponseModel(Globals.API_FAIL);
//                        }
//                    } catch (Exception e) {
//                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                    }
//                } catch (Exception e) {
//                    return new APIResponseModel(Globals.API_FAIL);
//                }
//            } else {
//                return new APIResponseModel(Globals.API_FAIL);
//            }
//        } catch (Exception e) {
//            return new APIResponseModel(Globals.API_FAIL);
//        }
//    }

    //商户处理外卖订单(接单、拒单)
    //外卖订单状态: 1用户下单成功，2商家接单(当商家接单后，配送状态默认为未配送)，3商户配送中(当商户点击已发起配送，状态被改变为3,配送中)
    //           4用户收货成功  5用户取消并退款成功  6商户拒接单 9订单完成状态
    //团购订单状态:10未使用   11使用　12取消订单  13订单完成


    //商户处理外卖订单(接单、拒单, 传入不同状态id)
    @Override
    @Transactional
    public APIResponseModel handlesTakeoutOrders(String token, String orderNumber, Integer stateId, String content) {
        if (StringUtils.isNotEmpty(orderNumber) && stateId != null) {
            //商户拒接单，则要输入拒单原因内容
            try {
                AccountShop accountShop = accountShopService.selectByToken(token);
                if (accountShop != null && accountShop.getId() != null) {
                    try {
                        Shop shop = shopService.getShopByAccountShopId(accountShop.getId());
                        if (shop != null && shop.getShopId() != null) {
                            try {
                                boolean result = false;
                                if (stateId == 6) {
                                    result = orderRecordService.handlesTakeoutOrder(shop.getShopId(), orderNumber, 5);
                                    //拒单
                                    if (result) {
                                        //拒单成功
                                        //写入本次数据到通知表中
                                        try {
                                            OrderRecord orderRecord = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
                                            if (orderRecord != null && orderRecord.getToken() != null && orderRecord.getId() != null && shop.getShopName() != null) {
                                                NoticeCenter noticeCenter = new NoticeCenter();
                                                //根据订单号查询出下单用户信息,并插入到通知中心表
                                                noticeCenter.setAccountToken(orderRecord.getToken());
                                                noticeCenter.setNoticeTitle(Globals.NOTICE_CENTER_TITLE_M_JUD);
                                                noticeCenter.setNoticeContent("您在『" + shop.getShopName() + "』下的订单商家已被商家拒单, 原因是:" + content);
                                                noticeCenter.setOrderId(orderRecord.getId());
                                                noticeCenterService.insertOrderMessageToNoticeCenterDAO(noticeCenter);
                                                //记录商户处理订单记录
                                                AccountShopHandleOrderLog accountShopHandleOrderLog = new AccountShopHandleOrderLog();
                                                accountShopHandleOrderLog.setAccountShopToken(token);
                                                accountShopHandleOrderLog.setContent("拒单成功," + " " + "内容:" + content);
                                                accountShopHandleOrderLog.setOrderNumber(orderNumber);
                                                handleOrderLogService.insertAccountShopHandleOrderLog(accountShopHandleOrderLog);
//                                                Jpush.jPushMethod(orderRecord.getToken(), "外卖订单商家拒单", "ALERT");
                                                JSONObject jsonObject = new JSONObject();
                                                jsonObject.put("orderNumber", orderRecord.getOrderNumber());
                                                jsonObject.put("orderState", orderRecord.getOrderState());
                                                jsonObject.put("orderId", orderRecord.getId());
                                                methodsUtils.jPushToMerAndAccount(orderRecord.getToken(),"外卖订单商家已成功接单", jsonObject.toJSONString(), token, "接单成功", jsonObject.toJSONString(),2);
                                                return new APIResponseModel(Globals.API_SUCCESS, "拒单成功");
                                            } else {
                                                throw new RuntimeException("订单异常");
                                            }
                                        } catch (Exception e) {
                                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                                        }
                                    } else {
                                        //拒单失败
                                        return new APIResponseModel(Globals.API_FAIL, "拒单失败");
                                    }
                                } else if (stateId == 2) {
                                    result = orderRecordService.handlesTakeoutOrder(shop.getShopId(), orderNumber, stateId);
                                    if (result) {
                                        //接单成功
                                        //写入本次数据到通知表中
                                        try {
                                            OrderRecord orderRecord = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
                                            if (orderRecord != null && orderRecord.getToken() != null && orderRecord.getId() != null && shop.getShopName() != null) {
                                                NoticeCenter noticeCenter = new NoticeCenter();
                                                //根据订单号查询出下单用户信息,并插入到通知中心表
                                                noticeCenter.setAccountToken(orderRecord.getToken());
                                                noticeCenter.setNoticeTitle(Globals.NOTICE_CENTER_TITLE_XDCG);
                                                noticeCenter.setNoticeContent("您在『" + shop.getShopName() + "』下的订单商家已接单成功   (点击查看详情)");
                                                noticeCenter.setOrderId(orderRecord.getId());
                                                noticeCenterService.insertOrderMessageToNoticeCenterDAO(noticeCenter);
                                                //记录商户处理订单记录
                                                AccountShopHandleOrderLog accountShopHandleOrderLog = new AccountShopHandleOrderLog();
                                                accountShopHandleOrderLog.setAccountShopToken(token);
                                                accountShopHandleOrderLog.setContent("接单成功," + " " + "内容: ");
                                                accountShopHandleOrderLog.setOrderNumber(orderNumber);
                                                handleOrderLogService.insertAccountShopHandleOrderLog(accountShopHandleOrderLog);
//                                                Jpush.jPushMethod(orderRecord.getToken(), "外卖订单商家已成功接单", "ALERT");
//                                                Jpush.jPushMethodToMerchant(token, "接单成功", "ALERT","商家版");
                                                JSONObject jsonObject = new JSONObject();
                                                jsonObject.put("orderNumber", orderRecord.getOrderNumber());
                                                jsonObject.put("orderState", orderRecord.getOrderState());
                                                jsonObject.put("orderId", orderRecord.getId());
                                                methodsUtils.jPushToMerAndAccount(orderRecord.getToken(),"外卖订单商家已成功接单", jsonObject.toJSONString(), token, "接单成功", jsonObject.toJSONString(),2);
                                                return new APIResponseModel(Globals.API_SUCCESS, "接单成功");
                                            } else {
                                                throw new RuntimeException("订单异常");
                                            }
                                        } catch (Exception e) {
                                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                            return new APIResponseModel(Globals.API_FAIL);
                                        }
                                    } else {
                                        //接单失败
                                        return new APIResponseModel(Globals.API_FAIL, "接单失败");
                                    }
                                } else {
                                    return new APIResponseModel(Globals.API_FAIL, "订单状态码未知");
                                }
                            } catch (Exception e) {
                                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                            }
                        } else {
                            return new APIResponseModel(Globals.API_FAIL);
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                    }
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "处理失败");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //商户备货成功　确认配送外卖
//    @Override
//    @Transactional
//    public APIResponseModel deliveryTakeout(String token, String orderNumber) {
//        if (StringUtils.isNotEmpty(orderNumber)) {
//            try {
//                AccountShop accountShop = accountShopService.selectByToken(token);
//                if (accountShop != null && accountShop.getId() != null) {
//                    try {
//                        Shop shop = shopService.getShopByAccountShopId(accountShop.getId());
//                        if (shop != null && shop.getShopId() != null) {
//                            OrderRecord orderRecord = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
//                            if (orderRecord != null && orderRecord.getToken() != null && orderRecord.getId() != null && shop.getShopName() != null) {
//                                //记录商户处理订单记录
//                                orderRecordService.handlesTakeoutOrder(shop.getShopId(), orderNumber, 3);
//                                AccountShopHandleOrderLog accountShopHandleOrderLog = new AccountShopHandleOrderLog();
//                                accountShopHandleOrderLog.setAccountShopToken(token);
//                                accountShopHandleOrderLog.setContent("接单成功," + " " + "内容: 商家已开始配送");
//                                accountShopHandleOrderLog.setOrderNumber(orderNumber);
//                                handleOrderLogService.insertAccountShopHandleOrderLog(accountShopHandleOrderLog);
//                                Jpush.jPushMethod(orderRecord.getToken(), "商家已开始配送", "ALERT");
//                                Jpush.jPushMethodToMerchant(token, "已开始配送", "ALERT");
//                                return new APIResponseModel(Globals.API_SUCCESS);
//                            } else {
//                                throw new RuntimeException("处理异常");
//                            }
//                        } else {
//                            return new APIResponseModel(Globals.API_FAIL);
//                        }
//                    } catch (Exception e) {
//                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                    }
//                } else {
//                    return new APIResponseModel(Globals.API_FAIL);
//                }
//            } catch (Exception e) {
//                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//            }
//        } else {
//            return new APIResponseModel(Globals.API_REQUEST_BAD);
//        }
//    }


    //验证用户消费团购订单
//    @Override
//    @Transactional
//    public APIResponseModel groupBuyConsumption(String token, String orderNumber) {
//        if (StringUtils.isNotEmpty(orderNumber)) {
//            //根据商户token查询商户下的商铺id
//            //根据商铺id查询商铺下的团购订单,再验证该订单是否已消费
//            try {
//                AccountShop accountShop = accountShopService.selectByToken(token);
//                if (accountShop != null && accountShop.getId() != null) {
//                    try {
//                        Shop shop = shopService.getShopByAccountShopIdAndMark(accountShop.getId(), 1);
//                        if (shop != null && shop.getShopId() != null) {
//                            //根据订单号和商铺id验证该订单状态
//                            OrderRecord orderRecord = orderRecordService.verifyOrderInformation(shop.getShopId(), orderNumber);
//                            if (orderRecord == null) {
//                                return new APIResponseModel(Globals.API_FAIL, "订单不存在");
//                            } else {
//                                //存在则再验证该订单是否已消费过
//                                if (orderRecord.getOrderState() == 10) {
//                                    //未使用
//                                    orderRecordService.handlesTakeoutOrder(shop.getShopId(), orderNumber, 11);
//                                    AccountShopHandleOrderLog handleOrderLog = new AccountShopHandleOrderLog();
//                                    handleOrderLog.setAccountShopToken(token);
//                                    handleOrderLog.setContent("团购," + " " + "内容:已使用");
//                                    handleOrderLog.setOrderNumber(orderNumber);
//                                    handleOrderLogService.insertAccountShopHandleOrderLog(handleOrderLog);
//                                    Account account = accountService.getAccountDataByOrderNumberAndMark(orderNumber, 1);
//                                    Jpush.jPushMethod(account.getToken(), "团购订单消费成功", "ALERT");
//                                    Jpush.jPushMethod(token, "团购订单" + orderNumber + "已消费", "ALERT");
//                                    return new APIResponseModel(Globals.API_SUCCESS);
//                                } else if (orderRecord.getOrderState() == 11) {
//                                    return new APIResponseModel(Globals.API_SUCCESS, "团购已使用");
//                                } else if (orderRecord.getOrderState() == 12) {
//                                    return new APIResponseModel(Globals.API_SUCCESS, "团购已取消");
//                                } else if (orderRecord.getOrderState() == 13) {
//                                    return new APIResponseModel(Globals.API_SUCCESS, "团购已消费");
//                                } else {
//                                    return new APIResponseModel(Globals.API_FAIL);
//                                }
//                            }
//                        } else {
//                            return new APIResponseModel(Globals.API_FAIL);
//                        }
//                    } catch (Exception e) {
//                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//                    }
//                } else {
//                    return new APIResponseModel(Globals.API_FAIL);
//                }
//            } catch (Exception e) {
//                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//            }
//        } else {
//            return new APIResponseModel(Globals.API_REQUEST_BAD);
//        }
//    }

    //回复催单接口
//    @Override
//    public AjaxResponseModel replyReminder(String orderNumber) {
//        try {
//            //改变订单状态为3（配送中）
////            boolean result = orderRecordService.changeOrderStateByOrderNumber(orderNumber, 3);
//            OrderRecord orderRecord = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
////            if (result) {
//            //给app推送消息
//            takeoutOrderService.updateReminderStateByOrderId(orderRecord.getId(), 0);
//            Jpush.jPushMethod(orderRecord.getToken(), "已收到催单", "ALERT");
////            }
//            return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
//        } catch (Exception e) {
//            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
//        }
//    }

    //商家app确认收货
    @Override
    public APIResponseModel confirmReceipt(String orderNumber, String token) {
        if(StringUtils.isNotEmpty(orderNumber) && StringUtils.isNotEmpty(token)){
            //执行逻辑
            try {
                //通过商户token　查找外卖商铺id
                AccountShop accountShop = accountShopService.selectByToken(token);
                if(accountShop != null){
                    //获取外卖商铺
                    Shop shop = shopService.getShopDataByAccountShopIdAndMark(accountShop.getId(), 0);
                    //1.根据订单号和店铺id查询订单
                    OrderRecord orderRecord = orderRecordService.getOrderRecordByTokenAndShopId(orderNumber, shop.getShopId());
                    //2.改变订单状态为已收货,并推送消息
                    orderRecordService.changeOrderStateByOrderNumber(orderNumber,4);
                    //3.推送消息
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderNumber", orderRecord.getOrderNumber());
                    jsonObject.put("orderState", orderRecord.getOrderState());
                    jsonObject.put("orderId", orderRecord.getId());
                    methodsUtils.jPushToMerAndAccount(orderRecord.getToken(),"订单被商家确认收货",jsonObject.toJSONString(),token,"订单收货成功",jsonObject.toJSONString(),2);
                    return new APIResponseModel(Globals.API_SUCCESS);
                }else {
                    return new APIResponseModel(Globals.API_FAIL,"商户不存在");
                }
            }catch (Exception e){
                return new APIResponseModel(Globals.API_FAIL,e.getMessage());
            }
        }else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //商家取消订单
    @Override
    public APIResponseModel cancelOrder(String orderNumber, String token) {
        if(StringUtils.isNotEmpty(orderNumber) && StringUtils.isNotEmpty(token)){
            try {
                //通过商户token　查找外卖商铺id
                AccountShop accountShop = accountShopService.selectByToken(token);
                if(accountShop != null){
                    //获取外卖商铺
                    Shop shop = shopService.getShopDataByAccountShopIdAndMark(accountShop.getId(), 0);
                    //1.改变订单状态为已取消,并推送消息
                    orderRecordService.changeOrderStateByOrderNumber(orderNumber,5);
                    //2.根据订单号和店铺id查询订单
                    OrderRecord orderRecord = orderRecordService.getOrderRecordByTokenAndShopId(orderNumber, shop.getShopId());
                    //3.推送消息
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderNumber", orderRecord.getOrderNumber());
                    jsonObject.put("orderState", orderRecord.getOrderState());
                    jsonObject.put("orderId", orderRecord.getId());
                    methodsUtils.jPushToMerAndAccount(orderRecord.getToken(),"订单被商家取消",jsonObject.toJSONString(),token,"订单取消成功",jsonObject.toJSONString(),2);
                    System.out.println("取消订单");
                }else {
                    return new APIResponseModel(Globals.API_FAIL,"商户不存在");
                }
            }catch (Exception e){
                return new APIResponseModel(Globals.API_FAIL,e.getMessage());
            }
        }else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
        return null;
    }

    //配送按钮
    @Override
    public APIResponseModel deliveryOrder(APIRequestParams params) {
        if(params != null && params.getOrderNumber() != null){
            try {
                //查询订单状态
                OrderRecord orderRecord = orderRecordService.getOrderRecordByOrderNumber(params.getOrderNumber());
                if(orderRecord != null){
                    //判断订单状态，是否属于需要配送状态
                    if(orderRecord.getOrderState() == 2){
                        //改变订单状态，推送消息
                        boolean result = orderRecordService.changeOrderStateByOrderNumber(params.getOrderNumber(),3);
                        if(result){
                            Shop shop = shopService.getShopDataById(orderRecord.getShopId());
                            AccountShop accountShop = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("orderNumber", orderRecord.getOrderNumber());
                            jsonObject.put("orderState", orderRecord.getOrderState());
                            jsonObject.put("orderId", orderRecord.getId());
                            methodsUtils.jPushToMerAndAccount(orderRecord.getToken(),"商家已开始配送",jsonObject.toJSONString(),accountShop == null ? "" : accountShop.getToken(),"订单配送中",jsonObject.toJSONString(),2);
                            return new APIResponseModel(Globals.API_SUCCESS);
                        }else {
                            return new APIResponseModel(Globals.API_FAIL);
                        }
                    }else {
                        return new APIResponseModel(Globals.API_FAIL,"当前订单状态操作异常");
                    }
                }else {
                    return new APIResponseModel(Globals.API_FAIL,"订单查询不存在");
                }
            }catch (Exception e){
                return new APIResponseModel(Globals.API_FAIL);
            }
        }else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }


    /* =========================接口结束============================= */
}
