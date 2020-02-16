package com.example.e_commericemvp.ui.databases;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_commericemvp.Activities.MyApp;
import com.example.e_commericemvp.Activities.registration.pojo.UserModel;
import com.example.e_commericemvp.Activities.registration.pojo.User_DAO;
import com.example.e_commericemvp.database.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

@RunWith(AndroidJUnit4.class)
public class RegistrationDatabaseTest {


    private AppDatabase db ;
    private User_DAO user_dao ;



    @Before
    public void setup (){
        db = AppDatabase.getInstance(getApplicationContext());
        user_dao = db.UserDao();
    }

    @After
    public void tearDown() {
        db.Close();
    }

    @Test
    public void Insert_New_User(){

         String Username = "Jenzo" ;
         String Password = " 123456" ;


        user_dao.insertNewUser(new UserModel(Username , Password , String.valueOf(Math.random())));

        UserModel model = user_dao.getUserDataFromDatabase(Username , Password);

        Assert.assertEquals(Username , model.getUsername());
    }
}
