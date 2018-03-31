package com.mrswimmer.coffeetea.data.model;

import java.util.ArrayList;

public class Shop {
    String id;
    String adress;
    int beginWork;
    int endWork;
    int rate;
    ArrayList<String> images = new ArrayList<>();
    ArrayList<Review> reviews = new ArrayList<>();
    String city;

    public Shop(String id, String adress, int beginWork, int endWork, ArrayList<String> images, ArrayList<Review> reviews, String city, int rate) {
        this.id = id;
        this.adress = adress;
        this.beginWork = beginWork;
        this.endWork = endWork;
        this.images = images;
        this.reviews = reviews;
        this.city = city;
        this.rate = rate;
    }

    public Shop() {
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getBeginWork() {
        return beginWork;
    }

    public void setBeginWork(int beginWork) {
        this.beginWork = beginWork;
    }

    public int getEndWork() {
        return endWork;
    }

    public void setEndWork(int endWork) {
        this.endWork = endWork;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHours() {
        return getBeginWork() + "-" + getEndWork();
    }

    public String getReviewsString() {
        return getReviews().size() + " Отзывов";
    }
}
