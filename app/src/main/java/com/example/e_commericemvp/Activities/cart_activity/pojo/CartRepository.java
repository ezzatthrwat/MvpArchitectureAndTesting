package com.example.e_commericemvp.Activities.cart_activity.pojo;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.example.e_commericemvp.Activities.cart_activity.ui.CartContract;
import com.example.e_commericemvp.database.AppDatabase;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepository implements CartContract.GetCartItemsInteractor {

    private static final String TAG = "CartRepository";
    private static CartRepository ourInstance;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    private CartRepository(Context context){
        mDb = AppDatabase.getInstance(context);
    }

    public static CartRepository getInstance(Context context) {
        if (ourInstance ==null ){
            ourInstance = new CartRepository(context);
        }
        return ourInstance ;
    }


    public void addProductToCart(final CartItemsModel SelectedProducts){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.cartItemDao().insertProductIntoCart(SelectedProducts);
                Log.e(TAG, "run: in Executer");
            }
        });
    }

    public void updateCartItemQuantity(final int userID, final int productID, final int productQuantityNumber) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.cartItemDao().updateCartItemQuantity(userID , productID , productQuantityNumber);
                Log.e("CartRepository", "run: in Executer");
            }
        });
    }


    public void RemoveItemFromCartByProductId(final int productID){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.cartItemDao().DeleteItemFromCartByProductId(productID);
            }
        });
    }

    private List<ProductModel> getCartItems(int userID){

        return mDb.cartItemDao().getAllProductSelected(userID);
    }

    @Override
    public void onFetchingCartItemsDataStart(final int UserID, final OnFinishedListener onFinishedListener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onFinishedListener.onFetchingCartItemsDataFinished(getCartItems(UserID));
            }
        }, 1200);

    }
}
