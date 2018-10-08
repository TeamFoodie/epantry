package com.example.teamfoodie.epantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.epantry.listAdapters.CustomPantryIngredientAdapter;
import com.example.teamfoodie.models.PantryIngredient;



/**
 * Class handles all activities from view_pantry xml.
 */
public class ViewPantryActivity extends AppCompatActivity {

    private int currentUSER_ID;
    private List<PantryIngredient> pantryList = new ArrayList<>();
    private DatabaseHandler dbHandler = new DatabaseHandler(this);

    /**
     * USER ID is passed through saved Instance state ensuring that only the ingredients belogining to the
     * current user is displayed.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pantry);
        this.dbHandler = new DatabaseHandler(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                System.out.println("Bundle extra was NULL user");
            } else {
                currentUSER_ID = extras.getInt("USER_ID");
            }
        } else {
            currentUSER_ID = (Integer) savedInstanceState.getSerializable("USER_ID");
            System.out.println("savedInstance was NULL");
        }

        System.out.println("Current user in View pantry is " + currentUSER_ID);


        pantryList = dbHandler.loadAllPantryIngredients(currentUSER_ID);
        ListView listView = (ListView) findViewById(R.id.viewAllPantry);
        listView.setAdapter(new CustomPantryIngredientAdapter(this, pantryList, dbHandler));

        configureAddButton();

    }

    /**
     * method implements button present in the view_pantry xml.
     * encodes the user id into intent and passes it onto the next implement activity.
     */
    private void configureAddButton() {
        ImageView addButton = (ImageView) findViewById(R.id.addIngredient);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPantryActivity.this, PantryScannerActivity.class);
                intent.putExtra("USER_ID", currentUSER_ID);
                System.out.println("USER ID FROM VIEW PANTRY IS " + currentUSER_ID);
                startActivity(intent);
            }
        });

    }
}
