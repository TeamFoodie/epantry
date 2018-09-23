package com.example.setavita.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.setavita.epantry.database.DatabaseHandler;
import com.example.setavita.epantry.models.PantryIngredient;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

public class PantryScannerActivity extends AppCompatActivity implements OnClickListener {

    DatabaseHandler database;
    PantryIngredient ingredient;
    private Button scanBtn, enterButton;
    private int currentUSER_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantry_scanner);
        scanBtn = (Button) findViewById(R.id.scan_button);
        enterButton = (Button) findViewById(R.id.lookup_button);
        database = new DatabaseHandler(this);

        scanBtn.setOnClickListener(this);
        enterButton.setOnClickListener(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
//                currentUSER_ID = 1;
                System.out.println("Bundle extra was NULL user");
            } else {
                currentUSER_ID = extras.getInt("USER_ID");
            }
        } else {
            currentUSER_ID = (Integer) savedInstanceState.getSerializable("USER_ID");
            System.out.println("savedInstance was NULL");
        }
    }

    public void onClick(View v) {


        if (v.getId() == R.id.scan_button) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        } else if (v.getId() == R.id.lookup_button) {
            Intent intent = new Intent(PantryScannerActivity.this, PantryUpdateActivity.class);
            intent.putExtra("USER_ID", currentUSER_ID);
            startActivity(intent);
        }


    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult.getContents() != null) {
            String scanContent = scanningResult.getContents();
            String message = "";
            boolean itemFound = false;

            Object object = database.findHandle(scanContent, "PantryIngredient");
            ingredient = (PantryIngredient) object;
            if (ingredient != null) {
                message = "Do you want to add " + ingredient.getTotalQuantity() + ingredient.getUnitMeasure()+ " of " + ingredient.getIngredientName() + "to your pantry?";
                itemFound = true;
                currentUSER_ID = ingredient.getOwner();
            } else {
                message = "Ingredient scanned was not recognized. Do you want to register new ingredient?";
                itemFound = false;
            }

            showCustomDialog(R.string.dialog_restock, message, scanContent, itemFound);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    private void showCustomDialog(int type, String message, final String id, boolean itemFound) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("Add Ingredient");
        dialog.setMessage(message);
        if (itemFound) {
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

                            int newQuantity = ingredient.getCurrentQuantity() + ingredient.getTotalQuantity();
                            ingredient.setCurrentQuantity(newQuantity);
                            database.updateQuantity(ingredient);

                            //Restarting intent will refresh the list view with updated ingredient quantity!
                            Intent intent = new Intent(PantryScannerActivity.this, ViewPantryActivity.class);
                            intent.putExtra("USER_ID", currentUSER_ID);
                            startActivity(intent);
//                            finish();
                        }
                    });
        } else {
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
                            intent.putExtra("USER_ID", currentUSER_ID);
                            startActivity(intent);
                        }
                    });


        }


        dialog.show();
    }

}
