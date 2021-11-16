package com.example.storecode_android.view.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ProductoBebes;
import com.example.storecode_android.entidades.ProductoBotas;
import com.example.storecode_android.entidades.ProductoCasual;
import com.example.storecode_android.entidades.ProductoSandalias;
import com.example.storecode_android.entidades.ProductoTenis;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.service.RestClientService;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.view.adapters.BebesAdapter;
import com.example.storecode_android.view.adapters.BotasAdapter;
import com.example.storecode_android.view.adapters.CasualAdapter;
import com.example.storecode_android.view.adapters.ModeloAdapterInstaladas;
import com.example.storecode_android.view.adapters.ModeloAdapterListener;
import com.example.storecode_android.view.adapters.SandaliasAdapter;
import com.example.storecode_android.view.adapters.TenisAdapter;

import org.apache.log4j.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;

/**
 */
public class HomeNoLogedFragment extends Fragment implements ModeloAdapterListener, SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    Button tenis;
    Button btnSandalias;
    Button btnBotas;
    Button btnCasual;
    Button btnBebes;
    Button btnTodo;
    View fragContent;
    ImageView imgInicio;
    LinearLayout lyBoton1;
    LinearLayout lyBoton2;
    RecyclerView rv_all_products;
    LinearLayout contenedor;


    private static final Logger log = LogFile.getLogger(HomeLogedFragment.class);
    public RecyclerView recyclerView;
    public Activity mContext;
    public List<RespObtenerProducto> respuesta;

    //declarar presenter y adapter
    ModeloAdapterInstaladas modeloAdapterInstaladas;
    private TenisAdapter adaptadorTenis;
    private SandaliasAdapter adaptadorSandalias;
    private BotasAdapter adaptadorBotas;
    private CasualAdapter adaptadorCasual;
    private BebesAdapter adaptadorBebes;
    private ProductPresenter productPresenter;

    private RelativeLayout rlBaseHome;


    public HomeNoLogedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeNoLogedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeNoLogedFragment newInstance(String param1, String param2) {
        HomeNoLogedFragment fragment = new HomeNoLogedFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*System.out.println("OnCreate HomeNoLogedFragments");
        String response = SharedPref.obtenerAplicaciones(getContext());
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
        View view = inflater.inflate(R.layout.fragment_home_no_loged, container, false);
        mContext = getActivity();

        btnSandalias = view.findViewById(R.id.btnSandalias);
        btnBotas = view.findViewById(R.id.btnBotas);
        btnCasual = view.findViewById(R.id.btnCasual);
        btnBebes = view.findViewById(R.id.btnBebes);
        btnTodo = view.findViewById(R.id.btnTodo);
        tenis = view.findViewById(R.id.tenis);
        fragContent = view.findViewById(R.id.fragContent);
        imgInicio = view.findViewById(R.id.imgInicio);
        lyBoton1 = view.findViewById(R.id.lyBoton1);
        lyBoton2 = view.findViewById(R.id.lyBoton2);
        rv_all_products = view.findViewById(R.id.rv_all_products);
        txtBuscar = view.findViewById(R.id.txtBuscar);






        tenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("TAG","Cargando productos");

                // Create new fragment and transaction
                invocarServicioTenis();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

// Replace whatever is in the fragment_container view with this fragment
                //transaction.replace(R.id.contenedormain, Fragment_Tenis.newInstance("",""));

// Commit the transaction
                transaction.commit();

                //Ocultar

                imgInicio.setVisibility(View.GONE);
                lyBoton1.setVisibility(View.GONE);
                lyBoton2.setVisibility(View.GONE);
                //rv_all_products.setVisibility(View.GONE);




            }



            private void invocarServicioTenis() {

                System.out.println("--consultar datos del vendedor--");
                //obtener el id con el shared preferences
                RestClientService api = new RestClientServiceImpl();
                Call<List<ProductoTenis>> call = api.getTenis();

                call.enqueue(new Callback<List<ProductoTenis>>() {
                    @Override
                    public void onResponse(Call<List<ProductoTenis>> call, Response<List<ProductoTenis>> response) {
                        ProductoTenis ListaTenis = null;
                        System.out.println("code response:"+response.code());
                        if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                            try{

                                recyclerView= view.findViewById(R.id.rv_all_products);
                                SearchView searchView = view.findViewById(R.id.svHomeNoLoged);


                                productPresenter.refreshAllProducts();


                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
                                adaptadorTenis = new TenisAdapter( getActivity(), searchView);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adaptadorTenis);

                                observePresenter2(response.body());



                                Toast.makeText(getContext(),response.body().get(0).getNombreProducto(), Toast.LENGTH_SHORT).show();


                            }catch (Exception e){

                            }

                        } else {

                            System.out.println("No se obtuvieron los datos");
                            Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductoTenis>> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("No se obtuvieron los datos");
                        AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
                    }
                });


            }













        });


        btnSandalias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG","Cargando productos");
                invocarServicioSandalias();

                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

// Replace whatever is in the fragment_container view with this fragment


// Commit the transaction
                transaction.commit();

                //Ocultar

                imgInicio.setVisibility(View.GONE);
                lyBoton1.setVisibility(View.GONE);
                lyBoton2.setVisibility(View.GONE);

            }

            private void invocarServicioSandalias() {

                System.out.println("--consultar datos del vendedor--");
                //obtener el id con el shared preferences
                RestClientService api = new RestClientServiceImpl();
                Call<List<ProductoSandalias>> call = api.getSandalias();

                call.enqueue(new Callback<List<ProductoSandalias>>() {
                    @Override
                    public void onResponse(Call<List<ProductoSandalias>> call, Response<List<ProductoSandalias>> response) {

                        System.out.println("code response:"+response.code());
                        if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                            try{

                                recyclerView= view.findViewById(R.id.rv_all_products);
                                SearchView searchView = view.findViewById(R.id.svHomeNoLoged);


                                productPresenter.refreshAllProducts();


                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
                                adaptadorSandalias = new SandaliasAdapter( getActivity(), searchView);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adaptadorSandalias);

                                observePresenterSandalias(response.body());


                                Toast.makeText(getContext(),response.body().get(0).getDesProducto(), Toast.LENGTH_SHORT).show();

                                //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                            }catch (Exception e){

                            }

                        } else {

                            System.out.println("No se obtuvieron los datos");
                            Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductoSandalias>> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("No se obtuvieron los datos");
                        AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
                    }
                });


            }








        });


        btnBotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG","Cargando productos");
                invocarServicioBotas();

                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

// Replace whatever is in the fragment_container view with this fragment


// Commit the transaction
                transaction.commit();

                //Ocultar

                imgInicio.setVisibility(View.GONE);
                lyBoton1.setVisibility(View.GONE);
                lyBoton2.setVisibility(View.GONE);

            }


            private void invocarServicioBotas() {

                System.out.println("--consultar datos del vendedor--");
                //obtener el id con el shared preferences
                RestClientService api = new RestClientServiceImpl();
                Call<List<ProductoBotas>> call = api.getBotas();

                call.enqueue(new Callback<List<ProductoBotas>>() {
                    @Override
                    public void onResponse(Call<List<ProductoBotas>> call, Response<List<ProductoBotas>> response) {

                        System.out.println("code response:"+response.code());
                        if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                            try{

                                recyclerView= view.findViewById(R.id.rv_all_products);
                                SearchView searchView = view.findViewById(R.id.svHomeNoLoged);


                                productPresenter.refreshAllProducts();


                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
                                adaptadorBotas = new BotasAdapter( getActivity(), searchView);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adaptadorBotas);

                                observePresenterBotas(response.body());


                                Toast.makeText(getContext(),response.body().get(0).getDesProducto(), Toast.LENGTH_SHORT).show();

                                //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                            }catch (Exception e){

                            }

                        } else {

                            System.out.println("No se obtuvieron los datos");
                            Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductoBotas>> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("No se obtuvieron los datos");
                        AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
                    }
                });


            }






        });


        btnCasual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG","Cargando productos");
                invocarServicioCasual();

                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

// Replace whatever is in the fragment_container view with this fragment


// Commit the transaction
                transaction.commit();

                //Ocultar

                imgInicio.setVisibility(View.GONE);
                lyBoton1.setVisibility(View.GONE);
                lyBoton2.setVisibility(View.GONE);

            }


            private void invocarServicioCasual() {

                System.out.println("--consultar datos del vendedor--");
                //obtener el id con el shared preferences
                RestClientService api = new RestClientServiceImpl();
                Call<List<ProductoCasual>> call = api.getCasual();

                call.enqueue(new Callback<List<ProductoCasual>>() {
                    @Override
                    public void onResponse(Call<List<ProductoCasual>> call, Response<List<ProductoCasual>> response) {

                        System.out.println("code response:"+response.code());
                        if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                            try{

                                recyclerView= view.findViewById(R.id.rv_all_products);
                                SearchView searchView = view.findViewById(R.id.svHomeNoLoged);


                                productPresenter.refreshAllProducts();


                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
                                adaptadorCasual = new CasualAdapter( getActivity(), searchView);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adaptadorCasual);

                                observePresenterCasual(response.body());

                                Toast.makeText(getContext(),response.body().get(0).getDesProducto(), Toast.LENGTH_SHORT).show();

                                //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                            }catch (Exception e){

                            }

                        } else {

                            System.out.println("No se obtuvieron los datos");
                            Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductoCasual>> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("No se obtuvieron los datos");
                        AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
                    }
                });


            }




        });


        btnBebes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG","Cargando productos");
                invocarServicioBebes();

                // Create new fragment and transaction
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setReorderingAllowed(true);

// Replace whatever is in the fragment_container view with this fragment


// Commit the transaction
                transaction.commit();

                //Ocultar


                imgInicio.setVisibility(View.GONE);
                lyBoton1.setVisibility(View.GONE);
                lyBoton2.setVisibility(View.GONE);

            }


            private void invocarServicioBebes() {

                System.out.println("--consultar datos del vendedor--");
                //obtener el id con el shared preferences
                RestClientService api = new RestClientServiceImpl();
                Call<List<ProductoBebes>> call = api.getBebes();

                call.enqueue(new Callback<List<ProductoBebes>>() {
                    @Override
                    public void onResponse(Call<List<ProductoBebes>> call, Response<List<ProductoBebes>> response) {

                        System.out.println("code response:"+response.code());
                        if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                            try{

                                recyclerView= view.findViewById(R.id.rv_all_products);
                                SearchView searchView = view.findViewById(R.id.svHomeNoLoged);


                                productPresenter.refreshAllProducts();


                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
                                adaptadorBebes = new BebesAdapter( getActivity(), searchView);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adaptadorBebes);

                                observePresenterBebes(response.body());

                                Toast.makeText(getContext(),response.body().get(0).getDesProducto(), Toast.LENGTH_SHORT).show();

                                //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                            }catch (Exception e){

                            }

                        } else {

                            System.out.println("No se obtuvieron los datos");
                            Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductoBebes>> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("No se obtuvieron los datos");
                        AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
                    }
                });


            }


        });

        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                imgInicio.setVisibility(View.GONE);
                lyBoton1.setVisibility(View.GONE);
                lyBoton2.setVisibility(View.GONE);

            }
        });


        txtBuscar.setOnQueryTextListener(this);
























        //recyclerView = view.findViewById(R.id.rv_all_products);
        //SearchView searchView = view.findViewById(R.id.svHomeNoLoged);


        /*if(respuesta != null){
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

         *//*recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
                    ModeloAdapterInstaladas adapter = new ModeloAdapterInstaladas(respuesta, getActivity(), searchView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);*//*
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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home_no_loged, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rlBaseHome = view.findViewById(R.id.rlBaseHomeNoLoged);
        //checar si manda error
        //productPresenter = new ProductPresenter(getContext());
        productPresenter = new ProductPresenter(getContext(), getView());
        recyclerView= view.findViewById(R.id.rv_all_products);
        SearchView searchView = view.findViewById(R.id.svHomeNoLoged);


        productPresenter.refreshAllProducts();


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
        modeloAdapterInstaladas = new ModeloAdapterInstaladas(this, getActivity(), searchView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(modeloAdapterInstaladas);

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
        productPresenter.listAllProducts.observe(getViewLifecycleOwner(), productsObserver);

        productPresenter.isLoadingAll.observe(getViewLifecycleOwner(),new Observer<Boolean>(){

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


    public void observePresenter2(List<ProductoTenis> listaTenis ){
        adaptadorTenis.updateData(listaTenis);
        // Create the observer which updates the UI.
       // final Observer<List<RespObtenerProducto>> productsObserver = new Observer<List<RespObtenerProducto>>() {
           // @Override
           // public void onChanged(List<RespObtenerProducto> products) {
               // modeloAdapterInstaladas.updateData(products);
           // }

       // };
       // productPresenter.listAllProducts.observe(getViewLifecycleOwner(), productsObserver);

       // productPresenter.isLoadingAll.observe(getViewLifecycleOwner(),new Observer<Boolean>(){

           // @Override
           // public void onChanged(Boolean isLoading) {
               // if(isLoading!=null){
                    //cambiar la visibilidad del relative layout
                    //rlBaseHome.setVisibility(View.INVISIBLE);
                  //  rlBaseHome.setVisibility(View.INVISIBLE);
               // }

           // }
        //});
    }

    public void observePresenterSandalias(List<ProductoSandalias> listaSandalias){
        adaptadorSandalias.updateData(listaSandalias);

    }

    public void observePresenterBotas(List<ProductoBotas> listaBotas){
        adaptadorBotas.updateData(listaBotas);
    }

    public void observePresenterCasual(List<ProductoCasual> listaCasual){
        adaptadorCasual.updateData(listaCasual);
    }

    public void observePresenterBebes(List<ProductoBebes> listaBebes){
        adaptadorBebes.updateData(listaBebes);
    }







    @Override
    public void OnProductClicked(RespObtenerProducto producto, int position) {

    }

    @Override
    public void OnProductClicked(ProductoTenis producto, int position) {
        System.out.println("producto:"+ producto.getNombreProducto());
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        imgInicio.setVisibility(View.GONE);
        lyBoton1.setVisibility(View.GONE);
        lyBoton2.setVisibility(View.GONE);
        modeloAdapterInstaladas.filtrado(s);
        return false;
    }
}