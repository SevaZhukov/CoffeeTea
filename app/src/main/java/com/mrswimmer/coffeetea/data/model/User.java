package com.mrswimmer.coffeetea.data.model;

import java.util.ArrayList;

public class User {
    private String firstName = "";
    private String lastName = "";
    private int city = 0;
    private String username = "";
    private String mail = "";
    private ArrayList<ProductInBasket> productInBasket = new ArrayList<>();
    public String id;

    public User() {
    }

    public User(String firstName, String lastName, int city, String username, String mail, ArrayList<ProductInBasket> productInBasket, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.username = username;
        this.mail = mail;
        this.productInBasket = productInBasket;
        this.id = id;
    }

    public User(String firstName, String lastName, int city, String username, String mail, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.username = username;
        this.mail = mail;
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
