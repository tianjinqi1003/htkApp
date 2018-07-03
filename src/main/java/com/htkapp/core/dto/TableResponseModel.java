package com.htkapp.core.dto;

/**
 * Created by yinqilei on 17-6-22.
 * bootstarp - table 异步请求返回数据封装类
 */

public class TableResponseModel<T> {

    private int total;

    private T rows;

    public TableResponseModel(int total, T rows){
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }
}
