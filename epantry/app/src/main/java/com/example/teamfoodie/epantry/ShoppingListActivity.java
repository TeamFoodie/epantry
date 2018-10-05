package com.example.teamfoodie.epantry;
/*
ShoppingListActivity class shows Shopping List from database
and user can delete items from database and take capture
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.SQLiteDatabaseDao;
import com.example.teamfoodie.models.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {
    private static final String TAG = "FoodMaterialListActivit";

    protected RecyclerView idRvMaterial;
    protected CheckBox idCbSelect;
    protected Button idBtnDelete;
    protected Button idBtnCapture;
    private List<ShoppingList> shoppingListList;
    private List<ShoppingList> shoppingListSelectList;

    private Context mContext;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        super.setContentView(R.layout.shopping_list);
        shoppingListList = new ArrayList<>();
        shoppingListSelectList = new ArrayList<>();

        mSharedPreferences = mContext.getSharedPreferences("cache",
                Context.MODE_PRIVATE);

        initView();


        final FoodMaterialAdapter foodMaterialAdapter = new FoodMaterialAdapter(shoppingListList);
        foodMaterialAdapter.setIOnCheckedChangeListener(new IOnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean b, ShoppingList shoppingList, int position) {
                if (b) {
                    shoppingListSelectList.add(shoppingList);
                } else {
                    shoppingListSelectList.remove(shoppingList);
                }
            }
        });
        idRvMaterial.setLayoutManager(new LinearLayoutManager(mContext));
        idRvMaterial.setAdapter(foodMaterialAdapter);
        new Thread() {
            @Override
            public void run() {
                super.run();
                shoppingListList = SQLiteDatabaseDao.getInstance().init(mContext).queryFoodMaterial();
                foodMaterialAdapter.update(shoppingListList);
            }
        }.start();


        idCbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int i = 0; i < shoppingListList.size(); i++) {
                    Log.d(TAG, "onCheckedChanged: b==" + b);
                    ShoppingList shoppingList = shoppingListList.get(i);
                    shoppingList.setChecked(b);
                    shoppingListList.set(i, shoppingList);
                    foodMaterialAdapter.notifyDataSetChanged();
                }
                if (!b) {
                    if (shoppingListSelectList.size() > 0)
                        shoppingListSelectList.clear();
                }
                Log.d(TAG, "onCheckedChanged: shoppingListList==" + shoppingListList.toString());
                Log.d(TAG, "onCheckedChanged: shoppingListSelectList==" + shoppingListSelectList.toString());
            }
        });

        idBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabaseDao sqLiteDatabaseDao = SQLiteDatabaseDao.getInstance();
                if (idCbSelect.isChecked()) {
                    shoppingListSelectList = shoppingListList;
                }
                int len = shoppingListSelectList.size();
                for (int i = 0; i < len; i++) {
                    ShoppingList shoppingList = shoppingListSelectList.get(i);
                    sqLiteDatabaseDao.deleteCity(shoppingList.getId());
                }
                if (len > 0) {
                    shoppingListSelectList.size();
                }
                Toast.makeText(mContext, "Deleted successfully, quit after 10 seconds.", Toast.LENGTH_SHORT).show();
                SystemClock.sleep(10000);
                ShoppingListActivity.this.finish();

            }
        });

//        idBtnCapture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bitmap captureBitmap = capture(ShoppingListActivity.this);
//                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//                captureBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
//                String captureUrl = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT));
//                SharedPreferences.Editor editor = mSharedPreferences.edit();
//                editor.putString("captureUrl", captureUrl);
//                editor.commit();
//                if (!TextUtils.isEmpty(captureUrl)){
//                    Toast.makeText(mContext, "capture sucess", Toast.LENGTH_SHORT).show();
//                    ShoppingListActivity.this.startActivity(new Intent(mContext, CaptureActivity.class));
//                }
//                String captureUrlTMp = mSharedPreferences.getString("captureUrl", "");
//                Log.e(TAG, "run: captureUrl===" + captureUrlTMp);
//
//            }
//        });


    }

    private void initView() {
        idRvMaterial = findViewById(R.id.id_rv_material);
        idCbSelect = findViewById(R.id.id_cb_select);
        idBtnDelete = findViewById(R.id.id_btn_delete);
        idBtnCapture = findViewById(R.id.id_btn_capture);
    }
//
//    public Bitmap capture(Activity activity) {
//        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
//        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
//        return bmp;
//    }

    public interface IOnCheckedChangeListener {

        void onCheckedChanged(boolean b, ShoppingList shoppingList, int position);
    }

    public class FoodMaterialAdapter extends RecyclerView.Adapter<FoodMaterialAdapter.FoodMaterialViewHolder> {
        private final String TAG = "FoodMaterialAdapter";

        private List<ShoppingList> mItemList;
        private IOnCheckedChangeListener mIOnCheckedChangeListener;

        public void setIOnCheckedChangeListener(IOnCheckedChangeListener iOnCheckedChangeListener) {
            this.mIOnCheckedChangeListener = iOnCheckedChangeListener;
        }

        public class FoodMaterialViewHolder extends RecyclerView.ViewHolder {
            CheckBox idCb;

            public FoodMaterialViewHolder(View v) {
                super(v);
                idCb = v.findViewById(R.id.id_cb);
            }

        }

        public FoodMaterialAdapter(List<ShoppingList> itemList) {
            mItemList = itemList;
        }

        @Override
        public FoodMaterialViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shopping_list_item, viewGroup, false);

            return new FoodMaterialViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull FoodMaterialViewHolder foodMaterialViewHolder, final int i) {
            final ShoppingList shoppingList = mItemList.get(i);
            String desc = shoppingList.getMaterialValue() + " " + shoppingList.getUnit() + " " + shoppingList.getMaterialName();
            foodMaterialViewHolder.idCb.setChecked(shoppingList.isChecked());
            foodMaterialViewHolder.idCb.setText(desc);
            foodMaterialViewHolder.idCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    shoppingList.setChecked(b);
                    mItemList.set(i, shoppingList);
                    if (mIOnCheckedChangeListener != null) {
                        mIOnCheckedChangeListener.onCheckedChanged(b, shoppingList, i);
                    }
                }
            });
        }


        @Override
        public int getItemCount() {
            return mItemList.size();
        }


        /**
         * update data
         *
         * @param itemList
         */
        public void update(List<ShoppingList> itemList) {
            mItemList.clear();
            mItemList.addAll(itemList);
            notifyDataSetChanged();
        }
    }
}
