package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htkapp.core.curdException.DeleteException;
import com.htkapp.core.curdException.UpdateException;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.dto.ReturnBuffetFoodOrderData;
import com.htkapp.modules.merchant.buffetFood.dao.BuffetFoodOrderMapper;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnOrderDetailInfo;
import com.htkapp.modules.merchant.buffetFood.dto.ReturnOrderInfo;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.service.BuffetFoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuffetFoodOrderServiceImpl implements BuffetFoodOrderService {

    @Resource
    private BuffetFoodOrderMapper buffetFoodOrderDao;

    /* ============================接口开始================================= */
    //新增自助点餐订单
    @Override
    public Map<String, String> insertOrder(BuffetFoodOrder order) throws Exception {
        Map<String, String> map = new HashMap<>();
        try {
            //查询当天有没有生成订单，没有则初始为1，有则取当前值加1存入

            int row = buffetFoodOrderDao.insertOrderDAO(order);
            if (row <= 0) {
                map.put("result", "false");
                return map;
            } else {
                map.put("result", "true");
                map.put("orderId", String.valueOf(order.getId()));
                return map;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //改变订单状态(操作订单)
    @Override
    public void changeOrderState(String orderNumber, int stateId) throws Exception {
        try {
            int row = buffetFoodOrderDao.changeOrderStateDAO(orderNumber, stateId);
            if (row <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单号查找订单
    @Override
    public BuffetFoodOrder getOrderByOrderNumber(String orderNumber, String token) throws Exception {
        try {
            return buffetFoodOrderDao.getOrderByOrderNumberDAO(orderNumber, token);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据用户token获取订单列表
    @Override
    public List<BuffetFoodOrder> getOrderListByToken(String token, int shopId, int pageNo, int pageLimit) throws Exception {
        String orderDesc = "buffet_food_order.gmt_create desc";
        try {
            List<BuffetFoodOrder> resultList = buffetFoodOrderDao.getOrderListByTokenDAO(token, shopId, orderDesc);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单id获取订单详情列表
    @Override
    public ReturnOrderDetailInfo getOrderDetailListByTokenAndId(String token, Integer orderId) throws Exception {
        try {
            return buffetFoodOrderDao.getOrderDetailListByTokenAndIdDAO(token, orderId);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //通过订单id改变订单支付状态
    @Override
    public void changeOrderPayState(int orderId, int stateId, String token, int paymentMethodId) throws Exception {
        int row = buffetFoodOrderDao.changeOrderPayStateDAO(orderId, stateId, token, paymentMethodId);
        if (row <= 0) {
            throw new Exception("改变订单状态失败");
        }
    }

    //根据用户token获取订单列表
    @Override
    public List<ReturnOrderInfo> getOrderListByToken(String token, int pageNo, int pageLimit) {
        String orderDesc = "buffet_food_order.id desc desc";
        PageHelper.startPage(pageNo, pageLimit);
        List<ReturnOrderInfo> resultList = buffetFoodOrderDao.getOrderListByTokenAndPageDAO(token, orderDesc);
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return null;
    }

    //根据订单ID删除订单
    @Override
    public void delOrderById(int orderId) throws Exception {
        int row = buffetFoodOrderDao.delOrderByIdDAO(orderId);
        if (row <= 0) {
            throw new Exception("删除订单失败");
        }
    }

    //通过订单号获取订单信息
    @Override
    public ReturnOrderInfo getOrderByOrderNumberAndToken(String token, String orderNumber) {
        return buffetFoodOrderDao.getOrderByOrderNumberAndTokenDAO(token, orderNumber);
    }

    //根据订单ID删除订单
    @Override
    public void delOrderByOrderNumber(String orderNumber) {
        int row = buffetFoodOrderDao.delOrderByOrderNumberDAO(orderNumber);
        if(row <= 0){
            throw new DeleteException(Globals.DEFAULT_EXCEPTION_DELETE_FAILED);
        }
    }

    //根据订单号查找订单
    @Override
    public ReturnBuffetFoodOrderData getOrderByOrderNumber(String orderNumber) {
        return buffetFoodOrderDao.getOrderByOrderNumberADAO(orderNumber);
    }

    //根据订单号查找订单
    @Override
    public BuffetFoodOrder getBuffetFoodOrderByOrderNumber(String orderNumber) {
        return buffetFoodOrderDao.getBuffetFoodOrderByOrderNumberDAO(orderNumber);
    }

    //自助点餐订单催单
    @Override
    public void reminder(String orderNumber, int stateId) {
        int row = buffetFoodOrderDao.reminderDAO(orderNumber, stateId);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //确认下单按钮 新增订单信息
    @Override
    public void confirmOrderButton(BuffetFoodOrder buffetFoodOrder) {
       int row = buffetFoodOrderDao.confirmOrderButtonDAO(buffetFoodOrder);
       if(row <= 0){
           throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
       }
    }

    //确认调单接口
    @Override
    public void enterAdjustOrder(BuffetFoodOrder buffetFoodOrder) {
        int row = buffetFoodOrderDao.enterAdjustOrderDAO(buffetFoodOrder);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    //调单接口
    @Override
    public void updateOrderAdjustOrderJson(BuffetFoodOrder order) {
        int row = buffetFoodOrderDao.updateOrderAdjustOrderJsonDAO(order);
        if(row <= 0){
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }
    
    //根据用户token和店铺id查询订单列表
    @Override
    public List<BuffetFoodOrder> getOrderListByTokenAndShopId(String token,int shopId) {
    	 List<BuffetFoodOrder> resultList = buffetFoodOrderDao.getOrderListByTokenAndShopId(token, shopId);
        if(resultList !=null){
        	return resultList;
        }
		return resultList;
    }
    /* ============================接口结束================================= */


    /* ===========================JSP页面接口开始=================================== */
    //查询自助点餐订单列表
    @Override
    public List<BuffetFoodOrder> getBuffetFoodOrderListByToken(String token, int pageNo, int pageLimit) throws Exception {
        try {
            PageHelper.startPage(pageNo, pageLimit);
            List<BuffetFoodOrder> resultList = buffetFoodOrderDao.getBuffetFoodOrderListByTokenDAO(token);
            if (resultList != null && resultList.size() > 0) {
                return resultList;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据订单号和商户token查询订单详情
    @Override
    public BuffetFoodOrder getBuffetFoodOrder(String accountShopToken, String orderNumber) throws Exception {
        try {
            return buffetFoodOrderDao.getBuffetFoodOrderDAO(accountShopToken, orderNumber);
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //根据商户token查找数据
    @Override
    public List<BuffetFoodOrder> getBuffetFoodOrderListByToken(String accountShopToken, String startTime, String endTime, int payState, int pageNumber, int pageLimit) {
        try {
            String orderDesc = "buffet_food_order.gmt_create desc";
            List<BuffetFoodOrder> resultList = buffetFoodOrderDao.getBuffetFoodOrderListByTokenAndConditionDAO(accountShopToken, payState, startTime, endTime, orderDesc);
            if(resultList != null && resultList.size() > 0){
                return resultList;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    //更改支付状态
    @Override
    public void changeOrderStateByAccountShopToken(String orderNumber, int stateId) throws Exception {
        try {
            int row = buffetFoodOrderDao.changeOrderStateByAccountShopTokenDAO(orderNumber, stateId);
            if (row <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    //订单结算追加订单中的商品，改变订单总额
    @Override
    public void updateOrderTotalAmount(String orderNumber, double orderAmount) throws Exception {
        try {
            int row = buffetFoodOrderDao.updateOrderTotalAmountDAO(orderNumber, orderAmount);
            if (row <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //根据商铺id查找自助点餐列表
    @Override
    public List<BuffetFoodOrder> getBuffetFoodOrderListByShopIdAndOrderStateId(String orderDesc, int shopId, int orderStateId, int pageNum, int pageLimit) {
        PageHelper.startPage(pageNum, pageLimit);
        List<BuffetFoodOrder> resultList = buffetFoodOrderDao.getBuffetFoodOrderListByShopIdAndOrderStateIdDAO(orderDesc, shopId, orderStateId);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //验证当天有没有生成订单
    @Override
    public BuffetFoodOrder verifyTodayOrder(Integer shopId, String startTime, String endTime) {
        return buffetFoodOrderDao.verifyTodayOrderDAO(shopId, startTime, endTime);
    }

    //获取最后一条订单信息
    @Override
    public BuffetFoodOrder getLastOrder(int shopId) {
        return buffetFoodOrderDao.getLastOrderDAO(shopId);
    }

    //查询调整订单列表
    @Override
    public List<BuffetFoodOrder> getAdjustOrderList(int shopId, String orderDesc, int pageNumber, int pageLimit) {
        PageHelper.startPage(pageNumber, pageLimit);
        List<BuffetFoodOrder> resultList = buffetFoodOrderDao.getAdjustOrderListDAO(shopId, orderDesc);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    //查询催单订单列表
    @Override
    public List<BuffetFoodOrder> getReminderOrderList(int shopId, String orderDesc, int pageNumber, int pageLimit) {
        PageHelper.startPage(pageNumber, pageLimit);
        List<BuffetFoodOrder> resultList = buffetFoodOrderDao.getReminderOrderListDAO(shopId, orderDesc);
        if(resultList != null && resultList.size() > 0){
            return resultList;
        }
        return null;
    }

    @Override
    public void dealWithNewOrder(String orderNumber, int orderStateId, int payState) {
        int row = buffetFoodOrderDao.dealWithNewOrderDAO(orderNumber, orderStateId, payState);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

    @Override
    public void replyReminder(String orderNumber, int reminderId) {
        int row = buffetFoodOrderDao.reminderDAO(orderNumber, reminderId);
        if (row <= 0) {
            throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
        }
    }

	@Override
	public void updataOrderAdjustState(BuffetFoodOrder order) {
		int row=buffetFoodOrderDao.updataOrderAdjustState(order);
		if(row<=0) {
			 throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
		}
	}

	@Override
	public BuffetFoodOrder getBFOLByToken(String orderNumber,int shopId) {
		BuffetFoodOrder bfo=buffetFoodOrderDao.getBFOLByToken(orderNumber, shopId);
		if(bfo!=null) {
			return bfo;
		}
		return null;
	}

	@Override
	public BuffetFoodOrder getBuffetFoodOrderByOrderStateAndSeatName(String seatName) {
		int orderstate=1;
		BuffetFoodOrder bfo=buffetFoodOrderDao.getBuffetFoodOrderByOrderStateAndSeatName(seatName, orderstate);
		if(bfo!=null) {
			return bfo;
		}
		return bfo;
	}

	@Override
	public void updateOrderSeatName(BuffetFoodOrder order) {
		int a=buffetFoodOrderDao.updateOrderSeatName(order);
		if(a<=0) {
			throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
		}
	}

	@Override
	public void updateTempSeatName(BuffetFoodOrder order) {
		int a=buffetFoodOrderDao.updateTempSeatName(order);
		if(a<=0) {
			throw new UpdateException(Globals.DEFAULT_EXCEPTION_UPDATE_FAILED);
		}
	}


    /* ============================JSP页面接口结束===================================== */
}
