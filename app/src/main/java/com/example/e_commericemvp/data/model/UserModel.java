package com.example.e_commericemvp.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UserModel {


    @PrimaryKey(autoGenerate = true)
    private int UserID ;
    private String Username ;
    private String Password ;
    private String UserToken ;

    @Ignore
    public UserModel(){

    }
    @Ignore
    public UserModel(String username, String password , String userToken) {
        Username = username;
        Password = password;
        UserToken = userToken ;
    }

    public UserModel(int UserID, String Username, String Password , String UserToken) {
        this.UserID = UserID;
        this.Username = Username;
        this.Password = Password;
        this.UserToken = UserToken ;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserToken() {
        return UserToken;
    }

    public void setUserToken(String userToken) {
        UserToken = userToken;
    }
}
