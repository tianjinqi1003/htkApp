package com.htkapp.core.exception;

/**
 * Created by yinqilei on 17-5-18.
 * 减库存异常
 */
public class ReduceInventoryException extends BuyException {

    public ReduceInventoryException(String message){
        super(message);
    }

    public ReduceInventoryException(String message, Throwable cause){
        super(message,cause);
    }
}
