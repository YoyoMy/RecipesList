package com.example.recipeslist.data;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class RecipeRepository {
    private RecipeLiveData recipe;
    private static RecipeRepository instance;
    private MutableLiveData<ArrayList<Recipe>> recipes;
    private FirebaseDatabase database;
    ChildEventListener childEventListener;
    Query query;
    DatabaseReference myRef;

    private RecipeRepository() {
        database = FirebaseDatabase.getInstance("https://recipeslist-6bcdb-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference();
        /*query = FirebaseDatabase.getInstance()
                .getReference()
                .child("recipes")
                .limitToLast(50);*/
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        };
       /* query.addChildEventListener(childEventListener);*/
        /*DatabaseReference myRef = database.getReference();
        recipes = new MutableLiveData<>();
        myRef.addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ArrayList<Recipe> recipes1 = new ArrayList<>();
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    recipes1.add(shot.getValue(Recipe.class));
                }
                recipes.setValue(recipes1);
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //recipe = dataSnapshot.getValue(Recipe.class);
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //recipeDAO = dataSnapshot.getValue(Recipe.class);
            }

            public void onCancelled(DatabaseError databaseError) {}

        });*/

       // myRef.child("recipes").push().setValue(new Recipe("Kebabcheta", "Bulgarian, tasty kebabcheta", 86, R.drawable.noimage));

    }

        public static RecipeRepository getInstance() {
        if(instance == null)
            instance = new RecipeRepository();
        return instance;
    }

    public void saveRecipe(Recipe recipe)
    {
        myRef.child("recipes").push().setValue(recipe);
    }
    public DatabaseReference getMyRef() {
        return myRef;
    }

    public RecipeLiveData getRecipe() {
        return recipe;
    }

    public MutableLiveData<ArrayList<Recipe>> getAllRecipes()
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
