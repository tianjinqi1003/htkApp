package com.htkapp.core.exception;

/**
 * Created by yinqilei on 17-5-18.
 * 抵扣异常
 */
public class DeductionException extends TradeException {

    public DeductionException(String message){
        super(message);
    }

    public DeductionException(String message, Throwable cause){
        super(message, cause);
    }
}
