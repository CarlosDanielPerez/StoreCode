package com.example.storecode_android.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.PurchasedItem;
import com.example.storecode_android.utils.LogFile;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ModeloAdapterPurchasedItem extends RecyclerView.Adapter<HolderPurchasedItem> implements Filterable {

    private static final Logger log = LogFile.getLogger(ModeloAdapterPurchasedItem.class);
    private final ArrayList<PurchasedItem> modeloList = new ArrayList<PurchasedItem>();
    private int aux;

    private int bandera_search = 0;
    private Context context;
    //private List<RespGetProductByUser> mFilteredList = modeloList;


    public ModeloAdapterPurchasedItem(Activity context){
        this.context= context;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public HolderPurchasedItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_purchased, parent, false);

        //filtrarModelos();
        return new HolderPurchasedItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPurchasedItem holder, int position) {

        PurchasedItem item = modeloList.get(position);
        System.out.println("item: "+item.toString());
        Picasso.get().load(item.getImagenProducto()).into(holder.ivProduct);
        holder.tvNameProduct.setText("Nombre del producto: "+item.getNombreProducto());
        holder.tvDescriptionProduct.setText("Descripción: "+item.getDesProducto());
        holder.tvPriceProduct.setText("Precio unitario: $"+item.getPrecioUnitarioProducto());
        holder.tvQuantityProduct.setText("Cantidad: "+item.getCantidadProducto());

        Double subtotal= item.getPrecioUnitarioProducto()*item.getCantidadProducto();

        holder.tvSubtotal.setText("Subtotal: $"+ subtotal);

    }

    @Override
    public int getItemCount() {
        return modeloList.size();
    }

    public void updateData(List<PurchasedItem> data) {
        System.out.println("en el update data de ModeloAdapterPurchasedItem");

        modeloList.clear();
        modeloList.addAll(data);
        System.out.println(modeloList);
        notifyDataSetChanged();
    }
}
