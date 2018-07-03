package com.htkapp.modules.merchant.common.service;


import com.htkapp.modules.common.entity.Coupon;

import java.util.List;

/**
 * 优惠券列表
 */
public interface CouponService {



    /*=============================================app接口=============================================*/
    //通过用户ID查找优惠券列表
    List<Coupon> getCouponListByAccountId(int pageNo, int pageLimit, Integer accountId);
}
