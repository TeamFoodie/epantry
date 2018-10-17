package com.example.teamfoodie.epantry;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.PantryIngredient;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

/**
 * Class is used to implement Barcode scanner an handle all activities from pantry_scanner xml file.
 */
public class PantryScannerActivity extends AppCompatActivity implements OnClickListener {

    DatabaseHandler database;
    PantryIngredient ingredient;
    private Button scanBtn, enterButton;
    private int currentUSER_ID;

    /**
     * On create passes through the current User's ID which is used to identify
     * which user the ingredients and pantry belongs to.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantry_scanner);
        scanBtn = (Button) findViewById(R.id.scan_button);
        enterButton = (Button) findViewById(R.id.lookup_button);
        database = new DatabaseHandler(this);

        scanBtn.setOnClickListener(this);
        enterButton.setOnClickListener(this);

//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if (extras == null) {
//                System.out.println("Bundle extra was NULL user");
//            } else {
//                currentUSER_ID = extras.getInt("USER_ID");
//            }
//        } else {
//            currentUSER_ID = (Integer) savedInstanceState.getSerializable("USER_ID");
//            System.out.println("savedInstance was NULL");
//        }

        Bundle extras = getIntent().getExtras();
        currentUSER_ID = extras.getInt("USER_ID");
    }

//    Bundle extras = Bui

    /**
     * Method identifies which button is pressed and applies appropriate correspondence.
     *
     * scan button will implement the IntentIntegrator class - which opens up the barcode scanner but
     * look up button will switch the view to pantry_update for manual input of barcode.
     * @param v
     */
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

    /**
     * Method returns or sets the value obtained from the barcode scanner and coducts the actual search of ingredient in the pantry from the database.
     * If ingredeint is found - then ingredient object is returned and only the quantity is updated to reflect the replenishment of the ingredient.
     * Otherwise - ingredient is not recongnized and user is prompted to register the ingredient.
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult.getContents() != null) {
            String scanContent = scanningResult.getContents();
            String message = "";
            boolean itemFound = false;

            database.setUSER_ID(currentUSER_ID);
            Object object = database.findHandle(scanContent, "PantryIngredient");
            ingredient = (PantryIngredient) object;
            if (ingredient != null) {
                message = "Do you want to add " + ingredient.getTotalQuantity() + ingredient.getUnitMeasure() + " of " + ingredient.getIngredientName() + " to your pantry?";
                itemFound = true;
                currentUSER_ID = ingredient.getOwner();
                System.out.println("CURRENT USER FROM THE MESSAGE IS " + currentUSER_ID);
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

    /**
     * Dialog box has if statement and message displayed relies on whether or not item was found
     * inside the database.
     *
     * @param type
     * @param message
     * @param id
     * @param itemFound
     */
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

