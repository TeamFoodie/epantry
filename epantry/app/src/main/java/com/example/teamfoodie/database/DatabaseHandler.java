package com.example.teamfoodie.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.teamfoodie.epantry.R;
import com.example.teamfoodie.models.PantryIngredient;
import com.example.teamfoodie.models.Recipe;
import com.example.teamfoodie.models.User;

import java.util.ArrayList;
import java.util.List;

/*
 * The DatabaseHandler class contains methods to establish a database
 * connection, read and store user details into a database.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PANTRY";

    //table names
    private static final String TABLE_INGREDIENT = "PantryIngredients";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_STORED_RECIPE = "StoredRecipe";


    private PantryIngredientTable pantryIngredientTable = new PantryIngredientTable();
    private UserTable userTable = new UserTable();
    private RecipeTable recipeTable = new RecipeTable();

    private String username = "";
    private String password = "";


    //initialize the database
    public DatabaseHandler(Context context)

    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(pantryIngredientTable.createIngredientTable(TABLE_INGREDIENT));
        db.execSQL(userTable.createUserTable(TABLE_USERS));
        db.execSQL(recipeTable.createRecipeTable(TABLE_STORED_RECIPE));
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORED_RECIPE);
        onCreate(db);
    }


    public boolean addHandle(Object object) {
        boolean createSuccessful = false;
        ContentValues values = null;
        String tableName = "";

        if (object instanceof PantryIngredient) {
            PantryIngredient ingredientObject = (PantryIngredient) object;
            tableName = TABLE_INGREDIENT;
            values = pantryIngredientTable.getIngredientContents(ingredientObject);
        } else if (object instanceof User) {
            User userObject = (User) object;
            tableName = TABLE_USERS;
            values = userTable.addNewUser(userObject);
        } else if (object instanceof Recipe){
            System.out.println("Database handler add handle");
            Recipe recipeObject = (Recipe) object;
            tableName = TABLE_STORED_RECIPE;
            values = recipeTable.addNewRecipe(recipeObject);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.insert(tableName, null, values);


        if (i == -1) {
            createSuccessful = false;
            System.out.println("could not populate");
        } else {
            createSuccessful = true;
            System.out.println("table populated");
        }
        System.out.println(createSuccessful);
        db.close();

        return createSuccessful;
    }

    public void setUser(String user, String pass) {
        this.username = user;
        this.password = pass;

    }

    public Object findHandle(String id, String tableName) {
        Object object = new Object();
        String query = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        PantryIngredient foundIngredient;
        User foundUser;
        Recipe storedRecipe;

        switch (tableName) {
            case "PantryIngredient":
                query = "Select * FROM " + TABLE_INGREDIENT + " WHERE IngredientID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                foundIngredient = pantryIngredientTable.findIngredient(cursor);
                System.out.println("FIND HANDLE FOR PANTRY INGREDIENT!!!!!!");
                object = (Object) foundIngredient;

                break;
            case "User":
                query = "SELECT * FROM " + TABLE_USERS + " WHERE UserName = '" + username + "' AND Password = '" + password + "'";

                System.out.println("user found : name: " + username + " password: " + password);
                cursor = db.rawQuery(query, null);
                foundUser = userTable.findUser(cursor);
                object = (Object) foundUser;
                break;
            case "StoredRecipe":
                query = "SELECT * FROM " + TABLE_STORED_RECIPE + " WHERE RecipeID = '" + Integer.parseInt(id);
                cursor = db.rawQuery(query, null);
                storedRecipe = recipeTable.findRecipe(cursor);
                object = (Object) storedRecipe;
                break;
            default:
                query = "Select * FROM " + TABLE_INGREDIENT + " WHERE IngredientID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                foundIngredient = pantryIngredientTable.findIngredient(cursor);
                object = (Object) foundIngredient;
                break;
        }
        db.close();
        return object;
    }

    public boolean updateQuantity(PantryIngredient ingredient) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values = pantryIngredientTable.updateQuantity(ingredient);
        String query = "IngredientID =" + ingredient.getIngredientID();
        return db.update(TABLE_INGREDIENT, values, query, null) > 0;

    }

    public List<PantryIngredient> loadAllPantryIngredients(int id) {
        List<PantryIngredient> ingredientList = new ArrayList<>();
        String query = "Select * FROM " + TABLE_INGREDIENT + " WHERE Owner" + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        ingredientList = pantryIngredientTable.loadAllPantryIngredients(cursor);


        return ingredientList;
    }


    public List<Recipe> loadAllRecipes(){
        List<Recipe> recipeList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_STORED_RECIPE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        recipeList = recipeTable.loadAllRecipes(cursor);

        return recipeList;
    }
    public boolean checkExistingUser(String username) {
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE username = '" + username + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            db.close();
            return false;
        } else {
            cursor.close();
            db.close();
            return true;
        }
    }
  
    public String getUserEmail(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String email = null;
        String query = "SELECT * FROM users WHERE username = '"+ username+"'";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            email = cursor.getString(3);
        }

        cursor.close();


        Log.d("getAllUserDetails()", cursor.toString()+ " Email: "+email);

        return email;
    }

//    public void populateRecipeDatabase(){
//
//        Recipe r1 = new Recipe("Afghans", R.drawable.afghans, "Afghans are a kiwi classic","https://edmondscooking.co.nz/recipes/biscuits/afghans/");
//        Recipe r2 = new Recipe("Anzac Biscuits", R.drawable.anzac_biscuits, "These are a soft chewy","https://edmondscooking.co.nz/recipes/biscuits/anzac-biscuits/" );
//        Recipe r3 = new Recipe("Apricot Balls", R.drawable.apricot_balls,"These are quickly","https://edmondscooking.co.nz/recipes/bliss-balls/apricot-bliss-balls/");
//        Recipe r4 = new Recipe("Apricot Jam", R.drawable.apricot_jam, "Simple Jam","https://edmondscooking.co.nz/recipes/jams-jellies/apricot-jam/");
//        Recipe r5 = new Recipe("Basic Biscuits", R.drawable.basic_biscuits, "Make one simple dough","https://edmondscooking.co.nz/recipes/biscuits/basic-refrigerator-biscuits/");
//        Recipe r6= new Recipe("Bliss Balls", R.drawable.bliss_balls, "Bliss balls","https://edmondscooking.co.nz/recipes/bliss-balls/apricot-cashew-bliss-balls/");
//        Recipe r7= new Recipe("Chelsea Buns", R.drawable.chelsea_buns, "Chelsea Buns","https://edmondscooking.co.nz/recipes/breads-and-buns/chelsea-buns/");
//        Recipe r8= new Recipe("Chocolate Cake", R.drawable.chocolate_cake, "Chocolate Cake ","https://edmondscooking.co.nz/recipes/cakes/chocolate-cake/");
//        Recipe r9= new Recipe("Chocolate Gateau", R.drawable.chocolate_gateau, "MMMMMM Gateau","https://edmondscooking.co.nz/recipes/cakes/christmas-chocolate-gateau/");
//        Recipe r10= new Recipe("Chocolate Meringue", R.drawable.chocolate_meringue_cake, "Meringue what?","https://edmondscooking.co.nz/recipes/cakes/chocolate-meringue-cake/");
//        Recipe r11=new Recipe("Chorizo and Tomato Salad", R.drawable.chorizo_and_tomato_salad, "Chorizo what a lovely sausage","https://edmondscooking.co.nz/recipes/salad/crisp-chorizo-tomato-salad-with-french-vinaigrette/");
//        Recipe r12= new Recipe("Crumpets", R.drawable.crumpets, "Ahhh the breakfast classic","https://edmondscooking.co.nz/recipes/breads-and-buns/crumpets/");
//        Recipe r13= new Recipe("Lamb and Feta Sliders", R.drawable.lamb_and_feta_sliders, "Sliders?! Miniture burgers!!","https://edmondscooking.co.nz/recipes/beef-pork-and-lamb/succulent-lamb-and-feta-sliders-with-minted-aioli/");
//        Recipe r14= new Recipe("Potato Salad", R.drawable.potato_salad, "Summer BBQ classic","https://edmondscooking.co.nz/recipes/salad/potato-salad/");
//        Recipe r15= new Recipe("Raspberry Jam", R.drawable.raspberry_jam, "Is the 'p' silent?","https://edmondscooking.co.nz/recipes/jams-jellies/raspberry-jam/");
//        Recipe r16= new Recipe("Soft White Loaf", R.drawable.soft_white_loaf, "Hot bread and the paper","https://edmondscooking.co.nz/recipes/breads-and-buns/edmonds-soft-white-loaf/");
//        Recipe r17= new Recipe("Wagyu Burger", R.drawable.wagyu_burgers, "What on earth is a Wagyu?","https://edmondscooking.co.nz/recipes/burgers-and-pizzas/wagyu-beef-burger-with-caramelised-onion-mayo/");
//
//
//        addHandle(r3);
//        addHandle(r4);
//        addHandle(r5);
//        addHandle(r6);
//        addHandle(r7);
//        addHandle(r8);
//        addHandle(r9);
//        addHandle(r10);
//        addHandle(r11);
//        addHandle(r12);
//        addHandle(r13);
//        addHandle(r14);
//        addHandle(r15);
//        addHandle(r16);
//        addHandle(r17);
//
//    }
}