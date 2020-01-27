package com.example.e_commericemvp.databases;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_commericemvp.Activities.cart_activity.pojo.CartItemsModel;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;
import com.example.e_commericemvp.database.AppDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class CartTest {

    private AppDatabase db ;

    private int productID = 1 ;
    private int UserID = 1 ;


    @Rule
    public ProductModel_CustomRule productModelCustomRule = new ProductModel_CustomRule();


    @Before
    public void setup (){

        db = AppDatabase.getInstance(ApplicationProvider.getApplicationContext());

    }

    @After
    public void tearDown() {

//        db.Close();
    }

    @Test
    public void Test_InsertNewProductInCart(){


        //Should Insert Product

        db.productDAO().insertproducts(productModelCustomRule.model);


        // Get Product Inserted into Cart Which Product is Foreign key

        CartItemsModel cartItemsModel = new CartItemsModel(1 , UserID , productID,1);

        db.cartItemDao().insertProductIntoCart(cartItemsModel);

        List<ProductModel> productModels = db.cartItemDao().getAllProductSelected(UserID) ;

        Assert.assertEquals(productModelCustomRule.model.getProductName() , productModels.get(0).getProductName() );
    }

    @Test
    public void Test_UpdateProductQuantityInCart(){

        //Should Insert Product

        db.productDAO().insertproducts((productModelCustomRule.model));

        // Test Update Quantity
        db.cartItemDao().updateCartItemQuantity(UserID , productID , 2);

        List<ProductModel> productModels = db.cartItemDao().getAllProductSelected(UserID) ;

        Assert.assertEquals(2 , productModels.get(0).getQuantity() );

    }

    @Test
    public void Test_RemoveProductItemFromCart(){

        db.productDAO().insertproducts((productModelCustomRule.model));

        CartItemsModel cartItemsModel = new CartItemsModel(1 , UserID , productID,1);

        db.cartItemDao().insertProductIntoCart(cartItemsModel);

       int RemovedProductFromCartID = db.cartItemDao().DeleteItemFromCartByProductId(productID);

       Assert.assertEquals(1 , RemovedProductFromCartID);
    }
}
