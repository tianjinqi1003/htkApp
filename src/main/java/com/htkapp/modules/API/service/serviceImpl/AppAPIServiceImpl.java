package com.htkapp.modules.API.service.serviceImpl;

import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.entity.AppCheckVersion;
import com.htkapp.modules.API.service.AppAPIService;
import com.htkapp.modules.API.service.AppCheckVersionService;
import com.htkapp.modules.common.dto.ReturnNoticeCenterData;
import com.htkapp.modules.common.entity.NoticeCenter;
import com.htkapp.modules.common.service.NoticeCenterService;
import com.htkapp.modules.merchant.pay.entity.OrderRecord;
import com.htkapp.modules.merchant.pay.service.OrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinqilei on 17-7-4.
 */
@Service
public class AppAPIServiceImpl implements AppAPIService {

    @Autowired
    private AppCheckVersionService appCheckVersionService;
    @Autowired
    private NoticeCenterService noticeCenterService;
    @Autowired
    private OrderRecordService orderRecordService;
    @Autowired(required = false)
    private HttpServletRequest request;

    /* ===================接口开始======================= */
    //检查app版本号
    @Override
    public APIResponseModel checkAppUpdate(String appId, String versionNumber) {
        if (StringUtils.isNotEmpty(appId) && StringUtils.isNotEmpty(versionNumber)) {
            try {
                AppCheckVersion version = appCheckVersionService.getTheLatestVersionNumber(appId);
                if (version != null) {
                    if (Integer.parseInt(version.getClientVersion().replaceAll(".0", "").trim()) > Integer.parseInt(versionNumber.replaceAll(".0", "").trim())) {
                        version.setDownloadUrl(OtherUtils.getRootDirectory() + version.getDownloadUrl());
                        return new APIResponseModel<AppCheckVersion>(Globals.API_SUCCESS, "成功", version);
                    } else if (Integer.parseInt(version.getClientVersion().replaceAll(".0", "").trim()) == Integer.parseInt(versionNumber.replaceAll(".0", "").trim())) {
                        return new APIResponseModel(Globals.API_SUCCESS, "当前是最新版本");
                    } else {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                } else {
                    return new APIResponseModel(Globals.API_FAIL, "获取最新版本号失败");
                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    //获取用户通知中心最新消息
    @Override
    public APIResponseModel getNoticeCenterByToken(String token, Integer pageNumber) {
        try {
            int pageNo = Globals.API_HOME_PAGE_CATEGORY_NO;
            int pageLimit = Globals.API_HOME_PAGE_CATEGORY_LIMIT;
            if (pageNumber > 1) {
                pageNo = pageNumber;
            }
            String orderByDesc = "gmt_create desc";  //条件语句
            List<NoticeCenter> resultList = noticeCenterService.getNoticeCenterListByToken(token, pageNo, pageLimit,orderByDesc);
            if (resultList != null && resultList.size() > 0) {
                List<ReturnNoticeCenterData> list = new ArrayList<>();
                //查询到了数据
                for (NoticeCenter each : resultList) {
                    //遍历item,根据订单id查询数据
                    ReturnNoticeCenterData noticeCenterData = new ReturnNoticeCenterData();
                    try {
                        OrderRecord orderRecord = orderRecordService.getOrderRecordById(each.getOrderId());
                        if (orderRecord != null) {
                            noticeCenterData.setMark(orderRecord.getMark());  //存入标识
                            noticeCenterData.setNoticeContent(each.getNoticeContent()); //通知内容
                            noticeCenterData.setNoticeTitle(each.getNoticeTitle());  //通知标题
                            noticeCenterData.setShopId(orderRecord.getShopId());  //商铺id
                            noticeCenterData.setOrderId(each.getOrderId());  //订单id
                            String noticeTime = each.getNoticeTime().substring(0, each.getNoticeTime().length() - 5);
                            System.out.println(noticeTime.substring(5));
                            noticeCenterData.setNoticeTime(noticeTime.substring(5));
                        }
                    } catch (Exception e) {
                        return new APIResponseModel(Globals.API_FAIL);
                    }
                    list.add(noticeCenterData);
                }
                return new APIResponseModel<List<ReturnNoticeCenterData>>(Globals.API_SUCCESS, "成功", list);
            } else {
                return new APIResponseModel(Globals.API_SUCCESS, "没有通知");
            }
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, e.getMessage());
        }
    }


    /* ======================接口结束======================== */
}
