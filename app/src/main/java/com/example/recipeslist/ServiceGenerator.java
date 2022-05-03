package com.example.recipeslist;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static RecipeAPI pokemonApi;
    private final static String apiKey = "b7186189567f4838ba69b4796a5e5311";

    public static RecipeAPI getRecipeApi() {
        if (pokemonApi == null) {
            pokemonApi = new Retrofit.Builder()
                    .baseUrl("https://api.spoonacular.com/recipes/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RecipeAPI.class);
        }
        return pokemonApi;
    }

    public static String getApiKey() {
        return apiKey;
    }
}

