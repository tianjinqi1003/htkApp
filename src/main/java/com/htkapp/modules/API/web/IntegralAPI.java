package com.htkapp.modules.API.web;

import com.github.pagehelper.PageInfo;
import com.htkapp.core.API.APIRequestParams;
import com.htkapp.core.GetIdUtil;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.exception.InsertRecordException;
import com.htkapp.core.exception.MinusPointsException;
import com.htkapp.core.exception.ReduceInventoryException;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.OrderNumGen;
import com.htkapp.modules.API.service.AccountFocusService;
import com.htkapp.modules.API.service.IntegralAPIService;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.common.entity.SinglePage;
import com.htkapp.modules.merchant.common.service.SinglePageService;
import com.htkapp.modules.merchant.integral.entity.*;
import com.htkapp.modules.merchant.integral.service.*;
import com.htkapp.modules.merchant.shop.entity.AccountShop;
import com.htkapp.modules.merchant.shop.entity.Shop;
import com.htkapp.modules.merchant.shop.entity.ShopSuggest;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import com.htkapp.modules.merchant.shop.service.ShopServiceI;
import com.xiaoleilu.hutool.crypto.symmetric.AES;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.xiaoleilu.hutool.crypto.SecureUtil.aes;

/**
 * Created by yinqilei on 17-7-15.
 * //TODO
 * 用户不用登陆即可查看数据,但是需要用户数据的地方，怎么让用户登陆 ?
 */

@Controller
@RequestMapping("/API/appMemberAPI")
public class IntegralAPI {

    @Resource
    private IntegralAPIService integralService;

    private static final String rootDirect = "merchant/";

    //首页活动列表接口
    @RequestMapping(value = "/getMemberHomeListData", method = RequestMethod.POST)
    @ResponseBody
    public APIResponseModel getMemberHomeListData(APIRequestParams params, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        params.setPageNumber(pageNum);
        return integralService.getMemberHomeListData(params);
    }

    //会员平台－我的信息
    @RequestMapping("/getMemberAccountMes")
    @ResponseBody
    public APIResponseModel getMemberAccountMes(APIRequestParams params) {
        return integralService.getMemberAccountMes(params);
    }

    //我的－消费券列表(需要token)
    @RequestMapping(value = "/getAccountCouponList", method = RequestMethod.POST)
    @ResponseBody
    public APIResponseModel getAccountCouponList(APIRequestParams params) {
        return integralService.getAccountCouponList(params);
    }

    //我的－交易记录
    @RequestMapping(value = "/getAccountTradingRecord", method = RequestMethod.POST)
    @ResponseBody
    public APIResponseModel getAccountTradingRecord(APIRequestParams params,
                                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        params.setPageNumber(pageNum);
        return integralService.getAccountTradingRecord(params);
    }

    //我的－我的预约
    @RequestMapping(value = "/getAccountReserve", method = RequestMethod.POST)
    @ResponseBody
    public APIResponseModel getAccountReserve(APIRequestParams params,
                                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        params.setPageNumber(pageNum);
        return integralService.getAccountReserve(params);
    }

    //我的－积分兑换
    @RequestMapping(value = "/getIntegralBuyData", method = RequestMethod.POST)
    @ResponseBody
    public APIResponseModel getIntegralBuyData(APIRequestParams params,
                                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        params.setPageNumber(pageNum);
        return integralService.getIntegralBuyData(params);
    }

    //我的－积分兑换操作
    @RequestMapping("/redeemOperation")
    @ResponseBody
    public APIResponseModel redeemOperation(APIRequestParams params) {
        //店铺id, 优惠券id, 用户token
        return integralService.redeemOperation(params);
    }

    //我的－积分记录
    @RequestMapping(value = "/getAccountIntegralRecord", method = RequestMethod.POST)
    @ResponseBody
    public APIResponseModel getAccountIntegralRecord(APIRequestParams params,
                                                     @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        params.setPageNumber(pageNum);
        return integralService.getAccountIntegralRecord(params);
    }

    //加入会员页面二维码
    @RequestMapping(value = "/getQrImgData", method = RequestMethod.POST)
    @ResponseBody
    public APIResponseModel getQrImgData(APIRequestParams params) {
        return integralService.getQrImgData(params);
    }

    //加入按钮操作请求
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    @ResponseBody
    public APIResponseModel addMember(APIRequestParams params) {
        return integralService.addMember(params);
    }

    //预定座位请求
    @RequestMapping(value = "/addReserveRequest", method = RequestMethod.POST)
    @ResponseBody
    public APIResponseModel addReserveRequest(APIRequestParams params, SeatOrder seatOrder) {
        seatOrder.setAccountToken(params.getToken());
        return integralService.addReserveRequest(params, seatOrder);
    }

    //会员平台首页资讯详情
    @RequestMapping("/articleDetails/{aId}")
    public String getArticleDetailsById(@PathVariable("aId") Integer aId, Model model) {
        integralService.articleDetailPage(aId, model);
        return rootDirect + "articleDetails";
    }

    //平台－关于
    @RequestMapping("/getShopIntroduce")
    @ResponseBody
    public APIResponseModel getShopIntroduce(APIRequestParams params) {
        return integralService.getShopIntroduce(params);
    }

    //平台－建议
    @RequestMapping("/accountSuggestRequest")
    @ResponseBody
    public APIResponseModel accountSuggestRequest(APIRequestParams params, ShopSuggest suggest) {
        return integralService.accountSuggestRequest(params, suggest);
    }

}
