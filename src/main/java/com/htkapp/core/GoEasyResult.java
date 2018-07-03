package com.htkapp.core;

public class GoEasyResult {

    private char classifyId;  //推送类型

    private int statusCode;  //请求状态码

    private String message;   //显示信息

    private static class GoEasyInner {
        private static final GoEasyResult result = new GoEasyResult();
    }

    public GoEasyResult(char classifyId, int statusCode, String message) {
        this.classifyId = classifyId;
        this.statusCode = statusCode;
        this.message = message;
    }

    public GoEasyResult(){
        super();
    }

    public static final GoEasyResult getInstance(){
        return GoEasyInner.result;
    }

    public char getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(char classifyId) {
        this.classifyId = classifyId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
