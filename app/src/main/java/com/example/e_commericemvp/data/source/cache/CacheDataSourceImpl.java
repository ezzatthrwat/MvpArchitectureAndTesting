package com.example.e_commericemvp.data.source.cache;

import android.content.SharedPreferences;

public class CacheDataSourceImpl  implements CacheDataSource{
    private final SharedPreferences sharedPreferences ;
    public CacheDataSourceImpl(SharedPreferences myPref) {
        this.sharedPreferences = myPref;
    }

    @Override
    public int getUserID() {
        return sharedPreferences.getInt("UserID" , 0);
    }

    @Override
    public void setUserID(int userID) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserID", userID);
        editor.apply();
    }
}
