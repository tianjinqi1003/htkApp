package com.htkapp.core.jsAjax;

/**
 * Created by yinqilei on 17-7-4.
 * js Ajax统一返回数据封装类
 */
public class AjaxResponseModel<T> {

    //-2参数错误，-1操作失败，0操作成功，1成功刷新当前页，
    //2成功并跳转到url，3成功并刷新iFrame的父界面
    //4成功并弹窗口

    private int code;

    private String message;

    private String url;

    private T data;

    private int pageNumber;
    public AjaxResponseModel() {
		super();
	}


    public AjaxResponseModel(int code){
        this.code = code;
        if (code == -2) {
            setMessage("参数错误");
        } else if (code == -1) {
            setMessage("操作失败");
        } else if (code == 0) {
            setMessage("操作成功");
        }
    }

    public AjaxResponseModel(int code, String message){
        this.code = code;
        this.message = message;
    }

    public AjaxResponseModel(int code, String message, T data, String url){
        this.code = code;
        this.message = message;
        this.data = data;
        this.url = url;
    }

    public AjaxResponseModel(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AjaxResponseModel(int code, String message, T data, int pageNumber){
        this.code = code;
        this.message = message;
        this.data = data;
        this.pageNumber = pageNumber;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
