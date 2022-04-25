package com.example.recipeslist;

import android.app.FragmentManager;
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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipesListViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipesListViewFragment extends Fragment {

    private RecyclerView recipeList;
    private RecipeAdapter recipeAdapter;
    private MainActivityViewModel recipeViewModel;
    private FragmentManager fragmentManager;

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
        recipeList = view.findViewById(R.id.rv);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        ArrayList<Recipe> recipes = (ArrayList<Recipe>) recipeViewModel.getRecipes();
        recipeAdapter = new RecipeAdapter(recipes);
        recipeAdapter.setOnClickListener(recipe -> {
            Bundle bundle = new Bundle();
            bundle.putString("recipe", recipe.toString());
            ((MainActivity)requireActivity()).changeFragment(R.id.recipeDetailFragment, bundle);
            recipeViewModel.setSelectedRecipe(recipe);
            //getParentFragmentManager().setFragmentResult("requestKey", bundle);
            // requireActivity().getSupportFragmentManager().saveFragmentInstanceState(this);
           /* getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, RecipeDetailFragment.class, bundle)
                    .hide(this)
                    .addToBackStack("recipe")
                    .setReorderingAllowed(true)
                    .commit();*/
        });
        recipeList.setAdapter(recipeAdapter);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

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