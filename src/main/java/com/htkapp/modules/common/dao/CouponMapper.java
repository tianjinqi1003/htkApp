package com.htkapp.modules.common.dao;



import com.htkapp.modules.common.entity.Coupon;

import java.util.List;

/**
 * 优惠券列表
 */
public interface CouponMapper {


    /*=============================================app接口=============================================*/
    //通过用户ID查找优惠券列表
    List<Coupon> findCouponListByAccountId(Integer accountId);
}
