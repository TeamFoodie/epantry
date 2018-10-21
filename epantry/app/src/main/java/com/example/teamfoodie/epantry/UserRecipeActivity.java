package com.example.teamfoodie.epantry;
/*
UserRecipeActivity include all main activity for user to add recipe
 */

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teamfoodie.R;
import com.example.teamfoodie.database.DatabaseHandler;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.Procedure;
import com.example.teamfoodie.models.Recipe;
//import com.example.teamfoodie.models.RecipeIngredient;
//import com.example.teamfoodie.models.UserRecipe;
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
    private DatabaseHandler database;
    public int currentUSER_ID;
    public int recipe_ID;
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

//    Spinner spinner;
    private ArrayList<Ingredient> mRecipeIngredientList;
    private ArrayList<Procedure> mProcedureList;
//    private List<String> mTagList;

    private SelectPicPopupWindow menuWindow;

    public static final int REQ_TAKE_PHOTO = 100;
    public static final int REQ_SELECT_PHOTO = 101;
    public static final int userID = 0;

    private Bitmap mBitmap;

    private UserRecipeActivity mActivity;
    private Context mContext;
//    List<String> IngredientUnit;//store Unit list
//    ArrayAdapter<String> adapter;//define a n arrayAdapter fir spinner

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = this;
        super.setContentView(R.layout.upload_recipe);
        initView(savedInstanceState);//initial view
        mRecipeIngredientList = new ArrayList<>();
        mProcedureList = new ArrayList<>();
//        mTagList = new ArrayList<>();

        mSharedPreferences = mContext.getSharedPreferences("cache",
                Context.MODE_PRIVATE);

        AndPermission.with(mActivity)
                .requestCode(100)
                .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
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
        final EditText idEtUnit = ingredientView.findViewById(R.id.id_et_unit);
//        final Spinner spinner= ingredientView.findViewById(R.id.id_et_Unit);
        final Ingredient recipeIngredient = new Ingredient();
        mRecipeIngredientList.add(recipeIngredient);

//        IngredientUnit= new ArrayList<String>();
//        IngredientUnit.add("grams");
//        IngredientUnit.add("tbsp");
//        IngredientUnit.add("cloves");
//        IngredientUnit.add("tsp");
//        IngredientUnit.add("cups");
//        IngredientUnit.add("litres");
//        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,IngredientUnit);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        idEtIngredient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    Ingredient recipeIngredient = mRecipeIngredientList.get(position);
                    recipeIngredient.setName(idEtIngredient.getText().toString().trim());
                    mRecipeIngredientList.set(position, recipeIngredient);
                    Log.d(TAG, "Ingredient onFocusChange: dismiss forces recipeIngredient===" + recipeIngredient.toString()
                    +"  set name "+recipeIngredient.getName()+"   mRecipeIngredientListName "+mRecipeIngredientList.get(position).getName());
                }
            }
        });
        idEtQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    Ingredient recipeIngredient = mRecipeIngredientList.get(position);
                    recipeIngredient.setMeasurement(Double.parseDouble(idEtQuantity.getText().toString().trim()));
                    mRecipeIngredientList.set(position, recipeIngredient);
                    Log.d(TAG, "Ingredient  onFocusChange: dismiss forces recipeIngredient===" + recipeIngredient.toString()
                            +"    set name "+recipeIngredient.getName()+"   set measurement "+recipeIngredient.getMeasurement()
                            +"   \nmRecipeIngredientListName "+mRecipeIngredientList.get(position).getName()+"   mRecipeIngredientListMeasurement "+mRecipeIngredientList.get(position).getMeasurement());
                }
            }
        });
        idEtUnit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    Ingredient recipeIngredient = mRecipeIngredientList.get(position);
                    recipeIngredient.setUnitCount(idEtQuantity.getText().toString().trim());
                    mRecipeIngredientList.set(position, recipeIngredient);
                    Log.d(TAG, "Ingredient  onFocusChange: dismiss forces recipeIngredient===" + recipeIngredient.toString()
                            +"    set name "+recipeIngredient.getName()+"   set measurement "+recipeIngredient.getMeasurement()
                            +"   \nmRecipeIngredientListName "+mRecipeIngredientList.get(position).getName()+"   mRecipeIngredientListMeasurement "+mRecipeIngredientList.get(position).getMeasurement()
                            +"    mRecipeIngredientListUnit "+mRecipeIngredientList.get(position).getUnitCount());
                }
            }
        });
    }

    private void addProcedureView() {
        View procedureView = LayoutInflater.from(mContext).inflate(R.layout.item_procedures, null, false);
        idLlProcedures.addView(procedureView);
        final int position = idLlProcedures.getChildCount() - 1;
        Log.d(TAG, "addProcedureView: position==" + position);
        final EditText idEtProcedure = procedureView.findViewById(R.id.id_et_procedure);
        final Procedure recipeProduce=new Procedure();
        mProcedureList.add(recipeProduce);
        idEtProcedure.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    Procedure recipeProcedure= mProcedureList.get(position);
                    recipeProcedure.setStep(idEtProcedure.getText().toString().trim());
                    mProcedureList.set(position,recipeProcedure);
                    Log.d(TAG, "Procedure  onFocusChange: dismiss forces procedure===" + idEtProcedure.getText().toString().trim());
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
                Log.d(TAG, "onClick: mProcedureList===" + mProcedureList.toString());
                break;
            case R.id.btn_addphoto:
                menuWindow = new SelectPicPopupWindow(mContext, itemsOnClick);
                menuWindow.showAtLocation(this.getWindow().getDecorView(),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.save:
                System.out.println("currentUSER_ID: " + currentUSER_ID);

                final String titleStr = title.getText().toString().trim();
                System.out.println("titleStr: " + titleStr);
                if (checkEmpty(titleStr, "The title cannot be empty ", title)) return;

                final String introductionStr = introduce.getText().toString().trim();
                System.out.println("introductionStr " + introductionStr);
                if (checkEmpty(introductionStr, "The introduction cannot be empty ", introduce))
                    return;

                String ingredientStr = mRecipeIngredientList.get(0).getName().toString();
                System.out.println("ingredientStr " + ingredientStr + "  " + mRecipeIngredientList.get(0).getName().toString()
                        + "    " + mRecipeIngredientList.get(0).getMeasurement());
                if (ingredientStr.contains("null")) {
                    showToast("The ingredients have not finished ");
                    return;
                }

                String proceduresStr = mProcedureList.get(0).getStep().toString();
                System.out.println("proceduresStr " + proceduresStr);
                if (proceduresStr.contains("null")) {
                    showToast("The procedures have not finished ");
                    return;
                }

                final int cookingTime = Integer.parseInt(CTime.getText().toString().trim());
                final String cookingTimeStr = String.valueOf(cookingTime);
                System.out.println("cookingTimeStr " + cookingTimeStr);
                if (checkEmpty(cookingTimeStr, "The Cooking Time cannot be empty ", CTime))
                    return;

                final int calorieCount = Integer.parseInt(NutritionalCount.getText().toString().trim());
                final String nutritionalCountStr = String.valueOf(calorieCount);
                System.out.println("calorieCount " + calorieCount + "   string " + nutritionalCountStr);
                if (checkEmpty(nutritionalCountStr, "The Nutritional Count cannot be empty ", NutritionalCount))
                    return;

                final int numberOfPeople = Integer.parseInt(NumberOfPeople.getText().toString().trim());
                final String NumberOfPeopleStr = String.valueOf(numberOfPeople);
                System.out.println("numberOfPeople " + numberOfPeople + "   string " + NumberOfPeopleStr);
                if (checkEmpty(NumberOfPeopleStr, "The Number Of People cannot be empty ", NumberOfPeople))
                    return;

                final String tagStr = Tag.getText().toString().trim();
                System.out.println("tagStr " + tagStr);
                if (checkEmpty(tagStr, "The tags cannot be empty ", Tag)) return;


                Recipe recipe = new Recipe(titleStr, introductionStr, calorieCount, cookingTime, currentUSER_ID, numberOfPeople, tagStr, mRecipeIngredientList, mProcedureList);


                Log.e(TAG, "run: ==" + (mBitmap == null));
                if (mBitmap != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    mBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                    String photoUrl = new String(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
                    recipe.setPhoto(photoUrl);
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("photoUrl", photoUrl);
                    System.out.println("photoUrl: " + photoUrl);
                    editor.commit();

                    String photoUrlTMp = mSharedPreferences.getString("photoUrl", "");
                    Log.e(TAG, "run: photo===" + photoUrlTMp);
                }
//                        userRecipe.setTags(tagStr);
                boolean recipeCreate = database.addHandle(recipe);
                if (recipeCreate) {
                    showCustomDialog(R.string.dialog_addedIng, "Recipe has successfully been added!");
//                            mActivity.startActivity(new Intent(mContext, SaveActivity.class));
                } else {
                    showCustomDialog(R.string.dialog_addedIng, "Recipe NOT added!");
                }
        }
    }
    private void showCustomDialog(int type, String message) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("Recipe Added");
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
    /**
     * Judge the edit box value, if it is empty, give a reminder.
     *
     * @param titleStr
     * @param msg
     * @param view
     * @return
     */
    private boolean checkEmpty(String titleStr, String msg, View view) {
        if (TextUtils.isEmpty(titleStr)) {
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
    private void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * initial view
     */
    private void initView(Bundle savedInstanceState) {
        title = findViewById(R.id.et_title);
        introduce = findViewById(R.id.introduce);
        idLlIngredient = findViewById(R.id.id_ll_ingredient);
        addstep = findViewById(R.id.addstep);
        CTime = findViewById(R.id.CTime);
        NutritionalCount = findViewById(R.id.NutritionalCount);
        NumberOfPeople = findViewById(R.id.NumberOfPeople);
        Tag = findViewById(R.id.Tag);
        save = findViewById(R.id.save);
        addIngredients = findViewById(R.id.addIngredients);
        photo = findViewById(R.id.btn_addphoto);
        imageView = findViewById(R.id.imageView);
        idLlProcedures = findViewById(R.id.id_ll_procedures);
        idLlRoot = findViewById(R.id.id_ll_root);
        database = new DatabaseHandler(this);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                System.out.println("Bundle extra was NULL from UploadRecipe Activity");
            } else {
                currentUSER_ID = extras.getInt("USER_ID");System.out.println("currentUSER_ID1  extras.getInt"+currentUSER_ID);
            }
        } else {
            currentUSER_ID = (Integer) savedInstanceState.getSerializable("USER_ID");System.out.println("currentUSER_ID2  extras.getInt"+currentUSER_ID);
            System.out.println("savedInstance was NULL");
        }
//        int userID = getIntent().getExtras().getInt("USER_ID");
//        System.out.println("userID"+userID);
//        user=dbHandler.getUser(userID);


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
