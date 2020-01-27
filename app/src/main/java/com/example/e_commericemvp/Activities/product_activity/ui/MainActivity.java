package com.example.e_commericemvp.Activities.product_activity.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;
import com.example.e_commericemvp.R;
import com.example.e_commericemvp.Activities.cart_activity.ui.CartActivity;
import com.example.e_commericemvp.Activities.product_activity.adapters.ProductListAdapter;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductContract.ProductView {


    RecyclerView recyclerView;
    ProgressBar progressBar;
    ProductPresenter productPresenter;
    ProductListAdapter productListAdapter;

    ImageView CartClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initWidget();

        productPresenter = new ProductPresenter(this, ProductRepository.getInstance(getApplicationContext()), getApplicationContext());

    }

    private void initWidget() {
        progressBar = findViewById(R.id.ProgressBar_id);
        CartClick = findViewById(R.id.CartIcon_id);

        CartClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        productPresenter.OnProductActivityStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        productPresenter.OnProductActivityDestroy();
    }

    private void initRecyclerView() {

        recyclerView = findViewById(R.id.button);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    //    view implementation methods
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetProducts(List<ProductModel> productsList) {

        productListAdapter = new ProductListAdapter(this, productsList , productPresenter);

        recyclerView.setAdapter(productListAdapter);
    }
}
