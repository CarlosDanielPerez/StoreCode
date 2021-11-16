package com.example.storecode_android.view;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

//import com.example.storecode_android.BuildConfig;
import com.example.storecode_android.Presenter.LoginPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.utils.LogFile;
import com.google.android.material.textfield.TextInputLayout;
import org.apache.log4j.Logger;
/*
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.BuildConfig;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.R;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.presenter.LoginPresenter;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.service.UpdateSMAService;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.FuncionesGenerales;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.LogFile;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.SharedPref;

import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.SHAR_PREF_TIPO_SERVICIO;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.TIPO_SERVICIO_TOTALMENTE_DETENIDO;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.VariablesSesion.isPreciadorColocado;
*/
/**
 * Description: Función encargada de tener los eventos y controles de vista para el actividad Login
 */
@SuppressWarnings("FieldCanBeLocal")
public class LoginActivity extends AppCompatActivity {
    /*private static final Logger log = LogFile.getLogger(LoginActivity.class);
    private LoginPresenter loginPresenter;
    public Switch mSwitch;
    public EditText etIdUsuario, etContrasenia;
    private TextView tvVersion;
    public Button btnEntrar;

    public TextView textInicio, textSingle;
    public TextInputLayout textLayout;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //log.info("--onCreate--");
        super.onCreate(savedInstanceState);
        //Asignamos la orientación del disposito
        //setRequestedOrientation(FuncionesGenerales.asigaOrientacionDispositivo(this));
        //Quitamos el título de la ventana
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Cambio de color de la barra de notificaciones
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBackground));
        setContentView(R.layout.activity_login);

        //configNav();

        //Seleccionamos el activity a mostrar
        //setContentView(R.layout.activity_login);
        //Asignamos las configuraciones especiales al activity
        //FuncionesGenerales.asignarConfiguracionesEspeciales(this);

        //Construimos el presentador
        /*loginPresenter = new LoginPresenter();
        //Inicializamos los componentes
        mSwitch = findViewById(R.id.inicio_sesion_switch);
        etIdUsuario = findViewById(R.id.editTextUser);
        etContrasenia = findViewById(R.id.editTextPass);

        textInicio = findViewById(R.id.TextInicio);
        Typeface typeface0 = ResourcesCompat.getFont(this, R.font.telcel_nova_media_otf);
        textInicio.setTypeface(typeface0);

        textSingle = findViewById(R.id.textSingle);
        Typeface typefaceX = ResourcesCompat.getFont(this, R.font.telcel_nova_media_otf);
        textSingle.setTypeface(typefaceX);

        textLayout = findViewById(R.id.textLayout);
        Typeface typeface2 = ResourcesCompat.getFont(this, R.font.telcel_nova_media_otf);
        textLayout.setTypeface(typeface2);

        etContrasenia = findViewById(R.id.editTextPass);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.telcel_nova_media_otf);
        etContrasenia.setTypeface(typeface);

        mSwitch = findViewById(R.id.inicio_sesion_switch);
        Typeface typeface3 = ResourcesCompat.getFont(this, R.font.telcel_nova_media_otf);
        mSwitch.setTypeface(typeface3);

        tvVersion = findViewById(R.id.version_app);
        String versionApp = "V " + BuildConfig.VERSION_NAME;
        tvVersion.setText(versionApp);

        etContrasenia.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                loginPresenter.cambiarColorBoton();
            }
        });

        etIdUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                loginPresenter.cambiarColorBoton();
            }
        });
        etContrasenia.setOnClickListener(v -> loginPresenter.cambiarColorBoton());

        //Agregamos un listener al botón validaUsuarioYPassword
        btnEntrar = findViewById(R.id.buttonLogin);

        btnEntrar.setOnClickListener(v ->
                loginPresenter.validaUsuarioYContrasenia()
                );

        etContrasenia.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE && btnEntrar.isEnabled()) {
                    btnEntrar.performClick();
                    return true;
                }
                return false;
            }
        });*/

        //Cargamos las preferencias del usuario
        /*
        loginPresenter.cargarPreferenciasSession();
        loginPresenter.cambiarColorBoton();

        //Preguntamos si el Preciador esta colocado!
        log.info("El preciador esta colocado?" + isPreciadorColocado);
        if (isPreciadorColocado){
            cargarPreferencias();
        }

        //Iniciamos el servicio que actualiza la aplicación SMA-Android
        iniciarServicioUpdateRemoteConfig();*/
    }

    /**
     * Función encargada de iniciar el servicio-Android que se encarga de validar que el SMA esté instalado
     */

    /*
    private void iniciarServicioUpdateRemoteConfig() {
        log.info( "-- iniciarServicioUpdateRemoteConfig --");
        try {
            //Validamos si el servicio se encuentra corriendo ya
            if (!FuncionesGenerales.isMyServiceRunning(this, UpdateSMAService.class)) {
                Intent intent = new Intent(this, UpdateSMAService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    log.info( "Es una versión superior a Oreo");
                    startService(intent);
                } else {
                    log.info( "Es una versión inferior a Oreo");
                    startService(intent);
                }
            } else {
                log.info( "El servicio UpdateSMAService ya se encuentra corriendo...");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }*/
    /*
    private void cargarPreferencias() {
        log.info("-- ReloadAplicationService.cargarPreferencias--");
        //Cargamos las preferencias del usuario
        try {
            //SharedPref.inicializaPreferencias(this);
            final int tipoServicio = SharedPref.getInteger(SHAR_PREF_TIPO_SERVICIO);
            log.info("tipoServicio:" + tipoServicio);
            if (tipoServicio != TIPO_SERVICIO_TOTALMENTE_DETENIDO) {
                log.info("Debemos colocar el preciador...");
                Intent intent = new Intent(getApplicationContext(), MainServicioActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }*/


    /**
     * Cuando se presiona el botón regresar
     */
    /*public void configNav(){
        //NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragContent));
        //NavigationUI.setupWithNavController(null,Navigation.findNavController(this,R.id.fragContent2));
        Navigation.findNavController(this, R.id.fragContent2);
    }*/
    /*
    @Override
    public void onBackPressed() {
        FuncionesGenerales.cerrarAplicacion(LoginActivity.this);
    }*/


}

