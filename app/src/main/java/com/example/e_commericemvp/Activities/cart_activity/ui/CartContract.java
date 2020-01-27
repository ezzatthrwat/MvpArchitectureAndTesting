package com.example.e_commericemvp.Activities.cart_activity.ui;

import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;

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

        void onFetchingCartItemsDataStart(int UserID , OnFinishedListener onFinishedListener);
    }


    interface Presenter {
        void OnCartActivityStart();
        void OnCartActivityDestroy();

        void RemoveItemFromCartByProductID(int productID);
        void AddMoreQuantity(int adapterPosition, int productQuantityNumber);
        void RemoveQuantity(int adapterPosition, int productQuantityNumber);
    }

}
