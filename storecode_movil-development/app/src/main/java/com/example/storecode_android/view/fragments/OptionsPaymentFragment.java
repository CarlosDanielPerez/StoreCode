package com.example.storecode_android.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.storecode_android.Proceso;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ProcesamientoPaypal;
import com.example.storecode_android.entidades.TokenPaypal;

import com.example.storecode_android.service.RestClientService;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;


public class OptionsPaymentFragment extends Fragment {


    Button btnpaypal;


    public OptionsPaymentFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_options_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnpaypal = view.findViewById(R.id.btnpaypal);



       btnpaypal.setOnClickListener(v->{
            invocarMetodoPaypal();
            invocarprocesamiento();

        });



    }
    public void invocarMetodoPaypal(){



            System.out.println("--consultar datos del vendedor--");
            //obtener el id con el shared preferences
            RestClientService api = new RestClientServiceImpl("","AYWQk94R0NB8beffpYJWH2AhroyW2_lir-aS38KZVqNQyLS-Y3tOVXLEC7sZKdpq0gvtcx2rbtZu_FLq","EE6WNm1cRycT-Yx3WPD_DwKORiqvBnXb-x-ZOPsAGCo5yC7zuOZ8a32V3mRXu2feyxmyZy7P0dx5qRXl");
            Call<TokenPaypal> call = api.obtenerToken("client_credentials");

            call.enqueue(new Callback<TokenPaypal>() {
                @Override
                public void onResponse(Call<TokenPaypal> call, Response<TokenPaypal> response) {

                    System.out.println("code response:"+response.code());
                    if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                        try{
                            response.body().getAccess_token();


                            System.out.print(response.body().getAccess_token());

                            //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                        }catch (Exception e){

                        }

                    } else {

                        System.out.println("No se obtuvieron los datos");
                        Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                    }
                }

                @Override
                public void onFailure(Call<TokenPaypal> call, Throwable t) {
                    t.printStackTrace();
                    System.out.println("No se obtuvieron los datos");
                    AnimacionesGenerales.mostrarLoader(false, getActivity(), null, null);
                    AnimacionesGenerales.mostrarAlertDialogErrorServer(getActivity());
                }
            });


    }




    public void invocarprocesamiento(){

        System.out.println("--consultar datos del vendedor--");
        //obtener el id con el shared preferences
        RestClientService api = new RestClientServiceImpl("");
        Call<List<ProcesamientoPaypal>> call = api.procesamientoPaypal("Bearer A21AAJNHb1-r5L24qCtaT5bx-_oz0fMiptElXqE3kNrfGE7q9-ehKp8uB7ciRlfeevY1Dx3CmG3CyYjnZ08OcYyCTl8y02aXw",new Proceso("CAPTURE",""));



        call.enqueue(new Callback<List<ProcesamientoPaypal>>() {
            @Override
            public void onResponse(Call<List<ProcesamientoPaypal>> call, Response<List<ProcesamientoPaypal>> response) {

                System.out.println("code response:"+response.code());
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                    try{


                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){

                    }

                } else {

                    System.out.println("No se obtuvieron los datos");
                    Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                }
            }

            @Override
            public void onFailure(Call<List<ProcesamientoPaypal>> call, Throwable t) {
                t.printStackTrace();
                System.out.println("No se obtuvieron los datos");
                AnimacionesGenerales.mostrarLoader(false, getActivity(), null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(getActivity());
            }
        });


    }

}