package com.example.e_commericemvp.Activities.cart_activity.pojo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;

import java.util.List;

@Dao
public interface CartItem_DAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertProductIntoCart(CartItemsModel cartItem);

    @Query("UPDATE cart_items SET quantity =:quantity WHERE productID_cart =:productID AND userID =:userID")
    void updateCartItemQuantity(int userID , int productID , int quantity);

    @Query("SELECT products.productID , products.productName ,products.ProductImage ,products.productPrice , cart_items.quantity  FROM products , cart_items WHERE productID = productID_cart AND userID =:id")
    List<ProductModel> getAllProductSelected(int id);

    @Query("Delete FROM cart_items Where productID_cart =:product_id")
    int DeleteItemFromCartByProductId(int product_id) ;
}
