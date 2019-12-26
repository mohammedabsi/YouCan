package com.example.youcan.model;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {
    private static NetworkUtils sInstance;
    private static final String BASE_URL = "https://api.edamam.com/";
    RecipeApiInterface recipeApiInterface;
    public static NetworkUtils getInstance() {
        if (sInstance == null) {
            sInstance = new NetworkUtils();
        }
        return sInstance;
    }

    private NetworkUtils() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         recipeApiInterface = retrofit.create(RecipeApiInterface.class);


    }
    public RecipeApiInterface getRecipeApiInterface(){
        return  recipeApiInterface;
    }

    public Map<String, String> getQueryMap(String meal) {
        Map<String, String> map = new HashMap<>();


            map.put("q", meal);
            map.put("app_id", "14db4e92");
            map.put("app_key", "d5e391d2909c985b2cba50740914e6e3");


        return map;
    }

}
