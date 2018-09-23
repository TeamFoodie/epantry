package com.example.teamfoodie.epantry;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.teamfoodie.database.SQLiteDatabaseDao;
import com.example.teamfoodie.listener.IOnCheckedChangeListener;
import com.example.teamfoodie.models.FoodMaterialBean;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FoodMaterialListActivity extends AppCompatActivity {
    private static final String TAG = "FoodMaterialListActivit";

    protected RecyclerView idRvMaterial;
    protected CheckBox idCbSelect;
    protected Button idBtnDelete;
    protected Button idBtnCapture;
    private List<FoodMaterialBean> foodMaterialBeanList;
    private List<FoodMaterialBean> foodMaterialBeanSelectList;

    private Context mContext;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        super.setContentView(R.layout.activity_food_material_list);
        foodMaterialBeanList = new ArrayList<>();
        foodMaterialBeanSelectList = new ArrayList<>();

        mSharedPreferences = mContext.getSharedPreferences("cache",
                Context.MODE_PRIVATE);

        initView();

        final FoodMaterialAdapter foodMaterialAdapter = new FoodMaterialAdapter(foodMaterialBeanList);
        foodMaterialAdapter.setIOnCheckedChangeListener(new IOnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean b, FoodMaterialBean foodMaterialBean, int position) {
                if (b){
                    foodMaterialBeanSelectList.add(foodMaterialBean);
                }else{
                    foodMaterialBeanSelectList.remove(foodMaterialBean);
                }
            }
        });
        idRvMaterial.setLayoutManager(new LinearLayoutManager(mContext));
        idRvMaterial.setAdapter(foodMaterialAdapter);
        new Thread() {
            @Override
            public void run() {
                super.run();
                foodMaterialBeanList = SQLiteDatabaseDao.getInstance().init(mContext).queryFoodMaterial();
                foodMaterialAdapter.update(foodMaterialBeanList);
            }
        }.start();


        idCbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int i = 0; i < foodMaterialBeanList.size(); i++) {
                    Log.d(TAG, "onCheckedChanged: b==" + b);
                    FoodMaterialBean foodMaterialBean = foodMaterialBeanList.get(i);
                    foodMaterialBean.setChecked(b);
                    foodMaterialBeanList.set(i, foodMaterialBean);
                    foodMaterialAdapter.notifyDataSetChanged();
                }
                if (!b) {
                    if (foodMaterialBeanSelectList.size() > 0)
                        foodMaterialBeanSelectList.clear();
                }
                Log.d(TAG, "onCheckedChanged: foodMaterialBeanList==" + foodMaterialBeanList.toString());
                Log.d(TAG, "onCheckedChanged: foodMaterialBeanSelectList==" + foodMaterialBeanSelectList.toString());
            }
        });

        idBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabaseDao sqLiteDatabaseDao = SQLiteDatabaseDao.getInstance();
                if (idCbSelect.isChecked()){
                    foodMaterialBeanSelectList = foodMaterialBeanList;
                }
                int len = foodMaterialBeanSelectList.size();
                for (int i = 0; i < len; i++) {
                    FoodMaterialBean foodMaterialBean = foodMaterialBeanSelectList.get(i);
                    sqLiteDatabaseDao.deleteCity(foodMaterialBean.getId());
                }                if (len > 0) {
                    foodMaterialBeanSelectList.size();
                }
                Toast.makeText(mContext, "Deleted successfully, quit after 10 seconds.", Toast.LENGTH_SHORT).show();
                SystemClock.sleep(10000);
                FoodMaterialListActivity.this.finish();

            }
        });

        idBtnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap captureBitmap = capture(FoodMaterialListActivity.this);
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                captureBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                String captureUrl = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT));
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("captureUrl", captureUrl);
                editor.commit();
                if (!TextUtils.isEmpty(captureUrl)){
                    Toast.makeText(mContext, "capture sucess", Toast.LENGTH_SHORT).show();
                    FoodMaterialListActivity.this.startActivity(new Intent(mContext, CaptureActivity.class));
                }
                String captureUrlTMp = mSharedPreferences.getString("captureUrl", "");
                Log.e(TAG, "run: captureUrl===" + captureUrlTMp);

            }
        });


    }

    private void initView() {
        idRvMaterial = findViewById(R.id.id_rv_material);
        idCbSelect = findViewById(R.id.id_cb_select);
        idBtnDelete = findViewById(R.id.id_btn_delete);
        idBtnCapture = findViewById(R.id.id_btn_capture);
    }

    public Bitmap capture(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }


    public class FoodMaterialAdapter extends RecyclerView.Adapter<FoodMaterialAdapter.FoodMaterialViewHolder> {
        private final String TAG = "FoodMaterialAdapter";

        private List<FoodMaterialBean> mItemList;
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

        public FoodMaterialAdapter(List<FoodMaterialBean> itemList) {
            mItemList = itemList;
        }

        @Override
        public FoodMaterialViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food_material, viewGroup, false);

            return new FoodMaterialViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull FoodMaterialViewHolder foodMaterialViewHolder, final int i) {
            final FoodMaterialBean foodMaterialBean = mItemList.get(i);
            String desc = foodMaterialBean.getMaterialValue() + " " + foodMaterialBean.getUnit() + " " + foodMaterialBean.getMaterialName();
            foodMaterialViewHolder.idCb.setChecked(foodMaterialBean.isChecked());
            foodMaterialViewHolder.idCb.setText(desc);
            foodMaterialViewHolder.idCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    foodMaterialBean.setChecked(b);
                    mItemList.set(i, foodMaterialBean);
                    if (mIOnCheckedChangeListener != null) {
                        mIOnCheckedChangeListener.onCheckedChanged(b, foodMaterialBean, i);
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
        public void update(List<FoodMaterialBean> itemList) {
            mItemList.clear();
            mItemList.addAll(itemList);
            notifyDataSetChanged();
        }
    }
}
