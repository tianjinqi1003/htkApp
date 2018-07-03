package com.htkapp.core.exception;

/**
 * Created by yinqilei on 17-5-18.
 * 购买异常
 */
public class BuyException extends RuntimeException {

    public  BuyException(String message){
        super(message);
    }

    public BuyException(String message, Throwable cause) {
        super(message, cause);
    }

}
