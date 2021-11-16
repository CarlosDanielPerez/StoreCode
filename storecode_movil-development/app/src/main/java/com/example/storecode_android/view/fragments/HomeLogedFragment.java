package com.example.storecode_android.view.fragments;

import android.app.Activity;
import android.os.Bundle;
/*import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;*/
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.entidades.ProductoTenis;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.adapters.ModeloAdapterInstaladas;
import com.example.storecode_android.view.adapters.ModeloAdapterListener;

import org.apache.log4j.Logger;

import java.util.List;

import com.example.storecode_android.R;

/*import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.R;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.model.RespObtenerDatosAplicaciones;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.utils.FuncionesGenerales;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.utils.LogFile;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.utils.SharedPref;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.view.SplashActivity;
import mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel.view.adapter_instaladas.ModeloAdapterInstaladas;*/

/**
 * Description: Fragmento para Aplicaciones Instaladas
 * Created by EX440831 on 14/02/2020.
 */

public class HomeLogedFragment extends Fragment implements ModeloAdapterListener {

    private static final Logger log = LogFile.getLogger(HomeLogedFragment.class);
    public RecyclerView recyclerView;
    public Activity mContext;
    public List<RespObtenerProducto> respuesta;

    //declarar presenter y adapter
    private ModeloAdapterInstaladas modeloAdapterInstaladas;
    private ProductPresenter productPresenter;

    private RelativeLayout rlBaseHome;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /*String response = SharedPref.obtenerAplicaciones(getContext());
        if(!response.equals("Vacio")) {
            log.info("Lista Aplicaciones: "+ response);

            Type listType = new TypeToken<ArrayList<RespObtenerProducto>>(){}.getType();
            //Type listType = new TypeToken<RespObtenerProducto>(){}.getType();
            respuesta = new Gson().fromJson(response, listType);
            log.info("Lista Productos Convertidas: "+ respuesta.toString());
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_loged, container, false);
        /*mContext = getActivity();

        recyclerView = view.findViewById(R.id.activity_preciadorunicomodelo_recyclerView);
        SearchView searchView = view.findViewById(R.id.activity_preciadorunicomodelo_searchView2);


        if(respuesta != null){
            if(respuesta.isEmpty()){
                log.info("No hay Registros Disponibles");
            }else{

                Iterator<RespObtenerProducto> it = respuesta.iterator();

                while(it.hasNext()){
                    RespObtenerProducto item=it.next();
                    log.info("Nombre Aplicación: " + item.getNombreProducto());

                    *//*if (!FuncionesGenerales.validaAplicacionInstalado(mContext.getApplicationContext(), item.getPackageName().trim())) {
                        it.remove();
                    }*//*

                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
                    ModeloAdapterInstaladas adapter = new ModeloAdapterInstaladas(this,respuesta, getActivity(), searchView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);
                }
            }
        }else{
            *//*Intent intent = new Intent(getActivity(), SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            getActivity().finish();*//*
        }*/


        return view;


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //loginPresenter = new LoginPresenter(view);
        rlBaseHome = view.findViewById(R.id.rlBaseHome);
        //checar si manda error
        //productPresenter = new ProductPresenter(getContext());
        productPresenter = new ProductPresenter(getContext(),getView());
        recyclerView= view.findViewById(R.id.activity_preciadorunicomodelo_recyclerView);
        SearchView searchView = view.findViewById(R.id.activity_preciadorunicomodelo_searchView2);

        Integer idUser = Integer.parseInt(SharedPref.obtenerIdUsuario(getContext()));

        System.out.println("------Home Loged-------");
        System.out.println(idUser);

        productPresenter.refreshProductsByUser(idUser.toString());



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
        modeloAdapterInstaladas = new ModeloAdapterInstaladas(this, getActivity(), searchView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(modeloAdapterInstaladas);

        //modeloAdapterInstaladas.filtrarModelos();

        observePresenter();
    }

    public void observePresenter(){

        // Create the observer which updates the UI.
        final Observer<List<RespObtenerProducto>> productsObserver = new Observer<List<RespObtenerProducto>>() {
            @Override
            public void onChanged(List<RespObtenerProducto> products) {
                modeloAdapterInstaladas.updateData(products);
            }

        };
        productPresenter.listProducts.observe(getViewLifecycleOwner(), productsObserver);

        productPresenter.isLoading.observe(getViewLifecycleOwner(),new Observer<Boolean>(){

            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading!=null){
                    //cambiar la visibilidad del relative layout
                    //rlBaseHome.setVisibility(View.INVISIBLE);
                    rlBaseHome.setVisibility(View.INVISIBLE);
                }

            }
        });
    }


    @Override
    public void OnProductClicked(RespObtenerProducto producto, int position) {
        System.out.println("CLicked: "+producto.getNombreProducto());

        //Navegar hacia la pantalla Detalle del producto
        /*HomeLogedFragmentDirections.ActionNavHomeLogedFragmentToProductDetailFragment action
        NavController navController = Navigation.findNavController(getActivity(), R.id.fragContentLoged);
        navController.navigate(R.id.toLoginActivity);*/
        HomeLogedFragmentDirections.HomeLogedToProductDetailAction action = HomeLogedFragmentDirections.HomeLogedToProductDetailAction(producto);
        Navigation.findNavController(getView()).navigate(action);
        //action.setRespObtenerProducto(producto);

        //HomeLogedFragmentDirections.ActionNavHomeLogedFragmentToProductDetailFragment action= HomeLogedFragmentDirections.ActionNavHomeLogedFragmentToProductDetailFragment();
    }

    @Override
    public void OnProductClicked(ProductoTenis producto, int position) {
        System.out.println("producto:"+ producto.getNombreProducto());
    }
}
