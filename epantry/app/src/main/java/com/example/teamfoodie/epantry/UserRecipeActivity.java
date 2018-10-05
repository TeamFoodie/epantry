package com.example.teamfoodie.epantry;
/*
UserRecipeActivity include all main activity for user to add recipe
 */
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.teamfoodie.database.SQLiteDatabaseDao;
import com.example.teamfoodie.models.ShoppingList;
import com.example.teamfoodie.models.RecipeIngredient;
import com.example.teamfoodie.models.UserRecipe;
import com.example.teamfoodie.utils.AboutBitmap;
import com.example.teamfoodie.utils.SelectPicPopupWindow;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "UserRecipeActivity";
    protected EditText title;
    protected EditText introduce;
    protected LinearLayout idLlIngredient;
    protected Button addstep;
    protected EditText CTime;
    protected EditText NutritionalCount;
    protected EditText NumberOfPeople;
    protected EditText Tag;
    protected Button save;
    protected ImageView imageView;
    protected Button addIngredients;
    protected Button photo;
    protected LinearLayout idLlProcedures;
    protected LinearLayout idLlRoot;


    private List<RecipeIngredient> mRecipeIngredientList;
    private List<String> mProcudureList;

    private SelectPicPopupWindow menuWindow;

    public static final int REQ_TAKE_PHOTO = 100;
    public static final int REQ_SELECT_PHOTO = 101;

    private Bitmap mBitmap;

    private UserRecipeActivity mActivity;
    private Context mContext;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        super.setContentView(R.layout.activity_make_food);
        initView();
        mRecipeIngredientList = new ArrayList<>();
        mProcudureList = new ArrayList<>();

        mSharedPreferences = mContext.getSharedPreferences("cache",
                Context.MODE_PRIVATE);

        AndPermission.with(mActivity)
                .requestCode(100)
                .permission(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .send();

        //default add one View of ingredient
        addIngredientView();

        //default add one view of procedures
        addProcedureView();
    }

    private void addIngredientView() {
        View ingredientView = LayoutInflater.from(this).inflate(R.layout.item_ingredient, null, false);
        idLlIngredient.addView(ingredientView);
        final int position = idLlIngredient.getChildCount() - 1;
        Log.d(TAG, "addIngredientView: position==" + position);
        final EditText idEtIngredient = ingredientView.findViewById(R.id.id_et_ingredient);
        final EditText idEtQuantity = ingredientView.findViewById(R.id.id_et_quantity);
        final RecipeIngredient recipeIngredient = new RecipeIngredient();
        mRecipeIngredientList.add(recipeIngredient);
        idEtIngredient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    RecipeIngredient recipeIngredient = mRecipeIngredientList.get(position);
                    recipeIngredient.setIngredient(idEtIngredient.getText().toString().trim());
                    mRecipeIngredientList.set(position, recipeIngredient);
                    Log.d(TAG, "Ingredient  onFocusChange: dismiss fouces recipeIngredient===" + recipeIngredient.toString());
                }
            }
        });
        idEtQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    RecipeIngredient recipeIngredient = mRecipeIngredientList.get(position);
                    recipeIngredient.setQuantity(idEtQuantity.getText().toString().trim());
                    mRecipeIngredientList.set(position, recipeIngredient);
                    Log.d(TAG, "Quantity  onFocusChange: dismiss fouces recipeIngredient===" + recipeIngredient.toString());
                }
            }
        });
    }

    private void addProcedureView() {
        View procudureView = LayoutInflater.from(mContext).inflate(R.layout.item_procedures, null, false);
        idLlProcedures.addView(procudureView);
        final int position = idLlProcedures.getChildCount() - 1;
        Log.d(TAG, "addProcedureView: position==" + position);
        final EditText idEtProcedure = procudureView.findViewById(R.id.id_et_procedure);
        mProcudureList.add("");
        idEtProcedure.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    mProcudureList.set(position, idEtProcedure.getText().toString().trim());
                    Log.d(TAG, "Quantity  onFocusChange: dismiss fouces ingredientBean===" + idEtProcedure.getText().toString().trim());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        //Because we get the data after we lose focus, we make sure we get the data every time we click on it
        setFoucs(idLlRoot);

        switch (view.getId()) {
            case R.id.addIngredients:
                //add  ingredients
                // from 0 start
                addIngredientView();

                Log.d(TAG, "onClick: mRecipeIngredientList==" + mRecipeIngredientList.toString());
                break;
            case R.id.addstep:
                //add step
                addProcedureView();
                Log.d(TAG, "onClick: mProcudureList===" + mProcudureList.toString());
                break;
            case R.id.btn_addphoto:
                menuWindow = new SelectPicPopupWindow(mContext, itemsOnClick);
                menuWindow.showAtLocation(this.getWindow().getDecorView(),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.save:
                final String titleStr = title.getText().toString().trim();
                if (checkEmpty(titleStr, "The title cannot be empty ", title)) return;
                final String indroductionStr = introduce.getText().toString().trim();
                if (checkEmpty(indroductionStr, "The indroduction cannot be empty ", introduce)) return;
                String ingredientStr = mRecipeIngredientList.toString();
                if (ingredientStr.contains("null")){
                    showToast("The ingredients have not finished ");
                    return;
                }
                String proceduresStr = mProcudureList.toString();
                if (proceduresStr.contains("null")){
                    showToast("The procedures have not finished ");
                    return;
                }
                final String cookingTimeStr = CTime.getText().toString().trim();
                if (checkEmpty(cookingTimeStr, "The Cooking Time cannot be empty ", CTime)) return;
                final String nutritionalCountStr = NutritionalCount.getText().toString().trim();
                if (checkEmpty(nutritionalCountStr, "The Nutritional Count cannot be empty ", NutritionalCount)) return;
                final String NumberOfPeopleStr=NumberOfPeople.getText().toString().trim();
                if (checkEmpty(NumberOfPeopleStr, "The Number Of People cannot be empty ", NutritionalCount)) return;
                final String tagStr = Tag.getText().toString().trim();
                if (checkEmpty(tagStr, "The tags cannot be empty ", Tag)) return;


                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        UserRecipe userRecipe = new UserRecipe();
                        userRecipe.setTitle(titleStr);
                        userRecipe.setIntroduction(indroductionStr);
                        userRecipe.setRecipeIngredientList(mRecipeIngredientList);
                        userRecipe.setProcedureList(mProcudureList);
                        userRecipe.setCookingTime(cookingTimeStr);
                        userRecipe.setNutritionalCount(nutritionalCountStr);
                        userRecipe.setNumberOfPeople(NumberOfPeopleStr);
                        Log.e(TAG, "run: ==" + (mBitmap==null));
                        if (mBitmap != null) {
                            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                            mBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                            String photoUrl = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT));
                            userRecipe.setPhotoUrl(photoUrl);
                            SharedPreferences.Editor editor = mSharedPreferences.edit();
                            editor.putString("photoUrl", photoUrl);
                            editor.commit();

                            //
                            String photoUrlTMp = mSharedPreferences.getString("photoUrl", "");
                            Log.e(TAG, "run: photo===" + photoUrlTMp);
                        }
                        userRecipe.setTags(tagStr);
                        SQLiteDatabaseDao sqLiteDatabaseDao = SQLiteDatabaseDao.getInstance().init(mContext);
                        long isSucess = sqLiteDatabaseDao.insertMainContent(userRecipe);
                        if (isSucess!=-1){
                            Log.e(TAG, "run: 1111111111");
                            addFoodMaterial(sqLiteDatabaseDao);

                            mActivity.startActivity(new Intent(mContext, SaveActivity.class));
                            mActivity.finish();
                        }else{
                            mActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showToast("save failed");
                                }
                            });
                        }
                    }
                }.start();
                break;
        }
    }

    public void addFoodMaterial(SQLiteDatabaseDao sqLiteDatabaseDao) {
        List<ShoppingList> shoppingLists = sqLiteDatabaseDao.queryFoodMaterial();
        if (shoppingLists.size()==0){
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.setUnit("g");
            shoppingList.setMaterialValue("50");
            shoppingList.setMaterialName("Cheese");
            sqLiteDatabaseDao.insertFoodMaterial(shoppingList);
            ShoppingList shoppingList1 = new ShoppingList();
            shoppingList1.setUnit("ml");
            shoppingList1.setMaterialValue("200");
            shoppingList1.setMaterialName("coconut milk");
            sqLiteDatabaseDao.insertFoodMaterial(shoppingList1);
            ShoppingList shoppingList2 = new ShoppingList();
            shoppingList2.setUnit("g");
            shoppingList2.setMaterialValue("50");
            shoppingList2.setMaterialName("mayonnaise");
            sqLiteDatabaseDao.insertFoodMaterial(shoppingList2);
            ShoppingList shoppingList3 = new ShoppingList();
            shoppingList3.setUnit("g");
            shoppingList3.setMaterialValue("250");
            shoppingList3.setMaterialName("butter");
            sqLiteDatabaseDao.insertFoodMaterial(shoppingList3);
            ShoppingList shoppingList4 = new ShoppingList();
            shoppingList4.setUnit("g");
            shoppingList4.setMaterialValue("50");
            shoppingList4.setMaterialName("chicken");
            sqLiteDatabaseDao.insertFoodMaterial(shoppingList4);
        }
    }

    /**
     * Judge the edit box value, if it is empty, give a reminder.
     *
     * @param titleStr
     * @param msg
     * @param view
     * @return
     */
    private boolean checkEmpty(String titleStr, String msg, View view) {
        if (TextUtils.isEmpty(titleStr)){
            showToast(msg);
            setFoucs(view);
            return true;
        }
        return false;
    }

    /**
     * get focus
     *
     * @param view
     */
    private void setFoucs(View view) {
        view.setFocusable(true);
        view.requestFocus();
        view.setFocusableInTouchMode(true);
        view.requestFocusFromTouch();
    }

    /**
     * pop up toast
     *
     * @param msg
     */
    private void showToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * initial view
     *
     */
    private void initView() {
        title = findViewById(R.id.et_title);
        introduce = findViewById(R.id.introduce);
        idLlIngredient = findViewById(R.id.id_ll_ingredient);
        addstep = findViewById(R.id.addstep);
        CTime = findViewById(R.id.CTime);
        NutritionalCount = findViewById(R.id.NutritionalCount);
        NumberOfPeople=findViewById(R.id.NumberOfPeople);
        Tag = findViewById(R.id.Tag);
        save = findViewById(R.id.save);
        addIngredients = findViewById(R.id.addIngredients);
        photo = findViewById(R.id.btn_addphoto);
        imageView=findViewById(R.id.imageView);
        idLlProcedures = findViewById(R.id.id_ll_procedures);
        idLlRoot = findViewById(R.id.id_ll_root);


        addstep.setOnClickListener(mActivity);
        save.setOnClickListener(mActivity);
        addIngredients.setOnClickListener(mActivity);
        photo.setOnClickListener(mActivity);
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.takePhotoBtn:
                    //take photo
                    mFile = AboutBitmap.openCamera(mActivity, REQ_TAKE_PHOTO);
                    break;
                case R.id.pickPhotoBtn:
                    //select album
                    AboutBitmap.openPic(mActivity, REQ_SELECT_PHOTO);
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * judge permission
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    private int REQUEST_CODE_SETTING = 300;
    private File mFile;
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            if (AndPermission.hasAlwaysDeniedPermission(mActivity, deniedPermissions)) {
                // use default dialog
                AndPermission.defaultSettingDialog(mActivity, REQUEST_CODE_SETTING).show();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case REQ_TAKE_PHOTO:
                    //the photo is taken
                    mBitmap = AboutBitmap.getBitmapFromUri(AboutBitmap.getCameraFileUri(mActivity, mFile), mActivity, imageView);
                    break;
                case REQ_SELECT_PHOTO:
                    //Invoke clipping after calling album
                    if (data == null || data.getData() == null) {
                        return;
                    }
                    mBitmap = AboutBitmap.getBitmapFromUri(data.getData(), mActivity, imageView);
                    break;
                default:
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
