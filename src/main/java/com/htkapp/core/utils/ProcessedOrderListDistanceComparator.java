package com.htkapp.core.utils;

import com.htkapp.modules.API.dto.ProcessedOrderList;

import java.util.Comparator;

/**
 * Created by yinqilei on 17-7-6.
 *
 */
public class ProcessedOrderListDistanceComparator implements Comparator<ProcessedOrderList> {

    @Override
    public int compare(ProcessedOrderList c1, ProcessedOrderList c2) {
        return Long.parseLong(c1.getOrderNumber()) < Long.parseLong(c2.getOrderNumber()) ? 0 : -1;
    }

}
