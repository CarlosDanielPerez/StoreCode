package com.example.storecode_android.view.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.storecode_android.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderProductsInCart extends RecyclerView.ViewHolder{

    ImageView ivProductOnCart;
    TextView tvNameOnCart;
    TextView tvDescriptionOnCart;
    TextView tvPriceOnCart;
    TextView tvStockOnCart;
    Button btnPayment;
    Button btnDeleteFromCart;
    TextView tvSubtotal;
    TextView tvTotal;
    Spinner spinner_cantidad;


    public HolderProductsInCart(@NonNull View itemView) {
        super(itemView);
        ivProductOnCart = itemView.findViewById(R.id.ivProductOnCart);
        tvNameOnCart = itemView.findViewById(R.id.tvNameOnCart);
        tvDescriptionOnCart = itemView.findViewById(R.id.tvDescriptionOnCart);
        tvPriceOnCart = itemView.findViewById(R.id.tvPriceOnCart);
        tvStockOnCart = itemView.findViewById(R.id.tvStockOnCart);
        btnPayment =  itemView.findViewById(R.id.btnPayment);
        btnDeleteFromCart = itemView.findViewById(R.id.btnDeleteFromCart);
        tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
        tvTotal = itemView.findViewById(R.id.tvTotal);
        spinner_cantidad = itemView.findViewById(R.id.spinner_cantidad);

    }
}
