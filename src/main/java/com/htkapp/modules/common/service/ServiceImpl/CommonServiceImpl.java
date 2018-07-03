package com.htkapp.modules.common.service.ServiceImpl;

import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.StringUtils;
import com.htkapp.modules.API.entity.AppAccountEventLog;
import com.htkapp.modules.API.service.SMSBaseServiceI;
import com.htkapp.modules.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.xiaoleilu.hutool.date.DateUtil.NORM_DATETIME_PATTERN;
import static com.xiaoleilu.hutool.date.DateUtil.format;

/**
 * Created by yinqilei on 17-7-13.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private SMSBaseServiceI smsService;

    /* ===============接口开始================ */
    //通过手机号获取验证码
    @Override
    public AjaxResponseModel getPhoneVerificationCode(String phone) {
        if (StringUtils.isNotEmpty(phone)) {
            //生成验证码
            String valCode = smsService.generatorValCode();
            String smsText = "【回头客】注册验证码是:" + valCode;
            try {
                smsService.saveOrUpdate(String.valueOf(phone), valCode);
                String smsRet = smsService.SendSms(String.valueOf(phone), smsText);
                if (Integer.parseInt(smsRet) < 0) {
                    return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "发送短信失败!");
                } else {
                    return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "短信已发送，请注意查收！");
                }
            } catch (Exception e) {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "发送短信失败!");
            }
        } else {
            return new AjaxResponseModel(Globals.API_REQUEST_BAD);
        }
    }

    /* ===============接口结束=============== */
}
