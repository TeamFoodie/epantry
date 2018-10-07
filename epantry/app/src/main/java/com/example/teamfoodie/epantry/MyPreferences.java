//NOTE: THIS CLASS IS A THE UPDATED DUPLICATE OF MYPREFERENCES CLASS
//
// TODO:
//2) checkboxes:
//      * if ticked:
//              - calculateThreshold()
//              - set each ingredient's threshold to "threshold"
//              - boolean priority = true
//      * if priority == true
//              - ... send alert to phone every "alertDays"... **** get rid of this???
//                                                               just do send alert immediately
//3) add when back button clicked...

package com.example.teamfoodie.epantry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.PantryIngredient;

import java.util.ArrayList;
import java.util.List;

public class MyPreferences extends AppCompatActivity {

    int currentUSER_ID;
    List<PantryIngredient> pantryList = new ArrayList<>();
    DatabaseHandler dbHandler = new DatabaseHandler(this);
    ArrayList<String> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_preferences_new);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("My Preferences"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
        System.out.println("Loaded class");

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

        selectedItems = new ArrayList<String>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onStart() {
        super.onStart();


        pantryList = dbHandler.loadAllPantryIngredients(currentUSER_ID);
        for (int i = 0; i < pantryList.size(); ++i) {
            Log.i("MyPreferences:", "" + pantryList.get(i).getIngredientName());
        }
        Log.i("MyPreferences", "after load ing loop");


        //ListView listView = (ListView) findViewById(R.id.viewAllPantry);
        ListView listView = (ListView) findViewById(R.id.checkable_list);


        List<String> pantryNames = new ArrayList<>();
        for (int i = 0; i < pantryList.size(); ++i) {
            pantryNames.add(pantryList.get(i).getIngredientName());
        }

        String[] pantryOthers = new String[pantryList.size()];
        for (int i = 0; i < pantryList.size(); ++i) {
            pantryOthers[i] = pantryList.get(i).getIngredientName();
            Log.i("My Preferences:", "PantryOthers: " + pantryList.get(i).getIngredientName());
        }

        //set multiple selection mode
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        String[] items = {"English", "Chinese", "French", "German", "Italian", "Khmer"};


        //supply data itmes to ListView
        //   ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.checkable_list_layout,R.id.txt_title,items);
        //listView.setAdapter(new PreferencesAdapter(this, pantryNames, dbHandler));
        ArrayAdapter<String> bb = new ArrayAdapter<String>(this, R.layout.checkable_list_layout, R.id.txt_title, pantryOthers);
        listView.setAdapter(bb);

        //set OnItemClickListener..comment this out for it to work
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                String selectedItem = ((TextView) view).getText().toString();
                if (selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                else
                    selectedItems.add(selectedItem); //add selected item to the list of selected items
            }

        });
    }
    //remember to change onClick in xml
    public void setNotification(View view) {
        String selItems = "";
        for (String item : selectedItems) {
            if (selItems == "") {
                selItems = item;
            } else {
                selItems += "/" + item;
            }
            Toast.makeText(this, "Notification set for: "+selItems,
                    Toast.LENGTH_LONG).show();
        }

        for (PantryIngredient ing : pantryList) {
            ing.setPriority(true);
        }
    }

    public void showSelectedItems(View view) {
        String selItems = "";
        for (String item : selectedItems) {
            if (selItems == "") {
                selItems = item;
            } else {
                selItems += "/" + item;
            }
            Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
        }
    }
}