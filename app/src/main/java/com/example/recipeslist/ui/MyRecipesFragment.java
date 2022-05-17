package com.example.recipeslist.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeslist.MainActivityViewModel;
import com.example.recipeslist.R;
import com.example.recipeslist.RecipeAdapter;
import com.example.recipeslist.data.Recipe;
import com.example.recipeslist.data.RecipeRepository;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class MyRecipesFragment extends Fragment {

    private RecyclerView recipeList;
    private RecipeAdapter recipeAdapter;
    private MainActivityViewModel recipeViewModel;
    RecipeRepository recipeRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipeViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recipeRepository = RecipeRepository.getInstance();
        recipeList = view.findViewById(R.id.mrv);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_recipes, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        recipeList.setLayoutManager(new LinearLayoutManager(requireActivity()));

        FirebaseRecyclerOptions<Recipe> options = recipeViewModel.getAllRecipes();
        recipeAdapter = new RecipeAdapter(options);
        recipeAdapter.setOnClickListener(recipe -> {
            Bundle bundle = new Bundle();
            bundle.putString("recipe", recipe.toString());
            ((MainActivity)requireActivity()).changeFragment(R.id.recipeDetailFragment, bundle);
            recipeViewModel.setSelectedRecipe(recipe);
        });
        recipeList.setAdapter(recipeAdapter);
    }
}