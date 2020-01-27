package com.example.e_commericemvp.Activities.cart_activity.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commericemvp.R;
import com.example.e_commericemvp.Activities.cart_activity.ui.CartActivity;
import com.example.e_commericemvp.Activities.cart_activity.ui.CartPresenter;
import com.example.e_commericemvp.Activities.product_activity.pojo.ProductModel;

import java.util.List;

public class CartItems_Adapter  extends  RecyclerView.Adapter<CartItems_Adapter.CartItems_ViewHolder>{

    private List<ProductModel> CartItemsList ;
    private Context context ;
    private CartPresenter cartPresenter ;


    public CartItems_Adapter(List<ProductModel> cartItemsList, Context context, CartPresenter cartPresenter) {
        CartItemsList = cartItemsList;
        this.context = context;
        this.cartPresenter = cartPresenter;
    }

    @NonNull
    @Override
    public CartItems_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartItems_Adapter.CartItems_ViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.cart_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartItems_ViewHolder holder, int position) {

        holder.bindData(CartItemsList.get(position));

    }

    @Override
    public int getItemCount() {
        return CartItemsList.size();
    }

    class CartItems_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int productQuantityNumber = 0 ;

        TextView productName , productPrice, productQuantity ;
        ImageView Delete , AddMoreQuantity , RemoveQuantity ,  ProductImageView;
        LinearLayout PlusMinus_Layout ;

        CartItems_ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.ProductName_id);
            productPrice = itemView.findViewById(R.id.ProductPrice_id);
            productQuantity = itemView.findViewById(R.id.ProductQuantity_id);
            PlusMinus_Layout = itemView.findViewById(R.id.PlusMinus_Layout);
            Delete = itemView.findViewById(R.id.Delete);
            AddMoreQuantity = itemView.findViewById(R.id.PlusIcon_id);
            RemoveQuantity = itemView.findViewById(R.id.MinusIcon_id);
            ProductImageView = itemView.findViewById(R.id.ProductImageView);


            ClickListeners();
        }

        private void bindData(ProductModel productModel_item){

            addTextWatcherForIncreasePrice(productModel_item.getProductPrice());

            productName.setText(productModel_item.getProductName());


            Glide.with(context).load(productModel_item.getProductImage()).into(ProductImageView);

            if (productModel_item.getQuantity() > 0) {

                PlusMinus_Layout.setVisibility(View.VISIBLE);

                productQuantity.setText(String.valueOf(productModel_item.getQuantity()));
                float productPrice = productModel_item.getProductPrice() * productModel_item.getQuantity();
                this.productPrice.setText(String.valueOf(productPrice));
                productQuantityNumber = productModel_item.getQuantity() ;

            }else{
                float productPrice = productModel_item.getProductPrice();
                this.productPrice.setText(String.valueOf(productPrice));

            }

        }

        private void ClickListeners(){
            Delete.setOnClickListener(this);
            AddMoreQuantity.setOnClickListener(this);
            RemoveQuantity.setOnClickListener(this);
        }

        private void addTextWatcherForIncreasePrice(final float Price){

            productQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (s.length() > 0) {
                        if (s.toString().equals("0")) {
                            productPrice.setText(String.valueOf(Price));
                        } else {
                            float newPrice = Price * Float.parseFloat(s.toString());
                            productPrice.setText(String.valueOf(newPrice));
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {

            int id =  v.getId() ;
            int productID =  CartItemsList.get(getAdapterPosition()).getProductID();
            if (id== R.id.Delete){
                cartPresenter.RemoveItemFromCartByProductID(productID);
                updateRemovedItemFromList();
            }else if (id == R.id.PlusIcon_id ){
                AddMoreUiLogic();
                cartPresenter.AddMoreQuantity(productID,productQuantityNumber);
            }else if (id == R.id.MinusIcon_id){
                DecreaseUiLogic();
                cartPresenter.RemoveQuantity(productID , productQuantityNumber);
                updateDecreaseQuantity();
            }

        }

        private void AddMoreUiLogic(){
            productQuantityNumber++ ;
            productQuantity.setText(String.valueOf(productQuantityNumber));
        }
        private void DecreaseUiLogic(){
            productQuantityNumber-- ;
            if (productQuantityNumber == 0) {
                PlusMinus_Layout.setVisibility(View.GONE);
            }else{
                productQuantity.setText(String.valueOf(productQuantityNumber));
            }

        }

        private void updateDecreaseQuantity(){

            if (productQuantityNumber == 0){
                CartItemsList.remove(getAdapterPosition());
                notifyDataSetChanged();
            }
            VisibleEmptyTextView();
        }
        private void updateRemovedItemFromList(){

            CartItemsList.remove(getAdapterPosition());
            notifyDataSetChanged();
            VisibleEmptyTextView();
        }

        private void VisibleEmptyTextView(){
            if (CartItemsList.size() == 0) {
                ((CartActivity) context).NoCartItem_TextView.setVisibility(View.VISIBLE);
            }
        }
    }

}
