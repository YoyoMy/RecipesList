package com.example.recipeslist;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class RecipeViewActivity extends AppCompatActivity {

    RecyclerView recipeList;
    RecipeAdapter recipeAdapter;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    NavigationView navigationDrawer;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    TextView description;
    TextView calories;
    TextView title;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view_activity);

        description = findViewById(R.id.recipeViewDescription);
        calories = findViewById(R.id.recipeViewCals);
        title = findViewById(R.id.recipeViewTitle);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       Bundle bundle = getIntent().getExtras();

       if (bundle != null && bundle.containsKey("recipe")) {
           String recipeStr = bundle.getString("recipe");
           Recipe recipeObj = Recipe.toObject(recipeStr);
           title.setText(recipeObj.getTitle());
           description.setText(recipeObj.getDescription());
           calories.setText(""+recipeObj.getCals());
       }
    }
}
