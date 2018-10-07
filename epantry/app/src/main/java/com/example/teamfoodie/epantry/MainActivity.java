package com.example.teamfoodie.epantry;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.view.View;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.PantryIngredient;

public class MainActivity extends AppCompatActivity {

    int currentUSER_ID;
    List<PantryIngredient> pantryList = new ArrayList<>();
    DatabaseHandler dbHandler = new DatabaseHandler(this);
    ArrayList<String> selectedItems;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //create an ArrayList object to store selected items
        selectedItems=new ArrayList<String>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onStart(){
        super.onStart();


        pantryList = dbHandler.loadAllPantryIngredients(currentUSER_ID);
        for(int i = 0; i < pantryList.size(); ++i) {
            Log.i("MyPreferences:",""+pantryList.get(i).getIngredientName());
        }
        Log.i("MyPreferences","after load ing loop");



        //ListView listView = (ListView) findViewById(R.id.viewAllPantry);
        ListView listView=(ListView) findViewById(R.id.checkable_list);


        List<String> pantryNames = new ArrayList<>();
        for(int i = 0; i < pantryList.size(); ++i) {
            pantryNames.add(pantryList.get(i).getIngredientName());
        }

        String[] pantryOthers = new String[pantryList.size()];
        for(int i = 0; i < pantryList.size(); ++i) {
            pantryOthers[i] = pantryList.get(i).getIngredientName();
            Log.i("My Preferences:","PantryOthers: "+pantryList.get(i).getIngredientName());
        }

        //set multiple selection mode
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        String[] items={"English","Chinese","French","German","Italian","Khmer"};


        //supply data itmes to ListView
     //   ArrayAdapter<String> aa=new ArrayAdapter<String>(this,R.layout.checkable_list_layout,R.id.txt_title,items);
        //listView.setAdapter(new PreferencesAdapter(this, pantryNames, dbHandler));
        ArrayAdapter<String> bb=new ArrayAdapter<String>(this,R.layout.checkable_list_layout,R.id.txt_title,pantryOthers);
        listView.setAdapter(bb);

        //set OnItemClickListener..comment this out for it to work
        listView.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                String selectedItem = ((TextView) view).getText().toString();
                if(selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                else
                    selectedItems.add(selectedItem); //add selected item to the list of selected items
            }

        });
    }

    public void showSelectedItems(View view){
        String selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
    }
}