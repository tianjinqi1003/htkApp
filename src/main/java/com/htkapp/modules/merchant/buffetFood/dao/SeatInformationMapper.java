package com.htkapp.modules.merchant.buffetFood.dao;


import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrder;
import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SeatInformationMapper {


    /* =====================接口开始======================= */
    //根据商户id查找商铺下的座位信息
    List<SeatInformation> getSeatInformationListByShopIdDAO(int shopId);
//根据商户id查找商铺下所有的座位信息
    List<SeatInformation> getSeatInformationListByShopId(int shopId);
    //为商铺增加对应的座位信息
     int addSeatInfoByShopId(SeatInformation seat);
    //根据商铺id、座位id删除对应的座位信息
   int delSeatInfoByShopIdAndId(SeatInformation seat);
    //根据商铺id修改座位状态信息
    int updataSeatInfoByShopId(@Param("bfo")BuffetFoodOrder bfo,@Param("b")Integer b);
  //根据座位名称及店铺id修改座位信息
    int updataSeatInfoBySeatName(@Param("newSeat")SeatInformation seat,@Param("oldName")String seatName);
    //通过座位名称以及店铺id查询特定的座位
    SeatInformation getSeatInformationByShopIdAndSeatName(int shopId,String seatName);
    //通过座位名称以及店铺id修改特定座位的状态以及入座时间(仅限点击座位管理修改状态)
    int updataSeatInfoBySeatNameAndShopId(SeatInformation seat);

    /* =====================接口开始======================= */
}
