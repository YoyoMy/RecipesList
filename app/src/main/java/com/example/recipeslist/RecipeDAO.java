package com.example.recipeslist;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {
    private MutableLiveData<List<Recipe>> allRecipes;
    private static  RecipeDAO instance;

    private RecipeDAO()
    {
        allRecipes = new MutableLiveData<>();
        List<Recipe> newList = new ArrayList<>();
        allRecipes.setValue(newList);

    }

}
