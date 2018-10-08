package com.example.teamfoodie.epantry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.teamfoodie.R;

import java.util.HashMap;
import java.util.Map;


public class DietaryRequirementActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    CheckBox cbvegetarian, cbvegan, cbhighfiber, cbdairyfree, cbhalal, cblowfat, cblowsalt, cblowcarb, cbglutenfree, cbkosher;

    boolean myBoolVariable = false;
    private static final String cbvegetariankey = "cbvegetarian_key";
    private static final String cbvegankey = "cbvegan_key";
    private static final String cbhighfiberkey = "cbhighfiber_key";
    private static final String cbdairyfreekey = "cbdairyfree_key";
    private static final String cbhalalkey = "cbhalal_key";
    private static final String cblowfatkey = "cblowfat_key";
    private static final String cblowsaltkey = "cblowsalt_key";
    private static final String cblowcarbkey = "cblowcarb_key";
    private static final String cbglutenfreekey = "cbglutenfree_key";
    private static final String cbkosherkey = "cbkosher_key";

    private Button btnupdate;

    SharedPreferences sharedPref = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dietary_requirements);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Dietary Requirements"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
        System.out.println("Loaded dietary req class");

        btnupdate = findViewById(R.id.BTNupdate_preference);

        //dietary
        cbvegetarian = (CheckBox) findViewById((R.id.CBvegetarian));
        cbvegan = (CheckBox) findViewById((R.id.CBvegan));
        cbhighfiber = (CheckBox) findViewById((R.id.CBhigh_fiber));
        cbdairyfree = (CheckBox) findViewById((R.id.CBdairy_free));
        cbhalal = (CheckBox) findViewById((R.id.CBhalal));
        cblowfat = (CheckBox) findViewById((R.id.CBlow_fat));
        cblowsalt = (CheckBox) findViewById((R.id.CBlow_salt));
        cblowcarb = (CheckBox) findViewById((R.id.CBlow_carb));
        cbglutenfree = (CheckBox) findViewById((R.id.CBgluten_free));
        cbkosher = (CheckBox) findViewById((R.id.CBkosher));


        sharedPref = getSharedPreferences("Dietry Requirements", Context.MODE_PRIVATE);

        Map<String, CheckBox> checkboxMap = new HashMap();

        checkboxMap.put(cbvegetariankey, cbvegetarian);
        checkboxMap.put(cbvegankey, cbvegan);
        checkboxMap.put(cbhighfiberkey, cbhighfiber);
        checkboxMap.put(cbdairyfreekey, cbdairyfree);
        checkboxMap.put(cbhalalkey, cbhalal);
        checkboxMap.put(cblowfatkey, cblowfat);
        checkboxMap.put(cblowsaltkey, cblowsalt);
        checkboxMap.put(cblowcarbkey, cblowcarb);
        checkboxMap.put(cbglutenfreekey, cbglutenfree);
        checkboxMap.put(cbkosherkey, cbkosher);

        loadInitialValues(checkboxMap);
        setupCheckedChangeListener(checkboxMap);

        btnupdate.setOnClickListener(this);

    }

    public void loadInitialValues(Map<String, CheckBox> checkBoxMap) {
        for (Map.Entry<String, CheckBox> checkboxEntry : checkBoxMap.entrySet()) {
            Boolean checked = sharedPref.getBoolean(checkboxEntry.getKey(), false);
            checkboxEntry.getValue().setChecked(checked);
        }
    }

        public void setupCheckedChangeListener(Map<String,CheckBox> checkBoxMap){
            for (final Map.Entry<String, CheckBox> checkboxEntry : checkBoxMap.entrySet()) {
                checkboxEntry.getValue().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        final SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean(checkboxEntry.getKey(), isChecked);
                        editor.apply();
                    }
                });
            }
        }


        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.BTNupdate_preference:
                    Toast.makeText(this, "Profile updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DietaryRequirementActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                    break;

            }

        }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        switch (buttonView.getId()){
//            case R.id.CBhalal:
//
//                break;
//            case R.id.CBshelfish:
//
//                break;
//
//        }
//    }

