package com.example.e_commericemvp.databases;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;
import com.example.e_commericemvp.database.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ProductTest {

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
    public void Test_InsertNewProduct(){

        ProductModel model = new ProductModel(1 , "ProductName" , 50 , "Image") ;

        db.productDAO().insertproducts(model);

        ProductModel InsertedModel = db.productDAO().getProduct(model.getProductName());

        Assert.assertEquals(InsertedModel.getProductName() , model.getProductName());

    }
}
