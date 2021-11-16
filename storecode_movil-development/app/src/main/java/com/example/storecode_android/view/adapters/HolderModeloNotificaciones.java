package com.example.storecode_android.view.adapters;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.R;


/**
 * Description: HolderRecycler Lista Notificaciones
 * Created by EX440831 on 14/02/2020.
 */

public class HolderModeloNotificaciones extends RecyclerView.ViewHolder{

    public final LinearLayout item_card_view;
    public final ImageView ivModelo;
    public final TextView tvTituloApp, tvDescripcion,tvPrice;
    public final Button btnVerDetalle;

    public HolderModeloNotificaciones(View itemView) {
        super(itemView);
        item_card_view = itemView.findViewById(R.id.item_card_view);
        ivModelo = itemView.findViewById(R.id.ivModelo);
        tvTituloApp = itemView.findViewById(R.id.tvTitulo);
        tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        tvPrice= itemView.findViewById(R.id.tvPrice);
        btnVerDetalle = itemView.findViewById(R.id.btnVerDetalle);
    }
}
