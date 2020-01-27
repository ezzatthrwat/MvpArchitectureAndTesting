package com.example.e_commericemvp.Activities.cart_activity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.e_commericemvp.Activities.cart_activity.adapters.CartItems_Adapter;
import com.example.e_commericemvp.Activities.cart_activity.pojo.CartRepository;
import com.example.e_commericemvp.R;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;

import java.util.List;

public class CartActivity extends AppCompatActivity  implements CartContract.CartView{

    ProgressBar ProgressBar_id ;

    private RecyclerView CartItems_RecyclerView ;

    private CartPresenter cartPresenter ;

    public TextView NoCartItem_TextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initRecyclerView();
        initWidGet();

        cartPresenter = new CartPresenter(this , CartRepository.getInstance(this), this );

    }

    private void initRecyclerView() {
        CartItems_RecyclerView = findViewById(R.id.CartItems_RecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CartActivity.this, 2);
        CartItems_RecyclerView.setHasFixedSize(true);
        CartItems_RecyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initWidGet() {
        ProgressBar_id = findViewById(R.id.ProgressBar_id);
        NoCartItem_TextView = findViewById(R.id.NoCartItem_TextView);

        ImageView backButton = findViewById(R.id.BackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cartPresenter.OnCartActivityStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cartPresenter.OnCartActivityDestroy();
    }

    @Override
    public void showProgress() {

        ProgressBar_id.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        ProgressBar_id.setVisibility(View.GONE);

    }

    @Override
    public void onGetProducts(List<ProductModel> productsList) {

        if (productsList.size() > 0) {
            CartItems_Adapter adapter = new CartItems_Adapter(productsList, this, cartPresenter);
            CartItems_RecyclerView.setAdapter(adapter);
        }else{
            NoCartItem_TextView.setVisibility(View.VISIBLE);
        }
    }
}
