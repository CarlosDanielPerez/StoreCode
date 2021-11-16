package com.example.storecode_android.view.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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
import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.view.fragments.MyShoppinFragmentDirections;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ModeloAdapterPurchase extends RecyclerView.Adapter<HolderModeloPurchase> implements Filterable {


    private static final Logger log = LogFile.getLogger(ModeloAdapterPurchase.class);
    private final Activity context;
    private final List<ReqItemProduct> modeloList;
    private List<ReqItemProduct> mFilteredList;
    private int aux;
    private RecyclerView mrecyclerView;

    /*private ImageButton btn_buscar;
    private int bandera_search=0;
    private SearchView msearchView;*/

    Drawable d;


    public ModeloAdapterPurchase(List<ReqItemProduct> modeloList, Activity context, RecyclerView recyclerView) {
        this.context = context;
        this.modeloList = modeloList;
        this.mFilteredList = modeloList;
        this.mrecyclerView = recyclerView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    mFilteredList = modeloList;
                } else {
                    ArrayList<ReqItemProduct> filteredList = new ArrayList<>();
                    for (ReqItemProduct modelo : modeloList) {
                        if (modelo.getNombreProducto().toLowerCase().startsWith(charString)) {
                            filteredList.add(modelo);
                        } else if (modelo.getNombreProducto().toLowerCase().contains(charString)) {
                            filteredList.add(modelo);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence,
                                          FilterResults filterResults) {
                mFilteredList = (ArrayList<ReqItemProduct>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public HolderModeloPurchase onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_purchase, parent, false);

        return new HolderModeloPurchase(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderModeloPurchase holder, int position) {
        final ReqItemProduct modelo = mFilteredList.get(position);

        holder.tvNameProduct.setText(modelo.getNombreProducto());
        Picasso.get().load(modelo.getImagenProducto()).into(holder.ivModelo);
        holder.tvDescripcion.setText("Descripción: "+modelo.getDescription());
        //holder.tvCantidad.setText(modelo.getQuantity());
        holder.tvPrice.setText("Precio unitario: $"+modelo.getPrice().toString());


    }

    @Override
    public int getItemCount() {
        return modeloList.size();
    }



}
