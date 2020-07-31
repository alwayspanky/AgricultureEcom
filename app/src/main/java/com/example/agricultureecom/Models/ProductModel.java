package com.example.agricultureecom.Models;

import com.google.gson.annotations.SerializedName;

public class ProductModel {

    @SerializedName("productImage")
    private String productImage;

    @SerializedName("productName")
    private String productName;

    @SerializedName("productCategory")
    private String productCategory;

    @SerializedName("productDescription")
    private String productDescription;

    @SerializedName("productPrice")
    private String productPrice;

    public ProductModel(String productImage, String productName, String productCategory, String productDescription, String productPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
