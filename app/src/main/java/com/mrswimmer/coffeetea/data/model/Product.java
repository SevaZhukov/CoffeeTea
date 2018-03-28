package com.mrswimmer.coffeetea.data.model;

import java.util.ArrayList;

public class Product {
    String id;
    int rate;
    int weight;
    String description;
    String name;
    int cost;
    int typeId;
    int kindId;
    ArrayList<String> images = new ArrayList<>();
    ArrayList<Availability> availabilities = new ArrayList<>();

    public Product() {
    }

    public Product(String id, int weight, String description, String name, int cost, ArrayList<String> images, ArrayList<Availability> availabilities, int typeId, int kindId, int rate) {
        this.weight = weight;
        this.description = description;
        this.name = name;
        this.cost = cost;
        this.images = images;
        this.availabilities = availabilities;
        this.typeId = typeId;
        this.kindId = kindId;
        this.id = id;
        this.rate = rate;
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

    public int getKindId() {
        return kindId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getType() {
        return getTypeId() == 0 ? "Кофе" : "Чай";
    }

    public String getKind() {
        if (getTypeId() == 0) {
            switch (getKindId()) {
                case 0:
                    return "Арабика";
                case 1:
                    return "Робуста";
                case 2:
                    return "Либерика";
                case 3:
                    return "Эксцельза";
                default:
                    return "Арабика";
            }
        } else {
            switch (getKindId()) {
                case 0:
                    return "Черный";
                case 1:
                    return "Зеленый";
                case 2:
                    return "Белый";
                case 3:
                    return "Желтый";
                case 4:
                    return "Улун";
                case 5:
                    return "Пуэр";
                default:
                    return "Черный";
            }
        }
    }

    public String getInStock() {
        if (getAvailabilities().size() > 0)
            return "Да";
        else
            return "Нет";
    }

    public String getCostString() {
        return getCost() + " руб";
    }
}

