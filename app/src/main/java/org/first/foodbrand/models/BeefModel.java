package org.first.foodbrand.models;

public class BeefModel {
    String title;
    String image;
    int id;

    public BeefModel(String title, String image,int id) {
        this.title = title;
        this.image = image;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
    public int getId() {
        return id;
    }

}
