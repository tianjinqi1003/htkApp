package com.htkapp.modules.merchant.takeout.dto;

import java.util.List;

public class AddProductList {

    private List<ListProperty> list;

    public AddProductList() {
        super();
    }

    public AddProductList(List<ListProperty> list) {
        super();
        this.list = list;
    }

    public List<ListProperty> getList() {
        return list;
    }

    public void setList(List<ListProperty> list) {
        this.list = list;
    }
}
