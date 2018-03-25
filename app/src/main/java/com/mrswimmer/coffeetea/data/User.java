package com.mrswimmer.coffeetea.data;

import java.util.ArrayList;

public class User {
    private String first_name = "";
    private String last_name = "";
    private int city = 0;
    private String username = "";
    private String mail = "";
    private ArrayList<Basket> basket = new ArrayList<>();

    public User() {
    }

    public User(String first_name, String last_name, int city, String username, String mail, ArrayList<Basket> basket) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.username = username;
        this.mail = mail;
        this.basket = basket;
    }

    public User(String first_name, String last_name, int city, String username, String mail) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.city = city;
        this.username = username;
        this.mail = mail;
        this.basket = basket;
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

    public ArrayList<Basket> getBasket() {
        return basket;
    }

    public void setBasket(ArrayList<Basket> basket) {
        this.basket = basket;
    }
}
