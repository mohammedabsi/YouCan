package com.example.youcan.model;

import com.example.youcan.model.recipegson.RecipeResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RecipeApiInterface {

    @GET("search?")
    public Call<RecipeResponse> getRecipeInfo(@QueryMap Map<String, String> params);


}
