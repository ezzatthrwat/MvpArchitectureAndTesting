package com.example.e_commericemvp.ui.Activities.cart_activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.e_commericemvp.data.model.ProductModel;
import com.example.e_commericemvp.data.repository.AppRepository;

import java.util.List;

public class CartPresenter implements CartContract.Presenter {

    private CartContract.CartView cartView ;

    private final AppRepository appRepository;

    private final int UserID ;

    CartPresenter(CartContract.CartView cartView, AppRepository appRepository) {
        this.cartView = cartView;

        this.appRepository = appRepository;


        UserID = appRepository.getUserID() ;
    }

    @Override
    public void OnCartActivityStart() {
        if (cartView != null) {
            cartView.showProgress();
        }

        appRepository.onFetchingCartItemsDataStart(UserID, new CartContract.GetCartItemsInteractor.OnFinishedListener() {
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
    }


    @Override
    public void RemoveItemFromCartByProductID(int productId) {

        appRepository.RemoveItemFromCartByProductId(productId);
    }

    @Override
    public void AddMoreQuantity(int productID, int productQuantityNumber) {

        appRepository.updateCartItemQuantity(UserID ,productID ,  productQuantityNumber);

    }

    @Override
    public void RemoveQuantity(int productID, int productQuantityNumber) {

        if (productQuantityNumber == 0){
            appRepository.RemoveItemFromCartByProductId(productID);
        }else {
            appRepository.updateCartItemQuantity(UserID ,productID ,  productQuantityNumber);
        }

    }
}
