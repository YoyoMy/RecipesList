package com.example.recipeslist.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipeslist.MainActivityViewModel;
import com.example.recipeslist.R;
import com.example.recipeslist.data.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class RecipeDetailFragment extends Fragment {

    TextView description;
    TextView calories;
    TextView title;
    TextView servings;
    ImageView image;
    private MainActivityViewModel recipeViewModdel;
    Recipe recipe;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.app_bar_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favourite:  {
                recipeViewModdel.addFavouriteRecipe(recipe);
                Toast.makeText(requireActivity().getApplicationContext(), "Recipe added to favourite", Toast.LENGTH_SHORT ).show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item = menu.findItem(R.id.search);
        item.setVisible(false);
        item = menu.findItem(R.id.nav_settings);
        item.setVisible(false);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recipeViewModdel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        description = view.findViewById(R.id.recipeViewDescription);
        calories = view.findViewById(R.id.recipeViewCals);
        title = view.findViewById(R.id.recipeViewTitle);
        servings = view.findViewById(R.id.recipeViewServings);
        image = view.findViewById(R.id.recipeViewImage);

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        //LiveData<Recipe> obj = recipeViewModdel.getSelectedRecipe();//Recipe.toObject(result);
        recipe = recipeViewModdel.getSelectedRecipe();//(Recipe)obj.getValue();
        title.setText(recipe.getTitle());
        description.setText(recipe.getDescription());
        calories.setText(""+recipe.getCalories());
        servings.setText(""+recipe.getServings());
        if(recipe.getImage() == 0)
            image.setImageResource(R.drawable.noimage);
        else image.setImageResource(recipe.getImage());
    }
}