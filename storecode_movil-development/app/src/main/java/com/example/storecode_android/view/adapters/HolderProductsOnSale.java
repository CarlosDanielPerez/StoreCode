package com.example.storecode_android.view.adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.R;

public class HolderProductsOnSale extends RecyclerView.ViewHolder {

    ImageView ivModeloOnSale;
    TextView tvNameOnSale;
    TextView tvDescriptionOnSale;
    TextView tvPriceOnSale;
    TextView tvStockOnSale;
    TextView tvStatusOnSale;
    TextView tvMarcaOnSale;
    TextView tvCategoryOnSale;
    ImageButton btnEdit,btnDelete,btnAddImages;

    public HolderProductsOnSale(@NonNull View itemView) {
        super(itemView);
        ivModeloOnSale = itemView.findViewById(R.id.ivModeloOnSale);
        tvNameOnSale = itemView.findViewById(R.id.tvNameOnSale);
        tvDescriptionOnSale = itemView.findViewById(R.id.tvDescriptionOnSale);
        tvPriceOnSale = itemView.findViewById(R.id.tvPriceOnSale);
        tvStockOnSale = itemView.findViewById(R.id.tvStockOnSale);
        tvStatusOnSale = itemView.findViewById(R.id.tvStatusOnSale);
        tvMarcaOnSale = itemView.findViewById(R.id.tvMarcaOnSale);
        tvCategoryOnSale = itemView.findViewById(R.id.tvCategoryOnSale);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        btnDelete = itemView.findViewById(R.id.btnDelete);
        btnAddImages = itemView.findViewById(R.id.btnAddImages);

    }

}
