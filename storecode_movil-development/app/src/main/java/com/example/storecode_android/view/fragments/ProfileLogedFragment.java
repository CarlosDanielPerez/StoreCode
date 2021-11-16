package com.example.storecode_android.view.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.entidades.RespUserData;
import com.example.storecode_android.service.RestClientService;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.MainDrawerActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.squareup.picasso.Picasso;


import org.apache.log4j.Logger;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileLogedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileLogedFragment extends Fragment {

    private static final Logger log = LogFile.getLogger(MainDrawerActivity.class);

    ImageView ivProfile;
    TextView tvName;
    TextView tvEmail;
    ImageButton btnSell;
    ImageButton btnProductsOnSale;
    ImageButton btnMercadoPago;

    ImageButton btn_miscompras;

    RespUserData resp;





    public ProfileLogedFragment() {
        // Required empty public constructor
    }

    public static ProfileLogedFragment newInstance(String param1, String param2) {
        ProfileLogedFragment fragment = new ProfileLogedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //cargarDatosUsuario();
        //
        String response = SharedPref.obtenerUsuario(getContext());
        System.out.println("obtener usuario");
        System.out.println(response);

        //Type userType = new TypeToken<RespUserData>(){}.getType();
        //Type userType = new TypeToken<RespUserData>(){}.getType();
        /*JsonReader reader = new JsonReader(new StringReader(response));
        reader.setLenient(true);*/
        resp = new Gson().fromJson(response, RespUserData.class);
        log.info("Lista de profile Convertidas: "+ resp.toString());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_loged, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivProfile = view.findViewById(R.id.ivProfile);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        btnSell = view.findViewById(R.id.btnSell);
        btnProductsOnSale = view.findViewById(R.id.btn_sell_products);
        btn_miscompras = view.findViewById(R.id.btn_miscompras);
        btnMercadoPago= view.findViewById(R.id.btnMercadoPago);

        if(resp.getApellido2Usuario().equals("null")){
            tvName.setText(resp.getNombreUsuario()+" "+resp.getApellido1Usuario());
        }else{
            tvName.setText(resp.getNombreUsuario()+" "+resp.getApellido1Usuario()+" "+resp.getApellido2Usuario());
        }

        tvEmail.setText(resp.getEmailUsuario());

        //
        if(!resp.getImagenUsuario().equals("null")){
            Picasso.get().load(Uri.parse(resp.getImagenUsuario())).into(ivProfile);
        }

        //
        btnSell.setOnClickListener( v ->{
            Navigation.findNavController(getView()).navigate(ProfileLogedFragmentDirections.toRegisterProduct());

        });

        //ir a la pantalla de productos en venta
        btnProductsOnSale.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(ProfileLogedFragmentDirections.toProductOnSale());
        });

        //ir a la pantalla mis compras
        btn_miscompras.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(ProfileLogedFragmentDirections.toMyShoppinFragment());
        });

        //ir a la pantalla "Datos de mercado pago"
        btnMercadoPago.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(ProfileLogedFragmentDirections.toDataMercadoPago());
        });


    }


}