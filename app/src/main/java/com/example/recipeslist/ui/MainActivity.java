package com.example.recipeslist.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.recipeslist.MainActivityViewModel;
import com.example.recipeslist.R;
import com.example.recipeslist.data.RecipeRepository;
import com.example.recipeslist.ui.SigninActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel recipeViewModel;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    NavigationView navigationDrawer;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    FloatingActionButton plus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupNavigation();
        checkIfSignedIn();
        setTheme();
    }

    private void setTheme()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this );
        boolean name = sharedPreferences.getBoolean("theme", true);
        if(!name) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        toolbar = findViewById(R.id.toolbar);
        recipeViewModel = new ViewModelProvider((this)).get(MainActivityViewModel.class);
        plus = findViewById(R.id.floating_action_button);
        plus.show();
        plus.setOnClickListener(v ->{
            this.changeFragment(R.id.addRecipeFragment, null);
            plus.hide();
        });
    }
    private void checkIfSignedIn() {
        recipeViewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                String message = "Welcome " + user.getDisplayName();
                Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT ).show();
            } else
                startLoginActivity();
        });
    }
    private void startLoginActivity() {
        startActivity(new Intent(this, SigninActivity.class));
        finish();
    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setSupportActionBar(toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_all_recipes)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupWithNavController(navigationDrawer, navController);
        setBottomNavigationVisibility();
    }


    private void setBottomNavigationVisibility() {
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            final int id = destination.getId();
            if (id == R.id.nav_all_recipes) {
                bottomNavigationView.setVisibility(View.VISIBLE);
            } else {
                bottomNavigationView.setVisibility(View.GONE);
                plus.hide();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        plus.show();
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();
            plus.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout)
        {
            recipeViewModel.signOut();
            return true;
        }
        plus.hide();
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
    public void signOut(View v) {
        recipeViewModel.signOut();
    }
    public void changeFragment(int fragment, Bundle data)
    {
        plus.hide();
        navController.navigate(fragment, data);
    }


}