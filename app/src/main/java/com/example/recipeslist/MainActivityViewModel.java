package com.example.recipeslist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipeslist.data.Recipe;
import com.example.recipeslist.data.RecipeLiveData;
import com.example.recipeslist.data.RecipeRepository;
import com.example.recipeslist.data.UserRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private List<Recipe> recipes;
    private Recipe selectedRecipe;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public MainActivityViewModel(Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance(app);
        recipeRepository = RecipeRepository.getInstance();
        recipeRepository.getRecipes();
        recipes = new ArrayList<>();
        selectedRecipe = new Recipe("no", "no", 0, 0, 0);
        //recipes.add(new Recipe(2,"Pizza", "Italian, tasty pizza", 555, R.drawable.noimage));
        //recipes.add(new Recipe(3,"Pasta", "Italian, tasty pasta", 600, R.drawable.noimage));
        //recipes.add(new Recipe(4,"Kebabcheta", "Bulgarian, tasty kebabcheta", 86, R.drawable.noimage));
        //recipes.add(recipeRepository.getRecipeDAO());
        recipes = recipeRepository.getAllRecipes();
    }
    public void init() {
        //String userId = userRepository.getCurrentUser().getValue().getUid();
    }
    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }
    public List<Recipe> getRecipes()
    {
        return recipes;
    }
    public void addRecipe(Recipe recipe)
    {
        recipes.add(recipe);
    }

    public Recipe getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(Recipe selectedRecipe) {
        this.selectedRecipe = (selectedRecipe);
    }
    public void signOut() {
        userRepository.signOut();
    }
}
