package com.htkapp.core.exception.costom;

/**
 * Created by yinqilei on 17-6-24.
 * 空数据异常
 */
public class NullDataException extends RuntimeException {

    public NullDataException(String  message){
        super(message);
    }

    public NullDataException(String message, Throwable cause){
        super(message, cause);
    }
}
