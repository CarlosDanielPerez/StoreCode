package com.example.storecode_android.view.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.R;

public class HolderComentarios extends RecyclerView.ViewHolder{

    public final ImageView ivPhoto;
    public final TextView tvName, tvComent;
    //public final Button activity_btn_abrir;

    public HolderComentarios(View itemView) {
        super(itemView);
        ivPhoto = itemView.findViewById(R.id.ivPhoto);
        tvName = itemView.findViewById(R.id.tvName);
        tvComent = itemView.findViewById(R.id.tvComent);
        //activity_btn_abrir = itemView.findViewById(R.id.activity_btn_abrir);
    }
}
