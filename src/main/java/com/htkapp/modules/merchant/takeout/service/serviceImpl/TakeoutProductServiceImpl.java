package com.htkapp.modules.merchant.takeout.service.serviceImpl;

import com.htkapp.core.exception.order.OrderException;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.takeout.dao.TakeoutProductMapper;
import com.htkapp.modules.merchant.takeout.entity.TakeoutProduct;
import com.htkapp.modules.merchant.takeout.service.TakeoutProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TakeoutProductServiceImpl implements TakeoutProductServiceI {
	@Resource
	private TakeoutProductMapper takeoutProductDao;

	@Override
	public TakeoutProduct getTakeoutProductByProductId(int productId) throws Exception {
		TakeoutProduct takeoutProduct = takeoutProductDao.getTakeoutProductByProductId(productId);
		return takeoutProduct;
	}

	/* ================接口开始======================== */
	//通过查出来的店铺类别id查找出店铺下的商品
	@Override
	public List<TakeoutProduct> getTakeoutProductById(int categoryId) throws Exception {
		try {
		 	List<TakeoutProduct> resultList = takeoutProductDao.getTakeoutProductByIdDAO(categoryId);
		 	if(resultList != null && resultList.size() > 0){
		 		return resultList;
			}else {
		 		return null;
			}
		}catch (Exception e){
			throw new Exception(Globals.CALL_DATABASE_ERROR);
		}
	}

	//减库存操作(外卖产品id,产品数量)
	@Override
	public void productReduceNumber(int productId, int quantity) throws OrderException {
		try {
			int row = takeoutProductDao.productReduceNumberDAO(productId, quantity);
//			if(row <= 0){
//				throw new OrderException("减库存失败");
//			}
		}catch (Exception e){
			throw new OrderException(Globals.CALL_DATABASE_ERROR);
		}
	}

	/* ================接口结束======================== */


	/* ===================JSP页面接口开始========================= */
	//查询店铺下的外卖产品
	@Override
	public List<TakeoutProduct> getTakeoutProductListByAccountShopId(int accountShopId) throws Exception {
		try {
			return takeoutProductDao.getTakeoutProductListByAccountShopIdDAO(accountShopId);
		}catch (Exception e){
			throw new Exception(Globals.CALL_DATABASE_ERROR);
		}
	}

	//通过类别id查找产品
	@Override
	public List<TakeoutProduct> getTakeoutProductListByCategoryId(int categoryId, int accountShopId) throws Exception {
		try {
			List<TakeoutProduct> result = takeoutProductDao.getTakeoutProductListByCategoryIdDAO(categoryId, accountShopId);
			if(result != null && result.size() > 0){
				return result;
			}else {
				return null;
			}
		}catch (Exception e){
			throw new Exception(Globals.CALL_DATABASE_ERROR);
		}
	}

	//商品数量置满
	@Override
	public void filledUpProductInventory(int accountShopId, int productId) throws Exception {
		try {
			int row = takeoutProductDao.filledUpProductInventoryDAO(accountShopId, productId);
			if(row <= 0){
				throw new Exception();
			}
		}catch (Exception e){
			throw new Exception(Globals.CALL_DATABASE_ERROR);
		}
	}

	//商品数量沽清
	@Override
	public void emptyProductInventory(int accountShopId, int productId) throws Exception {
		try {
			int row = takeoutProductDao.emptyProductInventoryDAO(accountShopId, productId);
			if(row <= 0){
				throw new Exception();
			}
		}catch (Exception e){
			throw new Exception(Globals.CALL_DATABASE_ERROR);
		}
	}

	//通过产品id查找商品
	@Override
	public TakeoutProduct getTakeoutProductById(int accountShopId, int productId) {
		return takeoutProductDao.getTakeoutProductByPIdDAO(accountShopId, productId);
	}

	//添加外卖商品
	@Override
	public void addTakeoutProduct(TakeoutProduct product)throws Exception {
		int row = takeoutProductDao.addTakeoutProductDAO(product);
		if(row <= 0){
			throw new Exception("添加失败");
		}
	}

	//通过商品id删除商品
	@Override
	public void delProductById(int productId) throws Exception {
		int row = takeoutProductDao.delProductByIdDAO(productId);
		if(row <= 0){
			throw new Exception("删除商品失败");
		}
	}

	//修改商品
	@Override
	public void editProductById(TakeoutProduct product)  throws Exception {
		int row = takeoutProductDao.editProductByIdDAO(product);
		if(row <= 0){
			throw new Exception("修改失败");
		}
	}

	//通过分类ID删除商品
	@Override
	public void delProductByCId(int categoryId) throws Exception {
		try {
			int row = takeoutProductDao.delProductByCIdDAO(categoryId);
		}catch (Exception e){
			//向上抛出，抛到service 调用层处理
			throw new Exception(e.getMessage());
		}
	}

	//关联团购添加产品表，只查询出未添加到团购产品表中的数据
	@Override
	public List<TakeoutProduct> getNotInGroupBuyProductListData(int shopId) {
		List<TakeoutProduct> resultList = takeoutProductDao.getNotInGroupBuyProductListDataDAO(shopId);
		if(resultList != null && resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public List<TakeoutProduct> getTakeoutProductByCategoryIdAndIfCanBuy(Integer categoryId) {
		List<TakeoutProduct> resultList = takeoutProductDao.getTakeoutProductByCategoryIdAndIfCanBuy(categoryId);
		if(resultList != null && resultList.size() > 0){
			return resultList;
		}
		return null;
	}

	@Override
	public AjaxResponseModel setProductTakeOn(List<Integer> idInts) {
		try {
			int row = takeoutProductDao.setProductTakeOn(idInts);
//			if (row<0)
//				return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "所选商品已经上架了");
			return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "上架成功");
		}catch (Exception e) {
			return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "上架失败");
		}
	}

	@Override
	public AjaxResponseModel setProductTakeOff(List<Integer> idInts) {
		try {
			int row = takeoutProductDao.setProductTakeOff(idInts);
//			if (row<0)
//				return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "所选商品已经下架了");
			return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "下架成功");
		}catch (Exception e) {
			return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED, "下架失败");
		}
	}

	/* ======================JSP页面接口结束============================ */
}
