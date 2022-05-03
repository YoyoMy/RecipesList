package com.example.recipeslist;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipeslist.data.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class RecipeDetailFragment extends Fragment {

    TextView description;
    TextView calories;
    TextView title;
    TextView servings;
    ImageView image;
    private MainActivityViewModel recipeViewModdel;

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

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getChildFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
         /*   @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("recipe");
                // Do something with the result

                //image.setImageResource(obj.getImage());
            }
        });*/

    }

    @Override
    public void onStart() {
        super.onStart();
        //LiveData<Recipe> obj = recipeViewModdel.getSelectedRecipe();//Recipe.toObject(result);
        Recipe recipe = recipeViewModdel.getSelectedRecipe();//(Recipe)obj.getValue();
        title.setText(recipe.getTitle());
        description.setText(recipe.getDescription());
        calories.setText(""+recipe.getCalories());
        servings.setText(""+recipe.getServings());
    }
}