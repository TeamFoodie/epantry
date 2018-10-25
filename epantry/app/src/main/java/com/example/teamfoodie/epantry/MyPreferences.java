// TODO:
//2) checkboxes:
//      - calculateThreshold() - this will be called from Se's "Make" button after it decreases
//      * if ticked:
//              - set boolean priority to true
//              - set each ingredient's threshold to "threshold" to save state??????
//      - get it to save state - by loading all that have priority == true
//3) add when back button clicked...

package com.example.teamfoodie.epantry;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class MyPreferences extends AppCompatActivity implements View.OnClickListener {

    private int currentUSER_ID;
    private EditText spices;
    private EditText poultry;
    private EditText staple;
    private EditText vegetables;
    private EditText meats;
    private EditText sauces;
    private EditText oils;
    private Button saveBtn;
    private List<Integer> thresholdList;
    private List<EditText> textBoxes;
    private DatabaseHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_preferences_new);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("My Preferences"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
        System.out.println("Loaded class");

        Bundle extras = getIntent().getExtras();
        currentUSER_ID = extras.getInt("USER_ID");
        this.dbHandler = new DatabaseHandler(this);


        this.spices = (EditText) findViewById(R.id.spicesThreshold);
        this.poultry = (EditText) findViewById(R.id.poultryThreshold);
        this.staple = (EditText) findViewById(R.id.staplesThreshold);
        this.vegetables = (EditText) findViewById(R.id.vegetablesThreshold);
        this.meats = (EditText) findViewById(R.id.meatsThreshold);
        this.sauces = (EditText) findViewById(R.id.saucesThreshold);
        this.oils = (EditText) findViewById(R.id.oilsThreshold);
        this.saveBtn = (Button) findViewById(R.id.savePreferenceBtn);

        this.textBoxes = new ArrayList<>();
        this.textBoxes.add(spices);
        this.textBoxes.add(poultry);
        this.textBoxes.add(staple);
        this.textBoxes.add(vegetables);
        this.textBoxes.add(meats);
        this.textBoxes.add(sauces);
        this.textBoxes.add(oils);
        saveBtn.setOnClickListener(this);


        List<Integer> storedThresholds = (List<Integer>) dbHandler.findHandle(String.valueOf(currentUSER_ID), "Thresholds");
        if(storedThresholds != null){
            for (int i = 0; i < textBoxes.size(); i++){
                this.textBoxes.get(i).setText(String.valueOf(storedThresholds.get(i + 1)));
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
            this.thresholdList = new ArrayList<>();

            this.thresholdList.add(currentUSER_ID);

            for (int i = 0; i < textBoxes.size(); i++) {
                    try{
                        if(textBoxes.get(i).getText().toString().isEmpty()) {
                            thresholdList.add(0);
                        }else
                        this.thresholdList.add(Integer.parseInt(textBoxes.get(i).getText().toString()));
                    }catch (NumberFormatException e){
                        Toast noMatch = Toast.makeText(MyPreferences.this, "Please ensure you enter WHOLE NUMBERS only!", Toast.LENGTH_SHORT);
                        noMatch.show();
                    }



            }

            this.thresholdList.add(50);


            if(thresholdList.size() == 9){
                dbHandler.addHandle(thresholdList);
                finish();
            }


    }

}