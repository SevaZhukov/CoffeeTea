package com.mrswimmer.coffeetea.data;

public class User {
    private String name;
    private String last;

    public User(String name, String last) {
        this.name = name;
        this.last = last;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
