package com.example.setavita.epantry.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.setavita.epantry.models.FoodMaterialBean;
import com.example.setavita.epantry.models.IngredientBean;
import com.example.setavita.epantry.models.MainContentBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseDao {

    private static final String TAG = "SQLiteDatabaseDao";

    public static final String DATABASE_NAME = "cate_database";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_MAIN_CONTENT_TABLE = "main_content";
    private static final String DATABASE_FOOD_MATERIAL_TABLE = "food_material";


    public static final String KEY_TITLE = "title";
    public static final String KEY_INTRODUCTION = "introduction";
    public static final String KEY_INGREDIENTS = "ingredients";
    public static final String KEY_PROCEDURES = "procedures";
    public static final String KEY_COOKING_TIME = "cooking_time";
    public static final String KEY_NUTRITIONAL_COUNT = "nutritional_count";
    public static final String KEY_PHOTO_URL = "photoUrl";
    public static final String KEY_NUMBEROFPEOPLE = "NumberOfPeople";
    public static final String KEY_TAGS = "tags";

    private static final String CREATE_CITY_TABLE = "CREATE TABLE IF NOT EXISTS " + DATABASE_MAIN_CONTENT_TABLE + " (id integer primary key autoincrement, "
            + KEY_TITLE + " text not null,  " + KEY_INTRODUCTION + " text not null, " + KEY_INGREDIENTS + " text not null, " + KEY_PROCEDURES
            + " text not null, " + KEY_COOKING_TIME + " text not null, " + KEY_NUTRITIONAL_COUNT + " text not null, " + KEY_NUMBEROFPEOPLE
            + " text not null, " + KEY_TAGS + " text not null);";
//            + " text not null, " + KEY_PHOTO_URL + " text not null, "+ KEY_TAGS + " text not null);";

    public static final String KEY_FOOD_MATERIAL_NAME = "material_name";
    public static final String KEY_FOOD_MATERIAL_VALUE = "material_value";
    public static final String KEY_FOOD_MATERIAL_UNIT = "material_unit";

    private static final String CREATE_MATERIAL_TABLE = "CREATE TABLE IF NOT EXISTS " + DATABASE_FOOD_MATERIAL_TABLE + " (id integer primary key autoincrement, "
            + KEY_FOOD_MATERIAL_NAME + " text not null,  " + KEY_FOOD_MATERIAL_VALUE + " text not null, " + KEY_FOOD_MATERIAL_UNIT + " text not null);";

    private static List<String> tables;

    private Context mCtx;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            for (String tableSql : tables) {
                Log.i(TAG, "Creating DataBase: " + tableSql);
                db.execSQL(tableSql);
            }
        }

        /*update version*/
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        }
    }

    private static class Singleton {
        private static final SQLiteDatabaseDao INSTANCE = new SQLiteDatabaseDao();
    }


    /* get singleton     */
    public static SQLiteDatabaseDao getInstance() {
        return SQLiteDatabaseDao.Singleton.INSTANCE;
    }

    /* initial context*/
    public SQLiteDatabaseDao init(Context ctx) {
        this.mCtx = ctx;
        tables = new ArrayList<>();
        tables.add(CREATE_CITY_TABLE);
        tables.add(CREATE_MATERIAL_TABLE);
        openDb();
        return this;
    }

    /*open database     */
    private SQLiteDatabaseDao openDb() throws SQLException {
        if (mCtx != null) {
            mDbHelper = new DatabaseHelper(mCtx);
            mDb = mDbHelper.getReadableDatabase();
        }
        return this;
    }

    /**
     * close database
     */
    public void closeDb() {
        mDbHelper.close();
    }

    /**
     * insert content in initial page
     *
     * @param mainContentBean
     * @return
     */
    public long insertMainContent(MainContentBean mainContentBean) {
        long result = -1;
        mDb.beginTransaction();
        try {
            ContentValues cityValue = new ContentValues();
            cityValue.put(KEY_TITLE, mainContentBean.getTitle());
            cityValue.put(KEY_INTRODUCTION, mainContentBean.getIntroduction());
            Gson gson = new Gson();
            cityValue.put(KEY_INGREDIENTS, gson.toJson(mainContentBean.getIngredientBeanList()));
            cityValue.put(KEY_PROCEDURES, gson.toJson(mainContentBean.getProcedureList()));
            cityValue.put(KEY_COOKING_TIME, mainContentBean.getCookingTime());
            cityValue.put(KEY_NUTRITIONAL_COUNT, mainContentBean.getNutritionalCount());
            cityValue.put(KEY_NUMBEROFPEOPLE, mainContentBean.getNumberOfPeople());
//            cityValue.put(KEY_PHOTO_URL, mainContentBean.getPhotoUrl());
            cityValue.put(KEY_TAGS, mainContentBean.getTags());
            result = mDb.insert(DATABASE_MAIN_CONTENT_TABLE, null, cityValue);
            Log.e(TAG, "insertMainContent: result==" + result);
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            mDb.endTransaction();
        }
        return result;
    }


    /**
     * get the list
     *
     * @return
     */
    public List<MainContentBean> queryMainContent() {
        List<MainContentBean> mainContentBeanList = new ArrayList<>();
        Cursor cursor = null;
        if (cursor == null) {
            String sql = "select * from " + DATABASE_MAIN_CONTENT_TABLE;
            cursor = mDb.rawQuery(sql, null);
        }
        while (cursor.moveToNext()) {
            MainContentBean mainContentBean = new MainContentBean();
            mainContentBean.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            mainContentBean.setIntroduction(cursor.getString(cursor.getColumnIndex(KEY_INTRODUCTION)));
            String ingredientsStr = cursor.getString(cursor.getColumnIndex(KEY_INGREDIENTS));
            List<IngredientBean> ingredientBeanList = new Gson().fromJson(ingredientsStr, new TypeToken<List<IngredientBean>>() {
            }.getType());
            String proceduresStr = cursor.getString(cursor.getColumnIndex(KEY_PROCEDURES));
            List<String> procedureList = new Gson().fromJson(proceduresStr, new TypeToken<List<String>>() {
            }.getType());
            mainContentBean.setIngredientBeanList(ingredientBeanList);
            mainContentBean.setProcedureList(procedureList);
            mainContentBean.setCookingTime(cursor.getString(cursor.getColumnIndex(KEY_COOKING_TIME)));
            mainContentBean.setNutritionalCount(cursor.getString(cursor.getColumnIndex(KEY_NUTRITIONAL_COUNT)));
            mainContentBean.setNumberOfPeople(cursor.getString(cursor.getColumnIndex(KEY_NUMBEROFPEOPLE)));
//            mainContentBean.setPhotoUrl(cursor.getString(cursor.getColumnIndex(KEY_PHOTO_URL)));
            mainContentBean.setTags(cursor.getString(cursor.getColumnIndex(KEY_TAGS)));
            mainContentBeanList.add(mainContentBean);
            Log.d(TAG, "queryMainContent: mainContentBeanList==" +mainContentBeanList.size());
        }
        cursor.close();
        return mainContentBeanList;
    }

    /**
     * insert food MaterialBean
     *
     * @param foodMaterialBean
     * @return
     */
    public long insertFoodMaterial(FoodMaterialBean foodMaterialBean) {
        long result = -1;
        mDb.beginTransaction();
        try {
            ContentValues materialValue = new ContentValues();
            materialValue.put(KEY_FOOD_MATERIAL_NAME, foodMaterialBean.getMaterialName());
            materialValue.put(KEY_FOOD_MATERIAL_VALUE, foodMaterialBean.getMaterialValue());
            materialValue.put(KEY_FOOD_MATERIAL_UNIT, foodMaterialBean.getUnit());
            result = mDb.insert(DATABASE_FOOD_MATERIAL_TABLE, null, materialValue);
            Log.e(TAG, "insertCity: relt==" + result);
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            mDb.endTransaction();
        }
        return result;
    }

    /**
     * delete food MaterialBean
     *
     * @param id
     * @return
     */
    public long deleteCity(int id) {
        long result = -1;
        try {
            result = mDb.delete(DATABASE_FOOD_MATERIAL_TABLE, "id = ? ", new String[]{String.valueOf(id)});
            Log.e(TAG, "insertSelectedCity: relt==" + result);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * get the list
     *
     * @return
     */
    public List<FoodMaterialBean> queryFoodMaterial() {
        List<FoodMaterialBean> foodMaterialBeanList = new ArrayList<>();
        Cursor cursor = null;
        if (cursor == null) {
            String sql = "select * from " + DATABASE_FOOD_MATERIAL_TABLE;
            cursor = mDb.rawQuery(sql, null);
        }
        while (cursor.moveToNext()) {
            FoodMaterialBean foodMaterialBean = new FoodMaterialBean();
            foodMaterialBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
            foodMaterialBean.setMaterialName(cursor.getString(cursor.getColumnIndex(KEY_FOOD_MATERIAL_NAME)));
            foodMaterialBean.setMaterialValue(cursor.getString(cursor.getColumnIndex(KEY_FOOD_MATERIAL_VALUE)));
            foodMaterialBean.setUnit(cursor.getString(cursor.getColumnIndex(KEY_FOOD_MATERIAL_UNIT)));
            foodMaterialBean.setChecked(false);
            foodMaterialBeanList.add(foodMaterialBean);
        }
        cursor.close();
        return foodMaterialBeanList;
    }



}
