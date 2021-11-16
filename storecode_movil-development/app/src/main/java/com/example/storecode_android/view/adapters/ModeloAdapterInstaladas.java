package com.example.storecode_android.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
/*import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;*/
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.storecode_android.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.utils.LogFile;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class ModeloAdapterInstaladas extends RecyclerView.Adapter<HolderModeloInstaladas> implements Filterable {

    private static final Logger log = LogFile.getLogger(ModeloAdapterInstaladas.class);
    /*private final Activity context;
    private final List<RespObtenerDatosAplicaciones> modeloList;
    private List<RespObtenerDatosAplicaciones> mFilteredList;*/

    private final ArrayList<RespObtenerProducto> modeloList= new ArrayList<RespObtenerProducto>();
    private int aux;

    private ImageButton btn_buscar;
    private int bandera_search = 0;
    private SearchView msearchView;
    private Context context;
    private List<RespObtenerProducto> mFilteredList= modeloList;
    List<RespObtenerProducto> listaoriginal;


    private ModeloAdapterListener modeloAdapterListener;

    String version;

    Drawable d;

    public ModeloAdapterInstaladas(ModeloAdapterListener modeloAdapterListener, Activity context, SearchView searchView) {
        this.context = context;
        //this.modeloList = modeloList;
        //this.mFilteredList = modeloList;
        this.aux = aux;
        this.msearchView =searchView;
        this.modeloAdapterListener= modeloAdapterListener;
        listaoriginal = new ArrayList<>();
        listaoriginal.addAll(mFilteredList);



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
                    ArrayList<RespObtenerProducto> filteredList = new ArrayList<>();
                    for (RespObtenerProducto modelo : modeloList) {
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
                mFilteredList = (ArrayList<RespObtenerProducto>) filterResults.values;
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
        RespObtenerProducto producto= mFilteredList.get(position);
        System.out.println("----------------");
        System.out.printf("OnBindViewHolder");
        System.out.println(producto.getNombreProducto());
        holder.tvName.setText(producto.getNombreProducto());
        holder.tvPrice.setText("$ "+producto.getPrecioUnitarioProducto());
        holder.tvDescription.setText(producto.getDesProducto());
        //holder.ivModelo.setImageURI(Uri.parse(producto.getImagenProducto()));
        /*Picasso.with(context)
                .load(Uri.parse(producto.getImagenProducto()))
                .into(holder.ivModelo);*/
        Picasso.get().load(Uri.parse(producto.getImagenProducto()))
                .into(holder.ivModelo);


        System.out.println("Click en:"+producto.getNombreProducto());

        //Metodo para mostrar el detalle del producto
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("producto:"+producto.getNombreProducto());
                modeloAdapterListener.OnProductClicked(modeloList.get(position),position);
            }
        });




        //Toast.makeText(context,"EL producto que seleccionaste es:"+producto.getNombreProducto()+"y su id es:"+producto.getIdProducto(),Toast.LENGTH_SHORT).show();

        //ENVIAR SIG SERVICIO
        //CONSULTAR PRODUCTO POR ID
        //guardar en prefs
        //consultarlo en la otra pantalla
        //reemplazar prefs



    }

    public void filtrado(final String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud == 0){
            mFilteredList.clear();
            mFilteredList.addAll(listaoriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<RespObtenerProducto> collecion = mFilteredList.stream()
                        .filter(i -> i.getNombreProducto().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());

                mFilteredList.clear();
                mFilteredList.addAll(collecion);
            } else {

                for (RespObtenerProducto c : listaoriginal) {
                    if(c.getNombreProducto().toLowerCase().contains(txtBuscar.toLowerCase())){
                        mFilteredList.add(c);
                    }

                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        System.out.println("---------getItemCount de modelo instaladas------------");
        System.out.println(modeloList.size());
        return mFilteredList.size();
    }



    public void updateData(List<RespObtenerProducto> data){
        System.out.println("en el update data");

        modeloList.clear();
        modeloList.addAll(data);
        mFilteredList=modeloList;
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

    //DetalleAplicacionesInstaladas mdetalleAplicacionesInstaladas;



    //@NonNull
    /*@Override
    public HolderModeloInstaladas onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_modelos_instaladas, parent, false);

        //filtrarModelos();
        return new HolderModeloInstaladas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderModeloInstaladas holder, final int position) {*/
        /*final RespObtenerDatosAplicaciones modelo = mFilteredList.get(position);

        log.info("Resultado Lista: "+ mFilteredList.size());
        if(mFilteredList.size() > 3 ){
            msearchView.setVisibility(View.VISIBLE);
        }

        version = modelo.getVersion();
        String version_app = FuncionesGenerales.getAppVersion(context.getApplicationContext(), modelo.getPackageName());

        holder.tvDescripcion.setText(modelo.getNombreAplicacion());

        String image = modelo.getApkIcon();
        byte [] encodeByte= Base64.decode(image,Base64.DEFAULT);
        InputStream inputStream  = new ByteArrayInputStream(encodeByte);
        Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
        d = new BitmapDrawable(context.getResources(), bitmap);
        holder.ivModelo.setImageDrawable(d);
        holder.tvTamanio.setText(modelo.getTamanioApp() + context.getString(R.string.name_megabytes));

        holder.ivModelo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder.ivModelo, "alpha",0, 1);
                        objectAnimator.setDuration(500);
                        objectAnimator.setStartDelay(0);
                        objectAnimator.start();
                        return true;

                    case MotionEvent.ACTION_UP:
                        String version = modelo.getVersion();
                        String version_app = FuncionesGenerales.getAppVersion(context.getApplicationContext(), modelo.getPackageName());

                        if(!version_app.equals(version)){
                            //Aplicacion con Detalle a Actualizar
                            consumoServicioDetalle(modelo.getAppId(), 1);
                        }else{
                            //Aplicacion con Detalle sin Actualizar
                            consumoServicioDetalle(modelo.getAppId(), 0);
                        }
                        return true;
                }
                return false;
            }
        });

        //Hay actualización
        if(!version_app.equals(version)){
            try {
                holder.activity_btn_abrir.setBackground(ContextCompat.getDrawable(context, R.drawable.button_border_azul));
                holder.activity_btn_abrir.setTextColor(ContextCompat.getColor(context,R.color.blanco));
                holder.activity_btn_abrir.setText("ACTUALIZAR");

                holder.activity_btn_abrir.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch(event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                holder.activity_btn_abrir.setBackground(context.getDrawable(R.drawable.button_border));
                                holder.activity_btn_abrir.setTextColor(context.getColor(R.color.colorBackground));
                                return true;

                            case MotionEvent.ACTION_UP:
                                holder.activity_btn_abrir.setBackgroundColor(context.getColor(R.color.azul_carta_2));
                                holder.activity_btn_abrir.setTextColor(context.getColor(R.color.blanco));

                                consumoServicioDescargaAplicativo(modelo.getAppId(), modelo.getNombreAplicacion());
                                return true;
                        }
                        return false;
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
            }

            log.info("Nombre de la App: "+ modelo.getNombreAplicacion() + "Versión Instalada:"+ version_app + "Version BD: "+ version);
        }else{

            try {

                holder.activity_btn_abrir.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch(event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                holder.activity_btn_abrir.setBackgroundColor(context.getColor(R.color.azul_carta_2));
                                holder.activity_btn_abrir.setTextColor(context.getColor(R.color.blanco));
                                return true;

                            case MotionEvent.ACTION_UP:
                                holder.activity_btn_abrir.setBackground(context.getDrawable(R.drawable.button_border));
                                holder.activity_btn_abrir.setTextColor(context.getColor(R.color.colorBackground));

                                consumoServicioDetalle(modelo.getAppId(),0);
                                return true;
                        }
                        return false;
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
            }
            log.info("Nombre de la App: "+ modelo.getNombreAplicacion() + "Versión Instalada:"+ version_app + "Version BD: "+ version);
        }
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
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
                    ArrayList<RespObtenerDatosAplicaciones> filteredList = new ArrayList<>();
                    for (RespObtenerDatosAplicaciones modelo : modeloList) {

                        if (modelo.getNombreAplicacion().toLowerCase().startsWith(charString)) {
                            filteredList.add(modelo);
                        } else if (modelo.getNombreAplicacion().toLowerCase().contains(charString)) {
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
                mFilteredList = (ArrayList<RespObtenerDatosAplicaciones>) filterResults.values;
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

    //Servicio que captura detalle del aplicativo
    private void consumoServicioDetalle(final Integer id, final Integer aux){
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

                        //Primero Elimino la descripcion anterior
                        SharedPref.deleteDescripcionAplicaciones(context);

                        //Guardo Preferencias Actuales
                        SharedPref.guardarDescripcionAplicaciones(context,response.body().toString());

                        Bundle bundle = new Bundle();
                        bundle.putInt("appID",id);
                        bundle.putInt("aux_return",1);
                        if(aux == 1){
                            bundle.putString("aux","notificaciones_actualizacion");
                        }else if (aux == 0){
                            bundle.putString("aux","notificaciones_sin_actualizacion");
                        }
                        bundle.putInt("appID",id);
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
    }


    //Servicio que descarga aplicativo
    public void consumoServicioDescargaAplicativo(Integer app_id, String nombre_aplicativo){
        log.info( "--consumirAPKAplicaciones--");

        ReqObtenerAPKAplicaciones request = new ReqObtenerAPKAplicaciones();
        request.setNombreUsuario(Constantes.USER_PRECIADORES);
        request.setContrasenia(Constantes.PASS_PRECIADORES);
        log.info("Datos Apps: " + app_id + " "+nombre_aplicativo);
        request.setAppId(app_id);

        AnimacionesGenerales.mostrarAlertDialogDescarga(context,"Descargando "+ nombre_aplicativo,"Espere un momento...",  true);

        RestClientServiceSMA api = new RestClientSerSMAImpl();
        Call<RespObtenerAPKAplicaciones> call = api.obtenerAPK(request);
        log.info("REQUEST:"+request.toString());
        call.enqueue(new Callback<RespObtenerAPKAplicaciones>() {
            @Override
            public void onResponse(Call<RespObtenerAPKAplicaciones> call, retrofit2.Response<RespObtenerAPKAplicaciones> response) {
                if (response != null && response.code() == 200 && response.isSuccessful()*//*&& response.body().getDetailResponse().getCode().equals(RESP_CODE_OK)*//*) {

                    log.info( "Se descarga correctamente...:");
                    RespObtenerAPKAplicaciones resp = response.body();
                    if (guardarApk(resp)) {
                        AnimacionesGenerales.mostrarAlertDialogDescarga(((Activity) context),null,null, false);
                        lanzarInstalacion();
                    } else {
                        AnimacionesGenerales.mostrarAlertDialogDescarga(((Activity) context),null,null, false);
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(((Activity) context));
                    }

                }
            }
            @Override
            public void onFailure(Call<RespObtenerAPKAplicaciones> call, final Throwable t) {
                AnimacionesGenerales.mostrarAlertDialogDescarga((Activity) context,null,null, false);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(((Activity) context));
            }
        });
    }

    private boolean guardarApk(RespObtenerAPKAplicaciones response) {
        log.info( "--guardarApk--");
        try {
            FileOutputStream fos;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                File newAPKFile = new File(context.getExternalFilesDir(null), Constantes.APK_TEMP_NAME);
                log.info( "PATH:" + newAPKFile.getAbsolutePath());
                fos = new FileOutputStream(newAPKFile);
            } else {
                fos = context.openFileOutput(Constantes.APK_TEMP_NAME, context.MODE_WORLD_READABLE | context.MODE_WORLD_WRITEABLE);
            }
            InputStream is = new ByteArrayInputStream(FuncionesGenerales.decodeBase64(response.getAplicationFile()));
            byte[] buffer = new byte[1024];
            int len1;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.flush();
            fos.close();
            is.close();

            return true;
        } catch (Exception e) {
            log.error( e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private void eliminarApk() {
        log.info( "--eliminarApk--");
        File fileLocation;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileLocation = new File(context.getExternalFilesDir(null), Constantes.APK_TEMP_NAME);
        } else {
            fileLocation = new File(context.getFilesDir(), Constantes.APK_TEMP_NAME);
        }
        if (fileLocation.exists())
            fileLocation.delete();
    }

    public void lanzarInstalacion() {
        try {
            log.info( "--lanzarInstalacion--");
            Intent downloadIntent = new Intent(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                downloadIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                File fileLocation = new File(context.getExternalFilesDir(null), Constantes.APK_TEMP_NAME);
                Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", fileLocation);
                downloadIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(downloadIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    context.grantUriPermission(packageName, apkUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
            } else {
                File fileLocation = new File(context.getFilesDir(), Constantes.APK_TEMP_NAME);
                downloadIntent.setDataAndType(Uri.fromFile(fileLocation), "application/vnd.android.package-archive");
            }
            context.startActivityForResult(downloadIntent, 55);
        } catch (Exception e) {
            log.error( e.getMessage());
            log.info( "ERROR:" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void porcentajeDescarga(int porcentaje) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AnimacionesGenerales.actualizaProgressBar(porcentaje);
            }
        });
    }*/


}

