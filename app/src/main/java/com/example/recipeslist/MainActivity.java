package com.example.recipeslist;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recipeList;
    RecipeAdapter recipeAdapter;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    NavigationView navigationDrawer;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        //setupNavigation();
        setRecipes();

    }
    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);
       // bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recipeList = findViewById(R.id.rv);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void setupNavigation() {
        //navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setSupportActionBar(toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder()
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
       // NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupWithNavController(navigationDrawer, navController);
        //setBottomNavigationVisibility();
    }
    private void setRecipes()
    {
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Pizza", "Italian, tasty pizza", 555, R.drawable.noimage));
        recipes.add(new Recipe("Pasta", "Italian, tasty pasta", 600, R.drawable.noimage));
        recipes.add(new Recipe("Kebabcheta", "Bulgarian, tasty kebabcheta", 86, R.drawable.noimage));
        recipeAdapter = new RecipeAdapter(recipes);

        recipeAdapter.setOnClickListener(recipe -> {
            Toast.makeText(this, recipe.getDescription(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, RecipeViewActivity.class);
            intent.putExtra("recipe",recipe.toString());
            startActivity(intent);
        });

        recipeList.setAdapter(recipeAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}