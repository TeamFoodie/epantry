package com.example.setavita.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.setavita.models.PantryIngredient;
import com.example.setavita.models.User;

public class UserTable {
    //username and password for current user
    private String username;
    private String password;


    //columns for User Table
    private static final String USER_ID = "UserID";
    private static final String USER_NAME = "UserName";
    private static final String USER_PASSWORD = "Password";
    private static final String USER_EMAIL = "Email";
    private static final String[] USER_COLUMNS = {USER_ID, USER_NAME, USER_PASSWORD, USER_EMAIL};


    private DatabaseHandler dbHandler;
    private User currentUser;
    private Object object;

    public String createUserTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE "+ TABLE_NAME +
                "( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT, "+
                USER_PASSWORD + " TEXT, "+
                USER_EMAIL + " TEXT );";

        return createTable;
    }

    public ContentValues getUserContents(User userObject) {
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userObject.getUsername());
        values.put(USER_PASSWORD, userObject.getPassword());
        values.put(USER_EMAIL, userObject.getEmail());


        System.out.println("User table!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return values;
    }

    public User findUser(Cursor cursor) {
        User user = new User();// ingredient = new PantryIngredient();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setID(cursor.getInt(0));//.setIngredientID(cursor.getString(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            cursor.close();
        } else {
            return null;
        }

        return user;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public boolean checkLogin(String user, String pass, DatabaseHandler dbHandler){

        this.dbHandler = dbHandler;
        dbHandler.setUser(user, pass);

        object = dbHandler.findHandle(user, "User");
        if(object == null){
            System.out.println("entered object is null");
            return false;
        }else{
            currentUser = (User) object;

        }
        return true;
    }



}
