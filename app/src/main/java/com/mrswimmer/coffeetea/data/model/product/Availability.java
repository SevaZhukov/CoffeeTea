package com.mrswimmer.coffeetea.data.model.product;

public class Availability {
    int quantity;
    String shopId;
    String id;

    public Availability() {
    }

    public Availability(int quantity, String shopId) {
        this.quantity = quantity;
        this.shopId = shopId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
