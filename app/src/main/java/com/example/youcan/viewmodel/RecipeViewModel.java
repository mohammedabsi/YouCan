package com.example.youcan.viewmodel;

import com.example.youcan.model.NetworkUtils;
import com.example.youcan.model.recipegson.RecipeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeViewModel {

    private NetworkUtils mNetworkUtils;

    public RecipeViewModel() {
        mNetworkUtils = NetworkUtils.getInstance();
    }

    public void getRecipeInfo(String newText) {


        Call<RecipeResponse> call = mNetworkUtils.getRecipeApiInterface().getRecipeInfo(mNetworkUtils.getQueryMap(newText));
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {

//todo : response success and data is ready to print on recycleview
                RecipeResponse recipe = response.body();
                for (int i = 0; i < recipe.getTo(); i++) {
                    String x = recipe.getHits().get(i).getRecipe().getLabel();
                    System.out.println("This is Response " + x + "\n");

                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });
    }


}
