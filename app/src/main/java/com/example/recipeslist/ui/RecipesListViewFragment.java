package com.example.recipeslist.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.recipeslist.MainActivityViewModel;
import com.example.recipeslist.R;
import com.example.recipeslist.RecipeAdapter;
import com.example.recipeslist.data.Recipe;
import com.example.recipeslist.data.RecipeRepository;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import java.util.ArrayList;


public class RecipesListViewFragment extends Fragment {

    private RecyclerView recipeList;
    private RecipeAdapter recipeAdapter;
    private MainActivityViewModel recipeViewModel;
    RecipeRepository recipeRepository;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        recipeViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes_list_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recipeRepository = RecipeRepository.getInstance();
        recipeList = view.findViewById(R.id.rv);
        //recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        FirebaseRecyclerOptions<Recipe> options
                = new FirebaseRecyclerOptions.Builder<Recipe>()
                .setQuery(recipeRepository.getMyRef().child("recipes"), Recipe.class)
                .build();
        //ArrayList<Recipe> recipes = (ArrayList<Recipe>) recipeViewModel.getRecipes();
        recipeAdapter = new RecipeAdapter(options);
        recipeAdapter.setOnClickListener(recipe -> {
            Bundle bundle = new Bundle();
            bundle.putString("recipe", recipe.toString());
            ((MainActivity)requireActivity()).changeFragment(R.id.recipeDetailFragment, bundle);
            recipeViewModel.setSelectedRecipe(recipe);
        });
        recipeList.setAdapter(recipeAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        recipeAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        recipeAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}