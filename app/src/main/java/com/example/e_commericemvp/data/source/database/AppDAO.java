package com.example.e_commericemvp.data.source.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.e_commericemvp.data.model.CartItemsModel;
import com.example.e_commericemvp.data.model.ProductModel;
import com.example.e_commericemvp.data.model.UserModel;

import java.util.List;

@Dao
public interface AppDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertNewUser(UserModel user);

    @Query("SELECT * FROM Users WHERE Username = :username AND Password = :password")
    UserModel getUserDataFromDatabase(String username , String password );

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertproducts(ProductModel productModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductModel> productModelList);

    @Query("SELECT * FROM products ORDER BY productID DESC")
    List<ProductModel> getAllProducts();


    @Query("SELECT * FROM products WHERE productName =:ProductName")
    ProductModel getProduct(String ProductName);

    @Query("SELECT products.productID , products.productName ,products.productPrice , products.productImage , cart_items.quantity  FROM products , cart_items WHERE productID = productID_cart AND userID =:id")
    List<ProductModel> getCartItems(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertProductIntoCart(CartItemsModel cartItem);

    @Query("UPDATE cart_items SET quantity =:quantity WHERE productID_cart =:productID AND userID =:userID")
    void updateCartItemQuantity(int userID , int productID , int quantity);

    @Query("SELECT products.productID , products.productName ,products.ProductImage ,products.productPrice , cart_items.quantity  FROM products , cart_items WHERE productID = productID_cart AND userID =:id")
    List<ProductModel> getAllProductSelected(int id);

    @Query("Delete FROM cart_items Where productID_cart =:product_id")
    int DeleteItemFromCartByProductId(int product_id) ;
}
