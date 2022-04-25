package com.example.recipeslist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private List<Recipe> recipes;
    private MutableLiveData<Recipe> selectedRecipe;

    public MainActivityViewModel()
    {
        recipes = new ArrayList<>();
        selectedRecipe = new Recipe("no", "no", 0, 0);
        recipes.add(new Recipe("Pizza", "Italian, tasty pizza", 555, R.drawable.noimage));
        recipes.add(new Recipe("Pasta", "Italian, tasty pasta", 600, R.drawable.noimage));
        recipes.add(new Recipe("Kebabcheta", "Bulgarian, tasty kebabcheta", 86, R.drawable.noimage));
    }
    public List<Recipe> getRecipes()
    {
        return recipes;
    }
    public void addRecipe(Recipe recipe)
    {
        recipes.add(recipe);
    }

    public MutableLiveData<Recipe> getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(Recipe selectedRecipe) {
        this.selectedRecipe.setValue(selectedRecipe);
    }
}
