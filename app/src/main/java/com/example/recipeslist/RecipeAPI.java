package com.example.recipeslist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipeAPI {
    @GET("random?apiKey=b7186189567f4838ba69b4796a5e5311")
    Call<RecipeResponse> getRandomRecipe();
}
