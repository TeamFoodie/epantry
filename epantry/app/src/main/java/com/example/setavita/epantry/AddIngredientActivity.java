package com.example.setavita.epantry;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.setavita.database.MyDBHandler;
import com.example.setavita.models.PantryIngredients;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

public class AddIngredientActivity extends AppCompatActivity implements OnClickListener {

    private Button saveButton;
    private EditText ingredientName, unitCount;
    private Spinner unitMeasure;
    private TextView ingredientID;
    public static String ingID;
    MyDBHandler database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ingredient);
        ingredientName = (EditText) findViewById(R.id.ingredientName);
        unitCount = (EditText) findViewById(R.id.ingredientName);
        unitMeasure = (Spinner) findViewById(R.id.unitMeasureDropDown);
        ingredientID = (TextView) findViewById(R.id.ingredientID);
        database = new MyDBHandler(this);
        setID(ingID);


        saveIngredient();
    }

    public void setID(String id) {
        ingredientID.setText("INGREDIENT ID: " + id);
    }

    public void saveIngredient() {
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ingredientCreated = false;
                String id = ingredientID.getText().toString();
                String name = ingredientName.getText().toString();
                String total = unitCount.getText().toString();
                int totals = Integer.parseInt(total);
                String measure = unitMeasure.getSelectedItem().toString();

                String messages = "33" + measure + " of " + name + " has been added";

//                PantryIngredients pantryIngredient = new PantryIngredients(id, name, total, total, measure, 001);
//                if (pantryIngredient != null){
//                    messages = "ingredient is empty!";
//                }else{
//                    messages = "ingredient is NOT empty";
//                }
//                ingredientCreated = database.addHandler(pantryIngredient);
//
//                if(ingredientCreated) {
//                    showCustomDialog(R.string.dialog_addedIng, "Ingredient has successfully been added!");
//                }else{
//                    showCustomDialog(R.string.dialog_addedIng, "Ingredient NOT added!");
//                }
                showCustomDialog(R.string.dialog_addedIng, messages);

            }
        });
    }

    private void showCustomDialog(int type, String message) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("Ingredient Added");
        dialog.setMessage(message);

        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(AddIngredientActivity.this, PantryScannerActivity.class);
//                        startActivity(intent);

                        finish();
                    }
                });

        dialog.show();
    }

    public void onClick(View v) {
//        if(v.getId() == R.id.save_button){
//            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
//            scanIntegrator.initiateScan();
//        }
    }

}
