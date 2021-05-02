package com.example.e_commericemvp.ui.Activities.product_activity;

import com.example.e_commericemvp.data.model.CartItemsModel;
import com.example.e_commericemvp.data.model.ProductModel;
import com.example.e_commericemvp.data.repository.AppRepository;

import java.util.List;

public class ProductPresenter implements ProductContract.Presenter   {


    private ProductContract.ProductView productView;

    private final int UserID ;

    private final AppRepository appRepository;

    ProductPresenter(ProductContract.ProductView productView, AppRepository appRepository) {

        this.productView = productView;

        this.appRepository = appRepository;
        appRepository.addSimpleData();

       UserID =  appRepository.getUserID();
    }


    @Override
    public void OnProductActivityStart() {
        if (productView != null) {
            productView.showProgress();
        }

        appRepository.onFetchingProductsDataStart(UserID , new ProductContract.GetProductsInteractor.OnFinishedListener(){
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
    }


    @Override
    public void AddProductToCart(int productID, int productQuantityNumber) {

        CartItemsModel cartData = new CartItemsModel(UserID, productID, productQuantityNumber);
        appRepository.addProductToCart(cartData);

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
