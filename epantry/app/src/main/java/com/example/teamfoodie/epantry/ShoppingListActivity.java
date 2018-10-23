package com.example.teamfoodie.epantry;
/*
 * ShoppingListActivity class shows Shopping List from database
 * and user can delete items from database and take capture
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.epantry.listAdapters.ShoppingListAdapter;
import com.example.teamfoodie.models.PantryIngredient;
import com.example.teamfoodie.models.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {
    private static final String TAG = "FoodMaterialListActivit";

    protected RecyclerView idRvMaterial;
    protected CheckBox idCbSelect;
    protected Button idBtnDelete;
    protected Button idBtnCapture;
    private List<PantryIngredient> shoppingListList;
    private List<PantryIngredient> shoppingListSelectList;
    List<PantryIngredient> lowStock = new ArrayList<>();
    private int currentUSER_ID;
    DatabaseHandler dbHandler = new DatabaseHandler(this);
    private List<PantryIngredient> pantryList = new ArrayList<>();
    ShoppingList listItems = new ShoppingList();
    private Context mContext;

    private SharedPreferences mSharedPreferences;


    public List<PantryIngredient> calculateLowStock(List<PantryIngredient> list) {


        for (PantryIngredient ing : list) {
            ing.setCurrentQuantity(2);

            if(ing.getCurrentQuantity() < ing.getTotalQuantity()) {
                System.out.println("LOWSTOCK!! ING CURRENT QUAN:" + ing.getCurrentQuantity() + "ING TOTAL QUAN:" + ing.getTotalQuantity());
                lowStock.add(ing);
            }
        }
        System.out.println("LOWSTOCK!! size: "+lowStock.size());

        return lowStock;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        currentUSER_ID = extras.getInt("USER_ID");
        pantryList = dbHandler.loadAllPantryIngredients(currentUSER_ID);
        System.out.println("Size of pantry is " + pantryList.size());

        for(int i = 0; i < pantryList.size(); ++i) {
            Log.i("Shopping list: ", ""+pantryList.get(i).getIngredientInformation());
        }
        lowStock = calculateLowStock(pantryList);

       // addFoodMaterial();
        for(PantryIngredient p : lowStock) {
            System.out.println("After calcLowStock(): "+p.getIngredientName());
        }
        mContext = this;
        super.setContentView(R.layout.shopping_list);
        shoppingListList = new ArrayList<>();
        shoppingListSelectList = new ArrayList<>();

        mSharedPreferences = mContext.getSharedPreferences("cache",
                Context.MODE_PRIVATE);



        initView();


        final ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(shoppingListList);
        idRvMaterial.setLayoutManager(new LinearLayoutManager(mContext));
        idRvMaterial.setAdapter(shoppingListAdapter);
        if(lowStock != null){
            System.out.println("before update():"+lowStock.get(0).getIngredientName());
        }

        shoppingListAdapter.update(lowStock);



        idBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SQLiteDatabaseDao sqLiteDatabaseDao = SQLiteDatabaseDao.getInstance();
                if (idCbSelect.isChecked()) {
                    shoppingListSelectList = shoppingListList;
                }
                int len = shoppingListSelectList.size();
                for (int i = 0; i < len; i++) {
                    PantryIngredient shoppingList = shoppingListSelectList.get(i);
//                    sqLiteDatabaseDao.deleteCity(shoppingList.getId());
                }
                if (len > 0) {
                    shoppingListSelectList.size();
                }
                Toast.makeText(mContext, "Ingredient successfully deleted.", Toast.LENGTH_SHORT).show();
                ShoppingListActivity.this.finish();

            }
        });
    }

    private void initView() {
        idRvMaterial = findViewById(R.id.id_rv_material);
        idCbSelect = findViewById(R.id.id_cb_select);
        idBtnDelete = findViewById(R.id.id_btn_delete);
        idBtnCapture = findViewById(R.id.id_btn_capture);
    }
}
