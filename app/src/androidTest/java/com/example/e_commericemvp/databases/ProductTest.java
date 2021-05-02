package com.example.e_commericemvp.databases;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_commericemvp.data.model.ProductModel;
import com.example.e_commericemvp.data.source.database.AppDAO;
import com.example.e_commericemvp.data.source.database.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ProductTest {

    private AppDatabase db ;
    private AppDAO appDAO;

    @Rule
    public ProductModel_CustomRule productModelCustomRule = new ProductModel_CustomRule();

    @Before
    public void setup (){

        db = AppDatabase.getInstance(ApplicationProvider.getApplicationContext());
        appDAO = db.appDAO();
    }

    @After
    public void tearDown() {

        db.Close();
    }

    @Test
    public void Test_InsertNewProduct(){


        appDAO.insertproducts(productModelCustomRule.model);

        ProductModel InsertedModel = appDAO.getProduct(productModelCustomRule.model.getProductName());

        Assert.assertEquals(InsertedModel.getProductName() , productModelCustomRule.model.getProductName());

    }
}
