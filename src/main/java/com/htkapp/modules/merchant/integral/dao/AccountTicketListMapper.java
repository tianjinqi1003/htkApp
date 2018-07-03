package com.htkapp.modules.merchant.integral.dao;

import com.htkapp.modules.merchant.integral.entity.AccountTicketList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountTicketListMapper {

    //通过用户token 和 店铺id 查找优惠券列表
    List<AccountTicketList> getTicketListByTokenAndShopIdDAO(String token, int shopId);
    //通过用户token 查找优惠券列表
    List<AccountTicketList> getTicketListByTokenDAO(String token);
    //插入用户优惠券
    int insertAccountTicketDAO(AccountTicketList ticketList);
    
    List<AccountTicketList> getTicketListByTokenAndCouponIdDAO(String token, Integer ticket_id);

    int updateTicketListByTokenAndCouponIdDAO(@Param("ticketQuantity") int ticketQuantity,@Param("token") String token,@Param("ticket_id") Integer couponId);
    
    //通过优惠券id、店铺id、用户token查询对应的优惠券
    AccountTicketList getTicketByTokenAndShopIdAndTicketId(Integer ticketId,String token, int shopId);
  //通过优惠券id、店铺id、用户token更新对应的优惠券
    int updataTicketByTokenAndShopIdAndTicketId(Integer ticketId, String token, int shopId,int ticketQuantity);
}
