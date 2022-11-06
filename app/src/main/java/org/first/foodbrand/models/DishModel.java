package org.first.foodbrand.models;

public class DishModel {
    String title;
    String image;
    int id;;

    public DishModel(String title, String image,int id) {
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
