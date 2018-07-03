package com.htkapp.modules.API.service.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.htkapp.core.MD5Utils;
import com.htkapp.core.shiro.common.utils.vcode.Encoder;
import com.htkapp.core.utils.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htkapp.modules.API.dao.SmsBaseMapper;
import com.htkapp.modules.API.entity.SmsBase;
import com.htkapp.modules.API.service.SMSBaseServiceI;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 发送短信基础类
 *
 * @author administration
 */
@Service("SMSBaseServiceImpl")
public class SMSBaseServiceImpl implements SMSBaseServiceI {

    @Autowired
    private SmsBaseMapper smsBaseDao;

    /**
     * @author 马鹏昊
     * @desc 新的短信平台
     * @param phones
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     */
    public String SendSms(String phones, String content)
            throws UnsupportedEncodingException {
        HttpURLConnection httpConn = null;
        String result = "-20";
        StringBuilder sb = new StringBuilder();
        sb.append(Globals.NEW_API_SMS_URL);
        sb.append("?username=").append(Globals.NEW_API_SMS_USERNAME);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String tkey = sdf.format(new Date());
        sb.append("&password=").append(MD5Utils.getMD5Encode( MD5Utils.getMD5Encode(Globals.NEW_API_SMS_PASSWORD).toLowerCase()  +  tkey).toLowerCase() );
        sb.append("&tkey=").append(tkey);
        sb.append("&mobile=").append(phones);
        sb.append("&productid=").append("160302");
        sb.append("&xh=").append(0);
        // 注意乱码的话换成UTF-8编码
        sb.append("&content=").append(URLEncoder.encode(content, "UTF-8"));
        try {
            System.out.println("sendSms:" + sb);
            URL url = new URL(sb.toString());
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod(String.valueOf(RequestMethod.POST));
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream()));
            result = rd.readLine();
            rd.close();
        } catch (MalformedURLException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
                httpConn = null;
            }
        }
        return result;
    }
//    public String SendSms(String phones, String content)
//            throws UnsupportedEncodingException {
//        HttpURLConnection httpConn = null;
//        String result = "-20";
//        StringBuilder sb = new StringBuilder();
//        sb.append(Globals.API_SMS_URL);
//        sb.append("?Account=").append(Globals.API_SMS_USERNAME);
//        sb.append("&Password=").append(Globals.API_SMS_PASSWORD);
//        sb.append("&Phones=").append(phones);
//        // 注意乱码的话换成gb2312编码
//        sb.append("&Content=").append(URLEncoder.encode(content, "gb2312"));
//        sb.append("&Channel=1");
//        try {
//            System.out.println("sendSms:" + sb);
//            URL url = new URL(sb.toString());
//            httpConn = (HttpURLConnection) url.openConnection();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(
//                    httpConn.getInputStream()));
//            result = rd.readLine();
//            rd.close();
//        } catch (MalformedURLException e) {
//            System.out.println(e.getLocalizedMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (httpConn != null) {
//                httpConn.disconnect();
//                httpConn = null;
//            }
//        }
//        return result;
//    }

    @Override
    public String generatorValCode() {

        StringBuilder code = new StringBuilder();
        Random random = new Random();
        // 生成6位验证码
        for (int i = 0; i < 6; i++) {
            code.append(String.valueOf(random.nextInt(10)));
        }
        return code.toString();
    }

    @Override
    public String findValCodeByPhone(String phone) throws Exception {
        try {
            SmsBase smsBase = smsBaseDao.selectByTelphone(phone);
            if (smsBase != null) {
                return smsBase.getValCode();
            }else {
                //对象为空则直接返回null
                return null;
            }
        } catch (Exception e) {
            throw new Exception("数据库层根据手机号查找验证码异常!");
        }
    }

    @Override
    public void saveOrUpdate(String telphone, String valCode) {
        SmsBase smsBase = smsBaseDao.selectByTelphone(telphone);
        if (smsBase != null) {
            smsBase.setValCode(valCode);
            smsBaseDao.updateByPrimaryKeySelective(smsBase);
        } else {
            smsBase = new SmsBase();
            smsBase.setTelPhone(telphone);
            smsBase.setValCode(valCode);
            smsBaseDao.insert(smsBase);
        }
    }
}