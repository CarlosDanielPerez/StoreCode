package com.example.storecode_android.view.adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.R;

public class HolderPurchasedItem  extends RecyclerView.ViewHolder {
    ImageView ivProduct;
    TextView tvNameProduct;
    TextView tvDescriptionProduct;
    TextView tvPriceProduct;
    TextView tvQuantityProduct;
    TextView tvSubtotal;



    public HolderPurchasedItem(@NonNull View itemView) {
        super(itemView);


        ivProduct= itemView.findViewById(R.id.ivProduct);
        tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
        tvDescriptionProduct = itemView.findViewById(R.id.tvDescriptionProduct);
        tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);
        tvQuantityProduct = itemView.findViewById(R.id.tvQuantityProduct);
        tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
    }
}
