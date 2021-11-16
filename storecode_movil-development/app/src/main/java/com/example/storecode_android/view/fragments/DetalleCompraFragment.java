package com.example.storecode_android.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.storecode_android.Presenter.LoginPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.MainDrawerActivity;
import com.example.storecode_android.view.adapters.ModeloAdapterNotificaciones;
import com.example.storecode_android.view.adapters.ModeloAdapterPurchase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleCompraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleCompraFragment extends Fragment {

    NotificationToDevice notification;

    TextView tvVendedor;
    TextView tvNameVendor;
    TextView tvEmailVendor;
    TextView tvTotal;
    ImageButton btnReturn;

    Toolbar toolbar;

    LinearLayout layout_notificaciones, layout_fondo_notificaciones_recycler, contenedor_principal;

    RecyclerView recyclerView;

    //Presenters
    LoginPresenter loginPresenter;



    public DetalleCompraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleCompraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleCompraFragment newInstance(String param1, String param2) {
        DetalleCompraFragment fragment = new DetalleCompraFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = ((MainDrawerActivity)getActivity()).findViewById(R.id.toolbar);


        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainDrawerActivity.class);
            startActivity(intent);
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_compra,container,false);
        // Inflate the layout for this fragment

        try{

            loginPresenter= new LoginPresenter();

            //layout_notificaciones = view.findViewById(R.id.layout_fondo_notificaciones);
            contenedor_principal = view.findViewById(R.id.contenedor_principal);
            recyclerView = view.findViewById(R.id.rv_detallecompra_items);
            tvNameVendor = view.findViewById(R.id.tvNameVendor);
            tvEmailVendor = view.findViewById(R.id.tvEmailVendor);
            tvTotal= view.findViewById(R.id.tvTotal);
            btnReturn = view.findViewById(R.id.btnReturn);




            contenedor_principal.setBackgroundColor(getActivity().getColor(R.color.blanco));

            String notificationString = SharedPref.obtenerNotificacionCompra(getContext());

            notification = new Gson().fromJson(notificationString, NotificationToDevice.class);

            String purchaseProduct= notification.getItems();
            System.out.println("-------------purchase product-----------------------");

            System.out.println(purchaseProduct);

            Type typeList = new TypeToken<List<ReqItemProduct>>(){}.getType();
            ArrayList<ReqItemProduct> purchaseProductList= new Gson().fromJson(purchaseProduct, typeList);

            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            ModeloAdapterPurchase adapter = new ModeloAdapterPurchase(purchaseProductList, getActivity(), recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            loginPresenter.getUserById(notification.getIdVendedor().toString());

            observer();


        }catch (Exception e){
            e.printStackTrace();
        }


        //Navegar a la pantalla anterior
        btnReturn.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainDrawerActivity.class);
            startActivity(intent);
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvVendedor =view.findViewById(R.id.tvVendedor);

        tvTotal.setText("Total: $"+notification.getTotalVendido().toString());


    }

    public void observer(){
        loginPresenter.vendedor.observe(getViewLifecycleOwner(), vendedor->{
            tvNameVendor.setText("Nombre: "+vendedor.getNombreUsuario()+" "+vendedor.getApellido1Usuario());
            tvEmailVendor.setText("Email: "+vendedor.getEmailUsuario());
        });
    }
}