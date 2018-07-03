package com.htkapp.modules.API.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.MD5Utils;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.params.ServiceParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.OrderNumGen;
import com.htkapp.modules.API.entity.Account;
import com.htkapp.modules.API.service.APICommonService;
import com.htkapp.modules.API.service.AccountServiceI;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodProduct;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderProductService;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderService;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodProductService;
import com.htkapp.modules.merchant.buffetFood.service.SeatInformationService;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackage;
import com.htkapp.modules.merchant.groupBuy.entity.BuyPackageContent;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageContentService;
import com.htkapp.modules.merchant.groupBuy.service.BuyPackageService;
import com.htkapp.modules.merchant.pay.dto.EnterPayReturn;
import com.htkapp.modules.merchant.pay.entity.*;
import com.htkapp.modules.merchant.pay.service.*;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.htkapp.modules.merchant.takeout.service.TakeoutProductServiceI;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.xiaoleilu.hutool.date.DatePattern.NORM_DATETIME_PATTERN;
import static org.apache.commons.lang.time.DateFormatUtils.format;

//共用service 方法
@Service
public class APICommonServiceImpl implements APICommonService {

	@Resource
	private  TakeoutOrderService takeoutOrderService;

	@Resource
	private SeatInformationService seatInformationService;
	@Resource
	private OrderRecordService orderRecordService;
	@Resource
	private OrderProductService orderProductService;
	@Resource
	private TakeoutProductServiceI takeoutProductService;
	@Resource
	private ShopServiceI shopService;
	@Resource
	private AccountShopServiceI accountShopService;
	@Resource
	private BillRecordStatisticsService billRecordStatisticsService;
	@Resource
	private BillRecordService billRecordService;
	@Resource
	private BuyPackageService buyPackageService;
	@Resource
	private AccountServiceI accountService;
	@Resource
	private OrderBuyPackageService orderBuyPackageService;
	@Resource
	private MoreMethodsUtils methodsUtils;
	@Resource
	private BuffetFoodOrderService buffetFoodOrderService;
	@Resource
	private BuffetFoodOrderProductService buffetFoodOrderProductService;
	@Resource
	private BuffetFoodProductService buffetFoodProductService;
	@Resource
	private OrderBuyPackageContentService orderBuyPackageContentService;
	@Resource
	private BuyPackageContentService buyPackageContentService;

	//外卖支付流程
	@Override
	@Transactional
	public void takeoutPaymentInterface(OrderRecord orderRecord) throws Exception {
		try {
			//这是插入order_record表记录
			orderRecordService.paymentSuccessfullyCreatedOrder(orderRecord);

			//这是插入takeout_order表记录(和order_record表关联)
			takeoutOrderService.insertReminderStateByOrderId(orderRecord.getId());

			for (OrderProduct each : orderRecord.getProductLists()) {
				each.setOrderId(orderRecord.getId());
				//写入外卖产品购买记录
				orderProductService.insertProductInfoByOrder(each);
				//购买成功,相应减库存数量
				takeoutProductService.productReduceNumber(each.getProductId(), each.getQuantity());
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new Exception(e.getMessage());
		}
	}

	//团购支付流程
	@Override
	@Transactional
	public void groupBuyPaymentInterface(OrderRecord orderRecord, String token) throws Exception {
		//根据套餐id查找出所属店铺id,套餐信息
		BuyPackage buyPackage = buyPackageService.getPackageInformation(orderRecord.getPackageId());
		Account account = accountService.selectByToken(token);
		//生成团购消费券
		long voucherNumber = OrderNumGen.getVoucherNumber();
		if (buyPackage != null) {
			orderRecord.setOrderAmount(buyPackage.getPrice() * orderRecord.getQuantity());  //价格
			orderRecord.setReceiptName(account.getNickName());  //购买人名字
			orderRecord.setReceivingCall(account.getPhone()); //手机号
			orderRecord.setSex(account.getSex());   //性别
			orderRecord.setShopId(buyPackage.getShopId());
			orderRecord.setVoucherNumber(voucherNumber);
			try {
				orderRecordService.paymentSuccessfullyCreatedOrder(orderRecord);

				//这是插入takeout_order表记录(和order_record表关联)
				takeoutOrderService.insertReminderStateByOrderId(orderRecord.getId());

				OrderBuyPackage orderBuyPackage = new OrderBuyPackage();
				orderBuyPackage.setPackageId(buyPackage.getId()); //套餐id
				orderBuyPackage.setLogoUrl(buyPackage.getImgUrl());  //套餐图片
				orderBuyPackage.setPackageName(buyPackage.getPackageName());//套餐名字
				orderBuyPackage.setQuantity(orderRecord.getQuantity());//套餐数量
				orderBuyPackage.setPrice(buyPackage.getPrice());//价格
				orderBuyPackage.setOrderId(orderRecord.getId());//订单id
				//插入团购订单内产品列表
				orderBuyPackageService.insertBuyPackageDataByOrderId(orderBuyPackage);
				//根据套餐id查出套餐下的商品
				List<BuyPackageContent> resultBuyPackageContentList = buyPackageContentService.getPackageItemListById(buyPackage.getId());
				if(resultBuyPackageContentList != null){
					for (BuyPackageContent each : resultBuyPackageContentList){
						OrderBuyPackageContent orderBuyPackageContent = new OrderBuyPackageContent();
						orderBuyPackageContent.setOriginalCost(each.getOriginalCost());
						orderBuyPackageContent.setPackageId(each.getPackageId());
						orderBuyPackageContent.setPrice(each.getPrice());
						orderBuyPackageContent.setProductName(each.getProductName());
						orderBuyPackageContent.setProductId(each.getProductId());
						orderBuyPackageContent.setOrderId(orderRecord.getId());
						orderBuyPackageContentService.insertOrderBuyPackageContentByGroupBuyOrder(orderBuyPackageContent);
					}
				}
				//插入团购订单下套餐商品内的套餐详情产品对象
			} catch (Exception e2) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new Exception(e2.getMessage());
			}
		} else {
			throw new Exception("查询团购套餐信息错误");
		}
	}

	//取消订单的修改账单记录
	@Override
	@Transactional
	public void updateBillData(ServiceParams serviceParams) throws Exception {
		if (serviceParams != null) {
			try {
				//减入账金额
				billRecordStatisticsService.updateBillRecordStatisticsByDate(serviceParams.getAccountShopToken(), serviceParams.getStartTime(), serviceParams.getEndTime(), serviceParams.getOrderAmount());
				//减入帐记录
				//                billRecordService.deleteRecordByOrderNumberAndDate(serviceParams.getAccountShopToken(), serviceParams.getOrderNumber());
				/**
				 * @author 马鹏昊
				 * @desc 改为修改status为未入账(1)，但不删除账单记录，因为需求要求账单记录页详情里能看到取消订单的信息
				 */
				billRecordService.updateBillStatus(serviceParams.getAccountShopToken(), serviceParams.getOrderNumber(),"1");
			} catch (Exception e) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				throw new Exception("修改账单失败");
			}
		} else {
			throw new Exception("修改账单失败");
		}
	}

	//支付成功修改订单状态,记录账单
	@Override
	public void updateOrderByPayment(ServiceParams serviceParams) {
	}

	//支付成功订单的插入账单记录
	@Override
	public void insertBillData(ServiceParams serviceParams) {
		try {
			int stateId = serviceParams.getMark() == 0 ? Globals.DEFAULT_T_OrderState : Globals.DEFAULT_G_OrderState;
			orderRecordService.changeOrderStateByOrderNumber(serviceParams.getOrderNumber(), stateId);
			OrderRecord order = orderRecordService.getOrderRecordByOrderNumber(serviceParams.getOrderNumber());
			Shop shop = shopService.getShopDataById(order.getShopId());
			AccountShop accountShop = accountShopService.getAccountShopDataById(shop.getAccountShopId());
			BillRecord billRecord = new BillRecord();
			billRecord.setOrderIncome(order.getOrderAmount());
			billRecord.setAmount(order.getOrderAmount());
			billRecord.setOrderNumber(serviceParams.getOrderNumber());
			billRecord.setAccountShopToken(accountShop.getToken());
			billRecordService.insertBillRecordByToken(billRecord);
			BillRecordStatistics statistics = new BillRecordStatistics();
			statistics.setAccountShopToken(accountShop.getToken());
			statistics.setAmount(order.getOrderAmount());
			statistics.setOrderIncome(order.getOrderAmount());
			billRecordStatisticsService.keepRecordByAccountShopToken(statistics);  //账单记录（一天的统计）
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("orderNumber", order.getOrderNumber());
			jsonObject.put("orderState", order.getOrderState());
			jsonObject.put("orderId", order.getId());
			jsonObject.put("flag", order.getMark());
			String mesTitle = order.getMark() == 0 ? "外卖" : "团购";
			char category = order.getMark() == 0 ? 't' : 'g';
			String mesCategory = order.getMark() == 1 ? "订单支付成功" : "订单支付成功，等待商家接单";
			methodsUtils.jPushToMerAndAccount(order.getToken(), mesTitle + mesCategory, jsonObject.toJSONString(), accountShop.getToken(), order.getOrderNumber() + mesCategory, jsonObject.toJSONString(), 2);
			methodsUtils.pushMesToManagePage(new PushMesEntity(mesTitle + "订单消息", String.valueOf(category), "有新" + mesTitle + "订单了", accountShop.getToken(), category, order.getOrderState(), "您有一个的" + mesTitle + "订单消息", accountShop.getId()));
		} catch (Exception x) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	//app自助点餐下单
	@Override
	public APIResponseModel insertBuffetFoodInitOrder(BuffetFoodOrder order) throws Exception {
		if (order != null && order.getShopId() != null) {
			try {
				String orderTime = format(new Date(), NORM_DATETIME_PATTERN);
				final String orderNumber = String.valueOf(OrderNumGen.next());
				order.setOrderNumber(orderNumber);
				order.setOrderTime(orderTime);
				order.setOrderBody("自助点餐初始下单");
				int pageNumber = Globals.DEFAULT_PAGE_NO;
				int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
				String startTime = format(DateUtil.beginOfDay(new Date()), NORM_DATETIME_PATTERN);
				String endTime = format(DateUtil.endOfDay(new Date()), NORM_DATETIME_PATTERN);
				List<BuffetFoodOrder> resultList=buffetFoodOrderService.getOrderListByToken(order.getToken(),order.getShopId(),pageNumber,pageLimit);
				if(resultList==null) {
					//查找当天有没有生成过订单
					BuffetFoodOrder order1 = buffetFoodOrderService.verifyTodayOrder(order.getShopId(),startTime,endTime);
					if(order1 != null){
						order.setSerialNumber((order1.getSerialNumber() + 1));
						System.out.println("========当前订单序号："+order1.getSerialNumber());
						order.setAllSerialNumber((order1.getAllSerialNumber() + 1));
						System.out.println("========当前订单总序号："+order1.getAllSerialNumber());
						order.setOrderState(0);
					}else {
						//取最后一条订单信息
						BuffetFoodOrder order2 = buffetFoodOrderService.getLastOrder(order.getShopId());
						if(order2 != null){
							order.setAllSerialNumber((order2.getAllSerialNumber()+1));
							System.out.println("最后一条========当前订单序号："+order2.getSerialNumber());
							order.setSerialNumber((order2.getSerialNumber() +1));
							System.out.println("最后一条========当前订单总序号："+order2.getAllSerialNumber());
						}
						order.setOrderState(0);
					}
					System.out.println("插入订单前");
					Map<String, String> resultMap = buffetFoodOrderService.insertOrder(order);
					int b=1;
					int a=seatInformationService.updataSeatInfoByOrder(order,b);
					if(a<=0||order.getSeatName()==null) {
						return new APIResponseModel(Globals.API_FAIL, "当前座位已被占用");
					}
					boolean result = Boolean.parseBoolean(resultMap.get("result"));
					if (result) {
						Integer orderId = Integer.parseInt(resultMap.get("orderId"));
						for (BuffetFoodOrderProduct each : order.getProductLists()) {
							BuffetFoodProduct product = buffetFoodProductService.getBuffetFoodProductDetailById(each.getProductId());
							if(product != null){
								each.setImgUrl(product.getImgUrl());
							}
							each.setOrderId(orderId);
							buffetFoodOrderProductService.insertProductDetailsUnderOrder(each);
						}
						if(order.getToken() != null){
							//用户已登陆  返回订单号
							 Shop shop = shopService.getShopDataById(order.getShopId());
					            System.out.println("shop is:"+shop.toString());
					            AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
							methodsUtils.pushMesToManagePage(new PushMesEntity("自助点餐订单消息", "b", "自助点餐订单下单成功", user.getToken(), 'b',1, "您有一个的自助点餐订单消息", user.getId()));
							return new APIResponseModel<>(Globals.API_SUCCESS, "成功", orderNumber);
						}else {
							//在这里创建一个token传递给app端

							//用户未登陆
							return new APIResponseModel<>(Globals.API_SUCCESS, "成功", orderNumber);
						}
					} else {
						return new APIResponseModel(Globals.API_FAIL,"错误1");
					}
				}else {
						if(resultList.get(resultList.size()-1).getOrderState()==2||
								resultList.get(resultList.size()-2).getOrderState()==2) {
							//查找当天有没有生成过订单
							BuffetFoodOrder order1 = buffetFoodOrderService.verifyTodayOrder(order.getShopId(),startTime,endTime);
							if(order1 != null){
								order.setSerialNumber((order1.getSerialNumber() + 1));
								System.out.println("========当前订单序号："+order1.getSerialNumber());
								order.setAllSerialNumber((order1.getAllSerialNumber() + 1));
								System.out.println("========当前订单总序号："+order1.getAllSerialNumber());
								order.setOrderState(0);
							}else {
								//取最后一条订单信息
								BuffetFoodOrder order2 = buffetFoodOrderService.getLastOrder(order.getShopId());
								if(order2 != null){
									order.setAllSerialNumber((order2.getAllSerialNumber()+1));
									System.out.println("最后一条========当前订单序号："+order2.getSerialNumber());
									order.setSerialNumber((order2.getSerialNumber() +1));
									System.out.println("最后一条========当前订单总序号："+order2.getAllSerialNumber());
									order.setOrderState(0);
								}
							}
							System.out.println("插入订单前");
							Map<String, String> resultMap = buffetFoodOrderService.insertOrder(order);
							int b=1;
							int a=seatInformationService.updataSeatInfoByOrder(order,b);
							if(a<=0||order.getSeatName()==null) {
								return new APIResponseModel(Globals.API_FAIL, "当前座位已被占用");
							}
							boolean result = Boolean.parseBoolean(resultMap.get("result"));
							if (result) {
								Integer orderId = Integer.parseInt(resultMap.get("orderId"));
								for (BuffetFoodOrderProduct each : order.getProductLists()) {
									BuffetFoodProduct product = buffetFoodProductService.getBuffetFoodProductDetailById(each.getProductId());
									if(product != null){
										each.setImgUrl(product.getImgUrl());
									}
									each.setOrderId(orderId);
									buffetFoodOrderProductService.insertProductDetailsUnderOrder(each);
								}
								if(order.getToken() != null){
									//用户已登陆  返回订单号
									 Shop shop = shopService.getShopDataById(order.getShopId());
							            System.out.println("shop is:"+shop.toString());
							            AccountShop user = accountShopService.getAccountShopDataById(shop.getAccountShopId());
									methodsUtils.pushMesToManagePage(new PushMesEntity("自助点餐订单消息", "b", "自助点餐订单下单成功", user.getToken(), 'b', 1, "您有一个的自助点餐订单消息", user.getId()));
									return new APIResponseModel<>(Globals.API_SUCCESS, "成功", orderNumber);
								}else {
									//在这里创建一个token传递给app端

									//用户未登陆
									return new APIResponseModel<>(Globals.API_SUCCESS, "成功", orderNumber);
								}
							} else {
								return new APIResponseModel(Globals.API_FAIL,"错误2");
							}
						}else {
							return new APIResponseModel(Globals.API_FAIL,"您有一个已存在订单未完成");
						}
				}
			} catch (Exception e) {
				return new APIResponseModel(Globals.API_FAIL, e.getMessage());
			}
		} else {
			return new APIResponseModel(Globals.API_REQUEST_BAD,"错误3");
		}
	}
	
		//app自助点餐下单
//		@Override
//		public APIResponseModel insertBuffetFoodInitOrder(BuffetFoodOrder order) throws Exception {
//			if (order != null && order.getShopId() != null) {
//				try {
//					String orderTime = format(new Date(), NORM_DATETIME_PATTERN);
//					final String orderNumber = String.valueOf(OrderNumGen.next());
//					order.setOrderNumber(orderNumber);
//					order.setOrderTime(orderTime);
//					order.setOrderBody("自助点餐初始下单");
//						try {
//							String startTime = format(DateUtil.beginOfDay(new Date()), NORM_DATETIME_PATTERN);
//							String endTime = format(DateUtil.endOfDay(new Date()), NORM_DATETIME_PATTERN);
//							//查找当天有没有生成过订单
//							BuffetFoodOrder order1 = buffetFoodOrderService.verifyTodayOrder(order.getShopId(),startTime,endTime);
//							if(order1 != null){
//								order.setSerialNumber((order1.getSerialNumber() + 1));
//								System.out.println("========当前订单序号："+order1.getSerialNumber());
//								order.setAllSerialNumber((order1.getAllSerialNumber() + 1));
//								System.out.println("========当前订单总序号："+order1.getAllSerialNumber());
//							}else {
//								//取最后一条订单信息
//								BuffetFoodOrder order2 = buffetFoodOrderService.getLastOrder(order.getShopId());
//								if(order2 != null){
//									order.setAllSerialNumber((order2.getAllSerialNumber()+1));
//									System.out.println("最后一条========当前订单序号："+order2.getSerialNumber());
//									order.setSerialNumber((order2.getSerialNumber() +1));
//									System.out.println("最后一条========当前订单总序号："+order2.getAllSerialNumber());
//								}
//							}
//							System.out.println("插入订单前");
//							Map<String, String> resultMap = buffetFoodOrderService.insertOrder(order);
//							boolean result = Boolean.parseBoolean(resultMap.get("result"));
//							if (result) {
//								Integer orderId = Integer.parseInt(resultMap.get("orderId"));
//								for (BuffetFoodOrderProduct each : order.getProductLists()) {
//									BuffetFoodProduct product = buffetFoodProductService.getBuffetFoodProductDetailById(each.getProductId());
//									if(product != null){
//										each.setImgUrl(product.getImgUrl());
//									}
//									each.setOrderId(orderId);
//									buffetFoodOrderProductService.insertProductDetailsUnderOrder(each);
//								}
//								if(order.getToken() != null){
//									//用户已登陆  返回订单号
//									return new APIResponseModel<>(Globals.API_SUCCESS, "成功", orderNumber);
//								}else {
//									//在这里创建一个token传递给app端
//									//用户未登陆
//									return new APIResponseModel<>(Globals.API_SUCCESS, "成功", orderNumber);
//								}
//							} else {
//								return new APIResponseModel(Globals.API_FAIL);
//							}
//						} catch (Exception e) {
//							return new APIResponseModel(Globals.API_FAIL, "下单失败");
//						}
//				} catch (Exception e) {
//					return new APIResponseModel(Globals.API_FAIL, e.getMessage());
//				}
//			} else {
//				return new APIResponseModel(Globals.API_REQUEST_BAD);
//			}
//		}
		/*
		 * 确认下单，调整后的流程
		 */
		//app自助点餐下单
		//	@Override
		//	public APIResponseModel insertBuffetFoodInitOrder(BuffetFoodOrder order) throws Exception {
		//		if (order != null && order.getShopId() != null) {
		//			try {
		//				String orderTime = format(new Date(), NORM_DATETIME_PATTERN);
		//				final String orderNumber = String.valueOf(OrderNumGen.next());
		//				order.setOrderNumber(orderNumber);
		//				order.setOrderTime(orderTime);
		//				order.setOrderBody("自助点餐初始下单");
		//				int pageNumber = Globals.DEFAULT_PAGE_NO;
		//				int pageLimit = Globals.DEFAULT_PAGE_LIMIT;
		//				String startTime = format(DateUtil.beginOfDay(new Date()), NORM_DATETIME_PATTERN);
		//				String endTime = format(DateUtil.endOfDay(new Date()), NORM_DATETIME_PATTERN);
		//				List<BuffetFoodOrder> resultList=buffetFoodOrderService.getOrderListByToken(order.getToken(),order.getShopId(),pageNumber,pageLimit);
		//				if(resultList==null) {
		//					//查找当天有没有生成过订单
		//					BuffetFoodOrder order1 = buffetFoodOrderService.verifyTodayOrder(order.getShopId(),startTime,endTime);
		//					if(order1 != null){
		//						order.setSerialNumber((order1.getSerialNumber() + 1));
		//						System.out.println("========当前订单序号："+order1.getSerialNumber());
		//						order.setAllSerialNumber((order1.getAllSerialNumber() + 1));
		//						System.out.println("========当前订单总序号："+order1.getAllSerialNumber());
		//					}else {
		//						//取最后一条订单信息
		//						BuffetFoodOrder order2 = buffetFoodOrderService.getLastOrder(order.getShopId());
		//						if(order2 != null){
		//							order.setAllSerialNumber((order2.getAllSerialNumber()+1));
		//							System.out.println("最后一条========当前订单序号："+order2.getSerialNumber());
		//							order.setSerialNumber((order2.getSerialNumber() +1));
		//							System.out.println("最后一条========当前订单总序号："+order2.getAllSerialNumber());
		//						}
		//					}
		//					System.out.println("插入订单前");
		//					Map<String, String> resultMap = buffetFoodOrderService.insertOrder(order);
		//					boolean result = Boolean.parseBoolean(resultMap.get("result"));
		//					if (result) {
		//						Integer orderId = Integer.parseInt(resultMap.get("orderId"));
		//						for (BuffetFoodOrderProduct each : order.getProductLists()) {
		//							BuffetFoodProduct product = buffetFoodProductService.getBuffetFoodProductDetailById(each.getProductId());
		//							if(product != null){
		//								each.setImgUrl(product.getImgUrl());
		//							}
		//							each.setOrderId(orderId);
		//							buffetFoodOrderProductService.insertProductDetailsUnderOrder(each);
		//						}
		//						if(order.getToken() != null){
		//							//用户已登陆  返回订单号
		//							return new APIResponseModel<>(Globals.API_SUCCESS, "成功", order);
		//						}else {
		//							//在这里创建一个token传递给app端
		//
		//							//用户未登陆
		//							return new APIResponseModel<>(Globals.API_SUCCESS, "成功", order);
		//						}
		//					} else {
		//						return new APIResponseModel(Globals.API_FAIL);
		//					}
		//				}else {
		//					for(BuffetFoodOrder resultOrder:resultList) {
		//						if(resultOrder.getOrderState()==2) {
		//							//查找当天有没有生成过订单
		//							BuffetFoodOrder order1 = buffetFoodOrderService.verifyTodayOrder(order.getShopId(),startTime,endTime);
		//							if(order1 != null){
		//								order.setSerialNumber((order1.getSerialNumber() + 1));
		//								System.out.println("========当前订单序号："+order1.getSerialNumber());
		//								order.setAllSerialNumber((order1.getAllSerialNumber() + 1));
		//								System.out.println("========当前订单总序号："+order1.getAllSerialNumber());
		//							}else {
		//								//取最后一条订单信息
		//								BuffetFoodOrder order2 = buffetFoodOrderService.getLastOrder(order.getShopId());
		//								if(order2 != null){
		//									order.setAllSerialNumber((order2.getAllSerialNumber()+1));
		//									System.out.println("最后一条========当前订单序号："+order2.getSerialNumber());
		//									order.setSerialNumber((order2.getSerialNumber() +1));
		//									System.out.println("最后一条========当前订单总序号："+order2.getAllSerialNumber());
		//								}
		//							}
		//							System.out.println("插入订单前");
		//							Map<String, String> resultMap = buffetFoodOrderService.insertOrder(order);
		//							boolean result = Boolean.parseBoolean(resultMap.get("result"));
		//							if (result) {
		//								Integer orderId = Integer.parseInt(resultMap.get("orderId"));
		//								for (BuffetFoodOrderProduct each : order.getProductLists()) {
		//									BuffetFoodProduct product = buffetFoodProductService.getBuffetFoodProductDetailById(each.getProductId());
		//									if(product != null){
		//										each.setImgUrl(product.getImgUrl());
		//									}
		//									each.setOrderId(orderId);
		//									buffetFoodOrderProductService.insertProductDetailsUnderOrder(each);
		//								}
		//								if(order.getToken() != null){
		//									//用户已登陆  返回订单号
		//									return new APIResponseModel<>(Globals.API_SUCCESS, "成功", order);
		//								}else {
		//									//在这里创建一个token传递给app端
		//
		//									//用户未登陆
		//									return new APIResponseModel<>(Globals.API_SUCCESS, "成功", order);
		//								}
		//							} else {
		//								return new APIResponseModel(Globals.API_FAIL);
		//							}
		//						}else {
		//							return new APIResponseModel(Globals.API_FAIL,"您有一个已存在订单未完成");
		//						}
		//					}
		//				}
		//			} catch (Exception e) {
		//				return new APIResponseModel(Globals.API_FAIL, e.getMessage());
		//			}
		//		} else {
		//			return new APIResponseModel(Globals.API_REQUEST_BAD);
		//		}
		//		return new APIResponseModel(Globals.API_FAIL, "下单失败");
		//	}
		
		
}
