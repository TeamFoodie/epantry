package com.example.teamfoodie.epantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.epantry.listAdapters.CustomRecipeListAdapter;
import com.example.teamfoodie.models.PantryIngredient;
import com.example.teamfoodie.models.Recipe;

import java.util.List;

/*
 * ViewAllRecipesActivity creates a list of all current recipes in the Database
 * Display Recipe image, name, brief description
 *
 */
public class ViewAllRecipesActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText searchTextBox;
    private ImageView filter;
    private ListView listView;
    private List<Recipe> recipeList;
    private List<PantryIngredient> pantryList;
    private DatabaseHandler dbHandler;
    private CustomRecipeListAdapter adapter;
    private int USER_ID;
    private boolean isFiltered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_recipes);
        Bundle extras = getIntent().getExtras();
        USER_ID = extras.getInt("USER_ID");
        this.isFiltered = false;
        this.searchTextBox = (EditText) findViewById(R.id.searchKeyword);
        this.filter = (ImageView) findViewById(R.id.filterRecipes);
        this.dbHandler = new DatabaseHandler(this);

        this.pantryList = dbHandler.loadAllPantryIngredients(USER_ID);
        this.recipeList = dbHandler.loadAllRecipes();
        this.listView = (ListView) findViewById(R.id.listView);

        this.adapter = new CustomRecipeListAdapter(this, recipeList);
        listView.setAdapter(adapter);
//        dbHandler.populateRecipeDatabase();


        /**All three items have different listeners thus implemented anonymously.*/


        filter.setOnClickListener(this);
        this.searchTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString().trim(), dbHandler);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object obj = listView.getItemAtPosition(position);
                Recipe recipe = (Recipe) obj;
                Intent intent = new Intent(ViewAllRecipesActivity.this, ViewSelectedRecipeActivity.class);
                intent.putExtra("RECIPE_ID", recipe.getRecipeID());

                intent.putExtra("USER_ID", USER_ID);
                startActivity(intent);

            }
        });
    }


    @Override
    public void onClick(View v) {

        if(isFiltered){
            isFiltered = false;
            System.out.println("filter set true");
            filter.setImageResource(R.drawable.ic_search_false);
            listView.invalidateViews();
            this.adapter = new CustomRecipeListAdapter(this, recipeList);
            listView.setAdapter(adapter);







        }else{
            isFiltered = true;
            System.out.println("filter set false");
            filter.setImageResource(R.drawable.ic_search_true);


            List<Recipe> matchingRecipes = RecipeFiltering.getRecipeList(recipeList, pantryList, dbHandler);
            listView.invalidateViews();
            this.adapter = new CustomRecipeListAdapter(this, matchingRecipes);
            listView.setAdapter(adapter);

        }


    }
}
