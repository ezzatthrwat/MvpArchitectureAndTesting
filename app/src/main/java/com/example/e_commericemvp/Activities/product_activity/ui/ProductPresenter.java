package com.example.e_commericemvp.Activities.product_activity.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.e_commericemvp.Activities.cart_activity.pojo.CartItemsModel;
import com.example.e_commericemvp.Activities.cart_activity.pojo.CartRepository;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductRepository;
import com.example.e_commericemvp.util.EspressoIdlingResource;

import java.util.List;

public class ProductPresenter implements ProductContract.Presenter   {

    private static final String TAG = "ProductPresenter";

    private ProductContract.ProductView productView;
    private ProductContract.GetProductsInteractor productsInteractor;

    private int UserID ;

    private CartRepository cartRepository ;

    ProductPresenter(ProductContract.ProductView productView, ProductContract.GetProductsInteractor productsInteractor, Context context ) {

        this.productView = productView;
        this.productsInteractor = productsInteractor;

        ProductRepository productRepository = ProductRepository.getInstance(context);
        productRepository.addSimpleData();

        cartRepository = CartRepository.getInstance(context) ;

        SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
        UserID = pref.getInt("UserID" , 0) ;
    }


    @Override
    public void OnProductActivityStart() {
        if (productView != null) {
            productView.showProgress();
        }

        productsInteractor.onFetchingProductsDataStart(UserID , new ProductContract.GetProductsInteractor.OnFinishedListener(){
            @Override
            public void onFetchingProductsDataFinished(List<ProductModel> ProductsList, List<ProductModel> productsFromCart) {
                if (productView != null) {

                    //Check if product item Added To Cart
                    for(ProductModel productItem : ProductsList) {
                        for(ProductModel cardItem : productsFromCart) {
                            if(productItem.getProductID() == cardItem.getProductID()){
                                productItem.setQuantity(cardItem.getQuantity());
                            }
                        }
                    }


                    productView.onGetProducts(ProductsList);
                    productView.hideProgress();

                }
            }
        });
    }

    @Override
    public void OnProductActivityDestroy() {
        productView = null ;
        productsInteractor = null ;
    }


    @Override
    public void AddProductToCart(int productID, int productQuantityNumber) {

        Log.e(TAG, "AddProductToCart: " + UserID );
        CartItemsModel cartData = new CartItemsModel(UserID, productID, productQuantityNumber);
        cartRepository.addProductToCart(cartData);

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
