package com.htkapp.core.exception;

/**
 * 自定义异常类
 */
public class CustomException extends RuntimeException {

    public CustomException(String message){
        super(message);
    }

    public CustomException(String message, Throwable cause){
        super(message, cause);
    }

}
