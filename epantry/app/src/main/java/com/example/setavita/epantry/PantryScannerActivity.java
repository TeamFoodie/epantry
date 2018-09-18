package com.example.setavita.epantry;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.setavita.database.DatabaseHandler;
import com.example.setavita.models.PantryIngredients;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

public class PantryScannerActivity extends AppCompatActivity implements OnClickListener {

    DatabaseHandler database;
    PantryIngredients ingredient;
    private Button scanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantry_scanner);
        scanBtn = (Button) findViewById(R.id.scan_button);
        database = new DatabaseHandler(this);

        scanBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.scan_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();

            showCustomDialog(R.string.dialog_restock, "Slide Left", scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void showCustomDialog(int type, String message, final String id) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("Add Ingredient");
        if (id =="9310797278959"){
            message = "Do you want to add " + ingredient.getTotalQuantity() + " of " + ingredient.getIngredientName() + "to your pantry?";
        }else{
            message = "Ingredient scanned was not recognized. Do you want to register new ingredient?";
        }
//
//        ingredient = database.findHandler(ingredientID);
//            if(ingredient != null){
//                message = "Do you want to add " + ingredient.getTotalQuantity() + " of " + ingredient.getIngredientName() + "to your pantry?";
//            }else{
//                message = "Ingredient scanned was not recognized. Do you want to register new ingredient?";
//            }

        dialog.setMessage(message);
        dialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddIngredientActivity.ingID = id;
                        Intent intent = new Intent(PantryScannerActivity.this, AddIngredientActivity.class);
                        startActivity(intent);

                    }
                });

        dialog.show();
    }

    public void changeScreens(View view){
        Intent startActivity = new Intent(PantryScannerActivity.this, AddIngredientActivity.class);

    }
}
