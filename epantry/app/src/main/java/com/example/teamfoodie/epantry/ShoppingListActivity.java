//package com.example.teamfoodie.epantry;
///*
//ShoppingListActivity class shows Shopping List from database
//and user can delete items from database and take capture
// */
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.Toast;
//
//import com.example.teamfoodie.R;
//import com.example.teamfoodie.database.DatabaseHandler;
//import com.example.teamfoodie.epantry.listAdapters.ShoppingListAdapter;
//import com.example.teamfoodie.database.SQLiteDatabaseDao;
//import com.example.teamfoodie.models.PantryIngredient;
//import com.example.teamfoodie.models.ShoppingList;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ShoppingListActivity extends AppCompatActivity {
//    private static final String TAG = "FoodMaterialListActivit";
//
//    protected RecyclerView idRvMaterial;
//    protected CheckBox idCbSelect;
//    protected Button idBtnDelete;
//    protected Button idBtnCapture;
//    private List<ShoppingList> shoppingListList;
//    private List<ShoppingList> shoppingListSelectList;
//    DatabaseHandler dbHandler = new DatabaseHandler(this);
//    private List<PantryIngredient> pantryList = new ArrayList<>();
//    private List<PantryIngredient> pantryList2 = new ArrayList<>();
//
//    private Context mContext;
//
//    private SharedPreferences mSharedPreferences;
//
//    public void addFoodMaterial(SQLiteDatabaseDao sqLiteDatabaseDao) {
//
////        List<ShoppingList> shoppingLists = sqLiteDatabaseDao.queryFoodMaterial();
//
////            ShoppingList shoppingList = new ShoppingList();
////            shoppingList.setUnit("g");
////            shoppingList.setMaterialValue("50");
////            shoppingList.setMaterialName("Cheese");
////            sqLiteDatabaseDao.insertFoodMaterial(shoppingList);
////            ShoppingList shoppingList1 = new ShoppingList();
////            shoppingList1.setUnit("ml");
////            shoppingList1.setMaterialValue("200");
////            shoppingList1.setMaterialName("coconut milk");
////            sqLiteDatabaseDao.insertFoodMaterial(shoppingList1);
////            ShoppingList shoppingList2 = new ShoppingList();
////            shoppingList2.setUnit("g");
////            shoppingList2.setMaterialValue("50");
////            shoppingList2.setMaterialName("mayonnaise");
////            sqLiteDatabaseDao.insertFoodMaterial(shoppingList2);
////            ShoppingList shoppingList3 = new ShoppingList();
////            shoppingList3.setUnit("g");
////            shoppingList3.setMaterialValue("250");
////            shoppingList3.setMaterialName("butter");
////            sqLiteDatabaseDao.insertFoodMaterial(shoppingList3);
////            ShoppingList shoppingList4 = new ShoppingList();
////            shoppingList4.setUnit("g");
////            shoppingList4.setMaterialValue("50");
////            shoppingList4.setMaterialName("chicken");
////            sqLiteDatabaseDao.insertFoodMaterial(shoppingList4);
//
//            for(PantryIngredient p : pantryList) {
//                ShoppingList listItems = new ShoppingList();
//                listItems.setUnit(p.getUnitMeasure());
//                listItems.setMaterialValue(String.valueOf(p.getCurrentQuantity()));
//                listItems.setMaterialName(p.getIngredientName());
//                sqLiteDatabaseDao.insertFoodMaterial(listItems);
//                System.out.println(p.getIngredientName()+"  "+p.getUnitMeasure()+"  "+String.valueOf(p.getCurrentQuantity()));
//            }
//        for(PantryIngredient p : pantryList2) {
//            ShoppingList listItems = new ShoppingList();
//            listItems.setUnit(p.getUnitMeasure());
//            listItems.setMaterialValue(String.valueOf(p.getCurrentQuantity()));
//            listItems.setMaterialName(p.getIngredientName());
//            sqLiteDatabaseDao.insertFoodMaterial(listItems);
//            System.out.println(p.getIngredientName()+"  "+p.getUnitMeasure()+"  "+String.valueOf(p.getCurrentQuantity()));
//        }
//
//
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        pantryList = dbHandler.loadAllPantryIngredients(0);
//        System.out.println(pantryList.size());
//        for(int i = 0; i < pantryList.size(); ++i) {
//            Log.i("Shopping list: ", ""+pantryList.get(i).getIngredientInformation());
//        }
//        addFoodMaterial(SQLiteDatabaseDao.getInstance().init(this));
//        mContext = this;
//        super.setContentView(R.layout.shopping_list);
//        shoppingListList = new ArrayList<>();
//        shoppingListSelectList = new ArrayList<>();
//
//        mSharedPreferences = mContext.getSharedPreferences("cache",
//                Context.MODE_PRIVATE);
//
//
//
//        initView();
//
//
//        final ShoppingListAdapter shoppingListAdapter = new ShoppingListAdapter(shoppingListList);
//        shoppingListAdapter.setIOnCheckedChangeListener(new ShoppingListAdapter.IOnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(boolean b, ShoppingList shoppingList, int position) {
//                if (b) {
//                    shoppingListSelectList.add(shoppingList);
//                } else {
//                    shoppingListSelectList.remove(shoppingList);
//                }
//            }
//        });
//        idRvMaterial.setLayoutManager(new LinearLayoutManager(mContext));
//        idRvMaterial.setAdapter(shoppingListAdapter);
////        new Thread() {
////            @Override
////            public void run() {
////                super.run();
//                shoppingListList = SQLiteDatabaseDao.getInstance().init(mContext).queryFoodMaterial();
//                shoppingListAdapter.update(shoppingListList);
////            }
////        }.start();
//
//
//        idCbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                for (int i = 0; i < shoppingListList.size(); i++) {
//                    Log.d(TAG, "onCheckedChanged: b==" + b);
//                    ShoppingList shoppingList = shoppingListList.get(i);
//                    shoppingList.setChecked(b);
//                    shoppingListList.set(i, shoppingList);
//                    shoppingListAdapter.notifyDataSetChanged();
//                }
//                if (!b) {
//                    if (shoppingListSelectList.size() > 0)
//                        shoppingListSelectList.clear();
//                }
//                Log.d(TAG, "onCheckedChanged: shoppingListList==" + shoppingListList.toString());
//                Log.d(TAG, "onCheckedChanged: shoppingListSelectList==" + shoppingListSelectList.toString());
//            }
//        });
//
//        idBtnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SQLiteDatabaseDao sqLiteDatabaseDao = SQLiteDatabaseDao.getInstance();
//                if (idCbSelect.isChecked()) {
//                    shoppingListSelectList = shoppingListList;
//                }
//                int len = shoppingListSelectList.size();
//                for (int i = 0; i < len; i++) {
//                    ShoppingList shoppingList = shoppingListSelectList.get(i);
//                    sqLiteDatabaseDao.deleteCity(shoppingList.getId());
//                }
//                if (len > 0) {
//                    shoppingListSelectList.size();
//                }
//                Toast.makeText(mContext, "Deleted successfully, quit after 10 seconds.", Toast.LENGTH_SHORT).show();
//                SystemClock.sleep(10000);
//                ShoppingListActivity.this.finish();
//
//            }
//        });
//
////        idBtnCapture.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Bitmap captureBitmap = capture(ShoppingListActivity.this);
////                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
////                captureBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
////                String captureUrl = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT));
////                SharedPreferences.Editor editor = mSharedPreferences.edit();
////                editor.putString("captureUrl", captureUrl);
////                editor.commit();
////                if (!TextUtils.isEmpty(captureUrl)){
////                    Toast.makeText(mContext, "capture success", Toast.LENGTH_SHORT).show();
////                    ShoppingListActivity.this.startActivity(new Intent(mContext, CaptureActivity.class));
////                }
////                String captureUrlTMp = mSharedPreferences.getString("captureUrl", "");
////                Log.e(TAG, "run: captureUrl===" + captureUrlTMp);
////
////            }
////        });
//
//
//    }
//
//    private void initView() {
//        idRvMaterial = findViewById(R.id.RecyclerView1);
//        idCbSelect = findViewById(R.id.id_cb_select);
//        idBtnDelete = findViewById(R.id.id_btn_delete);
//        idBtnCapture = findViewById(R.id.id_btn_capture);
//    }
////
////    public Bitmap capture(Activity activity) {
////        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
////        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
////        return bmp;
////    }
//
//
//}
