package com.example.e_commericemvp.ui.databases;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.e_commericemvp.Activities.cart_activity.pojo.CartItem_DAO;
import com.example.e_commericemvp.Activities.cart_activity.pojo.CartItemsModel;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;
import com.example.e_commericemvp.Activities.product_activity.pojo.Product_DAO;
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
    private Product_DAO product_dao ;
    private CartItem_DAO cartItem_dao ;

    private int productID = 1 ;
    private int UserID = 1 ;


    @Rule
    public ProductModel_CustomRule productModelCustomRule = new ProductModel_CustomRule();

//    Tells Mockito to create the mocks based on the @Mock annotation

    @Before
    public void setup (){

        db = AppDatabase.getInstance(ApplicationProvider.getApplicationContext());
        product_dao = db.productDAO() ;
        cartItem_dao = db.cartItemDao() ;
    }

    @After
    public void tearDown() {

        db.Close();
    }

    @Test
    public void Test_InsertNewProductInCart(){

        //Should Insert Product

        product_dao.insertproducts(productModelCustomRule.model);


        // Get Product Inserted into Cart Which Product is Foreign key

        CartItemsModel cartItemsModel = new CartItemsModel(1 , UserID , productID,1);

        cartItem_dao.insertProductIntoCart(cartItemsModel);

        List<ProductModel> productModels = cartItem_dao.getAllProductSelected(UserID) ;

        Assert.assertEquals(productModelCustomRule.model.getProductName() , productModels.get(0).getProductName() );
    }

    @Test
    public void Test_UpdateProductQuantityInCart(){

        //Should Insert Product

        product_dao.insertproducts((productModelCustomRule.model));

        // Test Update Quantity
        cartItem_dao.updateCartItemQuantity(UserID , productID , 2);

        List<ProductModel> productModels = cartItem_dao.getAllProductSelected(UserID) ;

        Assert.assertEquals(2 , productModels.get(0).getQuantity() );

    }

    @Test
    public void Test_RemoveProductItemFromCart(){

        product_dao.insertproducts((productModelCustomRule.model));

        CartItemsModel cartItemsModel = new CartItemsModel(1 , UserID , productID,1);

        cartItem_dao.insertProductIntoCart(cartItemsModel);

       int RemovedProductFromCartID = cartItem_dao.DeleteItemFromCartByProductId(productID);

       Assert.assertEquals(1 , RemovedProductFromCartID);
    }
}
