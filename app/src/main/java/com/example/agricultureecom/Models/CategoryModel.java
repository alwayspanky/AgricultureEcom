package com.example.agricultureecom.Models;

public class CategoryModel {

    public CategoryModel(String img, String catName) {
        this.img = img;
        this.catName = catName;
    }

    String img;
    String catName;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
