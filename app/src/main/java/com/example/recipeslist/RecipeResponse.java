package com.example.recipeslist;

import com.example.recipeslist.data.Recipe;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RecipeResponse {
    @SerializedName("0")
    String recipe;
  /* @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("instructions")
    private String instructions;
    @SerializedName("servings")
    private int servings;
    //private int image;
    //private int cals;
*/
   public Recipe getRandomRecipe()
    {
        return null;//new Recipe(title, instructions, 0, 0, servings);
    }
}
