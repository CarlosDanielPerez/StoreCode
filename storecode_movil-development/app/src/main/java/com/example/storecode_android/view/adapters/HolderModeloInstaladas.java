package com.example.storecode_android.view.adapters;

/*import android.support.v7.widget.RecyclerView;*/
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.R;

//import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.R;

/**
 * Description: HolderRecycler Lista Aplicaciones Instaladas
 * Created by EX440831 on 14/02/2020.
 */

public class HolderModeloInstaladas extends RecyclerView.ViewHolder{

    public final ImageView ivModelo;
    public final TextView tvName, tvPrice, tvDescription;
    //public final Button activity_btn_abrir;

    public HolderModeloInstaladas(View itemView) {
        super(itemView);
        ivModelo = itemView.findViewById(R.id.ivModelo);
        tvName = itemView.findViewById(R.id.tvName);
        tvPrice = itemView.findViewById(R.id.tvPrice);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        //activity_btn_abrir = itemView.findViewById(R.id.activity_btn_abrir);
    }
}
