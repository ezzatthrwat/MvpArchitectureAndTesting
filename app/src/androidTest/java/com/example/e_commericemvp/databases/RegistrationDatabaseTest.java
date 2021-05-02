package com.example.e_commericemvp.databases;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_commericemvp.data.model.UserModel;
import com.example.e_commericemvp.data.source.database.AppDAO;
import com.example.e_commericemvp.data.source.database.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

@RunWith(AndroidJUnit4.class)
public class RegistrationDatabaseTest {


    private AppDatabase db ;
    private AppDAO appDAO;



    @Before
    public void setup (){
        db = AppDatabase.getInstance(getApplicationContext());
        appDAO = db.appDAO();
    }

    @After
    public void tearDown() {
        db.Close();
    }

    @Test
    public void Insert_New_User(){

         String Username = "Jenzo" ;
         String Password = " 123456" ;


        appDAO.insertNewUser(new UserModel(Username , Password , String.valueOf(Math.random())));

        UserModel model = appDAO.getUserDataFromDatabase(Username , Password);

        Assert.assertEquals(Username , model.getUsername());
    }
}
