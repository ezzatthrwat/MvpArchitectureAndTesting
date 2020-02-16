package com.example.e_commericemvp.Activities;

import android.app.Application;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }


    public MyApp getMyAppContext(){
        return this;
    }
}
