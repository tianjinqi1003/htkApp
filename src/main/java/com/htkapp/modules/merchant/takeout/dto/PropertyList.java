package com.htkapp.modules.merchant.takeout.dto;

import java.util.List;

public class PropertyList {

    private List<Property> propertyList;

    public PropertyList(){
        super();
    }

    public PropertyList(List<Property> propertyList){
        super();
        this.propertyList = propertyList;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }
}

