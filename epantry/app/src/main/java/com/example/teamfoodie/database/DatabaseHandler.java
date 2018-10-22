package com.example.teamfoodie.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.teamfoodie.models.Dietary;
import com.example.teamfoodie.models.DietaryRequirement;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.PantryIngredient;
import com.example.teamfoodie.models.Procedure;
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
    private static final String DATABASE_NAME = "epaaantryDatabase";

    //table names
    private static final String TABLE_PANTRY = "PantryIngredients";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_RECIPE = "Recipe";
    private static final String TABLE_INGREDIENTS = "RecipeIngredients";
    private static final String TABLE_PROCEDURES = "RecipeProcedures";
    private static final String TABLE_DIETARY_REQUIREMENTS = "DietaryRequirements";
    private static final String TABLE_PREFERENCES = "Preferences";


    private PantryIngredientTable pantryIngredientTable = new PantryIngredientTable();
    private UserTable userTable = new UserTable();
    private RecipeTable recipeTable = new RecipeTable();
    private RecipeIngredientsTable ingredientsTable = new RecipeIngredientsTable();
    private RecipeProceduresTable proceduresTable = new RecipeProceduresTable();
    private DietaryRequirementsTable dietaryTable = new DietaryRequirementsTable();
    private PreferencesTable preferencesTable = new PreferencesTable();

    private String username = "";
    private String password = "";
    private int currentUSER;


    //initialize the database
    public DatabaseHandler(Context context)

    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(pantryIngredientTable.createIngredientTable(TABLE_PANTRY));
        db.execSQL(userTable.createUserTable(TABLE_USERS));
        db.execSQL(recipeTable.createRecipeTable(TABLE_RECIPE));
        db.execSQL(ingredientsTable.createRecipeIngredientTable(TABLE_INGREDIENTS));
        db.execSQL(proceduresTable.createRecipeProcedureTable(TABLE_PROCEDURES));
        db.execSQL(dietaryTable.createDietaryTable(TABLE_DIETARY_REQUIREMENTS));
        db.execSQL(preferencesTable.createPreferencesTable(TABLE_PREFERENCES));
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROCEDURES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIETARY_REQUIREMENTS);
        onCreate(db);
    }


    /**
     * Method handles the insertion of objects into database tables.
     * <p>
     * objects are passed through as unknown object type and then passed through switch case
     * to determine which class type the object belongs to and then inserted to appropriate table.
     *
     * @param object
     * @return
     */
    public boolean addHandle(Object object) {
        boolean createSuccessful = false;
        ContentValues values = null;
        String tableName = "";
        boolean newRecipe = false;
        Recipe recipeObject = new Recipe();

        if (object instanceof PantryIngredient) {
            PantryIngredient ingredientObject = (PantryIngredient) object;
            tableName = TABLE_PANTRY;
            values = pantryIngredientTable.getIngredientContents(ingredientObject);
        } else if (object instanceof User) {
            User userObject = (User) object;
            tableName = TABLE_USERS;
            values = userTable.addNewUser(userObject);
        } else if (object instanceof Recipe) {
            recipeObject = (Recipe) object;
            tableName = TABLE_RECIPE;
            values = recipeTable.addNewRecipe(recipeObject);
            newRecipe = true;
        } else if(object instanceof DietaryRequirement){
            DietaryRequirement dietary = (DietaryRequirement) object;
            tableName = TABLE_DIETARY_REQUIREMENTS;
            values = dietaryTable.addNewDietaryRequirement(dietary);
        } else if(object instanceof ArrayList){
            ArrayList<Integer> thresholds = new ArrayList<>();
            thresholds = (ArrayList<Integer>) object;
            tableName = TABLE_PREFERENCES;
            values = preferencesTable.getThresholds(thresholds);
            
        }
        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.insert(tableName, null, values);


        if (i == -1) {
            createSuccessful = false;
            System.out.println("could not populate");
        } else {
            createSuccessful = true;
            System.out.println("table populated");
            if (newRecipe) {
                addRecipeDetails(i, recipeObject);
            }
        }
        System.out.println(createSuccessful);
        db.close();


        return createSuccessful;
    }

    private void addRecipeDetails(double id, Recipe recipe) {
        List<Ingredient> rList = recipe.getIngredients();
        List<Procedure> pList = recipe.getProcedures();

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues ingredientValues = new ContentValues();
        for (int i = 0; i < rList.size(); i++) {
            ingredientValues.put(ingredientsTable.RECIPE_ID, id);
            ingredientValues.put(ingredientsTable.INGREDIENT_NAME, rList.get(i).getName());
            ingredientValues.put(ingredientsTable.MEASUREMENT, rList.get(i).getMeasurement());
            ingredientValues.put(ingredientsTable.UNIT_COUNT, rList.get(i).getUnitCount());
            DB.insert(TABLE_INGREDIENTS, null, ingredientValues);
        }

        ContentValues procedureValues = new ContentValues();
        for (int i = 0; i < pList.size(); i++) {
            procedureValues.put(proceduresTable.RECIPE_ID, id);
            procedureValues.put(proceduresTable.PROCEDURE, pList.get(i).getStep());
            DB.insert(TABLE_PROCEDURES, null, procedureValues);
        }


        DB.close(); // Now close the DB Object

    }

    /**
     * method sets user password and user name accordindly to be used in other queries.
     *
     * @param user
     * @param pass
     */
    public void setUser(String user, String pass) {
        this.username = user;
        this.password = pass;

    }

    public void setUSER_ID(int user_id){
        this.currentUSER = user_id;

    }

    /**
     * Method handles search queries. ID and table name is passed through parameters and then a switch case.
     * Methods implement relies on the tablename entered from other classes and then ID is used to search actual
     * database tables.
     *
     * @param id
     * @param tableName
     * @return
     */
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
                query = "Select * FROM " + TABLE_PANTRY + " WHERE IngredientID = " + "'" + id + "' AND Owner = '" + currentUSER + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                foundIngredient = pantryIngredientTable.findIngredient(cursor);

                if(foundIngredient != null){
                    System.out.println("user in the query is " + currentUSER);
                    System.out.println("FIND HANDLE FOR PANTRY INGREDIENT!!!!!! owner is " + foundIngredient.getOwner());
                }

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
                query = "SELECT * FROM " + TABLE_RECIPE + " WHERE RecipeID = '" + Integer.parseInt(id) + "'";
                cursor = db.rawQuery(query, null);
                storedRecipe = recipeTable.findRecipe(cursor);
                object = (Object) storedRecipe;
                break;
            case "ChangingUser":
                query = "SELECT * FROM " + TABLE_USERS + " WHERE UserID = '" + Integer.parseInt(id) + "'";// AND Password = '" + password + "'";
                cursor = db.rawQuery(query, null);
                foundUser = userTable.findUser(cursor);
                object = (Object) foundUser;
                break;
            default:
                query = "Select * FROM " + TABLE_PANTRY + " WHERE IngredientID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                foundIngredient = pantryIngredientTable.findIngredient(cursor);
                object = (Object) foundIngredient;
                break;
        }
        db.close();
        return object;
    }

    /**
     * Method executes update query to database - it is specific to update quantity of pantry ingredient
     * as only our pantry ingredient requires update from database.
     *
     * @param ingredient
     * @return
     */
    public boolean updateQuantity(PantryIngredient ingredient) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values = pantryIngredientTable.updateQuantity(ingredient);
        String query = "IngredientID =" + ingredient.getIngredientID();
        return db.update(TABLE_PANTRY, values, query, null) > 0;

    }

    /**
     * Method takes in userID of current user and loads all ingredients of current user
     * only.
     *
     * @param id
     * @return
     */
    public List<PantryIngredient> loadAllPantryIngredients(int id) {
        List<PantryIngredient> ingredientList = new ArrayList<>();
        String query = "Select * FROM " + TABLE_PANTRY + " WHERE Owner" + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        ingredientList = pantryIngredientTable.loadAllPantryIngredients(cursor);


        return ingredientList;
    }

    public List<Object> loadAllRecipeDetails(int id, int type) {
        List<Ingredient> ingredientList = new ArrayList<>();
        List<Procedure> procedureList = new ArrayList<>();
        List<Object> objects = new ArrayList<>();
        String query = "";
        Cursor cursor = null;

        switch (type) {
            case 1:
                query = "Select * FROM " + TABLE_INGREDIENTS + " WHERE RecipeID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                ingredientList = ingredientsTable.loadAllRecipeIngredients(cursor);
                objects.addAll(ingredientList);
                break;
            case 2:
                query = "Select * FROM " + TABLE_PROCEDURES + " WHERE RecipeID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                procedureList = proceduresTable.loadAllRecipeProcedures(cursor);
                objects.addAll(procedureList);
                break;
            default:
                objects = null;
        }

        return objects;


    }


    /**
     * Method handles the pulling of all recipes from the database.
     *
     * @return
     */
    public List<Recipe> loadAllRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RECIPE;
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

    /**
     * This method is used in the Navigation Drawer to display the
     * User's email details.
     *
     * @param username          String
     * @return User's email     String
     */
    public String getUserEmail(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String email = null;
        String query = "SELECT * FROM users WHERE username = '"+ username+"'";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            email = cursor.getString(3);
        }

        cursor.close();

        return email;
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new User();

        String query = "SELECT * FROM users WHERE UserID = '"+ id+"'";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));
        }

        cursor.close();

        return user;
    }

    public void updateUserDetails(ContentValues values, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_USERS, values, "UserID = "+id, null);
        db.close();
    }

    public void updatePantryQuantity(ContentValues values, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_PANTRY, values, "IngredientID = "+id, null);
        db.close();
    }


    public void populateRecipeDatabase() {
        PopulateRecipeTable.populateRecipeDatabase(this);
    }
}