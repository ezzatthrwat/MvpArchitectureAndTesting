package com.example.e_commericemvp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.e_commericemvp.Activities.cart_activity.pojo.CartItem_DAO;
import com.example.e_commericemvp.Activities.cart_activity.pojo.CartItemsModel;
import com.example.e_commericemvp.Activities.registration.pojo.UserModel;
import com.example.e_commericemvp.Activities.registration.pojo.User_DAO;
import com.example.e_commericemvp.Activities.product_activity.pojo.Product_DAO;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;

@Database(entities = {ProductModel.class , CartItemsModel.class , UserModel.class} , version = 25, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "e-commerceMvp.db";
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract Product_DAO productDAO();
    public abstract CartItem_DAO cartItemDao();
    public abstract User_DAO UserDao();


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