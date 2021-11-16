package com.example.storecode_android.view;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.storecode_android.MainActivity;
import com.example.storecode_android.Presenter.CarritoPresenter;
import com.example.storecode_android.Presenter.UserPresenter;
import com.example.storecode_android.R;

import com.example.storecode_android.entidades.CarritoVenta;
import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.entidades.ReqUpdateStock;
import com.example.storecode_android.entidades.TokenFCM;
import com.example.storecode_android.entidades.Venta;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.fragments.CartLogedFragment;
import com.example.storecode_android.view.fragments.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.Order;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;

import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


import static com.example.storecode_android.view.adapters.ProductsInCartAdapter.REQUEST_CODE;


/**
 * Description: Menu Principal de la Aplicacion
 * Created by EX440831 on 14/02/2020.
 */

public class MainDrawerActivity extends AppCompatActivity {

    private static final Logger log = LogFile.getLogger(MainDrawerActivity.class);

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ImageButton btn_notificaciones;
    private ImageButton btn_cerrar_sesion;
    //private RecyclerView rvProducto;
    BottomNavigationView bnvMenuLoged ;


    private CarritoPresenter carritoPresenter;
    CartLogedFragment cartLogedFragment;





    //PerfilActivity perfilActivity;
    //SoporteTecnicoActivity soporteTecnicoActivity;
    public static SharedPreferences sharedpreferences;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);



        //FuncionesGenerales.asignarConfiguracionesEspeciales(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //perfilActivity = new PerfilActivity();
        //soporteTecnicoActivity = new SoporteTecnicoActivity();

        cartLogedFragment= (CartLogedFragment) getSupportFragmentManager().findFragmentById(R.id.cart_loged_fragment);
        toolbar = findViewById(R.id.toolbar);
        carritoPresenter = new CarritoPresenter(this);

        //setActionBar(toolbar);
        //PENDIENTE
        //setSupportActionBar(toolbar);

        btn_notificaciones = findViewById(R.id.btn_notificaciones);
        //btn_cerrar_sesion = findViewById(R.id.btn_cerrar_sesion);

        btn_notificaciones.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                     ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn_notificaciones, "alpha",0, 1);
                    objectAnimator.setDuration(500);
                    objectAnimator.setStartDelay(0);
                    objectAnimator.start();
                    return true;

                case MotionEvent.ACTION_UP:
                    //Elimino la notificacion
                    //SharedPref.deleteNotificacionActiva(getApplicationContext());

                    NotificationsFragment notificationsFragment = new NotificationsFragment();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_contenedor, notificationsFragment).commit();



                    //btn_notificaciones.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_notifications_white_24dp));
                    btn_notificaciones.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_notification));

                    return true;
            }
            return false;
        });

        try{
            String resultado = SharedPref.obtenerguardarNotificacionActiva(this);
            if(!resultado.equals("Vacio")){
                btn_notificaciones.setBackground(ContextCompat.getDrawable(this, R.drawable.notification_full));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //Capturo Bandera para Tabulador
        /*Bundle datos = this.getIntent().getExtras();
        PrincipalFragmento principalActivity = new PrincipalFragmento();

        if(datos != null){
            try{
                    //Primero preguntamos si ha iniciado sesión en caso contrario lo mandamos al login
                    //Mandar a llamar el login mediante las variables de sesion.
                    //String user = SharedPref.getNoEmpleado(Constantes.SHAR_PREF_NUMERO_EMPLEADO);
                    //String pass = SharedPref.getPass(Constantes.SHAR_PREF_PASS);

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    String user = sharedpreferences.getString("user","");
                    String pass = sharedpreferences.getString("pass","");

                    if(!user.isEmpty()) {
                        log.info("Datos Llenos");

                        String id = datos.getString("app_id");
                        log.info("Result ID: "+ id);
                        assert id != null;

                        int id_apk = Integer.parseInt(id);
                        Bundle bundle = new Bundle();
                        bundle.putInt("appID",id_apk);
                        bundle.putString("aux","notificaciones_actualizacion");

                       log.info("Entro por Actualizaciones de Notificaciones");

                       DetalleAplicacionesInstaladas detalleAplicacionesInstaladas = new DetalleAplicacionesInstaladas();
                       detalleAplicacionesInstaladas.setArguments(bundle);
                       getSupportFragmentManager().beginTransaction().add(R.id.main_contenedor, detalleAplicacionesInstaladas).commit();

                    }else{
                        //Redireccion a la pantalla del Login.
                        log.info("Datos Nulos");
                        Toast.makeText(getApplicationContext(),"Necesita iniciar sesión para abrir la notificación",Toast.LENGTH_SHORT).show();
                        SharedPref.deleteAplicaciones(getApplicationContext());
                        Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(loginActivity);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    }

            }catch (Exception e){
                e.printStackTrace();
                getSupportFragmentManager().beginTransaction().add(R.id.main_contenedor, principalActivity).commit();
            }

        }else{
            //Pregunto primero por la variable de sesión de notificaciones
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String xd = sharedpreferences.getString("appID","");
            if(!xd.isEmpty()){
                log.info("HAY DATOS DE APP_ID -> "+xd);
                consumoServicioDetalle(Integer.parseInt(xd));
            }else{
                log.info("NO HAY DATOS DE APP_ID");
                getSupportFragmentManager().beginTransaction().add(R.id.main_contenedor, principalActivity).commit();
            }

        }*/

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.inflateMenu(R.menu.menu_navigation);
        navigationView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
        int colorInt = getResources().getColor(R.color.blanco);
        ColorStateList csl = ColorStateList.valueOf(colorInt);
        navigationView.setItemTextColor(csl);
        navigationView.setItemIconTintList(csl);

        //Método que será lanzado cuando se pulse un elemento del menú de navegación.
        navigationView.setNavigationItemSelectedListener(menuItem -> {

            if (menuItem.isChecked()) menuItem.setChecked(false);
            else menuItem.setChecked(true);

            //Cierre del drawer al seleccionar un elemento del menú.
            drawerLayout.closeDrawers();

            switch (menuItem.getItemId()) {

                case R.id.notificaciones:
                    //FragmentoInstaladas principalActivity = new FragmentoInstaladas();
                    //getSupportFragmentManager().beginTransaction().replace(R.id.main_contenedor, principalActivity).commit();

                    /*NotificacionesActivity notificacionesActivity = new NotificacionesActivity();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_contenedor, notificacionesActivity,"PROWI").commit();
*/
                    return true;

                case R.id.perfil:

                   /* PerfilActivity perfilActivity = new PerfilActivity();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_contenedor, perfilActivity).commit();
                    */
                    return true;

                case R.id.soporte_tecnico:

                   /* SoporteTecnicoActivity soporteTecnicoActivity = new SoporteTecnicoActivity();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_contenedor, soporteTecnicoActivity).commit();
*/
                    return true;

                default:
                    return false;
            }
        });

        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.abrirDrawer, R.string.cerrarDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

        btn_cerrar_sesion = findViewById(R.id.btn_cerrar_sesion);

        btn_cerrar_sesion.setOnTouchListener((v, event) -> {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn_notificaciones, "alpha",0, 1);
                    objectAnimator.setDuration(500);
                    objectAnimator.setStartDelay(0);
                    objectAnimator.start();
                    return true;

                case MotionEvent.ACTION_UP:
                    //SharedPref.deleteAplicaciones(getApplicationContext());
                    //sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                   /* SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.remove("pass");
                    editor.apply();*/
                    SharedPref.deleteUserData(getApplicationContext());
                    SharedPref.deleteProducts(getApplicationContext());
                    SharedPref.deleteIdUser(getApplicationContext());

                    Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainActivity);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    this.finish();


                   return true;
            }
            return false;
        });

        bnvMenuLoged = findViewById(R.id.bnvMenuLoged);
        configNav();

        //cargarProductos();

    }



    public void configNav(){
        NavigationUI.setupWithNavController(bnvMenuLoged, Navigation.findNavController(this, R.id.fragContentLoged));
    }




    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        log.info( "--onActivityResult--");
        log.info( "Request Code:" + requestCode);
        log.info( "Result Code:" + resultCode);

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE) {
                final Payment payment = (Payment) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT);
                //tvResults.setText("Resultado del pago: " + payment.getPaymentStatus());
                data.getExtras().getSerializable("");
                //System.out.println("Resultado del pago: " + payment.getPaymentStatus());
                if(payment.getPaymentStatus().equals("approved")){
                    System.out.println("Tu pago fue aprobado");
                    String resp = SharedPref.obtenerListProductInCard(this);
                    Type listType = new TypeToken<List<ReqItemProduct>>(){}.getType();
                    List<ReqItemProduct> listProductoCarrito  = new Gson().fromJson(resp, listType);
                    System.out.println("Productos que fueron comprados:");
                    AtomicReference<Double> totalVendido = new AtomicReference<>(0.0);
                    AtomicReference<String> email= new AtomicReference<>("");
                    AtomicReference<Integer> idCarrito= new AtomicReference<>(0);


                    listProductoCarrito.forEach(productoInCart -> {

                        idCarrito.set(productoInCart.getIdCarrito());
                        email.set(productoInCart.getClientEmail());
                        totalVendido.set(totalVendido.get() + productoInCart.getPrice() * productoInCart.getQuantity());
                        productoInCart.getIdCarrito();
                        System.out.println("-------idProductoCarrito---------");
                        System.out.println(productoInCart.getIdProductoCarrito());
                        System.out.println(productoInCart.getClientEmail());
                        System.out.println(productoInCart.getDescription());
                        System.out.println(productoInCart.getQuantity());

                        carritoPresenter.updateStockProductSelled(new ReqUpdateStock(
                                productoInCart.getIdProductoCarrito(),
                                productoInCart.getIdProducto(),
                                productoInCart.getQuantity()
                        ));

                    });

                    System.out.println("Imprimiendo ReqItemProduct-----------------");
                    System.out.println(listProductoCarrito.toString());

                    //enviar notificacion
                    UserPresenter userPresenter= new UserPresenter();
                    String idUsuario = SharedPref.obtenerIdUsuario(this);
                    String tokenFCM = SharedPref.obtenerTokenFCM(this);
                    userPresenter.sendNotificationToDevice(new NotificationToDevice(
                            Integer.parseInt(idUsuario),
                            tokenFCM,
                            listProductoCarrito.get(0).getIdVendedor(),
                            payment.getId().toString(),
                            Double.parseDouble(totalVendido.toString()),
                            listProductoCarrito.toString()
                    ));
                    //enviar notificacion

                    //String claveTransaccion = getRandomString(10);
                    Venta venta =new Venta(
                            Integer.parseInt(idUsuario),
                            1,
                            payment.getId().toString(),
                            "Vacio",
                            email.toString(),
                            Double.parseDouble(totalVendido.toString()),
                            "Calle Anteros 603"
                    );
                    /*CarritoVenta carritoVenta = new CarritoVenta(
                            Integer.parseInt(idCarrito.toString()),
                            0
                    );*/

                    carritoPresenter.createVenta(venta, listProductoCarrito);

                    //suscribir a notificaciones
                    String idVendedor = listProductoCarrito.get(0).getIdVendedor().toString();
                    suscribeToVendor(idVendedor);

                    try{

                        String paymentType=payment.getPaymentTypeId();
                        System.out.println("------------------Datos del pago-------------------------");
                        System.out.println("Metodo de pago"+paymentType);
                        System.out.println("Payment id: "+payment.getId());
                        System.out.println("CurrencyId: "+payment.getCurrencyId());
                        System.out.println("Description: "+payment.getDescription());

                        System.out.println(payment.getCard());



                        System.out.println(payment.getDateCreated());
                        System.out.println("Amount"+payment.getInstallments());

                        System.out.println("Email Payer:"+payment.getPayer().getEmail());
                        Order order = payment.getOrder();
                        System.out.println("OrderId: "+order.getId());


                    }catch (java.lang.NullPointerException e){
                        e.printStackTrace();
                    }
                    Toast.makeText(this,"Tu pago fue aprobado", Toast.LENGTH_LONG).show();
                    //En esta linea crear la venta
                    String productosVendidos = SharedPref.obtenerListProductInCard(this);
                    System.out.println("Productos vendidos\n"+productosVendidos);

                    //llamar al método para suscribir al cliente con el id del vendedor


                }
                //Done!
            } else if (resultCode == RESULT_CANCELED) {
                if (data != null && data.getExtras() != null
                        && data.getExtras().containsKey(MercadoPagoCheckout.EXTRA_ERROR)) {
                    final MercadoPagoError mercadoPagoError =
                            (MercadoPagoError) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR);
                    //tvResults.setText("Error: " +  mercadoPagoError.getMessage());
                    System.out.println("Error al realizar el pago:---------------------------------------------------------------------");
                    System.out.println("Error: " + mercadoPagoError.getMessage());
                    Toast.makeText(this, "Error" + mercadoPagoError.getMessage(), Toast.LENGTH_SHORT).show();
                    //Resolve error in checkout
                } else {
                    //Resolve canceled checkout
                }


            }


        }
    }


    public void suscribeToVendor(String idVendedor){
        FirebaseMessaging.getInstance().subscribeToTopic(idVendedor)
                .addOnCompleteListener(task -> {
                    String msg = "Ocurrio  un error al suscribir";
                    if (!task.isSuccessful()) {
                        msg = "Ocurrio un error al suscribir";
                        FirebaseMessaging.getInstance().unsubscribeFromTopic(idVendedor);
                        suscribeToVendor(idVendedor);
                    }
                    log.info("Result: "+ msg);
                });
    }




    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        log.info( "--onActivityResult--");
        log.info( "Request Code:" + requestCode);
        log.info( "Result Code:" + resultCode);

        super.onActivityResult(requestCode, resultCode, data);

        /*if (requestCode == Constantes.ACTIVITY_RESULT_INSTALL) {
            AnimacionesGenerales.mostrarAlertDialogDescarga(this,null,null, false);
            log.info( "Aplicacion Instalada por medio de Adapter");
            eliminarApk();
            Intent intent = new Intent(this, MainDrawerActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        //Verificar resulCode en caso de descarga exitosa y no exitosa
        if (requestCode == 1 && resultCode == 0) {
            AnimacionesGenerales.mostrarAlertDialogDescarga(this,null,null, false);
            log.info( "Actualización Realizada Exitosamente");
            eliminarApk();
            Intent intent = new Intent(this, MainDrawerActivity.class);
            intent.putExtra("bandera_tab",1);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        if(requestCode == 2 && resultCode == -1){
            AnimacionesGenerales.mostrarAlertDialogDescarga(this,null,null, false);
            log.info( "Aplicacion Eliminada Exitosamente");
            eliminarApk();
            Intent intent = new Intent(this, MainDrawerActivity.class);
            intent.putExtra("bandera_tab",1);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if(requestCode == 2 && resultCode == 1){
            AnimacionesGenerales.mostrarAlertDialogDescarga(this,null,null, false);
            log.info( "Aplicacion no pudo ser Eliminada");
            Toast.makeText(this, "La aplicación tiene permisos de administración activada",Toast.LENGTH_SHORT).show();
        } else if(requestCode == 2 && resultCode == 0){
            log.info( "Desinstalacion Cancelada");
        }

        if(requestCode == 99){
            String package_name;
            //Pregunto por el paquete en las preferencias
            package_name= SharedPref.obtenerPackageName();

            log.info("Nombre del paquete: "+ package_name);

            if(package_name != null){
                if(!package_name.equals("Vacio")){

                    if(package_name.equals("mx.com.telcel.di.sds.gsac.dapmov.mdm_telcel")){
                        Toast.makeText(getApplicationContext(),"La aplicación esta abierta", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent i =  getPackageManager().getLaunchIntentForPackage(package_name);
                        assert i != null;

                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                }
            }
        }

        if (requestCode == 55) {
            AnimacionesGenerales.mostrarAlertDialogDescarga(this,null,null, false);
            log.info( "Actualización de Aplicación");
            eliminarApk();
            Intent intent = new Intent(this, MainDrawerActivity.class);
            intent.putExtra("bandera_tab",1);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }*/

    /*private void eliminarApk() {
        log.info( "--eliminarApk--");
        File fileLocation;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileLocation = new File(getExternalFilesDir(null), Constantes.APK_TEMP_NAME);
        } else {
            fileLocation = new File(getFilesDir(), Constantes.APK_TEMP_NAME);
        }
        if (fileLocation.exists())
            fileLocation.delete();
    }*/

    @Override
    public void onBackPressed() {
            Intent intent = new Intent(this, MainDrawerActivity.class);
            startActivity(intent);
            this.finish();
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            super.onBackPressed();
    }

    //generar string aleatorio para obtener la clave transaccion
    public static String getRandomString(int i) {
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "0123456789";

        //create the StringBuffer
        builder = new StringBuilder(i);

        for (int m = 0; m < i; m++) {

            // generate numeric
            int myindex
                    = (int)(theAlphaNumericS.length()
                    * Math.random());

            // add the characters
            builder.append(theAlphaNumericS
                    .charAt(myindex));
        }

        return builder.toString();
    }

    //En caso de que la consulta sea mediante una notificacion
    /*private void consumoServicioDetalle(final Integer id){
        log.info( "--consumirAplicacionesDisponibles--");

        final ProgressDialog dialog = new ProgressDialog(MainDrawerActivity.this);
        dialog.setTitle("Mostrando Detalle Aplicación.");
        dialog.setMessage("Espere un momento ...");
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
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        dialog.dismiss();
                    }
                    if(response.body().getMensajeRespuesta().equals(Constantes.RESP_CODE_OK)){

                        //Elimino la preferencia de la notificacion Firebase
                        log.info("Elimine Notificacion Firebase......");
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.remove("appID");
                        editor.apply();

                        //Primero Elimino la descripcion anterior
                        SharedPref.deleteDescripcionAplicaciones(getApplicationContext());

                        //Guardo Preferencias Actuales
                        SharedPref.guardarDescripcionAplicaciones(getApplicationContext(),response.body().toString());

                        Bundle bundle = new Bundle();
                        bundle.putInt("appID",id);
                        bundle.putString("aux","notificaciones_actualizacion");

                        bundle.putInt("appID",id);
                        DetalleAplicacionesInstaladas mdetalleAplicacionesInstaladas = new DetalleAplicacionesInstaladas();
                        mdetalleAplicacionesInstaladas.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_contenedor, mdetalleAplicacionesInstaladas).commit();
                    }else{
                        Toast.makeText(getApplicationContext(), "No se pudo obtener información del aplicativo",Toast.LENGTH_SHORT).show();
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(MainDrawerActivity.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<RespObtenerDetalleAplicaciones> call, final Throwable t) {
                if (dialog.isShowing()) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    dialog.dismiss();
                }
                AnimacionesGenerales.mostrarLoader(false,getApplicationContext(), null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(MainDrawerActivity.this);
            }
        });
    }*/

    /*public void cargarProductos(){
        log.info("--consumirServicioProducto--");
        //AnimacionesGenerales.mostrarLoader(true, this, "Validando y cargando productos", getApplicationContext().getString(R.string.wait_retrofit));
        //ReqLoginDto reqLoginDto = new ReqLoginDto();
        //reqLoginDto.setEmailUsuario(this.etIdUsuario.getText().toString().replaceAll("\\s", ""));
        //reqLoginDto.setContraUsuario(view.etContrasenia.getText().toString().replaceAll("\\s", ""));
        Integer idUsuario = SharedPref.obtenerIdUsuario(getApplicationContext());

        RestClientService api = new RestClientServiceImpl();
        Call<List<RespObtenerProducto>> call = api.cargarProductos(idUsuario.toString());
        //log.info("REQUEST:" + reqLoginDto.toString());
        //Log.d("LOGIN APP PRESENTER REQUEST: ",reqLoginDto.toString());
        //System.out.println(reqLoginDto.toString());
        call.enqueue(new Callback<List<RespObtenerProducto>>() {
            @Override
            public void onResponse(Call<List<RespObtenerProducto>> call, retrofit2.Response<List<RespObtenerProducto>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    //AnimacionesGenerales.mostrarLoader(false, getApplicationContext(), null, null);
                    *//*usuarioSession = response.body().getPayLoad();
                    usuarioSession.setIdUsuario(view.etIdUsuario.getText().toString());
                    log.info("RESPONSE:" + usuarioSession.toString());
                    //Si está chequeado seteamos el usuario, en caso contrario seteamos null para olvidar el usuario
                    SharedPref.setString(Constantes.SHAR_PREF_USUARIO, view.mSwitch.isChecked() ? view.etIdUsuario.getText().toString() : null);
                    Ocultamos el diálogo
                    *//*



                    //Si el inicio de sesión es exitoso entonces guardamos el Primer evento de LOG
                    try{
                        *//*ModeloAdapterInstaladas modeloAdapter= new ModeloAdapterInstaladas(response.body());
                        rvProducto = findViewById(R.id.activity_preciadorunicomodelo_recyclerView);*//*
                        //rvProducto.setLayoutManager(,LinearLayoutManager.VERTICAL);
                        //asignar recycler
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


                    *//*Intent intent = new Intent(getApplicationContext(), MainDrawerActivity.class);
                    getApplicationContext().startActivity(intent);
                    getApplicationContext().finish();
                    view.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*//*
                } else {
                    Log.d("productos cargados no exitosos","RESPONSE NO EXITOSO");
                    Toast.makeText(getApplicationContext(),"Respuesta no exitosa",Toast.LENGTH_SHORT).show();

                    //getApplicationContext().etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    //view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    //view.etIdUsuario.requestFocus();
                    //AnimacionesGenerales.mostrarLoader(false,getApplicationContext(), null, null);
                    //log.info("Response:"+response.body().toString());
                    *//*if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                        log.error(response.body().getDetailResponse().getCode() + " " + response.body().getDetailResponse().getBusinessMeaning());
                        AnimacionesGenerales.mostrarAlertDialogError(view, response.body().getDetailResponse().getBusinessMeaning(), response.body().getDetailResponse().getCode());
                    } else {
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(view);
                        Toast.makeText(view, view.getResources().getString(R.string.msj_password_incorrecto), Toast.LENGTH_LONG).show();
                    }*//*
                }
            }

            @Override
            public void onFailure(Call<List<RespObtenerProducto>> call, final Throwable t) {
                t.printStackTrace();
                AnimacionesGenerales.mostrarLoader(false, getApplicationContext(), null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(MainDrawerActivity.this);
            }
        });
    }*/

}
