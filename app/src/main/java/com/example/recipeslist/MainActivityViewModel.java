package com.example.recipeslist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.recipeslist.data.Recipe;
import com.example.recipeslist.data.RecipeRepository;
import com.example.recipeslist.data.UserRepository;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Recipe>> recipesLiveData;
    private Recipe selectedRecipe;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public MainActivityViewModel(Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance(app);
        recipeRepository = RecipeRepository.getInstance();
        recipesLiveData = new MutableLiveData<>();
        recipesLiveData = recipeRepository.getAllRecipes();
        RecipeRepository.getInstance().randomRecipe();
       // init();
    }
  /*  public void init() {
        //String userId = userRepository.getCurrentUser().getValue().getUid();
        recipesLiveData.setValue(recipesList);
    }*/
    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }
   // public List<Recipe> getRecipes()
   /* {
        return recipesList;
    }*/
    public void addRecipe(Recipe recipe)
    {
        RecipeRepository.getInstance().saveRecipe(recipe);
    }
    public void addFavouriteRecipe(Recipe recipe)
    {
        RecipeRepository.getInstance().addFavouriteRecipe(recipe, getCurrentUser().getValue().getEmail());
    }

    public FirebaseRecyclerOptions<Recipe> getAllRecipes()
    {
        return  new FirebaseRecyclerOptions.Builder<Recipe>()
                .setQuery(recipeRepository.getMyRef().child("recipes"), Recipe.class)
                .build();
    }

    public FirebaseRecyclerOptions<Recipe> getMyRecipes()
    {
        String user = getCurrentUser().getValue().getEmail();
        int index = user.indexOf('@');
        user = user.substring(0, index);
        return  new FirebaseRecyclerOptions.Builder<Recipe>()
                .setQuery(recipeRepository.getMyRef().child("users").child(user), Recipe.class)
                .build();
    }

    public MutableLiveData<ArrayList<Recipe>> getRecipesLiveData()
    {
        return recipesLiveData;
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

    public Recipe getRandomRecipe(){
        Recipe recipe = RecipeRepository.getInstance().getRandomRecipe().getValue();
        return recipe;
    }
}
