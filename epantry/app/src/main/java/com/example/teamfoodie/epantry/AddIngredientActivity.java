package com.example.teamfoodie.epantry;
/*
AddIngredientActivity is used to save pantry ingredient when user registers new ingredient.
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.PantryIngredient;

public class AddIngredientActivity extends AppCompatActivity {

    private Button saveButton;
    private EditText ingredientName, unitCount;
    private Spinner unitMeasure;
    private Spinner foodGroup;
    private TextView ingredientID;
    public static String ingID;
    private DatabaseHandler database;
    private int currentUSER_ID;
    private String actualIngredientID;


    /**
     * On Create method - the USER ID is passed through the intent allowing users to create pantries that are specific to them;
     * this allows each user to have a single pantry unique to their account.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ingredient);
        ingredientName = (EditText) findViewById(R.id.ingredientName);
        unitCount = (EditText) findViewById(R.id.unitCount);
        unitMeasure = (Spinner) findViewById(R.id.unitMeasureDropDown);
        foodGroup = (Spinner) findViewById(R.id.foodGroupID);
        ingredientID = (TextView) findViewById(R.id.ingredientID);
        database = new DatabaseHandler(this);
        setID(ingID);

        Bundle extras = getIntent().getExtras();
        currentUSER_ID = extras.getInt("USER_ID");


        saveIngredient();
    }

    public void setID(String id) {
        ingredientID.setText("INGREDIENT ID: " + id);
        this.actualIngredientID = id;
    }

    /**
     * Method does that actual saving of data from add_ingredient form to the database.
     * However, all fields must be filled out before i can actually be saved.
     */
    public void saveIngredient() {
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean ingredientCreated = false;
                String name = ingredientName.getText().toString();
                String total = unitCount.getText().toString();


                if (name.isEmpty() || total.isEmpty()) {
                    Toast noMatch = Toast.makeText(AddIngredientActivity.this, "Please make sure all fields are filled out", Toast.LENGTH_SHORT);
                    noMatch.show();

                } else {
                    int totals = Integer.parseInt(total);
                    String measure = unitMeasure.getSelectedItem().toString();
                    String group = foodGroup.getSelectedItem().toString();
                    String messages = "";

                    PantryIngredient pantryIngredient = new PantryIngredient(actualIngredientID, name, totals, totals, measure, group, currentUSER_ID, false);
                    System.out.println("SAUCES OF NEW PANTRY INGREDIENT IS " + currentUSER_ID);
                    if (pantryIngredient != null) {
                        messages = "ingredient is empty!";
                    } else {
                        messages = "ingredient is NOT empty";
                    }
                    ingredientCreated = database.addHandle(pantryIngredient);

                    if (ingredientCreated) {
                        showCustomDialog(R.string.dialog_addedIng, "Ingredient has successfully been added!");
                    } else {
                        showCustomDialog(R.string.dialog_addedIng, "Ingredient NOT added!");
                    }

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
