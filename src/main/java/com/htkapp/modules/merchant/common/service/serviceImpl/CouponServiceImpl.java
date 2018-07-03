package com.htkapp.modules.merchant.common.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.htkapp.modules.common.dao.CouponMapper;
import com.htkapp.modules.common.entity.Coupon;
import com.htkapp.modules.merchant.common.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券列表
 */

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper couponDao;



    /*=============================================app接口=============================================*/
    @Override
    public List<Coupon> getCouponListByAccountId(int pageNo, int pageLimit, Integer accountId) {
        // TODO AUTO-generated method stub
        PageHelper.startPage(pageNo,pageLimit);
        return couponDao.findCouponListByAccountId(accountId);
    }
}
