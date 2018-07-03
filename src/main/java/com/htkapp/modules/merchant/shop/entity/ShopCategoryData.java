package com.htkapp.modules.merchant.shop.entity;

import com.xiaoleilu.hutool.date.DateTime;

import java.sql.Timestamp;

/**
 * Created by 马鹏昊 on 2018/1/8.
 */
public class ShopCategoryData {

    private Integer id;
    private String  categoryName;
    private String  description;
    private String  categoryUrl;
    private Integer  descNumber;
    private Character  mark;
    private Integer  parentId;
    private Timestamp gmtCreate;
    private Timestamp  gmtModified;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    public Integer getDescNumber() {
        return descNumber;
    }

    public void setDescNumber(Integer descNumber) {
        this.descNumber = descNumber;
    }

    public Character getMark() {
        return mark;
    }

    public void setMark(Character mark) {
        this.mark = mark;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
}
