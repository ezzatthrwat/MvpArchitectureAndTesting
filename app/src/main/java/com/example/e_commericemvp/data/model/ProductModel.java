package com.example.e_commericemvp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import androidx.room.PrimaryKey;

@Entity(tableName = "products" )
public class ProductModel  {

    @PrimaryKey(autoGenerate = true )
    private int productID ;
    @ColumnInfo(name = "productName")
    private String productName ;
    @ColumnInfo(name = "productPrice")
    private float productPrice ;
    @ColumnInfo(name = "ProductImage")
    private String ProductImage ;
    @ColumnInfo(name = "quantity" )
    private int Quantity ;



    @Ignore
    public ProductModel() {
    }

    @Ignore
    public ProductModel(String productName, float productPrice, String ProductImage) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.ProductImage = ProductImage;
    }

    public ProductModel(int productID, String productName, float productPrice , String ProductImage) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.ProductImage = ProductImage ;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", Quantity=" + Quantity +
                '}';
    }
}
