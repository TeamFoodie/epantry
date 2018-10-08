package com.example.teamfoodie.epantry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.epantry.listAdapters.CustomRecipeListAdapter;
import com.example.teamfoodie.models.Recipe;

import java.util.List;
/*
* ViewAllRecipesActivity creates a list of all current recipes in the Database
* Display Recipe image, name, brief discription 
*
 */
public class ViewAllRecipesActivity extends AppCompatActivity {

    private EditText searchTextBox;
    private ListView listView;
    private List<Recipe> recipeList;
    private DatabaseHandler dbHandler;
    private CustomRecipeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_recipes);

        this.searchTextBox = (EditText) findViewById(R.id.searchKeyword);
        this.dbHandler = new DatabaseHandler(this);
        this.recipeList = dbHandler.loadAllRecipes();
        this.adapter = new CustomRecipeListAdapter(this, recipeList);
//        dbHandler.populateRecipeDatabase();

        this.listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        this.searchTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString().trim(), dbHandler);

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Recipe recipe = (Recipe) o;
                Intent intent = new Intent(ViewAllRecipesActivity.this, ViewSelectedRecipeActivity.class);
                intent.putExtra("RECIPE_ID", recipe.getRecipeID());
                startActivity(intent);

            }
        });
    }



}
