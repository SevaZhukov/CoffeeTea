package com.mrswimmer.coffeetea.data.model;

public class Review {
    String description;
    int mark;
    String userId;
    String username;

    public Review(String description, int mark, String userId, String username) {
        this.description = description;
        this.mark = mark;
        this.userId = userId;
        this.username = username;
    }

    public Review() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
