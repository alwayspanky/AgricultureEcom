package com.example.agricultureecom.Api;

import com.example.agricultureecom.Models.ProductModel;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    public static String BaseUrl = "http://192.168.43.64:5000";

    @Headers({"Content-type: application/json","Accept: */*"})
    @GET("product")
    Call<List<ProductModel>> getAllProducts();

    @Headers({"Content-type: application/json","Accept: */*"})
    @GET("product")
    Call<List<ProductModel>> getAllProductsCategory(
            @Query("productCategory") String productCategory
    );

    @POST("user/login")
    Call<JsonObject> loginUser(JsonObject data);
}
