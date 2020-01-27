package com.example.e_commericemvp.Activities.product_activity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;
import com.example.e_commericemvp.Activities.product_activity.ui.ProductPresenter;
import com.example.e_commericemvp.R;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductList_ViewHolder>{


    private ProductPresenter productPresenter;

    private Context context ;
    private List<ProductModel> productsList;

    public ProductListAdapter(Context context, List<ProductModel> productsList , ProductPresenter productPresenter) {
        this.context = context;
        this.productsList = productsList;
        this.productPresenter = productPresenter;
    }

    @NonNull
    @Override
    public ProductList_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductList_ViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.products_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductList_ViewHolder holder, int position) {


        holder.bindData(productsList.get(position));
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    class ProductList_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int productQuantityNumber = 0 ;

        TextView productName , productPrice, productQuantity ;
        ImageView AddToCart , AddMoreQuantity , RemoveQuantity ,  ProductImageView;
        LinearLayout PlusMinus_Layout ;

        ProductList_ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.ProductName_id);
            productPrice = itemView.findViewById(R.id.ProductPrice_id);
            productQuantity = itemView.findViewById(R.id.ProductQuantity_id);
            AddToCart = itemView.findViewById(R.id.AddToCart_id);
            PlusMinus_Layout = itemView.findViewById(R.id.PlusMinus_Layout);
            AddMoreQuantity = itemView.findViewById(R.id.PlusIcon_id);
            RemoveQuantity = itemView.findViewById(R.id.MinusIcon_id);
            ProductImageView = itemView.findViewById(R.id.ProductImageView);

            ClickListeners();
        }
        private void bindData(ProductModel productModel_item){

            productName.setText(productModel_item.getProductName());

            Glide.with(context).load(productModel_item.getProductImage()).into(ProductImageView);

            if (productModel_item.getQuantity() > 0) {

                AddToCart.setVisibility(View.GONE);
                PlusMinus_Layout.setVisibility(View.VISIBLE);

                productQuantity.setText(String.valueOf(productModel_item.getQuantity()));

                float productPrice = productModel_item.getProductPrice() ;
                this.productPrice.setText(String.valueOf(productPrice));

                productQuantityNumber = productModel_item.getQuantity() ;

            }else{
                float productPrice = productModel_item.getProductPrice();
                this.productPrice.setText(String.valueOf(productPrice));
            }

        }
        private void ClickListeners(){
            AddToCart.setOnClickListener(this);
            AddMoreQuantity.setOnClickListener(this);
            RemoveQuantity.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            int id =  v.getId() ;
            int productID =  productsList.get(getAdapterPosition()).getProductID() ;
            if (id== R.id.AddToCart_id ){
                AddToCartLogic();
                productPresenter.AddProductToCart( productID, productQuantityNumber);
            }else if (id == R.id.PlusIcon_id ){
                AddMoreUiLogic();
                productPresenter.AddMoreQuantity(productID,productQuantityNumber);
            }else if (id == R.id.MinusIcon_id){
                DecreaseUiLogic();
                productPresenter.RemoveQuantity(productID, productQuantityNumber);
            }

        }
        private void AddToCartLogic(){
            productQuantityNumber = 1 ;
            productQuantity.setText(String.valueOf(productQuantityNumber));
            AddToCart.setVisibility(View.GONE);
            PlusMinus_Layout.setVisibility(View.VISIBLE);
        }
        private void AddMoreUiLogic(){
            productQuantityNumber++ ;
            productQuantity.setText(String.valueOf(productQuantityNumber));
        }
        private void DecreaseUiLogic(){
            productQuantityNumber-- ;
            if (productQuantityNumber == 0) {
                AddToCart.setVisibility(View.VISIBLE);
                PlusMinus_Layout.setVisibility(View.GONE);
            }else{
                productQuantity.setText(String.valueOf(productQuantityNumber));
            }
        }
    }

}
