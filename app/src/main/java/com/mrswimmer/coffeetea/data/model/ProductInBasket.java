package com.mrswimmer.coffeetea.data.model;

public class ProductInBasket {
    private String productId;
    private String shopId;
    private String name;
    private String adress;
    private String city;
    private int productRate;
    private int cost;
    private int newCost;
    private int count;
    private String id;

    public ProductInBasket(String productId, String shopId, String name, String adress, String city, int productRate, int cost, int newCost, int count) {
        this.productId = productId;
        this.shopId = shopId;
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.productRate = productRate;
        this.cost = cost;
        this.newCost = newCost;
        this.count = count;
    }

    public ProductInBasket(String productId, String shopId, String name, String adress, String city, int productRate, int cost, int newCost, int count, String id) {
        this.productId = productId;
        this.shopId = shopId;
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.productRate = productRate;
        this.cost = cost;
        this.newCost = newCost;
        this.count = count;
        this.id = id;
    }

    public ProductInBasket() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getProductRate() {
        return productRate;
    }

    public void setProductRate(int productRate) {
        this.productRate = productRate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNewCost() {
        return newCost;
    }

    public void setNewCost(int newCost) {
        this.newCost = newCost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
