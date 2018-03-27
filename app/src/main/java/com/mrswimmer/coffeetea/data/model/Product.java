package com.mrswimmer.coffeetea.data.model;

import java.util.ArrayList;

public class Product {
    int weight;
    String description;
    String name;
    int cost;
    int typeId;
    String kindId;
    ArrayList<String> images = new ArrayList<>();
    ArrayList<Availability> availabilities = new ArrayList<>();

    public Product() {
    }

    public Product(int weight, String description, String name, int cost, ArrayList<String> images, ArrayList<Availability> availabilities, int typeId, String kindId) {
        this.weight = weight;
        this.description = description;
        this.name = name;
        this.cost = cost;
        this.images = images;
        this.availabilities = availabilities;
        this.typeId = typeId;
        this.kindId = kindId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(ArrayList<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }
}

