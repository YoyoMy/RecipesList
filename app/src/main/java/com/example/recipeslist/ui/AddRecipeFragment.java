package com.example.recipeslist.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipeslist.MainActivityViewModel;
import com.example.recipeslist.R;
import com.example.recipeslist.data.Recipe;
import com.example.recipeslist.data.RecipeRepository;


public class AddRecipeFragment extends Fragment {

    EditText description;
    EditText calories;
    EditText title;
    EditText servings;
    Button save;
    private MainActivityViewModel recipeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_recipe, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recipeViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        setViews(view);
        save.setOnClickListener(v->
        {
            Recipe recipe = new Recipe(title.getText().toString(), description.getText().toString(),
                    Integer.parseInt(calories.getText().toString()),R.drawable.noimage,
                    Integer.parseInt(servings.getText().toString()));
            recipeViewModel.addRecipe(recipe);
            clearText();
            Toast.makeText(requireActivity().getApplicationContext(), "Recipe saved", Toast.LENGTH_SHORT ).show();
        });

    }
    private void setViews(View view)
    {
        description = view.findViewById(R.id.recipeAddDescription);
        calories = view.findViewById(R.id.recipeAddCalories);
        title = view.findViewById(R.id.recipeAddName);
        servings = view.findViewById(R.id.recipeAddServings);
        save = view.findViewById(R.id.save_recipe);
    }
    private void clearText()
    {
        description.setText("");
        calories.setText("");
        title.setText("");
        servings.setText("");
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}