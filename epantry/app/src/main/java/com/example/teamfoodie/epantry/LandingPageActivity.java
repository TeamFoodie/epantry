package com.example.teamfoodie.epantry;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * The LandingPageActivity class contains the navigation to other activity
 * pages such as the Pantry and Shopping List.
 */
public class LandingPageActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private ImageView restock;
    private ImageView search;
    private ImageView uploadRecipe;
    private ImageView shoppingList;
    private TextView tvUsername;
    private TextView tvEmail;
    private String username;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        restock = (ImageView) findViewById(R.id.myPantry);
        restock.setOnClickListener(this);
        search = (ImageView) findViewById(R.id.find_recipe);
        search.setOnClickListener(this);
        uploadRecipe = (ImageView) findViewById(R.id.newRecipe);
        uploadRecipe.setOnClickListener(this);
        shoppingList = (ImageView) findViewById(R.id.shop_list);
        shoppingList.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Opens up the menu of the navigation drawer.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        Log.i("Home", "Loading profile");

        tvUsername = (TextView) findViewById(R.id.navUsername); //create a TextView as matching type in xml
        username = getIntent().getStringExtra("Username");
        tvUsername.setText(username);

        tvEmail = (TextView) findViewById(R.id.navUserEmail); //create a TextView as matching type in xml
        email = getIntent().getStringExtra("Email");
        tvEmail.setText(email);

        return true;
    }

    /**
     * Handles action bar item clicks for settings.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handles navigation drawer item clicks. Links to another activity.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        // Handle the camera action
        } else if (id == R.id.nav_preferences) {
            Intent intent = new Intent(LandingPageActivity.this, MyPreferencesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_dietary) {
            Intent intent = new Intent(LandingPageActivity.this, DietaryRequirementsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) { //at the moment goes to RecyclerView screen
            Intent intent = new Intent(LandingPageActivity.this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.myPantry) {
            Intent intent = new Intent(LandingPageActivity.this, ViewPantryActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.find_recipe){
            Intent intent = new Intent(LandingPageActivity.this, ViewAllRecipesActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.newRecipe){
            Intent intent = new Intent(LandingPageActivity.this, UserRecipeActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.shop_list){
            Intent intent = new Intent(LandingPageActivity.this, ShoppingListActivity.class);
            startActivity(intent);
        }
    }
}
