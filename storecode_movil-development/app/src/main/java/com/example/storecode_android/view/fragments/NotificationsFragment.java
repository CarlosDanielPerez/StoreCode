package com.example.storecode_android.view.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.MainDrawerActivity;
import com.example.storecode_android.view.adapters.ModeloAdapterNotificaciones;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationsFragment extends Fragment {

    private static final Logger log = LogFile.getLogger(NotificationToDevice.class);
    RecyclerView recyclerView;
    Gson gson = new Gson();
    ArrayList<NotificationToDevice> notificacion_descartadas;
    LinearLayout layout_notificaciones, layout_fondo_notificaciones_recycler, contenedor_principal;
    ImageButton btn_notificaciones;

    Toolbar toolbar;
    TextView txt_Titulo;

    @SuppressLint("SetTextI18n")
    public NotificationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*FuncionesGenerales.asignarConfiguracionesEspeciales(getActivity());
        FuncionesGenerales.callFullScreen(Objects.requireNonNull(getActivity()));*/

        toolbar = ((MainDrawerActivity)getActivity()).findViewById(R.id.toolbar);
        /*Objects.requireNonNull(((MainDrawerActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((MainDrawerActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);*/

        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainDrawerActivity.class);
            startActivity(intent);
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

       /* txt_Titulo = ((MainDrawerActivity)getActivity()).findViewById(R.id.tvTitulo);
        txt_Titulo.setText("Notificaciones");*/

        btn_notificaciones = ((MainDrawerActivity)getActivity()).findViewById(R.id.btn_notificaciones);
        //btn_notificaciones.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_notifications_white_24dp));
        btn_notificaciones.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.ic_notification));
        //SharedPref.deleteNotificacionActiva(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_notifications, container, false);

        View view = inflater.inflate(R.layout.fragment_notifications,container,false);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        try{
            layout_notificaciones = view.findViewById(R.id.layout_fondo_notificaciones);
            layout_fondo_notificaciones_recycler = view.findViewById(R.id.layout_fondo_notificaciones_recycler);
            contenedor_principal = view.findViewById(R.id.contenedor_principal);
            recyclerView = view.findViewById(R.id.activity_notificaciones_recyclerView);

            String datosNotificaciones = SharedPref.obtenerNotificacionDescartada(getContext());

            System.out.println("NOTIFICACION ACTUAL"+ datosNotificaciones);



            if(datosNotificaciones.equals("Vacio")){
                layout_notificaciones.setVisibility(View.VISIBLE);
                layout_fondo_notificaciones_recycler.setVisibility(View.GONE);
                contenedor_principal.setBackgroundColor(getActivity().getColor(R.color.colorBackground));
            }else{
                layout_notificaciones.setVisibility(View.GONE);
                layout_fondo_notificaciones_recycler.setVisibility(View.VISIBLE);
                contenedor_principal.setBackgroundColor(getActivity().getColor(R.color.blanco));

                Type listType;
                listType = new TypeToken<List<NotificationToDevice>>(){}.getType();
                ArrayList<NotificationToDevice> notifications= gson.fromJson(datosNotificaciones, listType);


                log.info("Datos Notificaciones2:" + notifications.get(0));
                //log.info("ID App:" + notificacion_descartada.getClaveTransaccion());
                notificacion_descartadas = notifications;
                //notificacion_descartadas.add(notificacion_descartada);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                ModeloAdapterNotificaciones adapter = new ModeloAdapterNotificaciones(notificacion_descartadas, getActivity(),fragmentManager, recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}