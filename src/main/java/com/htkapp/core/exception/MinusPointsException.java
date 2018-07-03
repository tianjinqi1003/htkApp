package com.htkapp.core.exception;

/**
 * Created by yinqilei on 17-5-18.
 * 减积分异常
 */
public class MinusPointsException extends BuyException {

    public MinusPointsException(String message){
        super(message);
    }

    public MinusPointsException(String message, Throwable cause){
        super(message, cause);
    }

}
