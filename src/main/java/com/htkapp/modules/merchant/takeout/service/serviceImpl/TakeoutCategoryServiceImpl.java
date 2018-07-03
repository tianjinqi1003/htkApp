package com.htkapp.modules.merchant.takeout.service.serviceImpl;

import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.takeout.dao.TakeoutCategoryMapper;
import com.htkapp.modules.merchant.takeout.entity.TakeoutCategory;
import com.htkapp.modules.merchant.takeout.service.TakeoutCategoryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TakeoutCategoryServiceImpl implements TakeoutCategoryServiceI {
	@Autowired
	private TakeoutCategoryMapper takeoutCategoryDao;


	/* =====================接口开始=========================== */
	//通过店铺id查找外卖类别
	@Override
	public List<TakeoutCategory> getTakeoutCategoryById(int shopId) throws Exception {
		try {
			List<TakeoutCategory> resultList = takeoutCategoryDao.getTakeoutCategoryByIdDAO(shopId);
			if(resultList != null && resultList.size() > 0){
				return resultList;
			}else {
				//用于方法调用者判断是否查询出了结果
				return null;
			}
		}catch (Exception e){
			throw new Exception(Globals.CALL_DATABASE_ERROR);
		}
	}

	/* =====================接口结束=========================== */

	/* ===================JSP页面接口开始============================ */
	//通过商户id查找商铺下的外卖类别
	@Override
	public List<TakeoutCategory> getTakeoutCategoryListByAccountShopId(int accountShopId) throws Exception {
		try {
			List<TakeoutCategory> resultList = takeoutCategoryDao.getTakeoutCategoryListByAccountShopIdDAO(accountShopId);
			if(resultList != null && resultList.size() > 0){
				return resultList;
			}else {
				return null;
			}
		}catch (Exception e){
			throw new Exception(Globals.CALL_DATABASE_ERROR);
		}
	}

	//通过分类id修改分类名称
	@Override
	public void editCategoryNameById(TakeoutCategory category) throws Exception {
		try {
			int row = takeoutCategoryDao.editCategoryNameByIdDAO(category);
			if(row <= 0){
				throw new Exception();
			}
		}catch (Exception e){
			throw new Exception(Globals.CALL_DATABASE_ERROR);
		}

	}

	//商户新增分类
	@Override
	public void addCategoryById(String categoryName, int accountShopId,int mark) throws Exception {
		try {
			int row = takeoutCategoryDao.addCategoryByIdDAO(categoryName, accountShopId,mark);
			if(row <= 0){
				throw new Exception();
			}
		}catch (Exception e){
			throw new Exception(Globals.CALL_DATABASE_ERROR);
		}
	}

	//通过分类id查询分类信息
	@Override
	public TakeoutCategory getCategoryById(int categoryId) {
		return takeoutCategoryDao.getCategoryByIdDAO(categoryId);
	}

	//删除分类
	@Override
	public void delCategoryById(int categoryId) throws Exception {
		try {
			int row = takeoutCategoryDao.delCategoryByIdDAO(categoryId);
			if(row <= 0){
				throw new Exception("删除分类失败");
			}
		}catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
	/* ===================JSP页面接口结束============================ */
}
