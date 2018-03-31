package com.mrswimmer.coffeetea.data.model;

public class Review {
    String description;
    int mark;
    String userId;

    public Review(String description, int mark, String userId) {
        this.description = description;
        this.mark = mark;
        this.userId = userId;
    }

    public Review() {
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
