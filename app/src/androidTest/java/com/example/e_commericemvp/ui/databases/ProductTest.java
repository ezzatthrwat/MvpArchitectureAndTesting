package com.example.e_commericemvp.ui.databases;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;
import com.example.e_commericemvp.Activities.product_activity.pojo.Product_DAO;
import com.example.e_commericemvp.database.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ProductTest {

    private AppDatabase db ;
    private Product_DAO product_dao ;

    @Rule
    public ProductModel_CustomRule productModelCustomRule = new ProductModel_CustomRule();

    @Before
    public void setup (){

        db = AppDatabase.getInstance(ApplicationProvider.getApplicationContext());
        product_dao = db.productDAO();
    }

    @After
    public void tearDown() {

        db.Close();
    }

    @Test
    public void Test_InsertNewProduct(){


        product_dao.insertproducts(productModelCustomRule.model);

        ProductModel InsertedModel = product_dao.getProduct(productModelCustomRule.model.getProductName());

        Assert.assertEquals(InsertedModel.getProductName() , productModelCustomRule.model.getProductName());

    }
}
