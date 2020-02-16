package com.example.e_commericemvp.Activities.product_activity.pojo;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.e_commericemvp.Activities.product_activity.ui.ProductContract;
import com.example.e_commericemvp.database.AppDatabase;
import com.example.e_commericemvp.util.EspressoIdlingResource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ProductRepository  implements ProductContract.GetProductsInteractor {

    private static ProductRepository ourInstance;

    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();



    public ProductRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
    }

    public static ProductRepository getInstance(Context context) {

        if (ourInstance == null) {
            ourInstance = new ProductRepository(context);
        }
        return ourInstance;
    }


    public void addSimpleData(){

        final List<ProductModel> sampleData = new ArrayList<>();
        sampleData.add(new ProductModel(1,"BooK" , 50 ,  "https://thebusinessbookreview.com/wp-content/uploads/2019/06/book-stack-books-education-51342-1170x878.jpg"));
        sampleData.add(new ProductModel(2,"Cap" , 80 ,"https://eg.jumia.is/unsafe/fit-in/680x680/filters:fill(white)/product/74/78444/1.jpg?4641" ));
        sampleData.add(new ProductModel(3,"T-shirt" , 30 , "https://eg.jumia.is/unsafe/fit-in/680x680/filters:fill(white)/product/61/250031/1.jpg?4567"));
        sampleData.add(new ProductModel(4,"Shorts" , 40 ,"https://lcw.akinoncdn.com/products/2018/12/12/535526/8e53d3bd-11ce-43d1-bf0c-821ac97dceea_size561x730.jpg"));
        sampleData.add(new ProductModel(5,"BlueBarry" , 100, "https://www.haifa-group.com/sites/default/files/Blueberry%20isolated%2026223694_xxl.jpg"));
        sampleData.add(new ProductModel(6,"Mango" , 50 ,    "https://image.shutterstock.com/image-photo/mango-leaf-fresh-yellow-on-260nw-1115987162.jpg"));
        sampleData.add(new ProductModel(7,"Ship" , 1000 ,   "https://www.dw.com/image/51001580_303.jpg" ));
        sampleData.add(new ProductModel(8,"Car" , 500 ,"https://images.pexels.com/photos/170811/pexels-photo-170811.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        sampleData.add(new ProductModel(9,"Cat" , 20 ,"https://placekitten.com/200/300"));

        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.productDAO().insertAll(sampleData);
                Log.e("ProductRepository", "run: in Executer");
            }
        });
    }


    @Override
    public void onFetchingProductsDataStart(final int userID, final OnFinishedListener onFinishedListener) {

        EspressoIdlingResource.increment();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onFinishedListener.onFetchingProductsDataFinished(getAllProducts() , getProductsFromCart(userID));
                EspressoIdlingResource.decrement();

            }
        }, 1200);
    }


    private List<ProductModel> getAllProducts() {
        return mDb.productDAO().getAllProducts();
    }

    private List<ProductModel> getProductsFromCart(int UserID){
        return mDb.productDAO().getCartItems(UserID) ;
    }
}
