package com.example.teamfoodie.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.teamfoodie.models.User;

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

    /**
     * Create string for creating user table in database
     * @param TABLE_NAME
     * @return
     */
    public String createUserTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE "+ TABLE_NAME +
                "( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT, "+
                USER_PASSWORD + " TEXT, "+
                USER_EMAIL + " TEXT );";

        return createTable;
    }

    /**
     *
     * Method takes in a User object allocate them appropriate into the ContentValue
     * format for query execution.
     *
     *
     * @param userObject
     * @return
     */
    public ContentValues addNewUser(User userObject) {
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userObject.getUsername());
        values.put(USER_PASSWORD, userObject.getPassword());
        values.put(USER_EMAIL, userObject.getEmail());
        return values;
    }

    /**
     * Result cursor from database is passed through parameters and used to set user
     * details and then return a PantryIngredient type object
     *
     * @param cursor
     * @return
     */
    public User findUser(Cursor cursor) {
        User existingUser = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            existingUser.setID(cursor.getInt(0));
            existingUser.setUsername(cursor.getString(1));
            existingUser.setPassword(cursor.getString(2));
            existingUser.setEmail(cursor.getString(3));
            cursor.close();
        } else {
            return null;
        }

        return existingUser;
    }

    /**
     * method takes in username, password, and database and uses the information to set variables within
     * database handler file before conducting search for user
     * with the use of the findhandle method in the database handler file.
     * @param user
     * @param pass
     * @param db
     * @return
     */
    public User checkLogin(String user, String pass, DatabaseHandler db){

        this.dbHandler = db;
        dbHandler.setUser(user, pass);

        object = dbHandler.findHandle(user, "User");
        if(object == null){
            System.out.println("check login was  null");
            return null;
        }else{
            currentUser = (User) object;

        }
        return currentUser;
    }



}
