package com.htkapp.modules.merchant.buffetFood.service;


import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;

import java.util.List;

public interface SeatInformationService {


    /* =====================接口开始======================= */
   /* =====================接口开始======================= */
    //根据商户id查找商铺下空闲的的座位信息
    List<SeatInformation> getSeatInformationListByShopId(int shopId) throws Exception;
    //根据商铺id查询上铺下的所有座位信息
    List<SeatInformation> getSeatInformationAllByShopId(int shopId) throws Exception;
    //为商铺增加对应的座位信息
    AjaxResponseModel<SeatInformation> addSeatInfoByShopId(SeatInformation seat)throws Exception;
    //修改座位状态信息
    int updataSeatInfoByOrder(BuffetFoodOrder bfo, Integer b)throws Exception;
//删除座位信息
    AjaxResponseModel<SeatInformation> delSeatInfoByShopIdAndId(SeatInformation seat);
    //修改座位信息
    AjaxResponseModel<SeatInformation> updataSeatInfoBySeatName(SeatInformation seat,String oldName);
    //通过座位名称以及店铺id查询对应的座位信息
    SeatInformation getSeatInformationByShopIdAndSeatName(int shopId,String seatName);
  //通过座位名称以及店铺id修改特定座位的状态以及入座时间(仅限点击座位管理修改状态)
    int updataSeatInfoBySeatNameAndShopId(SeatInformation seat);

    /* =====================接口开始======================= */
}
