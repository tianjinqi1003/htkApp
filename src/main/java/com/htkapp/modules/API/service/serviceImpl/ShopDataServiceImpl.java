package com.htkapp.modules.API.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.LogUtil;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.exception.costom.NullDataException;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.ServiceMethodUtils;
import com.htkapp.core.utils.ShopDistanceComparator;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.dto.CommentInfo;
import com.htkapp.modules.API.dto.RecommendedShopInfo;
import com.htkapp.modules.API.dto.SeatOrderDetail;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.entity.AccountFocus;
import com.htkapp.modules.API.service.AccountFocusService;
import com.htkapp.modules.API.service.AccountServiceI;
import com.htkapp.modules.API.service.ShopDataService;
import com.htkapp.modules.admin.shopCategory.entity.ShopCategory;
import com.htkapp.modules.admin.shopCategory.service.ShopCategoryService;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderProductService;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderService;
import com.htkapp.modules.merchant.common.dto.MerchantReplyInfo;
import com.htkapp.modules.merchant.common.dto.ReturnCommentInfo;
import com.htkapp.modules.merchant.common.entity.AdvertisingInformation;
import com.htkapp.modules.merchant.common.entity.ShopMessageComment;
import com.htkapp.modules.merchant.common.service.AdvertisingInformationService;
import com.htkapp.modules.merchant.common.service.ShopMessageCommentService;
import com.htkapp.modules.merchant.groupBuy.dto.PackageDetails;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageContent;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageContentService;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageService;
import com.htkapp.modules.merchant.integral.entity.SeatOrder;
import com.htkapp.modules.merchant.integral.service.SeatOrderService;
import com.htkapp.modules.merchant.pay.dto.AppShopGoodsInfo;
import com.htkapp.modules.merchant.pay.dto.ReturnGroupBuyOrderDetails;
import com.htkapp.modules.merchant.pay.dto.ReturnGroupBuyOrderInfo;
import com.htkapp.modules.merchant.pay.dto.ReturnOrderInfo;
import com.htkapp.modules.merchant.pay.entity.OrderBuyPackage;
import com.htkapp.modules.merchant.pay.entity.OrderCommon;
import com.htkapp.modules.merchant.pay.entity.OrderProduct;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.*;
import com.htkapp.modules.merchant.shop.dto.AppShowShopInfo;
import com.htkapp.modules.merchant.shop.entity.*;
import com.htkapp.modules.merchant.shop.service.*;
import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import com.htkapp.modules.merchant.takeout.service.TakeoutCategoryServiceI;
import com.htkapp.modules.merchant.takeout.service.TakeoutProductServiceI;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

/**
 * Created by yinqilei on 17-6-26.
 */
@Service
public class ShopDataServiceImpl implements ShopDataService {

    @Resource
    private ShopCategoryService shopCategoryService;
    @Resource
    private ShopServiceI shopService;
    @Resource
    private AccountServiceI accountService;
    @Resource
    private BuyPackageService buyPackageService;
    @Resource
    private OrderBuyPackageService orderBuyPackageService;
    @Resource
    private AdvertisingInformationService advertisingInformationService;
    @Resource
    private OrderProductService orderProductService;
    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private AccountFocusService accountFocusService;
    @Resource
    private ShopBulletinService shopBulletinService;
    @Resource
    private TakeoutCategoryServiceI takeoutCategoryService;
    @Resource
    private ShopMessageCommentService shopMessageCommentService;
    @Resource
    private TakeoutProductServiceI takeoutProductService;
    @Resource
    private ShopConsumptionActivitiesService consumptionActivitiesService;
    @Resource
    private SeatOrderService seatOrderService;
    @Resource
    private OrderCommonService orderCommonService;
    @Resource
    private BuffetFoodOrderService buffetFoodOrderService;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private MoreMethodsUtils methodsUtils;
    @Resource
    private TakeoutOrderService takeoutOrderService;
    @Resource
    private BuffetFoodOrderProductService buffetFoodOrderProductService;
    @Resource
    private BuyPackageContentService buyPackageContentService;
    @Resource
    private ShopAlbumService shopAlbumService;

    final static Class<? extends Object> ele = ShopDataServiceImpl.class;


    /* =================接口开始=================== */

    //获取app首页的分类信息
    @Override
    public APIResponseModel getHomePageCategory(int pageNumber, Integer mark) {
        if (mark != null) {
            try {
                int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
                int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
                if (pageNumber > 1) {
                    pageNo = pageNumber;
                }
                List<ShopCategory> resultList = shopCategoryService.getAllCategory(pageNo, pageLimit, mark);
                if (resultList != null && resultList.size() > 0) {
                    for (ShopCategory each : resultList) {
                        if (each.getCategoryUrl() != null) {
                            each.setCategoryUrl(OtherUtils.getRootDirectory() + each.getCategoryUrl());
                        }
                    }
                }
                return new APIResponseModel<List<ShopCategory>>(Globals.API_SUCCESS, "成功", resultList);
            } catch (NullDataException e1) {
                return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
            } catch (Exception e2) {
                return new APIResponseModel(Globals.API_FAIL, e2.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD, "mark标识为空");
        }
    }

    //根据条件搜索商家
    @Override
    public APIResponseModel getShopByCondition(String keyWord, int mark, String token, int pageNumber) {
        boolean isFocus = false;
        List<AccountFocus> accountFocusList = null;
        int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
        int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
        if (pageNumber > 1) {
            pageNo = pageNumber;
        }
        try {
            accountFocusList = accountFocusService.getCollectListByToken(token);
            if (accountFocusList != null) {
                isFocus = true;
            }
        } catch (Exception e1) {
            return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
        }
        try {

            //多表联合查询，返回商铺id, 再通过商铺id查找数据
            List<Shop> shopList = shopService.getShopByCondition(keyWord, mark, pageNo, pageLimit);
            //距离排序
            Collections.sort(shopList, new ShopDistanceComparator());
            for (Shop each : shopList) {
                if (each.getLogoUrl() != null) {
                    each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                }
            }
            if (!isFocus) {
                return new APIResponseModel<List<Shop>>(Globals.API_SUCCESS, "成功", shopList);
            } else {
                List<Shop> list = ServiceMethodUtils.getShopCollectionMessageByShopList(shopList, accountFocusList);
                return new APIResponseModel<List<Shop>>(Globals.API_SUCCESS, "成功", list);
            }
        } catch (NullDataException e1) {
            e1.printStackTrace();
            return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
        } catch (Exception e2) {
            e2.printStackTrace();
            return new APIResponseModel(Globals.API_FAIL, e2.getMessage());
        }
    }

    //获取推荐商家
    @Override
    public APIResponseModel getBestShop(int distanceType, Double userLo, Double userLa, String token, int pageNumber, Integer mark) {
        if (userLa != null && userLo != null && mark != null) {
            int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
            int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
            if (pageNumber > 1) {
                pageNo = pageNumber;
            }
            try {
                if (StringUtils.isEmpty(token)) {
                    //推荐所有店铺分　
                    List<Shop> shopList = shopService.notLoginRecommendedBusinesses(mark, pageNo, pageLimit);
                    if (shopList != null) {
                        for (Shop each : shopList) {
                            if (each.getLogoUrl() != null) {
                                each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                            }
                            /**
                             * @author 马鹏昊
                             * @desc 查询每个商家最新一个月的销售数量（收货成功的）
                             */
                            Date currentTime = new Date();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Calendar c = Calendar.getInstance();
//                            long monthStart = currentTime.getTime()-(1000*60*60*24*30L);
//                            String dateStart = formatter.format(monthStart);
                            String dateEnd = formatter.format(currentTime);

                            //过去一月
                            c.setTime(new Date());
                            c.add(Calendar.MONTH, -1);
                            Date m = c.getTime();
                            String dateStart = formatter.format(m);
//                            System.out.println("过去一个月："+dateStart);

                            int row = orderRecordService.getOrderQuantities(each.getShopId(),dateStart,dateEnd);
                            each.setMonthlySalesVolume(row);
                        }
                        return new APIResponseModel<List<Shop>>(Globals.API_SUCCESS, "成功", shopList);
                    } else {
                        return new APIResponseModel<>(Globals.API_SUCCESS, "成功", null);
                    }
                } else {
                    //根据用户token推荐未关注的店铺
                    List<Shop> resultList = shopService.getAllShopLatitudeAndLongitude(pageNo, pageLimit, mark, token);
                    if (resultList != null) {
                        for (Shop each : resultList) {
                            System.out.println("====");
                            if (each.getLogoUrl() != null) {
                                each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                            }
                            /**
                             * @author 马鹏昊
                             * @desc 查询每个商家最新一个月的销售数量（收货成功的）
                             */
                            Date currentTime = new Date();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Calendar c = Calendar.getInstance();
//                            long monthStart = currentTime.getTime()-(1000*60*60*24*30L);
//                            String dateStart = formatter.format(monthStart);
                            String dateEnd = formatter.format(currentTime);

                            //过去一月
                            c.setTime(new Date());
                            c.add(Calendar.MONTH, -1);
                            Date m = c.getTime();
                            String dateStart = formatter.format(m);
//                            System.out.println("过去一个月："+dateStart);

                            int row = orderRecordService.getOrderQuantities(each.getShopId(),dateStart,dateEnd);
                            each.setMonthlySalesVolume(row);
                        }
                        return new APIResponseModel<List<Shop>>(Globals.API_SUCCESS, "成功", resultList);
                    } else {
                        return new APIResponseModel<>(Globals.API_SUCCESS, "成功", null);
                    }
                }
            } catch (Exception e1) {
                return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //通过传入的店铺id查找出店铺信息
    @Override
    public APIResponseModel getShopShowInfoById(Integer shopId, String token) {
        if (shopId != null) {
            AppShowShopInfo result = new AppShowShopInfo();
            try {
                Shop shop = shopService.getShopShowInfoById(shopId);
                //外卖
                if (shop.getMark() == 0) {
                    try {
//                        ShopBulletin shopBulletin = shopBulletinService.getShopBulletinById(shopId);

                        /**
                         * @author 马鹏昊
                         * @desc 直接把shop表里的公告填进去
                         */
                        ShopBulletin shopBulletin = new ShopBulletin();
                        shopBulletin.setContent(shop.getIntro());
                        shopBulletin.setShopId(shop.getShopId());

                        if (shopBulletin != null) {
                            result.setShopBulletin(shopBulletin);
                        } else {
                            result.setShopBulletin(null);
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                    }
                    try {
                        List<ShopConsumptionActivities> shopConsumptionActivitiesList = consumptionActivitiesService.getShopConsumptionActivityListById(shopId);
                        if (shopConsumptionActivitiesList != null && shopConsumptionActivitiesList.size() > 0) {
                            result.setShopConsumptionActivities(shopConsumptionActivitiesList);
                        } else {
                            result.setShopConsumptionActivities(null);
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                    }
                    result.setShopName(shop.getShopName())
                            .setLogoUrl(OtherUtils.getRootDirectory() + shop.getLogoUrl())
                            .setState(shop.getState())
                            .setDeliveryFee(shop.getDeliveryFee())
                            .setCategoryName(shop.getCategoryName())
                            .setLatitude(shop.getLatitude())
                            .setLongitude(shop.getLongitude())
                            .setLocationAddress(shop.getLocation() + shop.getAddress())
                            .setMonthlySalesVolume(shop.getMonthlySalesVolume())
                            .setOpeningTime(shop.getOpeningTime())
                            .setScore(shop.getScore())
                    		.setMobilePhone(shop.getMobilePhone());

                    /**
                     * @author 马鹏昊
                     * @desc 查询每个商家最新一个月的销售数量（收货成功的）
                     */
                    Date currentTime = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar c = Calendar.getInstance();
//                            long monthStart = currentTime.getTime()-(1000*60*60*24*30L);
//                            String dateStart = formatter.format(monthStart);
                    String dateEnd = formatter.format(currentTime);

                    //过去一月
                    c.setTime(new Date());
                    c.add(Calendar.MONTH, -1);
                    Date m = c.getTime();
                    String dateStart = formatter.format(m);
//                            System.out.println("过去一个月："+dateStart);

                    int row = orderRecordService.getOrderQuantities(shop.getShopId(),dateStart,dateEnd);
                    result.setMonthlySalesVolume(row);

                    return new APIResponseModel<AppShowShopInfo>(Globals.API_SUCCESS, "成功", result);
                } else if (shop.getMark() == 1) {
                    //团购
                    List<AccountFocus> accountFocusList = null;
                    boolean focus = false;
                    try {
                        accountFocusList = accountFocusService.getCollectListByToken(token);
                        if (accountFocusList != null) {
                            System.out.println("查看是否有关注店铺");
                            focus = true;
                        }
                    } catch (Exception e1) {
                        return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
                    }
                    result.setShopName(shop.getShopName())
                            .setScore(shop.getScore())
                            .setPerCapitaPrice(shop.getPerCapitaPrice())
                            .setLocationAddress(shop.getLocation() + shop.getAddress())
                            .setPhone(shop.getPhone());
                    //查询团购店铺下的套餐
                    List<BuyPackage> buyPackageList = buyPackageService.getGroupBuyListById(shopId);
                    if (buyPackageList != null) {
                        for (BuyPackage each : buyPackageList) {
                            each.setImgUrl(OtherUtils.getRootDirectory() + each.getImgUrl());
                        }
                        result.setBuyPackageList(buyPackageList);
                        if (!focus) {
                            return new APIResponseModel<AppShowShopInfo>(Globals.API_SUCCESS, "成功", result);
                        } else {
                            for (AccountFocus every : accountFocusList) {
                                if (Objects.equals(every.getShopId(), shopId)) {
                                    result.setCollection(true);
                                }
                            }
                            return new APIResponseModel<AppShowShopInfo>(Globals.API_SUCCESS, "成功", result);
                        }
                    } else {
                        return new APIResponseModel(Globals.API_FAIL);
                    }

                } else {
                    return new APIResponseModel(Globals.API_FAIL);
                }
            } catch (NullDataException e1) {
                return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
            } catch (Exception e2) {
                return new APIResponseModel(Globals.API_FAIL, e2.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //通过传入的店铺id获得店铺商品
    @Override
    public APIResponseModel getGoodsListByShopId(Integer shopId) {
        if (shopId != null) {
            try {
                List<TakeoutCategory> resultList = takeoutCategoryService.getTakeoutCategoryById(shopId);
                List<AppShopGoodsInfo> appShopGoodsInfoList = new ArrayList<>();
                if (resultList != null && resultList.size() > 0) {
                    try {
                        for (TakeoutCategory each : resultList) {
//                            List<TakeoutProduct> productList = takeoutProductService.getTakeoutProductById(each.getId());

                            /**
                             * @author 马鹏昊
                             * @desc 通过删选是否下架来决定是否返回该商品信息
                             */
                            List<TakeoutProduct> productList = takeoutProductService.getTakeoutProductByCategoryIdAndIfCanBuy(each.getId());


                            try {
                                if (productList != null && productList.size() > 0) {
                                    for (TakeoutProduct every : productList) {
                                        //外卖产品加前缀
                                        every.setImgUrl(OtherUtils.getRootDirectory() + every.getImgUrl());
                                    }
                                    AppShopGoodsInfo goodsInfo = new AppShopGoodsInfo()
                                            .setId(each.getId())
                                            .setCategoryName(each.getCategoryName())
                                            .setTakeoutProductList(productList);
                                    appShopGoodsInfoList.add(goodsInfo);
                                } else {
                                    AppShopGoodsInfo goodsInfo = new AppShopGoodsInfo()
                                            .setId(each.getId())
                                            .setCategoryName(each.getCategoryName())
                                            .setTakeoutProductList(null);
                                    appShopGoodsInfoList.add(goodsInfo);
                                }
                            } catch (Exception e) {
                                return new APIResponseModel(Globals.API_FAIL, "存入数据失败");
                            }
                        }
                        return new APIResponseModel<List<AppShopGoodsInfo>>(Globals.API_SUCCESS, "成功", appShopGoodsInfoList);
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, "获取商品失败");
                    }
                } else {
                    return new APIResponseModel<>(Globals.API_SUCCESS, "获取店铺类别失败", null);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //根据店铺id获取店铺下的评论
    @Override
    public APIResponseModel getShopUserReviews(Integer shopId, Integer pageNumber) {
        if (shopId != null) {
            int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
            int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
            if (pageNumber > 1) {
                pageNo = pageNumber;
            }
            try {
                Shop shop = shopService.getShopDataById(shopId);
                //根据店铺id查询店铺下的所有评论总数
                int commentCount = shopMessageCommentService.getShopCommentCount(shopId);
                if (shop.getMark() == 0) {
                    try {
                        //根据店铺id查找店铺下的评论
                        List<ReturnCommentInfo> resultList = shopMessageCommentService.getTakeoutCommentById(shopId, pageNo, pageLimit);


                        if (resultList != null) {
                            for (ReturnCommentInfo each : resultList) {
                                try {
                                    Account account = accountService.getTakeoutCommentAccountMessageByToken(each.getAccountToken());
                                    if (account != null) {
                                        System.out.println("重复了");
                                        each.setAccountId(account.getAccountId());
                                        each.setAvaUrl(OtherUtils.getRootDirectory() + account.getAvatarUrl());
                                        each.setNickName(account.getNickName());
                                        each.setCommentTime(each.getCommentTime().substring(0, each.getCommentTime().length() - 10));
                                    }

                                    /**
                                     * @author 马鹏昊
                                     * @desc 加上商家回复的内容
                                     */
                                    List<MerchantReplyInfo> merchantReplyInfos = shopMessageCommentService.getMerchantReplyListByUserCommentId(each.getCommentId());
                                    if (merchantReplyInfos != null && merchantReplyInfos.size() > 0) {
                                        //因为商家只能回复一条，所以集合里只有一条数据
                                        MerchantReplyInfo merchantReplyInfo = merchantReplyInfos.get(0);
                                        String userCommentInfo = merchantReplyInfo.getReplyContent();
                                        each.setMerchantReply(userCommentInfo);
                                    }

                                } catch (Exception e) {
                                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                                }
                            }
                            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", new CommentInfo(resultList, commentCount));
                        } else {
                            return new APIResponseModel<>(Globals.API_SUCCESS, "店铺下没有评论", new CommentInfo(null, commentCount));
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                    }
                } else if (shop.getMark() == 1) {
                    //查找团购下的所有评论
                    try {
                        //根据店铺id查找店铺下的评论
                        List<ReturnCommentInfo> resultList = shopMessageCommentService.getTakeoutCommentById(shopId, pageNo, pageLimit);
                        if (resultList != null) {
                            for (ReturnCommentInfo each : resultList) {
                                //再根据订单id查找评论
                                Account account = accountService.getTakeoutCommentAccountMessageByToken(each.getAccountToken());
                                if (account != null) {
                                    each.setAccountId(account.getAccountId());
                                    each.setAvaUrl(OtherUtils.getRootDirectory() + account.getAvatarUrl());
                                    each.setNickName(account.getNickName());
                                    each.setCommentTime(each.getCommentTime().substring(0, each.getCommentTime().length() - 10));
                                }
                            }
                            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", new CommentInfo(resultList, commentCount));
                        } else {
                            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", new CommentInfo(null, commentCount));
                        }
                    } catch (Exception e) {
                        return new APIResponseModel<>(Globals.API_FAIL, "失败", new CommentInfo(null, 0));
                    }
                } else {
                    return new APIResponseModel(Globals.API_REQUEST_BAD);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_FAIL);
        }

    }

    //通过传入的店铺类别id，返回当前类别下的所有店铺列表
    @Override
    public APIResponseModel getShopListByCategoryId(String token, Integer categoryId, Integer pageNumber) {
        if (categoryId != null) {
            boolean focus = false;
            List<AccountFocus> accountFocusList = null;
            int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
            int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
            if (pageNumber > 1) {
                pageNo = pageNumber;
            }
            try {
                accountFocusList = accountFocusService.getCollectListByToken(token);
                if (accountFocusList != null) {
                    System.out.println("没有关注的商铺");
                    focus = true;
                }
            } catch (Exception e1) {
                return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
            }
            try {
                List<Shop> shopList = shopService.getShopListByCategoryId(categoryId, pageNo, pageLimit, 0);
                if (shopList != null && shopList.size() > 0) {
                    for (Shop each : shopList) {
                        each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                    }
                    if (!focus) {
                        return new APIResponseModel<List<Shop>>(Globals.API_SUCCESS, "成功", shopList);
                    } else {
                        List<Shop> resultList = ServiceMethodUtils.getShopCollectionMessageByShopList(shopList, accountFocusList);
                        return new APIResponseModel<List<Shop>>(Globals.API_SUCCESS, "成功", resultList);
                    }
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "当前类别下没店铺");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //通过传入的订单id查找订单详情
    @Override
    public APIResponseModel getOrderRecordDetailById(Integer orderId) {
        if (orderId != null) {
            try {
                ReturnOrderInfo orderInfo = new ReturnOrderInfo();
                OrderRecord orderRecord = orderRecordService.getOrderRecordById(orderId);
                try {
                    List<OrderProduct> resultList = orderProductService.getProductListByOrderId(orderId);
                    int statusCode = shopMessageCommentService.getCommentStatusByOrderId(orderId);
                    if (resultList != null) {
                        orderInfo.setOrderAmount(orderRecord.getOrderAmount())
                                .setOrderNumber(orderRecord.getOrderNumber())
                                .setOrderState(orderRecord.getOrderState())
                                .setReceiptName(orderRecord.getReceiptName())
                                .setReceivingCall(orderRecord.getReceivingCall())
                                .setOrderTime(orderRecord.getOrderTime())
                                .setPaymentMethod(orderRecord.getPaymentMethod())
                                .setSex(orderRecord.getSex())
                                .setProductList(resultList)
                                .setRemark(orderRecord.getRemark())
                                .setShippingAddress(orderRecord.getShippingAddress());
                        orderInfo.setCommentStatus(statusCode);
                        Date curDate = new Date();
                        if (orderRecord.getOrderState() == 1) {
                            int value = 1000 * 60 * 5;
                            Date date = DateUtil.parseDateTime(orderRecord.getLastUpdateTime());
                            System.out.println("订单毫秒数" + date.getTime());
                            System.out.println(format(date, NORM_DATETIME_PATTERN));
                            System.out.println("当前时间差毫秒数:" + (curDate.getTime() - value));
                            System.out.println(format(curDate, NORM_DATETIME_PATTERN));
                            System.out.println("详情计时");
                            if (date.getTime() >= (curDate.getTime() - value)) {
                                orderInfo.setTimeLeft(date.getTime() + value - curDate.getTime());
                            } else {
                                orderInfo.setTimeLeft(0);
                            }
                        } else {
                            orderInfo.setTimeLeft(0);
                        }
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                }
                try {
                    if (orderRecord.getShopId() != null) {
                        Shop shop = shopService.getShopDataById(orderRecord.getShopId());
                        orderInfo.setShopName(shop.getShopName())
                                .setLogoUrl(OtherUtils.getRootDirectory() + shop.getLogoUrl())
                                .setShopMobilePhone(shop.getMobilePhone());
                        orderInfo.setDeliveryFee(shop.getDeliveryFee());
                        return new APIResponseModel<ReturnOrderInfo>(Globals.API_SUCCESS, "成功", orderInfo);
                    } else {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                } catch (Exception e) {
                    return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                }
            } catch (NullDataException e1) {
                return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
            } catch (Exception e2) {
                return new APIResponseModel(Globals.API_FAIL, e2.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //根据用户token查询订单列表
    @Override
    public APIResponseModel getOrderRecordList(String token, int pageNumber) {
        List list = new ArrayList();
        if (token != null) {
            int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
            int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
            if (pageNumber == 1 || pageNumber > 1) {
                pageNo = (pageNumber - 1) * 8;
            }
            try {
                List<OrderCommon> orderCommonList = orderCommonService.getAllOrderList(token, pageNo, pageLimit);
                if (orderCommonList != null) {
                    for (OrderCommon each : orderCommonList) {
                        switch (each.getMark()) {
                            case 0:
                                OrderRecord orderRecordT = orderRecordService.getOrderRecordByOrderNumber(each.getOrderNumber());
                                if (orderRecordT != null) {
                                    ReturnOrderInfo orderInfo = new ReturnOrderInfo();
                                    List<OrderProduct> resultList = orderProductService.getProductListByOrderId(orderRecordT.getId());
                                    if (resultList != null) {
                                        orderInfo.setOrderAmount(orderRecordT.getOrderAmount())
                                                .setOrderId(orderRecordT.getId())
                                                .setOrderNumber(orderRecordT.getOrderNumber())
                                                .setOrderState(orderRecordT.getOrderState())
                                                .setReceiptName(orderRecordT.getReceiptName())
                                                .setReceivingCall(orderRecordT.getReceivingCall())
                                                .setOrderTime(orderRecordT.getOrderTime())
                                                .setPaymentMethod(orderRecordT.getPaymentMethod())
                                                .setSex(orderRecordT.getSex())
                                                .setProductList(resultList)
                                                .setShippingAddress(orderRecordT.getShippingAddress());

                                        Date curDate = new Date();
                                        if (orderRecordT.getOrderState() == 1) {
                                            int value = 1000 * 60 * 5;
                                            Date date = DateUtil.parseDate(orderRecordT.getOrderTime());
                                            if (date.getTime() >= (curDate.getTime() - value)) {
                                                orderInfo.setTimeLeft(date.getTime() + value - curDate.getTime());
                                            } else {
                                                orderInfo.setTimeLeft(0);
                                            }
                                        } else {
                                            orderInfo.setTimeLeft(0);
                                        }
                                        orderInfo.setOneProductName(resultList.get(0).getProductName());
                                        orderInfo.setMark(each.getMark());
                                        Shop shop = shopService.getShopDataById(orderRecordT.getShopId());
                                        orderInfo.setShopName(shop.getShopName())
                                                .setLogoUrl(OtherUtils.getRootDirectory() + shop.getLogoUrl())
                                                .setShopId(shop.getShopId())
                                                .setShopMobilePhone(shop.getMobilePhone());
                                        list.add(orderInfo);
                                    }

                                    break;
                                }
//                                else {
//                                    return new APIResponseModel<String>(Globals.API_SUCCESS, "没有外卖订单");
//                                }
                            case 1:
                                //订单id，店铺名,店铺logo,下单时间,订单状态,价格,套餐名,套餐id,1
                                OrderRecord orderRecordG = orderRecordService.getOrderRecordByOrderNumber(each.getOrderNumber());
                                if (orderRecordG != null) {
                                    ReturnGroupBuyOrderInfo info = new ReturnGroupBuyOrderInfo();
                                    OrderBuyPackage orderBuyPackage = orderBuyPackageService.getOrderPackageById(orderRecordG.getId());
                                    info.setOrderId(orderRecordG.getId());
                                    info.setOrderState(orderRecordG.getOrderState());
                                    info.setOrderTime(orderRecordG.getOrderTime());
                                    info.setOrderAmount(orderRecordG.getOrderAmount());
                                    info.setShopId(orderRecordG.getShopId()); //店铺id
                                    info.setOrderNumber(orderRecordG.getOrderNumber());
                                    info.setVoucherNumber(orderRecordG.getVoucherNumber());
                                    if (orderBuyPackage != null) {
                                        info.setPackageId(orderBuyPackage.getPackageId());
                                        info.setPackageName(orderBuyPackage.getPackageName());
                                    } else {
                                        orderBuyPackage = new OrderBuyPackage();
                                        info.setPackageId(orderBuyPackage.getPackageId());
                                        info.setPackageName(orderBuyPackage.getPackageName());
                                    }
                                    Shop shop = shopService.getShopDataById(orderRecordG.getShopId());
                                    info.setShopName(shop.getShopName());
                                    info.setMark(each.getMark());
                                    info.setLogoUrl(OtherUtils.getRootDirectory() + shop.getLogoUrl());
                                    list.add(info);
                                    break;
                                }
//                                else {
//                                    return new APIResponseModel<String>(Globals.API_SUCCESS, "没有团购订单");
//                                }
                            case 2:
                                SeatOrder seatOrder = seatOrderService.getSeatOrderByOrderNumber(each.getOrderNumber());
                                if (seatOrder != null) {
                                    seatOrder.setMark(each.getMark());
                                    seatOrder.setLogoUrl(OtherUtils.getRootDirectory() + seatOrder.getLogoUrl());
                                    list.add(seatOrder);
                                }
                                break;
                            case 3:
                                try {
                                    com.htkapp.modules.merchant.buffetFood.dto.ReturnOrderInfo orderInfo = buffetFoodOrderService.getOrderByOrderNumberAndToken(token, each.getOrderNumber());
                                    if (orderInfo != null) {
                                        orderInfo.setMark(each.getMark());
                                        orderInfo.setLogoUrl(OtherUtils.getRootDirectory() + orderInfo.getLogoUrl());

                                        List<BuffetFoodOrderProduct> productList = buffetFoodOrderProductService.getOrderProductListById(orderInfo.getId());
                                        orderInfo.setProductList(productList);

                                        list.add(orderInfo);
                                    }
                                    break;
                                } catch (Exception e) {
//                                    return new APIResponseModel(Globals.API_FAIL, "获取订单列表失败");
                                }
                            default:
                                break;
                        }
                    }
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", list);
                } else {
                    return new APIResponseModel<String>(Globals.API_SUCCESS, "没有外卖订单");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //随机获取五条团购广告列表
    @Override
    public APIResponseModel getRandomlyAdList() {
        try {
            List<AdvertisingInformation> resultList = advertisingInformationService.getRandomlyAdList();
            if (resultList != null && resultList.size() > 0) {
                for (AdvertisingInformation each : resultList) {
                    if (each.getImgUrl() != null) {
                        each.setImgUrl(OtherUtils.getRootDirectory() + each.getImgUrl());
                    }
                }
            }
            return new APIResponseModel<List<AdvertisingInformation>>(Globals.API_SUCCESS, "成功", resultList);
        } catch (NullDataException e1) {
            return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
        } catch (Exception e2) {
            return new APIResponseModel(Globals.API_FAIL, e2.getMessage());
        }
    }

    //根据团购店铺id获取店铺广告
    @Override
    public APIResponseModel getGroupBuyAdListById(Integer shopId) {
        if (shopId != null) {
            try {
                List<AdvertisingInformation> resultList = advertisingInformationService.getGroupBuyAdList(shopId);
                if (resultList != null && resultList.size() > 0) {
                    for (AdvertisingInformation each : resultList) {
                        if (each.getImgUrl() != null) {
                            each.setImgUrl(OtherUtils.getRootDirectory() + each.getImgUrl());
                        }
                    }
                }
                return new APIResponseModel<List<AdvertisingInformation>>(Globals.API_SUCCESS, "成功", resultList);
            } catch (NullDataException e1) {
                //当前店铺下没有添加
                return new APIResponseModel(Globals.API_SUCCESS, e1.getMessage());
            } catch (Exception e2) {
                return new APIResponseModel(Globals.API_FAIL, e2.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //查看评价详情
    @Override
    public APIResponseModel viewReviewDetailsById(Integer orderId) {
        if (orderId != null) {
            try {
                ShopMessageComment result = shopMessageCommentService.viewReviewDetailsById(orderId);
                if (result != null) {
                    result.setComment(true);
                    return new APIResponseModel<ShopMessageComment>(Globals.API_SUCCESS, "成功", result);
                } else {
                    result = new ShopMessageComment();
                    result.setComment(false);
                    return new APIResponseModel<ShopMessageComment>(Globals.API_SUCCESS, "成功", result);
                }
            } catch (Exception e2) {
                return new APIResponseModel(Globals.API_FAIL, e2.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //评价订单
    @Override
    public APIResponseModel evaluateOrderById(String token, Double commentsStars, String content, Integer orderId) {
        if (StringUtils.isNotEmpty(token) && commentsStars != null && orderId != null) {
            try {
                //根据订单id查找商铺id
                OrderRecord orderRecord = orderRecordService.getOrderRecordById(orderId);
                if (orderRecord.getShopId() != null) {
                    //查看是否已评论
                    boolean result = shopMessageCommentService.commentOrderById(token, commentsStars, content, orderId, orderRecord.getShopId());
                    if (result) {
                        //查找店铺下的总评论数值(计算完成后更改到店铺评分字段下)
                        return new APIResponseModel(Globals.API_SUCCESS);
                    } else {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "评论失败");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //根据传入的套餐id查询套餐详情
    @Override
    public APIResponseModel getPackageDetailsById(Integer packageId) {
        if (packageId != null) {
            PackageDetails info = new PackageDetails();
            try {
                BuyPackage buyPackage = buyPackageService.getPackageInformation(packageId);
                if (buyPackage != null) {
                    try {
                        Shop shop = shopService.getShopShowInfoById(buyPackage.getShopId());
                        //TODO
                        List<BuyPackageContent> buyPackageContentList = buyPackageContentService.getPackageItemListById(buyPackage.getId());
                        if (shop != null) {
                            buyPackage.setImgUrl(OtherUtils.getRootDirectory() + buyPackage.getImgUrl());
                            //根据店铺id查找店铺详情
                            info.setShopName(shop.getShopName());
                            info.setLatitude(shop.getLatitude());
                            info.setLongitude(shop.getLongitude());
                            info.setBuyPackage(buyPackage);
                            info.setPhone(shop.getPhone());
                            info.setBuyPackageContentList(buyPackageContentList);
                            info.setLocationAddress(shop.getLocation() + shop.getAddress());
                            try {
                                List<ShopMessageComment> commentList = shopMessageCommentService.getBuyPackageCommentList(packageId, 1);
                                if (commentList != null && commentList.size() > 0) {
                                    info.setCommentCount(commentList.size());
                                }
                            } catch (Exception e) {
                                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                            }
                            return new APIResponseModel<PackageDetails>(Globals.API_SUCCESS, "成功", info);
                        } else {
                            return new APIResponseModel(Globals.API_FAIL);
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                    }
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "查找失败");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //根据套餐id查询套餐下的所有评论
    @Override
    public APIResponseModel getCommentsUnderThePackage(Integer packageId) {
        if (packageId != null) {
            try {
                List<ShopMessageComment> commentList = shopMessageCommentService.getBuyPackageCommentList(packageId, 1);
                if (commentList != null) {
                    //根据查询到的用户token查询用户信息
                    List<ReturnCommentInfo> list = new ArrayList<>();
                    try {
                        for (ShopMessageComment each : commentList) {
                            Account account = accountService.getTakeoutCommentAccountMessageByToken(each.getAccountToken());
                            ReturnCommentInfo info = new ReturnCommentInfo();
                            info.setCommentId(each.getId());
                            info.setCommentsStars(each.getCommentsStars());
                            info.setCommentTime(each.getGmtCreate().substring(0, each.getGmtCreate().length() - 10));
                            info.setAccountId(account.getAccountId());
                            info.setAvaUrl(OtherUtils.getRootDirectory() + account.getAvatarUrl());
                            info.setNickName(account.getNickName());
                            info.setContent(each.getContent());
                            list.add(info);
                        }
                        return new APIResponseModel<List<ReturnCommentInfo>>(Globals.API_SUCCESS, "成功", list);
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL, e.getMessage());
                    }
                } else {
                    return new APIResponseModel(Globals.API_SUCCESS, "没有评论");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    /*
    *
    * { 订单状态   ，  订单id  ， 套餐id ， 支付方式 ， 下单时间
    * 商铺名称  ， 商铺id ， 商铺头像 ， 套餐名称，实付款 ，商铺电话  }
    * 订单状态： 取消15　　　未使用　　　使用
    * */
    //根据团购订单查询团购订单信息
    @Override
    public APIResponseModel getGroupBuyOrderDetailById(Integer orderId) {
        try {
            OrderRecord orderRecord = orderRecordService.getOrderRecordById(orderId);
            //通过订单id查找团购订单下的套餐详情
            ReturnGroupBuyOrderDetails groupBuyOrderDetails = new ReturnGroupBuyOrderDetails();
            try {
                OrderBuyPackage orderBuyPackage = orderBuyPackageService.getOrderPackageById(orderId);
                if (orderBuyPackage != null) {
                    groupBuyOrderDetails.setVoucherNumber(orderRecord.getVoucherNumber());
                    groupBuyOrderDetails.setOrderAmount(orderRecord.getOrderAmount());
                    groupBuyOrderDetails.setOrderId(orderId);
                    groupBuyOrderDetails.setOrderState(orderRecord.getOrderState());
                    groupBuyOrderDetails.setOrderTime(orderRecord.getOrderTime());
                    groupBuyOrderDetails.setPackageName(orderBuyPackage.getPackageName());
                    groupBuyOrderDetails.setPackageId(orderBuyPackage.getPackageId());
                    groupBuyOrderDetails.setPaymentMethod(orderRecord.getPaymentMethod());
                    groupBuyOrderDetails.setOrderNumber(orderRecord.getOrderNumber());
                    groupBuyOrderDetails.setQuantity(orderBuyPackage.getQuantity());
                    groupBuyOrderDetails.setUserPhone(orderRecord.getReceivingCall());
                    //门市价　商家地址
                    BuyPackage buyPackage = buyPackageService.getPackageInformation(orderBuyPackage.getPackageId());
                    Shop shop = shopService.getShopDataById(orderRecord.getShopId());
                    //TODO
                    List<BuyPackageContent> buyPackageContentList = buyPackageContentService.getPackageItemListById(buyPackage.getId());
                    groupBuyOrderDetails.setRetailPrice(buyPackage.getRetailPrice());
                    groupBuyOrderDetails.setShopAddress(shop.getLocation() + shop.getAddress());
                    groupBuyOrderDetails.setLatitude(shop.getLatitude());
                    groupBuyOrderDetails.setLongitude(shop.getLongitude());
                    groupBuyOrderDetails.setBuyPackageContentList(buyPackageContentList);
                    groupBuyOrderDetails.setValidityTime(buyPackage.getValidityTime());
                } else {
                    return new APIResponseModel(Globals.API_FAIL);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
            try {
                if (orderRecord.getShopId() != null) {
                    Shop shop = shopService.getShopDataById(orderRecord.getShopId());
                    groupBuyOrderDetails.setShopId(shop.getShopId());
                    groupBuyOrderDetails.setLogoUrl(OtherUtils.getRootDirectory() + shop.getLogoUrl());
                    groupBuyOrderDetails.setShopName(shop.getShopName());
                    groupBuyOrderDetails.setShopPhone(shop.getMobilePhone());
                } else {
                    return new APIResponseModel(Globals.API_FAIL);
                }
            } catch (NullDataException e1) {
                return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
            } catch (Exception e2) {
                return new APIResponseModel(Globals.API_FAIL);
            }
            return new APIResponseModel<ReturnGroupBuyOrderDetails>(Globals.API_SUCCESS, "成功", groupBuyOrderDetails);
        } catch (NullDataException e1) {
            return new APIResponseModel(Globals.API_FAIL, e1.getMessage());
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL);
        }
    }


    //套餐详情页面显示滚动图片
    @Override
    public APIResponseModel getPackagePicture(Integer packageId) {
        return null;
    }

    //根据分类id查询获取分类下的所有店铺,并筛选店铺
//    @Override
//    public APIResponseModel getShopListByCategoryIdAndChildIdAndConditions(Integer categoryId, Integer childCId,
//                                                                           Double userLo, Double userLa, String token,
//                                                                           int pageNumber, Integer mark, Integer distanceType) {
//        if (categoryId != null && childCId != null && mark != null) {
//            try {
//                int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
//                int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
//                if (pageNumber > 1) {
//                    pageNo = pageNumber;
//                }
//                Set<Integer> focusList = accountFocusService.getCollectionShopIdListByToken(token, mark);
//                if (focusList==null){
//                    //因为数据库查询的时候这个集合不能为null，size也不能为0，再加上shop_id是shop表的主键所以不会是-1，所以这里添加个-1
//                    focusList = new HashSet<>();
//                    focusList.add(-1);
//                }
//                //把判断去掉，是因为如果新用户还没有关注任何商家的时候看不到任何推荐的商家
////                if (focusList != null) {
//                    //有关注的店铺，再根据店铺分类查找
//                    //如果childCId = 0 则查询当前大类下的所有店铺
//                    if (childCId == 0) {
//                        //查询当前大分类下的所有店铺  查询所有二级分类店铺
//                        List<ShopCategory> resultList = shopCategoryService.getCategoryListById(categoryId);
//                        Set<String> setList = new HashSet<>();
//                        if (resultList != null && resultList.size() > 0) {
//                            for (ShopCategory each : resultList) {
//                                setList.add(each.getId().toString());
//                            }
//                            List<Shop> shops = shopService.getShopListByChildCategoryIdsAndFocus(setList, focusList, token, 1, pageNo, pageLimit);
//                            if (shops != null) {
//                                for (Shop each : shops) {
//                                    each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
//                                    each.setCollection(true);
//                                }
//                                RecommendedShopInfo info = new RecommendedShopInfo();
//                                if (distanceType != 0) {
//                                    ServiceMethodUtils.filterByDistanceType(distanceType, userLo, userLa, shops);
//                                }
//                                info.setShopList(shops);
//                                if (shops.size() <= 3) {
//                                    //推荐商铺
//                                    //根据用户token推荐未关注的店铺
//                                    List<Shop> shopList = shopService.getShopListByChildCategoryIdsAndFocus(setList, focusList, token, 0, pageNo, 10);
//                                    if (shopList != null) {
//                                        for (Shop each : shopList) {
//                                            if (each.getLogoUrl() != null) {
//                                                each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
//                                            }
//                                        }
//                                        info.setRecommendShopList(shopList);
//                                    } else {
//                                        info.setRecommendShopList(null);
//                                    }
//                                } else {
//                                    info.setRecommendShopList(null);
//                                }
//                                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", info);
//                            } else {
//                                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", null);
//                            }
//                        } else {
//                            return new APIResponseModel<>(Globals.API_SUCCESS, " 当前分类下没有店铺", null);
//                        }
//                    } else {
//                        List<Shop> shopList = shopService.getShopListByCategoryIdAndFocus(childCId, focusList, token, 1, pageNo, pageLimit);
//                        if (shopList != null && shopList.size() > 0) {
//                            for (Shop each : shopList) {
//                                each.setCollection(true);
//                                each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
//                            }
//                            RecommendedShopInfo info = new RecommendedShopInfo();
//                            info.setShopList(shopList);
//                            if (shopList.size() <= 3) {
//                                //推荐商铺
//                                //根据用户token推荐未关注的店铺（tag为0是查未关注的）
//                                List<Shop> shops = shopService.getShopListByCategoryIdAndFocus(childCId, focusList, token, 0, pageNo, pageLimit);
//                                if (shops != null) {
//                                    for (Shop each : shops) {
//                                        if (each.getLogoUrl() != null) {
//                                            each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
//                                        }
//                                        System.out.println("重复了");
//                                    }
//                                    info.setRecommendShopList(shops);
//                                } else {
//                                    info.setRecommendShopList(null);
//                                }
//                            } else {
//                                info.setRecommendShopList(null);
//                            }
//                            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", info);
//                        } else {
//                            return new APIResponseModel<>(Globals.API_SUCCESS, "当前类别下没店铺", null);
//                        }
//                    }
////                } else {
////
////
////                    //根据用户token推荐未关注的店铺（tag为0是查未关注的）
////                    Set<Integer> nullfocusList = new HashSet<>();
////                    nullfocusList.add(-1);
////                    List<Shop> shops = shopService.getShopListByCategoryIdAndFocus(childCId, nullfocusList, token, 0, pageNo, pageLimit);
////                    RecommendedShopInfo info = new RecommendedShopInfo();
////                    if (shops != null) {
////                        for (Shop each : shops) {
////                            if (each.getLogoUrl() != null) {
////                                each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
////                            }
////                            System.out.println("重复了");
////                        }
////                        info.setShopList(null);
////                        info.setRecommendShopList(shops);
////                    }
////
////                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", info);
////                }
//            } catch (Exception e) {
//                LogUtil.error(ele, "错误:", e);
//                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//            }
//        } else {
//            return new APIResponseModel(Globals.API_REQUEST_BAD);
//        }
//    }


    //根据分类id查询获取分类下的所有店铺,并筛选店铺
    @Override
    public APIResponseModel getShopListByCategoryIdAndChildIdAndConditions(Integer categoryId, Integer childCId,
                                                                           Double userLo, Double userLa, String token,
                                                                           int pageNumber, Integer mark, Integer distanceType) {
        if (categoryId != null && childCId != null && mark != null) {
            try {
                int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
                int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
                if (pageNumber > 1) {
                    pageNo = pageNumber;
                }
                //先拿到用户收藏的所有商家集合
                Set<Integer> focusList = accountFocusService.getCollectionShopIdListByToken(token, mark);
                if (focusList == null) {
                    //因为数据库查询的时候这个集合不能为null，size也不能为0，再加上shop_id是shop表的主键所以不会是-1，所以这里添加个-1
                    focusList = new HashSet<>();
                    focusList.add(-1);
                }
                //childCId = 0 代表App选择的是全部分类
                if (childCId == 0) {
                    //此处setList只保存了首页点击的大分类的id，因为是全部，所以先查到所有这个大分类下的子分类
                    Set<Integer> allChildCategoryIdList = shopService.getAllChildCategoryIdList(categoryId);
                    //大分类id也要放进去
                    allChildCategoryIdList.add(categoryId);

                    //查找属于这些分类下的所有商家
//                    List<Shop> allShop = shopService.getShopListByCategoryList(allChildCategoryIdList);

                    //转成String
                    Set<String> setList = new HashSet<>();
                    for (Integer s : allChildCategoryIdList) {
                        setList.add(s + "");
                    }

                    //tag=1代表查询用户关注了并且在此分类下的商家
                    List<Shop> shops = shopService.getShopListByChildCategoryIdsAndFocus(mark, setList, focusList, token, 1, pageNo, pageLimit);
                    if (shops != null) {
                        for (Shop each : shops) {
                            each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                            each.setCollection(true);

                            /**
                             * @author 马鹏昊
                             * @desc 查询每个商家最新一个月的销售数量（收货成功的）
                             */
                            Date currentTime = new Date();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Calendar c = Calendar.getInstance();
//                            long monthStart = currentTime.getTime()-(1000*60*60*24*30L);
//                            String dateStart = formatter.format(monthStart);
                            String dateEnd = formatter.format(currentTime);

                            //过去一月
                            c.setTime(new Date());
                            c.add(Calendar.MONTH, -1);
                            Date m = c.getTime();
                            String dateStart = formatter.format(m);
//                            System.out.println("过去一个月："+dateStart);

                            int row = orderRecordService.getOrderQuantities(each.getShopId(),dateStart,dateEnd);
                            each.setMonthlySalesVolume(row);
                        }
                        //要返回的此分类下的所有商家集合（包括商家已关注的和被推荐的）
                        RecommendedShopInfo info = new RecommendedShopInfo();
                        //根据距离过滤商家
                        if (distanceType != 0) {
                            ServiceMethodUtils.filterByDistanceType(distanceType, userLo, userLa, shops);
                        }
                        //把查询出的用户已关注的商家集合填进去
                        info.setShopList(shops);
                        //如果已关注的商家数量小于3，那么还需要返回推荐商家
                        if (shops.size() <= 3) {
                            //tag=0代表查询用户未关注的且在此分类下的商家
                            List<Shop> shopList = shopService.getShopListByChildCategoryIdsAndFocus(mark, setList, focusList, token, 0, pageNo, 10);
                            if (shopList != null) {
                                for (Shop each : shopList) {
                                    if (each.getLogoUrl() != null) {
                                        each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                                    }
                                    /**
                                     * @author 马鹏昊
                                     * @desc 查询每个商家最新一个月的销售数量（收货成功的）
                                     */
                                    Date currentTime = new Date();
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Calendar c = Calendar.getInstance();
//                            long monthStart = currentTime.getTime()-(1000*60*60*24*30L);
//                            String dateStart = formatter.format(monthStart);
                                    String dateEnd = formatter.format(currentTime);

                                    //过去一月
                                    c.setTime(new Date());
                                    c.add(Calendar.MONTH, -1);
                                    Date m = c.getTime();
                                    String dateStart = formatter.format(m);
//                            System.out.println("过去一个月："+dateStart);

                                    int row = orderRecordService.getOrderQuantities(each.getShopId(),dateStart,dateEnd);
                                    each.setMonthlySalesVolume(row);

                                }
                                //根据距离过滤商家
                                if (distanceType != 0) {
                                    ServiceMethodUtils.filterByDistanceType(distanceType, userLo, userLa, shopList);
                                }
                                //填充推荐商家
                                info.setRecommendShopList(shopList);
                            } else {
                                info.setRecommendShopList(null);
                            }
                        } else {
                            info.setRecommendShopList(null);
                        }
                        return new APIResponseModel<>(Globals.API_SUCCESS, "成功", info);
                    } else {//如果没有查询出已关注商家，那么更需要返回推荐商家
                        //要返回的此分类下的所有商家集合（这里只有被推荐的）
                        RecommendedShopInfo info = new RecommendedShopInfo();
                        //没有已关注商家
                        info.setShopList(null);
                        //tag=0代表查询用户未关注的且在此分类下的商家
                        List<Shop> shopList = shopService.getShopListByChildCategoryIdsAndFocus(mark, setList, focusList, token, 0, pageNo, 10);
                        if (shopList != null) {
                            for (Shop each : shopList) {
                                if (each.getLogoUrl() != null) {
                                    each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                                }

                                /**
                                 * @author 马鹏昊
                                 * @desc 查询每个商家最新一个月的销售数量（收货成功的）
                                 */
                                Date currentTime = new Date();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Calendar c = Calendar.getInstance();
//                            long monthStart = currentTime.getTime()-(1000*60*60*24*30L);
//                            String dateStart = formatter.format(monthStart);
                                String dateEnd = formatter.format(currentTime);

                                //过去一月
                                c.setTime(new Date());
                                c.add(Calendar.MONTH, -1);
                                Date m = c.getTime();
                                String dateStart = formatter.format(m);
//                            System.out.println("过去一个月："+dateStart);

                                int row = orderRecordService.getOrderQuantities(each.getShopId(),dateStart,dateEnd);
                                each.setMonthlySalesVolume(row);
                            }
                            //根据距离过滤商家
                            if (distanceType != 0) {
                                ServiceMethodUtils.filterByDistanceType(distanceType, userLo, userLa, shopList);
                            }
                            //填充推荐商家
                            info.setRecommendShopList(shopList);
                        } else {
                            info.setRecommendShopList(null);
                        }
                        return new APIResponseModel<>(Globals.API_SUCCESS, "成功", info);
                    }
                } else {//如果childCId不为0，则代表App是选择了某个子分类
                    //tag=1代表查询用户关注的且在这个子分类下的商家
                    List<Shop> shopList = shopService.getShopListByCategoryIdAndFocus(mark, childCId, focusList, token, 1, pageNo, pageLimit);
                    if (shopList != null && shopList.size() > 0) {
                        for (Shop each : shopList) {
                            each.setCollection(true);
                            each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                            /**
                             * @author 马鹏昊
                             * @desc 查询每个商家最新一个月的销售数量（收货成功的）
                             */
                            Date currentTime = new Date();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Calendar c = Calendar.getInstance();
//                            long monthStart = currentTime.getTime()-(1000*60*60*24*30L);
//                            String dateStart = formatter.format(monthStart);
                            String dateEnd = formatter.format(currentTime);

                            //过去一月
                            c.setTime(new Date());
                            c.add(Calendar.MONTH, -1);
                            Date m = c.getTime();
                            String dateStart = formatter.format(m);
//                            System.out.println("过去一个月："+dateStart);

                            int row = orderRecordService.getOrderQuantities(each.getShopId(),dateStart,dateEnd);
                            each.setMonthlySalesVolume(row);
                        }
                        RecommendedShopInfo info = new RecommendedShopInfo();
                        //根据距离过滤商家
                        if (distanceType != 0) {
                            ServiceMethodUtils.filterByDistanceType(distanceType, userLo, userLa, shopList);
                        }
                        info.setShopList(shopList);
                        if (shopList.size() <= 3) {
                            //查询推荐商家
                            List<Shop> shops = shopService.getShopListByCategoryIdAndFocus(mark, childCId, focusList, token, 0, pageNo, pageLimit);
                            if (shops != null) {
                                for (Shop each : shops) {
                                    if (each.getLogoUrl() != null) {
                                        each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                                    }
                                    /**
                                     * @author 马鹏昊
                                     * @desc 查询每个商家最新一个月的销售数量（收货成功的）
                                     */
                                    Date currentTime = new Date();
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Calendar c = Calendar.getInstance();
//                            long monthStart = currentTime.getTime()-(1000*60*60*24*30L);
//                            String dateStart = formatter.format(monthStart);
                                    String dateEnd = formatter.format(currentTime);

                                    //过去一月
                                    c.setTime(new Date());
                                    c.add(Calendar.MONTH, -1);
                                    Date m = c.getTime();
                                    String dateStart = formatter.format(m);
//                            System.out.println("过去一个月："+dateStart);

                                    int row = orderRecordService.getOrderQuantities(each.getShopId(),dateStart,dateEnd);
                                    each.setMonthlySalesVolume(row);
                                    System.out.println("重复了");
                                }
                                //根据距离过滤商家
                                if (distanceType != 0) {
                                    ServiceMethodUtils.filterByDistanceType(distanceType, userLo, userLa, shops);
                                }
                                info.setRecommendShopList(shops);
                            } else {
                                info.setRecommendShopList(null);
                            }
                        } else {
                            info.setRecommendShopList(null);
                        }
                        return new APIResponseModel<>(Globals.API_SUCCESS, "成功", info);
                    } else {
                        RecommendedShopInfo info = new RecommendedShopInfo();
                        info.setShopList(null);
                        //查询推荐商家
                        List<Shop> shops = shopService.getShopListByCategoryIdAndFocus(mark, childCId, focusList, token, 0, pageNo, pageLimit);
                        if (shops != null) {
                            for (Shop each : shops) {
                                if (each.getLogoUrl() != null) {
                                    each.setLogoUrl(OtherUtils.getRootDirectory() + each.getLogoUrl());
                                }
                                System.out.println("重复了");
                                /**
                                 * @author 马鹏昊
                                 * @desc 查询每个商家最新一个月的销售数量（收货成功的）
                                 */
                                Date currentTime = new Date();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Calendar c = Calendar.getInstance();
//                            long monthStart = currentTime.getTime()-(1000*60*60*24*30L);
//                            String dateStart = formatter.format(monthStart);
                                String dateEnd = formatter.format(currentTime);

                                //过去一月
                                c.setTime(new Date());
                                c.add(Calendar.MONTH, -1);
                                Date m = c.getTime();
                                String dateStart = formatter.format(m);
//                            System.out.println("过去一个月："+dateStart);

                                int row = orderRecordService.getOrderQuantities(each.getShopId(),dateStart,dateEnd);
                                each.setMonthlySalesVolume(row);
                            }
                            //根据距离过滤商家
                            if (distanceType != 0) {
                                ServiceMethodUtils.filterByDistanceType(distanceType, userLo, userLa, shops);
                            }
                            info.setRecommendShopList(shops);
                        } else {
                            info.setRecommendShopList(null);
                        }
                        return new APIResponseModel<>(Globals.API_SUCCESS, "成功", info);
                    }
                }
            } catch (Exception e) {
                LogUtil.error(ele, "错误:", e);
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }


    //根据标记和大分类id获取小分类列表(加全部分类)
    @Override
    public APIResponseModel getChildCategoryListByCIdAndMark(Integer mark, Integer categoryId) {
        if (mark != null && categoryId != null) {
            //根据标记和分类id查询子分类列表
            try {
                List<ShopCategory> resultList = shopCategoryService.getCategoryListByIdAndMark(categoryId, mark);
                if (resultList != null) {
                    ShopCategory category = new ShopCategory();
                    category.setId(0);
                    category.setCategoryName("全部");
                    category.setMark(mark);
                    resultList.add(0, category);
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", resultList);
                } else {
                    return new APIResponseModel<>(Globals.API_SUCCESS, "没有分类", null);
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //搜索关键词接口(返回店铺名) 随机返回一个店铺关键词
    @Override
    public APIResponseModel getSearchKeywords(Integer mark) {
        //先根据mark获取所有店铺数量
        if (mark != null) {
            try {
                //调用产生随机数工具类产生随机数(店铺数量用于指定随机数产生范围最大值)
                int count = shopService.getAllShopCount(mark);
                if (count > 0) {
                    //传入最起始数和最终数，返回一个随机数
                    int randomNumber = RandomUtil.randomInt(0, count);
                    System.out.println("随机数:" + randomNumber);
                    //根据返回的index查询店铺
                    Shop shop = shopService.getShopByIndex(randomNumber, mark);
                    if (shop != null) {
                        return new APIResponseModel<>(Globals.API_SUCCESS, "成功", shop.getShopName());
                    }
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", "");
                } else {
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", "");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //外卖催单接口
    @Override
    public APIResponseModel callReminderInterface(String token, String orderNumber) {
        //改变订单状态，向商家后台页面发起推送,把信息加入到通知中心
        if (StringUtils.isNotEmpty(orderNumber)) {
            try {
                OrderRecord orderRecord = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
                Shop shop = shopService.getShopDataById(orderRecord.getShopId());
                AccountShop accountShop = accountShopService.getAccountShopDataById(shop.getAccountShopId());
                takeoutOrderService.insertReminderStateByOrderId(orderRecord.getId());

                /**
                 * @author 马鹏昊
                 * @desc 插入完立马把催单状态修改成催单中
                 */
                takeoutOrderService.updateReminderStateByOrderId(orderRecord.getId(),1);


                methodsUtils.pushMesToManagePage(new PushMesEntity(
                        "外卖订单消息", "t", "你有一个催单消息", accountShop.getToken(),
                        't', 6, "您有一个催单消息", accountShop.getId()
                ));
                return new APIResponseModel(Globals.API_SUCCESS, "成功");
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, "失败");
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //删除订单
    @Override
    public APIResponseModel deleteOrderByOrderNumber(String orderNumber, String token, Integer mark) {
        if (StringUtils.isNotEmpty(orderNumber) && mark != null) {
            //根据订单号获取订单id
            //根据订单id删除订单购买的商品
            try {
                if (mark > 1) {
                    BuffetFoodOrder order = buffetFoodOrderService.getOrderByOrderNumber(orderNumber, token);
                    buffetFoodOrderService.delOrderByOrderNumber(orderNumber);
                    buffetFoodOrderProductService.deleteOrderProductByOrderId(order.getId());
                } else {
                    OrderRecord orderRecord = orderRecordService.getOrderRecordByOrderNumber(orderNumber);
                    if (mark == 0) {
                        orderProductService.deleteOrderProductByOrderId(orderRecord.getId());
                    } else if (mark == 1) {
                        orderBuyPackageService.deleteOrderBuyPackageByOrderId(orderRecord.getId());
                    }
                    orderRecordService.deleteOrderByOrderNumber(orderNumber, token);
                }
                return new APIResponseModel(Globals.API_SUCCESS);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //店铺头像接口
    @Override
    public APIResponseModel getShopImgUrl(APIRequestParams params) {
        if (params != null && params.getShopId() != null) {
            try {
                Shop shop = shopService.getShopDataById(params.getShopId());
                if (shop != null) {
                    JSONObject object = new JSONObject();
                    object.put("shopImgUrl", OtherUtils.getRootDirectory() + shop.getLogoUrl());
                    return new APIResponseModel<>(Globals.API_SUCCESS, "成功", object);
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "店铺不存在");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //店铺相册接口
    @Override
    public APIResponseModel getShopAlbumList(APIRequestParams params) {
        if (params != null && params.getShopId() != null) {
            //根据店铺id查找相册列表
            try {
                List<ShopAlbum> resultList = shopAlbumService.getShopAlbumListByShopId(params.getShopId());
                if (resultList != null) {
                    for (ShopAlbum each : resultList) {
                        each.setImgUrl(OtherUtils.getRootDirectory() + each.getImgUrl());
                    }
                }
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", resultList);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //订座订单详情
    @Override
    public APIResponseModel getSeatOrderDetail(APIRequestParams params) {
        if (params != null && params.getOrderNumber() != null) {
            try {
                SeatOrderDetail seatOrder = seatOrderService.getSeatOrderDetail(params.getOrderNumber(), params.getToken());
                Shop shop = shopService.getShopDataById(seatOrder.getShopId());
                seatOrder.setPhone(shop.getMobilePhone());
                seatOrder.setAddress(shop.getAddress());
                return new APIResponseModel<>(Globals.API_SUCCESS, "成功", seatOrder);
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL);
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    /* =================接口结束=================== */
}
