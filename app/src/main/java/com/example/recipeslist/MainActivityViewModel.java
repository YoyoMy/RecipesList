package com.example.recipeslist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeslist.data.Recipe;
import com.example.recipeslist.data.RecipeLiveData;
import com.example.recipeslist.data.RecipeRepository;
import com.example.recipeslist.data.UserRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Recipe>> recipesLiveData;
    private ArrayList<Recipe> recipesList;
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
       // init();
    }
    public void init() {
        //String userId = userRepository.getCurrentUser().getValue().getUid();
        recipesLiveData.setValue(recipesList);
    }
    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }
    public List<Recipe> getRecipes()
    {
        return recipesList;
    }
    public void addRecipe(Recipe recipe)
    {
        recipesList.add(recipe);
        recipesLiveData.setValue(recipesList);
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
}
