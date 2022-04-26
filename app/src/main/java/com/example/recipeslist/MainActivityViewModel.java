package com.example.recipeslist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private List<Recipe> recipes;
    private MutableLiveData<Recipe> selectedRecipe;
    private final UserRepository userRepository;

    public MainActivityViewModel(Application app)
    {
        super(app);
        userRepository = UserRepository.getInstance(app);
        recipes = new ArrayList<>();
        selectedRecipe = new Recipe("no", "no", 0, 0);
        recipes.add(new Recipe("Pizza", "Italian, tasty pizza", 555, R.drawable.noimage));
        recipes.add(new Recipe("Pasta", "Italian, tasty pasta", 600, R.drawable.noimage));
        recipes.add(new Recipe("Kebabcheta", "Bulgarian, tasty kebabcheta", 86, R.drawable.noimage));
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

    public MutableLiveData<Recipe> getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(Recipe selectedRecipe) {
        this.selectedRecipe.setValue(selectedRecipe);
    }
    public void signOut() {
        userRepository.signOut();
    }
}
