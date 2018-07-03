package com.htkapp.modules.merchant.buffetFood.service.ServiceImpl;


import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.buffetFood.dao.SeatInformationMapper;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;
import com.htkapp.modules.merchant.buffetFood.service.SeatInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatInformationServiceImpl implements SeatInformationService{

    @Resource
    private SeatInformationMapper seatInformationDao;

    /* =======================接口开始======================== */
    //根据商户id查找商铺下的座位信息
    @Override
    public List<SeatInformation> getSeatInformationListByShopId(int shopId) throws Exception {
        try {
            List<SeatInformation> resultList = seatInformationDao.getSeatInformationListByShopIdDAO(shopId);
            if(resultList != null && resultList.size() > 0){
                return resultList;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

    @Override
    public List<SeatInformation> getSeatInformationAllByShopId(int shopId) throws Exception {
        try {
            List<SeatInformation> resultList = seatInformationDao.getSeatInformationListByShopId(shopId);
            if(resultList != null && resultList.size() > 0){
                return resultList;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(Globals.CALL_DATABASE_ERROR);
        }
    }

 //添加座位信息
    @Override
   public AjaxResponseModel<SeatInformation> addSeatInfoByShopId(SeatInformation seat){
        try {
        	SeatInformation oldseat=seatInformationDao.getSeatInformationByShopIdAndSeatName(seat.getShopId(), seat.getSeatName());
        	if(oldseat==null) {
        		int a=seatInformationDao.addSeatInfoByShopId(seat);
        		if(a<=0) {
        			 return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED,"添加失败");
        		}
    		}else {
    			 return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED,"座位已存在");
    		}
        	SeatInformation returnseat=seatInformationDao.getSeatInformationByShopIdAndSeatName(seat.getShopId(), seat.getSeatName());
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION,"添加成功",returnseat);
        } catch (Exception e) {
            return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED,"添加失败");
        }
    }

    @Override
    public int updataSeatInfoByOrder(BuffetFoodOrder bfo,Integer b) throws Exception {
        int a=seatInformationDao.updataSeatInfoByShopId(bfo,b);
        if(a<=0) {
            return a;
        }
        return a;
    }
 //删除座位信息
	@Override
	public AjaxResponseModel<SeatInformation> delSeatInfoByShopIdAndId(SeatInformation seat) {
		int i=seatInformationDao.delSeatInfoByShopIdAndId(seat);
		if(i<=0) {
			return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED,"删除失败");
		}
		 return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION,"删除成功");
	}
	//修改座位信息
	@Override
	public AjaxResponseModel<SeatInformation> updataSeatInfoBySeatName(SeatInformation seat,String oldName) {
		int a=seatInformationDao.updataSeatInfoBySeatName(seat, oldName);
		if(a<=0) {
			return new AjaxResponseModel<>(Globals.COMMON_OPERATION_FAILED,"修改失败");
		}
		return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION,"修改成功");
	}
	//获得单独的某条座位信息
	@Override
	public SeatInformation getSeatInformationByShopIdAndSeatName(int shopId, String seatName) {
		SeatInformation sif=seatInformationDao.getSeatInformationByShopIdAndSeatName(shopId, seatName);
		if(sif!=null) {
			return sif;
		}
		return sif;
	}

	@Override
	public int updataSeatInfoBySeatNameAndShopId(SeatInformation seat) {
		int a =seatInformationDao.updataSeatInfoBySeatNameAndShopId(seat);
		if(a<=0) {
			return a;
		} 
		return a;
	}
	
	



    /* =======================接口结束========================== */
}
