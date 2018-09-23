package com.example.setavita.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.setavita.epantry.database.DatabaseHandler;
import com.example.setavita.epantry.models.PantryIngredient;

public class PantryUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler database;
    private PantryIngredient ingredient;
    private Button searchButton;
    private EditText ingredientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantry_update);
        database = new DatabaseHandler(this);
        searchIngredient();
    }

    public void searchIngredient() {
        searchButton = (Button) findViewById(R.id.find_button);
        ingredientID = (EditText) findViewById(R.id.ingredientID);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ingredientID.getText().toString();
                if (id.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Object object = database.findHandle(id, "PantryIngredient");
                    ingredient = (PantryIngredient) object;
                    int option = 1;
                    String message = "";

                    if (ingredient != null) {
                        message = "Do you want to add " + ingredient.getTotalQuantity() + " of " + ingredient.getIngredientName() + " to your pantry?";
                        option = 1;
                    } else {
                        message = "Ingredient scanned was not recognized. Do you want to register new ingredient?";
                        option = 2;
                    }

                    showCustomDialog(R.string.dialog_restock, message, id, option);
                }
            }


        });

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
                    Intent intent = new Intent(PantryUpdateActivity.this, AddIngredientActivity.class);
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

    public void changeScreens(View view) {
        Intent startActivity = new Intent(PantryUpdateActivity.this, AddIngredientActivity.class);

    }

    @Override
    public void onClick(View v) {

    }
}
