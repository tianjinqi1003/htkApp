package com.htkapp.core.exception;

/**
 * Created by yinqilei on 17-5-18.
 * 插入记录异常
 */
public class InsertRecordException extends BuyException {

    public InsertRecordException(String message){
        super(message);
    }

    public InsertRecordException(String message, Throwable cause){
        super(message, cause);
    }
}
