package com.example.e_commericemvp.databases;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_commericemvp.data.model.CartItemsModel;
import com.example.e_commericemvp.data.model.ProductModel;
import com.example.e_commericemvp.data.source.database.AppDAO;
import com.example.e_commericemvp.data.source.database.AppDatabase;

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
    private AppDAO appDAO;

    private final int productID = 1 ;
    private final int UserID = 1 ;


    @Rule
    public ProductModel_CustomRule productModelCustomRule = new ProductModel_CustomRule();

//    Tells Mockito to create the mocks based on the @Mock annotation

    @Before
    public void setup (){

        db = AppDatabase.getInstance(ApplicationProvider.getApplicationContext());
        appDAO = db.appDAO() ;
    }

    @After
    public void tearDown() {

        db.Close();
    }

    @Test
    public void Test_InsertNewProductInCart(){

        //Should Insert Product

        appDAO.insertproducts(productModelCustomRule.model);


        // Get Product Inserted into Cart Which Product is Foreign key

        CartItemsModel cartItemsModel = new CartItemsModel(1 , UserID , productID,1);

        appDAO.insertProductIntoCart(cartItemsModel);

        List<ProductModel> productModels = appDAO.getAllProductSelected(UserID) ;

        Assert.assertEquals(productModelCustomRule.model.getProductName() , productModels.get(0).getProductName() );
    }

    @Test
    public void Test_UpdateProductQuantityInCart(){

        //Should Insert Product

        appDAO.insertproducts((productModelCustomRule.model));

        // Test Update Quantity
        appDAO.updateCartItemQuantity(UserID , productID , 2);

        List<ProductModel> productModels = appDAO.getAllProductSelected(UserID) ;

        Assert.assertEquals(2 , productModels.get(0).getQuantity() );

    }

    @Test
    public void Test_RemoveProductItemFromCart(){

        appDAO.insertproducts((productModelCustomRule.model));

        CartItemsModel cartItemsModel = new CartItemsModel(1 , UserID , productID,1);

        appDAO.insertProductIntoCart(cartItemsModel);

       int RemovedProductFromCartID = appDAO.DeleteItemFromCartByProductId(productID);

       Assert.assertEquals(1 , RemovedProductFromCartID);
    }
}
