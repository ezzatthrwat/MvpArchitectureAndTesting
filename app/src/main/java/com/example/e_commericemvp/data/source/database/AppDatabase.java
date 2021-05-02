package com.example.e_commericemvp.data.source.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.e_commericemvp.data.model.CartItemsModel;
import com.example.e_commericemvp.data.model.UserModel;
import com.example.e_commericemvp.data.model.ProductModel;

@Database(entities = {ProductModel.class , CartItemsModel.class , UserModel.class} , version = 25, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "e-commerceMvp.db";
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract AppDAO appDAO();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {

                        instance = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }

        return instance;
    }

    public void Close(){
        if (instance != null){

            instance.close();
        }
    }
}