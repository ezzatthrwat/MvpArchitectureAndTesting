package com.example.e_commericemvp.ui.Activities.product_activity;

import com.example.e_commericemvp.data.model.ProductModel;

import java.util.List;

public interface ProductContract {

    interface ProductView{
        void showProgress();
        void hideProgress();
        void onGetProducts(List<ProductModel> productsList );
    }

    interface GetProductsInteractor {

        interface OnFinishedListener {
            void onFetchingProductsDataFinished(List<ProductModel> ProductsList, List<ProductModel> productsFromCart);
        }
    }

    interface Presenter {
        void OnProductActivityStart();
        void OnProductActivityDestroy();

        void AddProductToCart(int productID, int productQuantityNumber);
        void AddMoreQuantity(int adapterPosition, int productQuantityNumber);
        void RemoveQuantity(int adapterPosition, int productQuantityNumber);

    }
}
