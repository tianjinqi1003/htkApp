package com.htkapp.modules.API.dto;

import java.util.List;

/**
 * Created by yinqilei on 17-7-6.
 * 返回已处理的所有订单（外卖、团购）封装类
 */
public class ReturnAllProcessedOrderList {

    List<ReturnProcessedTakeoutOrder> processedTakeoutOrderList;

    List<ReturnProcessedGroupBuyOrder> processedGroupBuyOrderList;

    public List<ReturnProcessedTakeoutOrder> getProcessedTakeoutOrderList() {
        return processedTakeoutOrderList;
    }

    public void setProcessedTakeoutOrderList(List<ReturnProcessedTakeoutOrder> processedTakeoutOrderList) {
        this.processedTakeoutOrderList = processedTakeoutOrderList;
    }

    public List<ReturnProcessedGroupBuyOrder> getProcessedGroupBuyOrderList() {
        return processedGroupBuyOrderList;
    }

    public void setProcessedGroupBuyOrderList(List<ReturnProcessedGroupBuyOrder> processedGroupBuyOrderList) {
        this.processedGroupBuyOrderList = processedGroupBuyOrderList;
    }
}
