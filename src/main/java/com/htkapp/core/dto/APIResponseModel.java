package com.htkapp.core.dto;

/**
 * Created by yinqilei on 17-6-22.
 * api 返回封装类
 */
public class APIResponseModel<T> {

    /** code: 100成功　-99失败  -98请求参数错误  **/

    private int code; //状态代码

    private String message;  //返回信息

    private T data;  //返回数据

    //返回code
    public APIResponseModel(int code){
        if(code == -98){
            this.message = "请求参数错误";
            this.code = code;
        }else if(code == -99){
            this.message = "失败";
            this.code = code;
        }else if(code == 100){
            this.message = "成功";
            this.code = code;
        }
    }

    //出现未定义错误
    public APIResponseModel(int code, String message){
        this.code = code;
        this.message = message;
    }

    //返回处理的结果结果
    public APIResponseModel(int code, String message, T data){
        this.code = code;
        this.message =message;
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
