package com.htkapp.core.exception;

/**
 * Created by yinqilei on 17-5-18.
 *　积分管理下的赠送和抵扣　交易异常
 */
public class TradeException extends RuntimeException {

    public TradeException(String message){
        super(message);
    }

    public TradeException(String message, Throwable cause){
        super(message, cause);
    }
}
