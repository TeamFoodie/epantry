package com.example.setavita.epantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.setavita.database.DatabaseHandler;
import com.example.setavita.models.PantryIngredient;

import java.util.ArrayList;

public class ViewPantryActivity extends AppCompatActivity {

    private ArrayList<PantryIngredient> ingredientArray;
    private DatabaseHandler database;
    private SimpleCursorAdapter cursorAdapter;
    private ListView listView;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pantry);
        ingredientArray = new ArrayList<>();
        listView = (ListView) findViewById(R.id.ingredientListView);
        database = new DatabaseHandler(this);

//        cursorAdapter = new SimpleCursorAdapter(getBaseContext(),
//                R.layout.listview_pantry_item, null, new String[] {ingredientArray.get})

//        loadIngredients();
        configureAddButton();

    }

    private void configureAddButton(){
        Button addButton = (Button) findViewById(R.id.addIngredient);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ViewPantryActivity.this, PantryScannerActivity.class));
            }
        });

    }

    public void loadIngredients(){
//        this.ingredientArray = database.loadHandler();

    }


}
