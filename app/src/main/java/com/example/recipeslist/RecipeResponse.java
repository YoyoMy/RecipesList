package com.example.recipeslist;

public class RecipeResponse {
    private String title;
    private String description;
    private int cals;
    private int image;

    public Recipe getRecipe()
    {
        return new Recipe(title, description, cals, image);
    }
}
