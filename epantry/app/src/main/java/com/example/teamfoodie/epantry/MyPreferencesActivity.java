//TODO:
//1) get Title for each ingredient in pantry
//          - for String : arrayList { new Checkbox(); }
//2) checkboxes:
//      * if ticked:
//              - calculateThreshold()
//              - set each ingredient's threshold to "threshold"
//              - boolean priority = true
//      * if priority == true
//              - ... send alert to phone every "alertDays"
//3) add when back button clicked...

package com.example.teamfoodie.epantry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.PantryIngredient;

import java.util.ArrayList;
import java.util.List;

import com.example.teamfoodie.R;
public class MyPreferencesActivity extends AppCompatActivity{

    int currentUSER_ID;
    List<PantryIngredient> pantryList = new ArrayList<>();
    DatabaseHandler dbHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_preferences);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("My Preferences"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
        System.out.println("Loaded class");

        //
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                System.out.println("Bundle extra was NULL user");
            } else {
                currentUSER_ID = extras.getInt("USER_ID");
            }
        } else {
            currentUSER_ID = (Integer) savedInstanceState.getSerializable("USER_ID");
            System.out.println("savedInstance was NULL");
        }

        //PantryIngredient ing = new PantryIngredient();
       PantryIngredient ing = new PantryIngredient("1", "Cereal", 3, 3, "g", "Poultry", 0);
        this.dbHandler = new DatabaseHandler(this);
        ing = dbHandler.populatePantry();
        //dbHandler.addHandle(ing);

        pantryList = dbHandler.loadAllPantryIngredients(currentUSER_ID);
        for(int i = 0; i < pantryList.size(); ++i) {
            Log.i("MyPreferences:",""+pantryList.get(i));
        }
        Log.i("MyPreferences","after load ing loop");

       //ListView listView = (ListView) findViewById(R.id.viewAllPantry);
      //  listView.setAdapter(new CustomIngredientAdapter(this, pantryList, dbHandler));
    }
}
