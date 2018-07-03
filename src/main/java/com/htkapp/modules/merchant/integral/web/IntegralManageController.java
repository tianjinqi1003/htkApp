package com.htkapp.modules.merchant.integral.web;

import com.htkapp.core.dto.TableResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.merchant.integral.entity.AccountSaverTicket;
import com.htkapp.modules.merchant.integral.entity.ShopArticleInfo;
import com.htkapp.modules.merchant.integral.service.IntegralManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.util.Date;

import static com.htkapp.modules.merchant.common.web.MerchantController.mDirectory;

/**
 * 积分管理 web
 */
@Controller
@RequestMapping("/merchant/integral")
public class IntegralManageController {

	@Resource
	private IntegralManageService integralManageService;

	//==============================================积分列表
	//====　接口
	//赠送积分－抵扣积分
	@RequestMapping("/presentIntegralInterface")
	@ResponseBody
	public AjaxResponseModel presentOrDeductionIntegral(AjaxRequestParams params, BindingResult result) {
		if (result.hasErrors()) {
			StringBuffer bf = new StringBuffer();
			for (ObjectError each : result.getAllErrors()) {
				bf.append(each.getDefaultMessage());
			}
			return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR, bf.toString());
		} else {
			return integralManageService.presentOrDeductionIntegral(params);
		}
	}

	//创建资讯
	@RequestMapping("/createNewActive")
	@ResponseBody
	public AjaxResponseModel createNewActive(ShopArticleInfo shopArticleInfo){
		return integralManageService.createNewActive(shopArticleInfo);
	}

	//创建资讯图片上传(最初的，殷其磊做)
	@RequestMapping("/uploadMsgImg")
	@ResponseBody
	public AjaxResponseModel uploadMsgImg(MultipartFile file){
		return integralManageService.uploadMsgImg(file);
	}

	/**
	 * @author 马鹏昊
	 * @desc 创建资讯标题图片上传
	 */
	@RequestMapping("/uploadMsgTitleImg")
	@ResponseBody
	public AjaxResponseModel uploadMsgTitleImg(MultipartFile file){
		return integralManageService.uploadMsgTitleImg(file);
	}

	/**
	 * @author 石超
	 * @desc  创建资讯内容图片上传
	 */
	@RequestMapping("/uploadNewsContentImg")
	@ResponseBody
	public String uploadNewsContentImg(MultipartFile file){
		return integralManageService.uploadNewsContentImg(file);
	}
	//创建兑换活动
	@RequestMapping("/createExchangeActivity")
	@ResponseBody
	public AjaxResponseModel createExchangeActivity(AccountSaverTicket saverTicket, String startTime, String endTime){
		return integralManageService.createExchangeActivity(saverTicket, startTime, endTime);
	}


	//====  页面
	//==活动
	//新建活动
	@RequestMapping(value = "/getNewActivePage", method = RequestMethod.GET)
	public String getNewActivePage(Model model){
		model.addAttribute("date", new Date().getTime());
		model.addAttribute("int_mark", true);
		model.addAttribute("int_mark_active", true);
		return mDirectory + "new_active";
	}
	//未开始活动
	@RequestMapping(value = "/getExitsActiveNoStartPage", method = RequestMethod.GET)
	public String getExitsActiveNoStartPage(Model model, RequestParams params){
		model.addAttribute("date", new Date().getTime());
		model.addAttribute("int_mark_active", true);
		model.addAttribute("int_mark", true);
		params.setModel(model);
		integralManageService.getExitsActiveNoStartPage(params);
		return mDirectory + "exits_active_notStart";
	}

	//进行中活动
	@RequestMapping(value = "/getExitsActiveProcessPage", method = RequestMethod.GET)
	public String getExitsActiveProcessPage(Model model, RequestParams params){
		model.addAttribute("date", new Date().getTime());
		model.addAttribute("int_mark_active", true);
		model.addAttribute("int_mark", true);
		params.setModel(model);
		integralManageService.getExitsActiveProcessPage(params);
		return mDirectory + "exits_active_process";
	}

	//已停止活动
	@RequestMapping(value = "/getExitsActiveStopPage", method = RequestMethod.GET)
	public String getExitsActiveStopPage(Model model, RequestParams params){
		model.addAttribute("date", new Date().getTime());
		model.addAttribute("int_mark_active", true);
		model.addAttribute("int_mark", true);
		params.setModel(model);
		integralManageService.getExitsActiveStopPage(params);
		return mDirectory + "exits_active_stop";
	}
	//资讯
	@RequestMapping(value = "/getExitsActiveMessagePage", method = RequestMethod.GET)
	public String getExitsActiveMessagePage(Model model, RequestParams params,
			@RequestParam(value = "pageNum",required = false, defaultValue = "1")Integer pageNum){
		model.addAttribute("date", new Date().getTime());
		model.addAttribute("int_mark_active", true);
		model.addAttribute("int_mark", true);
		params.setPageNum(pageNum);
		params.setModel(model);
		integralManageService.getExitsActiveMessagePage(params);
		return mDirectory + "exits_active_message";
	}

	//作废活动接口
	@RequestMapping("/obsoleteActivity")
	@ResponseBody
	public AjaxResponseModel obsoleteActivity(AjaxRequestParams params){
		return integralManageService.obsoleteActivity(params);
	}

	//资讯关闭显示接口
	@RequestMapping("/closeTheDisplay")
	@ResponseBody
	public AjaxResponseModel closeTheDisplay(AjaxRequestParams params){
		return integralManageService.closeTheDisplay(params);
	}


	//开启活动
	@RequestMapping("/openActivity")
	@ResponseBody
	public AjaxResponseModel openActivity(AjaxRequestParams params){
		return integralManageService.openActivity(params);
	}

	//资讯修改
	@RequestMapping("/updateMes")
	@ResponseBody
	public AjaxResponseModel updateMes(ShopArticleInfo shopArticleInfo){
		return integralManageService.updateMes(shopArticleInfo);
	}
	//获取订座订单信息
	@RequestMapping("/getSeatInfo")
	@ResponseBody
	public AjaxResponseModel getSeatInfo(){
		return integralManageService.getSeatInfo();
	}
	//订座订单操作(安排座位)
	@RequestMapping("/updataSeatInfo")
	@ResponseBody
	public AjaxResponseModel updataSeatInfo(String seatName,String orderNumber){
		return integralManageService.updataSeatInfo(seatName,orderNumber);
	}
	//获取订座订单信息(未处理)
	@RequestMapping("/getSeatInfoByStatus")
	@ResponseBody
	public AjaxResponseModel getSeatInfoByStatus(){
		return integralManageService.getSeatInfoByStatus();
	}
	//订座订单撤销操作
	@RequestMapping("/deleteSeatOrder")
	@ResponseBody
	public AjaxResponseModel deleteSeatOrder(String selectedIds){
		try {
			return integralManageService.deleteSeatOrder(selectedIds);
		} catch (Exception e) {
			return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "撤销失败");
		}
	}

}
