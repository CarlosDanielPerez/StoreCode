package com.example.storecode_android.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.RespDetaProductoComen;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.utils.LogFile;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<HolderComentarios> implements Filterable {

    private static final Logger log = LogFile.getLogger(ModeloAdapterInstaladas.class);

    private final ArrayList<RespDetaProductoComen> modeloList= new ArrayList<RespDetaProductoComen>();
    private int aux;

    private Activity context;


    //El filtered para que funciona?
    private List<RespDetaProductoComen> mFilteredList= modeloList;

    //private ModeloAdapterListener modeloAdapterListener;


    public ComentariosAdapter(Activity context) {
        this.context= context;
    }


    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public HolderComentarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_coments, parent, false);

        //filtrarModelos();
        return new HolderComentarios(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HolderComentarios holder, int position) {
        RespDetaProductoComen comentario= modeloList.get(position);
        System.out.println("----------------");
        System.out.printf("OnBindViewHolder Comentarios");

        System.out.println(comentario.getComentario());


        if(comentario.getImagenUsuario()!=null){
            Picasso.get().load(Uri.parse(comentario.getImagenUsuario()))
                    .into(holder.ivPhoto);
        }


        holder.tvName.setText(comentario.getNombre());
        holder.tvComent.setText(comentario.getComentario());


    }

    @Override
    public int getItemCount() {
        System.out.println("---------getItemCount de modelo instaladas------------");
        System.out.println(modeloList.size());
        return modeloList.size();
    }

    public void updateData(List<RespDetaProductoComen> data){
        System.out.println("en el update data");

        modeloList.clear();
        modeloList.addAll(data);
        System.out.println(modeloList);
        notifyDataSetChanged();
    }

}
