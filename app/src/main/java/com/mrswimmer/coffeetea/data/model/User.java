package com.mrswimmer.coffeetea.data.model;

import java.util.ArrayList;

public class User {
    private String first_name = "";
    private String last_name = "";
    private int city = 0;
    private String username = "";
    private String mail = "";
    private ArrayList<ProductInBasket> productInBasket = new ArrayList<>();

    public User() {
    }

    public User(String first_name, String last_name, int city, String username, String mail, ArrayList<ProductInBasket> productInBasket) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.username = username;
        this.mail = mail;
        this.productInBasket = productInBasket;
    }

    public User(String first_name, String last_name, int city, String username, String mail) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.username = username;
        this.mail = mail;
        this.productInBasket = productInBasket;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<ProductInBasket> getProductInBasket() {
        return productInBasket;
    }

    public void setProductInBasket(ArrayList<ProductInBasket> productInBasket) {
        this.productInBasket = productInBasket;
    }
}
