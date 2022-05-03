package com.example.recipeslist.data;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecipeRepository {
    private RecipeLiveData recipe;
    private static RecipeRepository instance;
    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    private RecipeRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://recipeslist-6bcdb-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference();
        myRef.addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    recipes.add(shot.getValue(Recipe.class));
                }
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //recipe = dataSnapshot.getValue(Recipe.class);
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //recipeDAO = dataSnapshot.getValue(Recipe.class);
            }

            public void onCancelled(DatabaseError databaseError) {}

        });

       // myRef.child("recipes").push().setValue(new Recipe("Kebabcheta", "Bulgarian, tasty kebabcheta", 86, R.drawable.noimage));

    }

        public static RecipeRepository getInstance() {
        if(instance == null)
            instance = new RecipeRepository();
        return instance;
    }

    public RecipeLiveData getRecipe() {
        return recipe;
    }

    public ArrayList<Recipe> getAllRecipes()
    {
        return recipes;
    }
    public void getRecipes() {
       /* RecipeAPI recipeApi = ServiceGenerator.getRecipeApi();
        Call<RecipeResponse> call = recipeApi.getRecipes();
        call.enqueue(new Callback<RecipeResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()) {
                    recipeDAO = response.body().getRecipe();
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });*/

    }
}
