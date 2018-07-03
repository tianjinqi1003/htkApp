package com.htkapp.core.utils;

import com.htkapp.modules.common.dto.ReturnNoticeCenterData;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;

import java.util.Comparator;

/**
 * Created by yinqilei on 17-7-7.
 */
public class NoticeCenterListDistanceComparator implements Comparator<ReturnNoticeCenterData> {

    @Override
    public int compare(ReturnNoticeCenterData c1, ReturnNoticeCenterData c2)
    {
        return c1.getOrderId() < c2.getOrderId() ? 0 : -1;
    }
}
