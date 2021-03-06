package com.example.teamfoodie.models;

/*
 * The User class stores information about a
 * user's name and password
 */
public class User {

    private String username;
    private String password;
    private String email;
    private int id;

    public User() {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
