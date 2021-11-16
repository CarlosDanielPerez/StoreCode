package com.example.storecode_android.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
/*import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;*/
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.storecode_android.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.entidades.ProductoBebes;
import com.example.storecode_android.entidades.ProductoTenis;
import com.example.storecode_android.utils.LogFile;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/*import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.R;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.model.ReqObtenerAPKAplicaciones;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.model.ReqObtenerDetallesAplicaciones;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.model.RespObtenerAPKAplicaciones;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.model.RespObtenerDatosAplicaciones;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.model.RespObtenerDetalleAplicaciones;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.service.rest.RestClientServiceSMA;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.service.rest.RetrofitDownloadListener;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.service.rest.impl.RestClientSerSMAImpl;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.utils.AnimacionesGenerales;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.utils.Constantes;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.utils.FuncionesGenerales;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.utils.LogFile;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.utils.SharedPref;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.view.DetalleAplicacionesInstaladas;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.view.MainDrawerActivity;*/

/**
 * Description: ModelorRecycler Lista Aplicaciones Instaladas
 * Created by EX440831 on 14/02/2020.
 */

@SuppressWarnings("ALL")
public class BebesAdapter extends RecyclerView.Adapter<HolderModeloInstaladas> implements Filterable {

    private static final Logger log = LogFile.getLogger(BebesAdapter.class);
    /*private final Activity context;
    private final List<RespObtenerDatosAplicaciones> modeloList;
    private List<RespObtenerDatosAplicaciones> mFilteredList;*/

    private final ArrayList<ProductoBebes> modeloList = new ArrayList<ProductoBebes>();
    private int aux;

    private ImageButton btn_buscar;
    private int bandera_search = 0;
    private SearchView msearchView;
    private Context context;
    private List<ProductoBebes> mFilteredList = modeloList;

    private ModeloAdapterListener modeloAdapterListener;

    String version;

    Drawable d;

    public BebesAdapter(Activity context, SearchView searchView) {
        this.context = context;
        //this.modeloList = modeloList;
        //this.mFilteredList = modeloList;
        this.aux = aux;
        this.msearchView = searchView;

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
                    ArrayList<ProductoBebes> filteredList = new ArrayList<>();
                    for (ProductoBebes modelo : modeloList) {
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
                mFilteredList = (ArrayList<ProductoBebes>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public HolderModeloInstaladas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_modelos_instaladas, parent, false);
        filtrarModelos();
        //filtrarModelos();
        return new HolderModeloInstaladas(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HolderModeloInstaladas holder, int position) {
        ProductoBebes producto = mFilteredList.get(position);
        System.out.println("----------------");
        System.out.printf("OnBindViewHolder");
        System.out.println(producto.getNombreProducto());
        holder.tvName.setText(producto.getNombreProducto());
        holder.tvPrice.setText("$ " + producto.getPrecioUnitarioProducto());
        holder.tvDescription.setText(producto.getDesProducto());
        //holder.ivModelo.setImageURI(Uri.parse(producto.getImagenProducto()));
        /*Picasso.with(context)
                .load(Uri.parse(producto.getImagenProducto()))
                .into(holder.ivModelo);*/
        Picasso.get().load(Uri.parse(producto.getImagenProducto()))
                .into(holder.ivModelo);


        System.out.println("Click en:" + producto.getNombreProducto());

        //Metodo para mostrar el detalle del producto
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("producto:" + producto.getNombreProducto());
                //modeloAdapterListener.OnProductClicked(modeloList.get(position),position);
            }
        });


        //Toast.makeText(context,"EL producto que seleccionaste es:"+producto.getNombreProducto()+"y su id es:"+producto.getIdProducto(),Toast.LENGTH_SHORT).show();

        //ENVIAR SIG SERVICIO
        //CONSULTAR PRODUCTO POR ID
        //guardar en prefs
        //consultarlo en la otra pantalla
        //reemplazar prefs


    }

    @Override
    public int getItemCount() {
        System.out.println("---------getItemCount de modelo instaladas------------");
        System.out.println(modeloList.size());
        return mFilteredList.size();
    }

    public void updateData(List<ProductoBebes> data) {
        System.out.println("en el update data");

        modeloList.clear();
        modeloList.addAll(data);
        mFilteredList = modeloList;
        System.out.println(modeloList);
        notifyDataSetChanged();
    }


    public void filtrarModelos() {
        msearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                getFilter().filter(query);
                return false;
            }
        });
    }


}
