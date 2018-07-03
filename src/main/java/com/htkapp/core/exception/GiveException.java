package com.htkapp.core.exception;

/**
 * Created by yinqilei on 17-5-18.
 * 赠送异常
 */
public class GiveException extends TradeException {

    public GiveException(String message){
        super(message);
    }

    public GiveException(String message, Throwable cause){
        super(message, cause);
    }
}
