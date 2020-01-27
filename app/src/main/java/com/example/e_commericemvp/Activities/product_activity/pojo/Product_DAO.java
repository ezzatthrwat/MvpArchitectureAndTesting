package com.example.e_commericemvp.Activities.product_activity.pojo;

        import androidx.room.Dao;
        import androidx.room.Insert;
        import androidx.room.OnConflictStrategy;
        import androidx.room.Query;

        import java.util.List;

@Dao
public interface Product_DAO {

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

}
