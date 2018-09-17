package com.example.setavita.epantry;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.setavita.database.MyDBHandler;
import com.example.setavita.models.PantryIngredients;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

public class PantryScannerActivity extends AppCompatActivity implements OnClickListener {

    MyDBHandler database;
    PantryIngredients ingredient;
    private Button scanBtn, enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantry_scanner);
        scanBtn = (Button) findViewById(R.id.scan_button);
        enterButton = (Button) findViewById(R.id.enterButton);
        database = new MyDBHandler(this);

        scanBtn.setOnClickListener(this);
        enterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(PantryScannerActivity.this, PantryUpdateActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onClick(View v) {
        if (v.getId() == R.id.scan_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        } else if (v.getId() == R.id.enterButton) {
            Intent intent = new Intent(PantryScannerActivity.this, PantryUpdateActivity.class);
            startActivity(intent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String message = "";

            ingredient = database.findHandler(scanContent);
            int option = 1;

            if (ingredient != null) {
                message = "Do you want to add " + ingredient.getTotalQuantity() + " of " + ingredient.getIngredientName() + " to your pantry?";
                option = 1;
            } else {
                message = "Ingredient scanned was not recognized. Do you want to register new ingredient?";
                option = 2;
            }


            showCustomDialog(R.string.dialog_restock, message, scanContent, option);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void showCustomDialog(int type, String message, final String id, int option) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        final int OKoption = option;
        dialog.setTitle("Add Ingredient");
        dialog.setMessage(message);
        dialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (OKoption == 2) {
                    AddIngredientActivity.ingID = id;
                    Intent intent = new Intent(PantryScannerActivity.this, AddIngredientActivity.class);
                    startActivity(intent);
                } else {
                    topUpIngredient();
                            finish();
                }

            }
        });

        dialog.show();
    }

    public void topUpIngredient() {
        int newQuantity = ingredient.getCurrentQuantity() + ingredient.getTotalQuantity();
        ingredient.setCurrentQuantity(newQuantity);
        boolean updated = database.updateQuantity(ingredient);
        if (updated) {
            System.out.println("topped up");
        } else {
            System.out.println("rank");
        }
    }

}
