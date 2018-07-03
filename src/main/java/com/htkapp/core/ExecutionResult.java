package com.htkapp.core;

import com.htkapp.core.enums.ResultStatEnum;

/**
 * 封装执行后的结果
 */
public class ExecutionResult<T> {

    public ResultStatEnum statEnum;

    public T data;

    public ExecutionResult(ResultStatEnum statEnum, T data){
        this.statEnum = statEnum;
        this.data = data;
    }

}
