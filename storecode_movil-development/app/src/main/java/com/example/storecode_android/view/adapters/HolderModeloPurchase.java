package com.example.storecode_android.view.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.R;

public class HolderModeloPurchase extends RecyclerView.ViewHolder {

    public final LinearLayout item_card_view;
    public final ImageView ivModelo;
    public final TextView tvNameProduct, tvDescripcion, tvCantidad, tvPrice;


    public HolderModeloPurchase(View itemView) {
        super(itemView);
        item_card_view = itemView.findViewById(R.id.item_card_view);
        ivModelo = itemView.findViewById(R.id.ivProductPurchase);
        tvNameProduct = itemView.findViewById(R.id.tvNameProductPurchase);
        tvDescripcion = itemView.findViewById(R.id.tvDescriptionPurchase);
        tvCantidad = itemView.findViewById(R.id.tvQuantityPurchase);
        tvPrice= itemView.findViewById(R.id.tvPricePurchase);
    }
}
