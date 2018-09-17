package com.example.setavita.epantry;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.setavita.database.MyDBHandler;
import com.example.setavita.models.PantryIngredients;

public class AddIngredientActivity extends AppCompatActivity implements OnClickListener {

    private Button saveButton;
    private EditText ingredientName, unitCount;
    private Spinner unitMeasure;
    private TextView ingredientIDTEXT;
    public static String ingID;
    private MyDBHandler database;
    private String ingredientID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ingredient);
        ingredientName = (EditText) findViewById(R.id.ingredientName);
        unitCount = (EditText) findViewById(R.id.unitCount);
        unitMeasure = (Spinner) findViewById(R.id.unitMeasureDropDown);
        ingredientIDTEXT = (TextView) findViewById(R.id.ingredientID);
        saveButton = (Button) findViewById(R.id.save_button);
        database = new MyDBHandler(this);

        saveButton.setOnClickListener(this);
        setID(ingID);

    }

    public void setID(String id) {
        ingredientIDTEXT.setText("INGREDIENT ID: " + id);
        this.ingredientID = id;
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

    public void onClick(View v) {
        if(v.getId() == R.id.save_button){
            boolean ingredientCreated = false;
            String name = ingredientName.getText().toString();
            int total = Integer.parseInt(unitCount.getText().toString());
            String measure = unitMeasure.getSelectedItem().toString();

            String messages = "33 " + measure + " of " + name + " has been added";

            PantryIngredients pantryIngredient = new PantryIngredients(ingredientID, name, total, total, measure, 001);
            ingredientCreated = database.addHandle(pantryIngredient);

            if(ingredientCreated) {
                messages = total + measure + " of " + name + " has been added successfully!";
            }else{
                messages = "Sorry, ingredient could not be added, please try again";
            }
            showCustomDialog(R.string.dialog_addedIng, messages);
        }
    }

}
