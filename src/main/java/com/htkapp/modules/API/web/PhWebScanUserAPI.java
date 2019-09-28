package com.htkapp.modules.API.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.service.PhWebScanUserService;
import com.htkapp.modules.merchant.buffetFood.entity.SeatInformation;
import com.htkapp.modules.merchant.buffetFood.service.SeatInformationService;

@Controller
@RequestMapping("/API/phWebScanUserAPI")
public class PhWebScanUserAPI {

	@Resource
	private PhWebScanUserService phWebScanUserService;
	@Resource
	private SeatInformationService seatInformationService;
	
	@RequestMapping(value="/login")
	@ResponseBody
	public APIResponseModel login(String shopId, String seatName) {
		
		String zhuoNo=shopId+"_"+seatName;
		return phWebScanUserService.login(zhuoNo);
	}
	
	@RequestMapping(value = "/getShopSeatInfoById")
	@ResponseBody
    public APIResponseModel getShopSeatInfoById(Integer shopId) {
		
		if (shopId != null) {
            try {
                List<SeatInformation> resultList = seatInformationService.getSeatInformationAllByShopId(shopId);
                if (resultList != null) {
                    return new APIResponseModel<List<SeatInformation>>(Globals.API_SUCCESS, "成功", resultList);
                } else {
                    return new APIResponseModel<String>(Globals.API_SUCCESS, "失败", null);

                }
            } catch (Exception e) {
                return new APIResponseModel(Globals.API_FAIL, e.getMessage());
            }
        } else {
            return new APIResponseModel(Globals.API_REQUEST_BAD);
        }
    }
}
