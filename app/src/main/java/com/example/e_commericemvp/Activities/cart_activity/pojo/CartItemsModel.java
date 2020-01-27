package com.example.e_commericemvp.Activities.cart_activity.pojo;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;

@Entity(tableName = "cart_items" , foreignKeys = {
        @ForeignKey(entity = ProductModel.class , parentColumns = "productID" , childColumns = "productID_cart")
}
, indices = {@Index("productID_cart")})
public class CartItemsModel {

    @PrimaryKey(autoGenerate = true)
    private int cartID ;
    private int userID ;
    private int productID_cart;
    private int quantity ;


    @Ignore
    public CartItemsModel(int userID , int productID_cart, int quantity) {
        this.productID_cart = productID_cart;
        this.quantity = quantity;
        this.userID = userID;
    }

    public CartItemsModel(int cartID, int userID ,int productID_cart, int quantity ) {
        this.cartID = cartID;
        this.productID_cart = productID_cart;
        this.quantity = quantity;
        this.userID = userID ;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID_cart() {
        return productID_cart;
    }

    public void setProductID_cart(int productID_cart) {
        this.productID_cart = productID_cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
