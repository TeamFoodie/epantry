package com.example.teamfoodie.epantry;

import android.arch.persistence.room.Database;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ViewAllRecipesActivity extends AppCompatActivity {

    private ListView listView;
    private List<Recipe> recipeList;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_recipes);
        this.dbHandler = new DatabaseHandler(this);
//        dbHandler.populateRecipeDatabase();


        List<Recipe> image_details = dbHandler.loadAllRecipes();




        this.listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomRecipeListAdapter(this, image_details));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Recipe recipe = (Recipe) o;
                Toast.makeText(ViewAllRecipesActivity.this, "Selected :" + " " + recipe, Toast.LENGTH_LONG).show();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(recipe.getURL()));
                startActivity(i);

            }
        });
    }



}
