package com.example.e_commericemvp.databases;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_commericemvp.Activities.registration.pojo.UserModel;
import com.example.e_commericemvp.Activities.registration.pojo.User_DAO;
import com.example.e_commericemvp.database.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegistrationDatabaseTest {

    private AppDatabase db ;

    @Before
    public void setup (){

        db = AppDatabase.getInstance(ApplicationProvider.getApplicationContext());

    }

    @After
    public void tearDown() {

        db.Close();
    }

    @Test
    public void Insert_New_User(){

        String Username = "Jenzo" ;
        String Password = " 123456" ;

        db.UserDao().insertNewUser(new UserModel(Username , Password , String.valueOf((int)Math.random())));

        UserModel model = db.UserDao().getUserDataFromDatabase(Username , Password);

        Assert.assertEquals(Username , model.getUsername());
    }
}
