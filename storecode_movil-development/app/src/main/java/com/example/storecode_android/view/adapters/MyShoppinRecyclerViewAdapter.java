package com.example.storecode_android.view.adapters;

import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.storecode_android.R;
import com.example.storecode_android.entidades.Purchase;
import com.example.storecode_android.entidades.RespMyShopping;
import com.example.storecode_android.view.fragments.MyShoppinFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class MyShoppinRecyclerViewAdapter extends RecyclerView.Adapter<MyShoppinRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Purchase> mValues= new ArrayList();

    /*public MyShoppinRecyclerViewAdapter(ArrayList<RespMyShopping> items) {
        mValues = items;
    }*/

    public  MyShoppinRecyclerViewAdapter(){

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_myshopping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvClaveTransaccion.setText("Clave Transacción: "+holder.mItem.getClaveTransaccion());
        holder.tvDatePurchase.setText("Fecha: "+holder.mItem.getFechaVenta().toString());
        holder.tvMonto.setText("Monto: $"+holder.mItem.getTotalVendido().toString());

        holder.itemView.setOnClickListener(v-> Navigation.findNavController(v).navigate(MyShoppinFragmentDirections.toPurchasedDetailFragment(holder.mItem)));
        //holder.tvDesProducto.setText(holder.mItem.getDesProducto());
        //holder.tvQuantityProducto.setText(holder.mItem.getCantidadProducto());
        //holder.tvTotalVendido.setText(holder.mItem.getTotalVendido().toString());

        //Picasso.get().load(holder.mItem.getImagenProducto()).into(holder.ivModeloMyShopping);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateData(List<Purchase> data) {
        System.out.println("------------en el update data de RespMyShopping---------");

        mValues.clear();
        mValues.addAll(data);
        System.out.println(mValues);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvClaveTransaccion;
        public final TextView tvDatePurchase;
        public final TextView tvMonto;



        public Purchase mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvClaveTransaccion = view.findViewById(R.id.tvClaveTransaccion);
            tvDatePurchase = view.findViewById(R.id.tvDatePurchase);
            tvMonto= view.findViewById(R.id.tvMonto);
        }

        @Override
        public String toString() {

            return super.toString() + " '" + tvClaveTransaccion.getText() + "'";
        }
    }
}