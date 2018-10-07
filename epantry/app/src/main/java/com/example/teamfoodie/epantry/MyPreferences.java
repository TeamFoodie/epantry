//NOTE: THIS CLASS IS A THE UPDATED DUPLICATE OF MYPREFERENCES CLASS
//
// TODO:
//2) checkboxes:
//      - calculateThreshold() - this will be called from Se's "Make" button after it decreases
//      * if ticked:
//              - set each ingredient's threshold to "threshold"
//      * if priority == true
//              - ... send alert to phone every "alertDays"... **** get rid of this???
//                                                               just do send alert immediately
//3) add when back button clicked...

package com.example.teamfoodie.epantry;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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
    ArrayList<String> selectedItems = new ArrayList<>();
    ArrayList<String> lowStock = new ArrayList<>();
    DatabaseHandler dbHandler = new DatabaseHandler(this);

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

        //go through pantryList
        for (PantryIngredient ing : pantryList) {
            for(int i = 0; i < pantryList.size(); ++i) {
                if (ing.getIngredientName().contains(selectedItems.get(i))) { //if selectedItem (String) is in pantry
                    ing.setPriority(true);                                    //set priority to true
                }
            }
        }
    }

    public void calculateThreshold() {
        for (PantryIngredient ing : pantryList) {
    //        if(ing.getThreshold <= threshold)
    //        lowStock.add("- "+ing"\n");
    //        sendNotification();
        }
    }

    public void sendNotification(View view) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_logo)
                .setTicker("e-Pantry Ingredients Low Stock")
                .setContentTitle("Low-Stock Ingredients:")
                .setContentText(""+pantryList)
                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText(lowStock.toString()));
                        .bigText("HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE " +
                                 "HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE " +
                                 "HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE " +
                                 "HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE"));
                //.setContentInfo("Info");

        notificationManager.notify(/*notification id*/1, notificationBuilder.build());
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