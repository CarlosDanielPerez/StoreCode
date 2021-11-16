package com.example.storecode_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.storecode_android.Presenter.UserPresenter;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.entidades.TokenFCM;
import com.example.storecode_android.service.RestClientService;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;
import com.example.storecode_android.utils.Constantes;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.MainDrawerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import org.apache.log4j.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.ContentValues.TAG;
import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;
import static com.example.storecode_android.utils.Constantes.TOPIC_CLIENTS;

public class MainActivity extends AppCompatActivity {

    private static final Logger log = LogFile.getLogger(MainActivity.class);

    BottomNavigationView bnvMenu ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnvMenu = findViewById(R.id.bnvMenu);
        // Ajustes de navegacion y cargar productos



        loginVerified();
    }

    public void configNav(){
        NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragContent));
    }

    public void loginVerified(){

        String idUser =SharedPref.obtenerIdUsuario(this);

        if(!idUser.equals("null")){
            System.out.println("----------------------id user-------------------------------");
            System.out.println(idUser);
            notification(Integer.parseInt(idUser));
            // Cambia de activity
            Intent intent = new Intent(this, MainDrawerActivity.class);
            this.startActivity(intent);
            this.finish();

            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }else{
            configNav();
            cargarProductos();

        }
    }

    public void notification(Integer idUsuario){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Get new FCM registration token
                    String token = task.getResult();
                    UserPresenter userPresenter= new UserPresenter();
                    SharedPref.guardarTokenFCM(this,token);
                    userPresenter.guardarUsuarioTokenFCM(new TokenFCM(
                            idUsuario,
                            token
                    ));
                    System.out.println("fcm token unico del dispositivo:"+token);

                    // Log and toast
                    /*String msg = getString(R.string.msg_token_fmt, token);
                    Log.d(TAG, msg);
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();*/
                });
        //FirebaseMessaging.getInstance().subscribeToTopic("100");

        //temas (topics)
    }


    public void cargarProductos(){
        log.info("--consumirServicioProducto--");

        RestClientService api = new RestClientServiceImpl();
        Call<List<RespObtenerProducto>> call = api.cargarAllProductos();
        //log.info("REQUEST:" + reqLoginDto.toString());
        //Log.d("LOGIN APP PRESENTER REQUEST: ",reqLoginDto.toString());
        //System.out.println(reqLoginDto.toString());
        call.enqueue(new Callback<List<RespObtenerProducto>>() {
            @Override
            public void onResponse(Call<List<RespObtenerProducto>> call, retrofit2.Response<List<RespObtenerProducto>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    //Si el inicio de sesión es exitoso entonces guardamos el Primer evento de LOG
                    try{

                        System.out.println(response.body().get(0).getNombreProducto());
                        System.out.println("---------------------------------------");
                        System.out.println(response.body());

                        SharedPref.guardarAplicaciones(getApplicationContext(), response.body().toString());

                        //FragmentoInstaladas principalActivity = new FragmentoInstaladas();
                        //getSupportFragmentManager().beginTransaction().add(R.id.main_contenedor, principalActivity).commit();
                        Log.d("productos cargados exitosos","RESPONSE EXITOSO");
                        Toast.makeText(getApplicationContext(),"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    Log.d("productos cargados no exitosos","RESPONSE NO EXITOSO");
                    Toast.makeText(getApplicationContext(),"Respuesta no exitosa",Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<List<RespObtenerProducto>> call, final Throwable t) {
                t.printStackTrace();
                AnimacionesGenerales.mostrarLoader(false, getApplicationContext(), null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(MainActivity.this);
            }
        });
    }
}