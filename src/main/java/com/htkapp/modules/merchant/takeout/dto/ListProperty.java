package com.htkapp.modules.merchant.takeout.dto;

public class ListProperty {

    private double price;

    private double priceCanhe;

    private int inventory;

    private int inventoryCount;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceCanhe() {
        return priceCanhe;
    }

    public void setPriceCanhe(double priceCanhe) {
        this.priceCanhe = priceCanhe;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
