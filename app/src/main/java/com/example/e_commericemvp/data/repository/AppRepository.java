package com.example.e_commericemvp.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.example.e_commericemvp.data.source.cache.CacheDataSource;
import com.example.e_commericemvp.data.source.cache.CacheDataSourceImpl;
import com.example.e_commericemvp.data.source.database.AppDAO;
import com.example.e_commericemvp.ui.Activities.cart_activity.CartContract;
import com.example.e_commericemvp.ui.Activities.product_activity.ProductContract;
import com.example.e_commericemvp.ui.Activities.Login_activity.LoginContract;
import com.example.e_commericemvp.ui.Activities.SingUp_activity.SignUpContract;
import com.example.e_commericemvp.data.model.CartItemsModel;
import com.example.e_commericemvp.data.model.ProductModel;
import com.example.e_commericemvp.data.model.UserModel;
import com.example.e_commericemvp.data.source.database.AppDatabase;
import com.example.e_commericemvp.util.EspressoIdlingResource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {

    private static AppRepository ourInstance;
    private final AppDAO appDAO ;
    private final CacheDataSource cacheDataSource;
    private final Executor executor = Executors.newSingleThreadExecutor();

    private AppRepository(Context context){
        AppDatabase mDb = AppDatabase.getInstance(context);
        appDAO = mDb.appDAO();
        cacheDataSource = new CacheDataSourceImpl(context.getSharedPreferences("MyPref", 0));
    }

    public static AppRepository getInstance(Context context) {
        if (ourInstance ==null ){
            ourInstance = new AppRepository(context);
        }
        return ourInstance ;
    }

    public void onFetchingUserDataStart(final String Username, final String Password, final LoginContract.getUserDataInteractor.OnFinishedListener onFinishedListener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserModel User = appDAO.getUserDataFromDatabase(Username , Password);

                if(User != null) {
                    setUserID(User.getUserID());
                }

                onFinishedListener.onFetchingUserDataFinished(User);

            }
        } , 1500);
    }


    public void onInsertUserDataStart(final String Username, final String Password, final SignUpContract.insertUserDataInteractor.OnFinishedListener onFinishedListener) {
        final UserModel User = appDAO.getUserDataFromDatabase(Username , Password) ;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (User==null){
                    long InsertedID = appDAO.insertNewUser(new UserModel(Username , Password , String.valueOf((int)Math.random()))) ;
                    onFinishedListener.onInsertUserDataFinished(InsertedID);
                }else{
                    onFinishedListener.UserIsExist();
                }

            }
        });

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
                appDAO.insertAll(sampleData);
                Log.e("ProductRepository", "run: in Executer");
            }
        });
    }


    public void onFetchingProductsDataStart(final int userID, final ProductContract.GetProductsInteractor.OnFinishedListener onFinishedListener) {

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
        return appDAO.getAllProducts();
    }

    private List<ProductModel> getProductsFromCart(int UserID){
        return appDAO.getCartItems(UserID) ;
    }

    public void addProductToCart(final CartItemsModel SelectedProducts){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.insertProductIntoCart(SelectedProducts);
            }
        });
    }

    public void updateCartItemQuantity(final int userID, final int productID, final int productQuantityNumber) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.updateCartItemQuantity(userID , productID , productQuantityNumber);
            }
        });
    }


    public void RemoveItemFromCartByProductId(final int productID){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.DeleteItemFromCartByProductId(productID);
            }
        });
    }

    private List<ProductModel> getCartItems(int userID){

        return appDAO.getAllProductSelected(userID);
    }

    public void onFetchingCartItemsDataStart(final int UserID, final CartContract.GetCartItemsInteractor.OnFinishedListener onFinishedListener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onFinishedListener.onFetchingCartItemsDataFinished(getCartItems(UserID));
            }
        }, 1200);

    }

    private void setUserID(int userID) {
        cacheDataSource.setUserID(userID);
    }

    public int getUserID() {
       return cacheDataSource.getUserID();
    }
}
