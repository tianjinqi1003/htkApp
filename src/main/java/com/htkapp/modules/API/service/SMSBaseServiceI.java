package com.htkapp.modules.API.service;

import java.io.UnsupportedEncodingException;

public interface SMSBaseServiceI {
    String findValCodeByPhone(String phone) throws Exception;

    void saveOrUpdate(String phone, String valCode);

    String generatorValCode();

    String SendSms(String phones, String content) throws UnsupportedEncodingException;
}
