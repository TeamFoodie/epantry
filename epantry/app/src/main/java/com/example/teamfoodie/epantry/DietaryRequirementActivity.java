package com.example.teamfoodie.epantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.DietaryRequirement;

import java.util.ArrayList;
import java.util.List;


public class DietaryRequirementActivity extends AppCompatActivity implements View.OnClickListener {//}, CompoundButton.OnCheckedChangeListener {

    private CheckBox cbvegetarian;
    private CheckBox cbvegan;
    private CheckBox cbhighfiber;
    private CheckBox cbdairyfree;
    private CheckBox cbhalal;
    private CheckBox cblowfat;
    private CheckBox cblowsalt;
    private CheckBox cblowcarb;
    private CheckBox cbglutenfree;
    private CheckBox cbkosher;
    private List<CheckBox> checkBoxList;
    private int USER_ID = 1;
    private DatabaseHandler dbHandler;


    private Button btnupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dietary_requirements);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Dietary Requirements");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        System.out.println("Loaded dietary req class");

        this.dbHandler = new DatabaseHandler(this);
        btnupdate = findViewById(R.id.BTNupdate_preference);

        //dietary
        checkBoxList = new ArrayList<>();
        checkBoxList.add(cbvegetarian = (CheckBox) findViewById((R.id.CBvegetarian)));
        checkBoxList.add(cbvegan = (CheckBox) findViewById((R.id.CBvegan)));
        checkBoxList.add(cbhighfiber = (CheckBox) findViewById((R.id.CBhigh_fiber)));
        checkBoxList.add(cbdairyfree = (CheckBox) findViewById((R.id.CBdairy_free)));
        checkBoxList.add(cbhalal = (CheckBox) findViewById((R.id.CBhalal)));
        checkBoxList.add(cblowfat = (CheckBox) findViewById((R.id.CBlow_fat)));
        checkBoxList.add(cblowsalt = (CheckBox) findViewById((R.id.CBlow_salt)));
        checkBoxList.add(cblowcarb = (CheckBox) findViewById((R.id.CBlow_carb)));
        checkBoxList.add(cbglutenfree = (CheckBox) findViewById((R.id.CBgluten_free)));
        checkBoxList.add(cbkosher = (CheckBox) findViewById((R.id.CBkosher)));


        btnupdate.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        List<DietaryRequirement> requirements = new ArrayList<>();
        DietaryRequirement diet;
        for(int i = 0; i < checkBoxList.size(); i++){
            if(checkBoxList.get(i).isChecked()){
                diet = new DietaryRequirement(USER_ID, checkBoxList.get(i).getText().toString());
                dbHandler.addHandle(diet);
//                requirements.add(new DietaryRequirement(USER_ID, checkBoxList.get(i).getText().toString()));
            }
        }
        Toast.makeText(this, "Profile updated", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DietaryRequirementActivity.this, LandingPageActivity.class);
        startActivity(intent);
    }

}

