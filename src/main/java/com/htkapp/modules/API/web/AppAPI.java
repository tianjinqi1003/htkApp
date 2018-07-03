package com.htkapp.modules.API.web;

import com.alibaba.fastjson.JSONObject;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.service.AppAPIService;
import com.xiaoleilu.hutool.crypto.symmetric.AES;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.xiaoleilu.hutool.crypto.SecureUtil.aes;

/**
 * Created by yinqilei on 17-7-4.
 * app控制类
 */

@RestController
@RequestMapping("/API/appAPI/")
public class AppAPI {

    @Resource
    private AppAPIService appAPIService;

    //检查app版本号
    @RequestMapping("/checkAppUpdate")
    public APIResponseModel checkAppUpdate(String appId, String versionNumber) {
        return appAPIService.checkAppUpdate(appId, versionNumber);
    }

    //获取用户通知中心最新消息
    @RequestMapping("/getNoticeCenterByToken")
    public APIResponseModel getNoticeCenterByToken(String token,
                                                   @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
        return appAPIService.getNoticeCenterByToken(token, pageNumber);
    }

    //二维码解码接口
    @RequestMapping("/QRCodeDecoding")
    public APIResponseModel QRCodeDecoding(String qrKey) {
        String keys = Globals.encryptionKey;
        try {
            AES aesResult = aes(keys.getBytes());
            byte[] key = aesResult.getSecretKey().getEncoded();
            byte[] val = Base64.decodeBase64(qrKey);
            byte[] decryption = aes(key).decrypt(val);
            String toStr = StrUtil.str(decryption, "UTF8");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("shopId", Integer.parseInt(toStr));
            return new APIResponseModel<>(Globals.API_SUCCESS, "成功", jsonObject);
        } catch (Exception e) {
            return new APIResponseModel(Globals.API_FAIL, "失败");
        }
    }


}
