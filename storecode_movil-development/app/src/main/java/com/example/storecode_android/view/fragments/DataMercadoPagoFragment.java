package com.example.storecode_android.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.example.storecode_android.Presenter.UserPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ReqMercadoPago;
import com.example.storecode_android.utils.SharedPref;


public class DataMercadoPagoFragment extends Fragment {

    ImageButton btnReturn;
    Button btnGuardarDatosMP;
    EditText editTextPublicKey;
    EditText editTextAccessToken;
    UserPresenter userPresenter;


    public DataMercadoPagoFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_mercado_pago, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnGuardarDatosMP = view.findViewById(R.id.btnGuardarDatosMP);
        btnReturn = view.findViewById(R.id.btnReturn);
        editTextPublicKey = view.findViewById(R.id.editTextPublicKey);
        editTextAccessToken = view.findViewById(R.id.editTextAccessToken);

        userPresenter = new UserPresenter(getActivity());

        btnGuardarDatosMP.setOnClickListener(v->{
            String idUsuario = SharedPref.obtenerIdUsuario(getContext());
            String pk_mercadoPago= editTextPublicKey.getText().toString();
            String accessToken = editTextAccessToken.getText().toString();
            userPresenter.guardarDatosMercadoPago(
                    new ReqMercadoPago(
                            idUsuario,
                            pk_mercadoPago,
                            accessToken
                    )
            );
        });

        btnReturn.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(DataMercadoPagoFragmentDirections.toNavProfileLogedFragment());
        });
    }
}