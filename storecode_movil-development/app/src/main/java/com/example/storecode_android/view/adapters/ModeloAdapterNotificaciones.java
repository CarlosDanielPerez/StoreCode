package com.example.storecode_android.view.adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.fragments.DetalleCompraFragment;
import com.example.storecode_android.view.fragments.NotificationsFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Description: ModeloRecycler Lista Notificaciones
 * Created by EX440831 on 14/02/2020.
 */

@SuppressWarnings("ALL")
public class ModeloAdapterNotificaciones extends RecyclerView.Adapter<HolderModeloNotificaciones> implements Filterable {

    private static final Logger log = LogFile.getLogger(ModeloAdapterNotificaciones.class);

    private final Activity context;
    private final FragmentManager fragmentManager;
    private final List<NotificationToDevice> modeloList;
    private List<NotificationToDevice> mFilteredList;
    private int aux;
    private RecyclerView mrecyclerView;

    private ImageButton btn_buscar;
    private int bandera_search=0;
    private SearchView msearchView;

    Drawable d;

    //DetalleAplicacionesInstaladas mdetalleAplicacionesInstaladas;

    public ModeloAdapterNotificaciones(List<NotificationToDevice> modeloList, Activity context, FragmentManager fragmentManager, RecyclerView recyclerView) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.modeloList = modeloList;
        this.mFilteredList = modeloList;
        this.mrecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public HolderModeloNotificaciones onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_notificaciones, parent, false);

        return new HolderModeloNotificaciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderModeloNotificaciones holder, final int position) {
        final NotificationToDevice modelo = mFilteredList.get(position);

        final List<ReqItemProduct> productosComprados = getProducts(modelo.getItems());



        holder.tvTituloApp.setText("Clave transacción: \n"+modelo.getClaveTransaccion());

        if(productosComprados.get(0).getImagenProducto()!=null){
            Picasso.get().load(Uri.parse(productosComprados.get(0).getImagenProducto()))
                    .into(holder.ivModelo);
        }

        String description="Detalles de tu compra";

        for (ReqItemProduct productoComprado: productosComprados) {
            description= description+ "\n"+"Producto: "+productoComprado.getNombreProducto()+"\n"+
                    "Cantidad: "+productoComprado.getQuantity()+
                    "\n"+"Precio Unitario: $"+productoComprado.getPrice();
        }

        //description= description+"\n"+modelo.getTotalVendido().toString();

        holder.tvDescripcion.setText(description);
        holder.tvPrice.setText("Total: $"+modelo.getTotalVendido().toString());


        holder.btnVerDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* NotificationsFragmentDirections.ToDetalleCompraFragment action= NotificationsFragmentDirections.toDetalleCompraFragment(modelo);
                Navigation.findNavController(v).navigate(action);

                System.out.println(modelo.toString());*/
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref.guardarNotificacionCompra(context, modelo);
                System.out.println("--------------modelo--------------------------");
                System.out.println(modelo.toString());

                DetalleCompraFragment detalleCompraFragment = new DetalleCompraFragment();

                context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                fragmentManager.beginTransaction().replace(R.id.main_contenedor, detalleCompraFragment).commit();

                //context.getParent().getFragmentManager().beginTransaction().replace(R.id.main_contenedor, detalleCompraFragment).commit();
            }
        });

        /*holder.item_card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navegar hacia la pantalla "Detalle de la compra"


                //log.info("ID de la aplicacion de la notificacion: "+ modelo.getItems());
                //consumoServicioDetalle(Integer.parseInt(modelo.getAppID()));

                //Elimino la notificacion seleccionada
                SharedPref.deleteNotificacionDescartada(context);
                mFilteredList.remove(position);
                mrecyclerView.removeViewAt(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mFilteredList.size());
                notifyDataSetChanged();

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }


    public void updateData(List<NotificationToDevice> data) {
        System.out.println("en el update data");

        modeloList.clear();
        modeloList.addAll(data);
        System.out.println(modeloList);
        notifyDataSetChanged();
    }

    public ArrayList<ReqItemProduct> getProducts(String items){
        Type listType = new TypeToken<List<ReqItemProduct>>(){}.getType();
        ArrayList<ReqItemProduct> listProducts= new Gson().fromJson(items, listType);
        return listProducts;
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
                    ArrayList<NotificationToDevice> filteredList = new ArrayList<>();
                    for (NotificationToDevice modelo : modeloList) {
                        if (modelo.getClaveTransaccion().toLowerCase().startsWith(charString)) {
                            filteredList.add(modelo);
                        } else if (modelo.getTotalVendido().toString().toLowerCase().contains(charString)) {
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
                mFilteredList = (ArrayList<NotificationToDevice>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private void filtrarModelos() {
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

  /*  //Servicio que captura detalle del aplicativo
    private void consumoServicioDetalle(final Integer id){
        log.info( "--consumirAplicacionesDisponibles--");

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle(R.string.title_progress);
        dialog.setMessage(context.getString(R.string.message_progress));
        dialog.setCancelable(false);
        dialog.show();

        ReqObtenerDetallesAplicaciones request = new ReqObtenerDetallesAplicaciones();
        request.setNombreUsuario(Constantes.USER_PRECIADORES);
        request.setContrasenia(Constantes.PASS_PRECIADORES);
        request.setSistemaOperativo(Constantes.SERV_KEY_SO);
        request.setTipoDispositivo(Constantes.SERV_KEY_DISP_CELULAR);
        request.setAppId(id);

        RestClientServiceSMA api = new RestClientSerSMAImpl();
        Call<RespObtenerDetalleAplicaciones> call = api.obtenerDetalle(request);
        log.info("REQUEST:"+request.toString());
        call.enqueue(new Callback<RespObtenerDetalleAplicaciones>() {
            @Override
            public void onResponse(Call<RespObtenerDetalleAplicaciones> call, retrofit2.Response<RespObtenerDetalleAplicaciones> response) {
                if (response != null && response.code() == Constantes.RESP_CODE_WEB_OK && response.isSuccessful()) {

                    if (dialog.isShowing()) {
                        ((Activity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        dialog.dismiss();
                    }

                    if(response.body().getMensajeRespuesta().equals(Constantes.RESP_CODE_OK)){

                        //Elimino Preferencias Anteriores
                        SharedPref.deleteDescripcionAplicaciones(context);

                        //Guardo Preferencias Actuales
                        SharedPref.guardarDescripcionAplicaciones(context,response.body().toString());

                        Bundle bundle = new Bundle();
                        bundle.putInt("appID",id);
                        bundle.putString("aux","notificaciones_actualizacion");
                        mdetalleAplicacionesInstaladas = new DetalleAplicacionesInstaladas();
                        mdetalleAplicacionesInstaladas.setArguments(bundle);
                        ((MainDrawerActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_contenedor, mdetalleAplicacionesInstaladas).commit();

                    }else{
                        Toast.makeText(context, context.getString(R.string.error_info),Toast.LENGTH_SHORT).show();
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(context);
                    }
                }
            }
            @Override
            public void onFailure(Call<RespObtenerDetalleAplicaciones> call, final Throwable t) {
                if (dialog.isShowing()) {
                    ((MainDrawerActivity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    dialog.dismiss();
                }
                AnimacionesGenerales.mostrarLoader(false,context, null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(context);
            }
        });
    }*/
}

