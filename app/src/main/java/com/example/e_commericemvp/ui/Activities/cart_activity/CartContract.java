package com.example.e_commericemvp.ui.Activities.cart_activity;

import com.example.e_commericemvp.data.model.ProductModel;

import java.util.List;

public interface CartContract {

    interface CartView{
        void showProgress();
        void hideProgress();
        void onGetProducts(List<ProductModel> productsList );
    }


    interface GetCartItemsInteractor {

        interface OnFinishedListener {
            void onFetchingCartItemsDataFinished(List<ProductModel> ProductsList);
        }
    }


    interface Presenter {
        void OnCartActivityStart();
        void OnCartActivityDestroy();

        void RemoveItemFromCartByProductID(int productID);
        void AddMoreQuantity(int adapterPosition, int productQuantityNumber);
        void RemoveQuantity(int adapterPosition, int productQuantityNumber);
    }

}
