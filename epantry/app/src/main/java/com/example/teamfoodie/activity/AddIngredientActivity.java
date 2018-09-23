package com.example.teamfoodie.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.teamfoodie.epantry.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.PantryIngredient;

public class AddIngredientActivity extends AppCompatActivity{

    private Button saveButton;
    private EditText ingredientName, unitCount;
    private Spinner unitMeasure;
    private TextView ingredientID;
    public static String ingID;
    private DatabaseHandler database;
    private int currentUSER_ID;
    private String actualIngredientID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ingredient);
        ingredientName = (EditText) findViewById(R.id.ingredientName);
        unitCount = (EditText) findViewById(R.id.unitCount);
        unitMeasure = (Spinner) findViewById(R.id.unitMeasureDropDown);
        ingredientID = (TextView) findViewById(R.id.ingredientID);
        database = new DatabaseHandler(this);
        setID(ingID);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                System.out.println("Bundle extra was NULL from AddIngredient Activity");
            } else {
                currentUSER_ID = extras.getInt("USER_ID");
            }
        } else {
            currentUSER_ID = (Integer) savedInstanceState.getSerializable("USER_ID");
            System.out.println("savedInstance was NULL");
        }


        saveIngredient();
    }

    public void setID(String id) {
        ingredientID.setText("INGREDIENT ID: " + id);
        this.actualIngredientID = id;
    }

    public void saveIngredient() {
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ingredientCreated = false;
                String name = ingredientName.getText().toString();
                String total = unitCount.getText().toString();
                int totals = Integer.parseInt(total);
                String measure = unitMeasure.getSelectedItem().toString();
                String messages = "";

                PantryIngredient pantryIngredient = new PantryIngredient(actualIngredientID, name, totals, totals, measure, currentUSER_ID);
                System.out.println("OWNER OF NEW PANTRY INGRIENT IS " + currentUSER_ID);
                if (pantryIngredient != null){
                    messages = "ingredient is empty!";
                }else{
                    messages = "ingredient is NOT empty";
                }
                ingredientCreated = database.addHandle(pantryIngredient);

                if(ingredientCreated) {
                    showCustomDialog(R.string.dialog_addedIng, "Ingredient has successfully been added!");
                }else{
                    showCustomDialog(R.string.dialog_addedIng, "Ingredient NOT added!");
                }

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
                        finish();
                    }
                });

        dialog.show();
    }


}
