package com.example.recipeslist;

import com.example.recipeslist.data.Recipe;
import com.google.gson.annotations.SerializedName;

public class RecipeResponse {
    private int id;
    private String title;
    @SerializedName("instructions")
    private String description;
    private int servings;
    private int image;
    private int cals;

    public Recipe getRecipe()
    {
        return new Recipe(title, description, cals, image, servings);
    }
}
