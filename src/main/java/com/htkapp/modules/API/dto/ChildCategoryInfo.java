package com.htkapp.modules.API.dto;

public class ChildCategoryInfo {

    private Integer id;  //id

    private String categoryName; //分类名

    private Integer mark;  //标记

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
