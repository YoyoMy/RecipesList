package com.example.recipeslist;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class RecipeRepository {
    private RecipeDAO recipeDAO;
    private static RecipeRepository instance;

    private RecipeRepository()
    {

    }

    public static RecipeRepository getInstance() {
        if(instance == null)
            instance = new RecipeRepository();
        return instance;
    }

    public MutableLiveData<List<Recipe>> getAllRecipes()
    {
        return null;
    }
}
