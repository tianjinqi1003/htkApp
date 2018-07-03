package com.htkapp.core.exception.order;

/**
 * Created by yinqilei on 17-6-29.
 * 订单异常类（用于事物）
 */
public class OrderException extends RuntimeException {

    public OrderException(String message){
        super(message);
    }

    public OrderException(String message, Throwable cause){
        super(message, cause);
    }
}
