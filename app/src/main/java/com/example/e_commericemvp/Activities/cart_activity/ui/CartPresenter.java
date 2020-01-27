package com.example.e_commericemvp.Activities.cart_activity.ui;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.e_commericemvp.Activities.cart_activity.pojo.CartRepository;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;

import java.util.List;

public class CartPresenter implements CartContract.Presenter {

    private CartContract.CartView cartView ;
    private CartContract.GetCartItemsInteractor cartItemsInteractor ;

    private CartRepository cartRepository ;

    private int UserID ;

    CartPresenter(CartContract.CartView cartView, CartContract.GetCartItemsInteractor cartItemsInteractor, Context context) {
        this.cartView = cartView;
        this.cartItemsInteractor = cartItemsInteractor;

        cartRepository = CartRepository.getInstance(context);

        SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
        UserID = pref.getInt("UserID" , 0) ;
    }

    @Override
    public void OnCartActivityStart() {
        if (cartView != null) {
            cartView.showProgress();
        }

        cartItemsInteractor.onFetchingCartItemsDataStart(UserID, new CartContract.GetCartItemsInteractor.OnFinishedListener() {
            @Override
            public void onFetchingCartItemsDataFinished(List<ProductModel> ProductsList) {
                cartView.onGetProducts(ProductsList);
                cartView.hideProgress();
            }
        });
    }

    @Override
    public void OnCartActivityDestroy() {

        cartView = null ;
        cartItemsInteractor = null ;
    }


    @Override
    public void RemoveItemFromCartByProductID(int productId) {

        cartRepository.RemoveItemFromCartByProductId(productId);
    }

    @Override
    public void AddMoreQuantity(int productID, int productQuantityNumber) {

        cartRepository.updateCartItemQuantity(UserID ,productID ,  productQuantityNumber);

    }

    @Override
    public void RemoveQuantity(int productID, int productQuantityNumber) {

        if (productQuantityNumber == 0){
            cartRepository.RemoveItemFromCartByProductId(productID);
        }else {
            cartRepository.updateCartItemQuantity(UserID ,productID ,  productQuantityNumber);
        }

    }
}
