package com.htkapp.modules.merchant.integral.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 礼品兑换（活动下）
 */
public class ActivityGoods {

    private Integer id;   //主键

    private String entityName;  //名称

    private String description;  //活动声明

    private Integer integral;  //竞换所需积分

    private Integer entityCount;  //数量

    private Integer shopId;  //商铺ID

    private Integer parentId;   //父ID

    private String activityName; //活动名字

    private String img;  //图片路径

    private Date gmt_create;  //创建时间

    private Date gmt_modifiedn;  //最后修改时间


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Integer getEntityCount() {
        return entityCount;
    }

    public void setEntityCount(Integer entityCount) {
        this.entityCount = entityCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }


    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modifiedn() {
        return gmt_modifiedn;
    }

    public void setGmt_modifiedn(Date gmt_modifiedn) {
        this.gmt_modifiedn = gmt_modifiedn;
    }


}
