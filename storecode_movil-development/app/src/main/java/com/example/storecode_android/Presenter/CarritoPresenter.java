package com.example.storecode_android.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.storecode_android.entidades.CarritoVenta;
import com.example.storecode_android.entidades.ProductInCard;
import com.example.storecode_android.entidades.ProductoCarrito;
import com.example.storecode_android.entidades.Purchase;
import com.example.storecode_android.entidades.ReqCarrito;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.entidades.ReqUpdateStock;
import com.example.storecode_android.entidades.RespFolioVenta;
import com.example.storecode_android.entidades.RespGetCarrito;
import com.example.storecode_android.entidades.RespIdCarritoVenta;
import com.example.storecode_android.entidades.RespIdPreference;
import com.example.storecode_android.entidades.RespMyShopping;
import com.example.storecode_android.entidades.RespUserData;
import com.example.storecode_android.entidades.TokenFCM;
import com.example.storecode_android.entidades.Venta;
import com.example.storecode_android.entidades.RespLoginDto;
import com.example.storecode_android.entidades.RespMensaje;
import com.example.storecode_android.entidades.Venta;
import com.example.storecode_android.service.RestClientService;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.LoginActivity;
import com.example.storecode_android.view.MainDrawerActivity;
import com.google.gson.Gson;
import com.mercadopago.android.px.core.MercadoPagoCheckout;

import org.apache.log4j.Logger;

import java.sql.SQLOutput;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;
import static com.example.storecode_android.view.adapters.ProductsInCartAdapter.PUBLIC_KEY;
import static com.example.storecode_android.view.adapters.ProductsInCartAdapter.REQUEST_CODE;

public class CarritoPresenter {

    RestClientServiceImpl restClientService= new RestClientServiceImpl();
    private static final Logger log = LogFile.getLogger(LoginPresenter.class);
    private final FragmentActivity view;
    ProductPresenter productPresenter;

    public MutableLiveData<List<ProductInCard>> listProductsInCart= new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoadingProductsInCart= new MutableLiveData<>();

    public MutableLiveData<Integer> idUsuario= new MutableLiveData();

    public MutableLiveData<List<Purchase>> mShopping= new MutableLiveData();
    public MutableLiveData<Boolean> isLoadingMyShopping= new MutableLiveData<>();

    //String idUser;

    public CarritoPresenter(){
        this.view= null;
    }
    public CarritoPresenter(FragmentActivity view) {
        this.view = view;
        productPresenter = new ProductPresenter();
        //this.idUser= SharedPref.obtenerIdUsuario(this.view);
    }

    public void refreshMyShopping(String idUser){
        getMyShopping(idUser);
    }

    public void refreshProductsInCart(String id){
        getProductsInCart(id);
    }


    public void insertCarrito(ReqCarrito reqCarrito){
        log.info("--consumir insertar en carrito--");
        RestClientService api = new RestClientServiceImpl();
        Call<RespMensaje> call = api.insertCarrito(reqCarrito);

        log.info("REQUEST:" + reqCarrito.toString());

        call.enqueue(new Callback<RespMensaje>() {
            @Override
            public void onResponse(Call<RespMensaje> call, Response<RespMensaje> response) {

                if(response!=null && response.code()==RESP_CODE_WEB_OK){

                    try{
                        System.out.println("");
                        Log.d("CARRITO APP PRESENTER","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //Toast.makeText(view,response.body().getMensaje(),Toast.LENGTH_SHORT).show();

                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(view,"Lo sentimos, ha ocurrido un error inesperado, intentalo mas tarde", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RespMensaje> call, Throwable t) {
                t.printStackTrace();
                AnimacionesGenerales.mostrarAlertDialogErrorServer(view);
            }
        });
    }

    //Obtener el id del carrito

    public void getIdCarrito(String idUser, Integer idProducto, Integer cantidad){
        log.info("--consumir obtener id del carrito--");
        RestClientService api = new RestClientServiceImpl();
        Call<RespGetCarrito> call = api.getCarritoByIdUser(idUser);

        log.info("REQUEST:" + idUser);

        call.enqueue(new Callback<RespGetCarrito>() {
            @Override
            public void onResponse(Call<RespGetCarrito> call, Response<RespGetCarrito> response) {
                if(response!=null && response.code()==RESP_CODE_WEB_OK){
                    try{
                        System.out.println("");
                        Log.d("CARRITO APP PRESENTER-GET ID CARRITO","RESPONSE EXITOSO");
                        System.out.println(response.body());


                        insertProductInCart(new ProductoCarrito(
                                idProducto,
                                response.body().getIdCarrito(),
                                cantidad
                        ));
                        //idUsuario.postValue(response.body().getIdCarrito());
                        //Toast.makeText(view,response.body().getMensaje(),Toast.LENGTH_SHORT).show();

                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("NO SE PUDO OBTENER EL ID DEL CARRITO");
                }
            }

            @Override
            public void onFailure(Call<RespGetCarrito> call, Throwable t) {
                t.printStackTrace();
                AnimacionesGenerales.mostrarAlertDialogErrorServer(view);
            }
        });
    }

    //Obtener el id del carrito

    /**
     * Método para insertar un producto en el carrito
     */

    public void insertProductInCart(ProductoCarrito productoCarrito){
        log.info("--consumir insertar producto en el carrito--");
        RestClientService api = new RestClientServiceImpl();
        Call<RespMensaje> call = api.insertProductInCart(productoCarrito);

        log.info("REQUEST:" + productoCarrito);

        call.enqueue(new Callback<RespMensaje>() {
            @Override
            public void onResponse(Call<RespMensaje> call, Response<RespMensaje> response) {
                if(response!=null && response.code()==RESP_CODE_WEB_OK){
                    try{
                        System.out.println("");
                        Log.d("CARRITO APP PRESENTER-INSERT INTO CARRITO","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //refreshProductsInCart(productoCarrito.getIdCarrito().toString());

                        Toast.makeText(view,response.body().getMensaje(),Toast.LENGTH_SHORT).show();

                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("NO SE PUDO INSERTAR EL PRODUCTO EN EL CARRITO");
                }
            }

            @Override
            public void onFailure(Call<RespMensaje> call, Throwable t) {
                t.printStackTrace();
                AnimacionesGenerales.mostrarAlertDialogErrorServer(view);
            }
        });



    }

    /**
     * Description: Función encargada de traer los productos que estan en el carrito
     */

    public void getProductsInCart(String id) {
        log.info("--Obteniendo los productos del carrito---");


        Call<List<ProductInCard>> call = restClientService.getProductsInCart(id);

        Log.d("GET PRODUCTS IN CART PRESENTER REQUEST: ", "");

        call.enqueue(new Callback<List<ProductInCard>>() {
            @Override
            public void onResponse(Call<List<ProductInCard>> call, Response<List<ProductInCard>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);

                    System.out.println(response.body());
                    if(response.body().size()==0){
                        System.out.println("La lista esta vacio:"+response.body().size());

                        //logica para agregar nuevo carrito
                    }else{
                        System.out.println("La lista tiene este");
                    }

                    try {
                        System.out.println("");
                        Log.d("GET PRODUCTS IN CART", "RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //Toast.makeText(view, "Respuesta exitosa", Toast.LENGTH_SHORT).show();
                        listProductsInCart.postValue(response.body());
                        processFinished();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {


                }
            }

            @Override
            public void onFailure(Call<List<ProductInCard>> call, Throwable t) {
                System.err.println("Ocurrio un error al obtener los productos en el carrito" + t.getMessage());

            }

            public void processFinished(){
                isLoadingProductsInCart.setValue(true);
            }
        });
    }


    /**
     * Método para eliminar un producto del carrito
     */

    public void deleteProductFromCart(String idProductoCarrito){
        log.info("--consumir eliminar producto del carrito--");
        RestClientService api = new RestClientServiceImpl();
        Call<RespMensaje> call = api.deleteProductFromCart(idProductoCarrito);

        log.info("REQUEST:" + idProductoCarrito);

        call.enqueue(new Callback<RespMensaje>() {
            @Override
            public void onResponse(Call<RespMensaje> call, Response<RespMensaje> response) {
                if(response!=null && response.code()==RESP_CODE_WEB_OK){
                    try {
                        System.out.println("");
                        Log.d("CARRITO APP PRESENTER-DELETE FROM CARRITO","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //productPresenter.refreshProductsInCart(productoCarrito.getIdCarrito().toString());
                        Toast.makeText(view,response.body().getMensaje(),Toast.LENGTH_SHORT).show();


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(response.code());
                    System.out.println("Ocurrio un error al eliminar el producto del carrito");
                    Toast.makeText(view,"Ocurrio un error al eliminar el producto del carrito",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespMensaje> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(view,"Ocurrio un error al eliminar el producto del carrito",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //metodo para obtener el id preference del proceso de pago

    public void createIdPreference(Context context, List<ReqItemProduct> reqItemProduct){

        log.info("--consumir create id preference--");
        RestClientService api = new RestClientServiceImpl();
        Call<RespIdPreference> call = api.createIdPreference(reqItemProduct);

        log.info("REQUEST:" + reqItemProduct);

        call.enqueue(new Callback<RespIdPreference>() {
            @Override
            public void onResponse(Call<RespIdPreference> call, Response<RespIdPreference> response) {
                if(response!=null && response.code()==RESP_CODE_WEB_OK){
                    try {
                        System.out.println("");
                        Log.d("CARRITO APP PRESENTER- CREATE ID PREFERENCE","RESPONSE EXITOSO");
                        System.out.println(response.body().getId());
                        SharedPref.guardarIdPreference(view,response.body().getId());
                        String idPreference = response.body().getId();


                        SharedPref.guardarListProductInCard(context, reqItemProduct.toString());
                        RespUserData vendedor = getCurrentUser(context);
                        startMercadoPagoCheckout(context,idPreference, vendedor.getPk_mercadopago());
                        //productPresenter.refreshProductsInCart(productoCarrito.getIdCarrito().toString());
                        //Toast.makeText(view,"Iniciando proceso de pago",Toast.LENGTH_SHORT).show();


                    }catch (Exception e){
                        System.out.println("Error al guardar el id preference");
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(response.code());
                    System.out.println("Ocurrio un error al crear el preference");
                    Toast.makeText(view,"Ocurrio un error al crear el preference",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RespIdPreference> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(view,"Ocurrio un error al crear el preference",Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void startMercadoPagoCheckout(Context context, final String checkoutPreferenceId, String publicKey) {
        System.out.println("Public key"+publicKey);
        MercadoPagoCheckout mercadoPagoCheckout = new MercadoPagoCheckout.Builder(publicKey,checkoutPreferenceId).build();
        mercadoPagoCheckout.startPayment(context,REQUEST_CODE);

    }

    public RespUserData getCurrentUser(Context context){
        String userString = SharedPref.obtenerVendedor(context);
        return new Gson().fromJson(userString,RespUserData.class);
    }

    //metodo para obtener el id preference del proceso de pago

    public void createVenta(Venta venta,List<ReqItemProduct> listProductoCarrito){

        log.info("--consumir create venta--");
        RestClientService api = new RestClientServiceImpl();
        Call<RespFolioVenta> call = api.createVenta(venta);

        System.out.println(("REQUEST:" + venta));

        call.enqueue(new Callback<RespFolioVenta>() {
            @Override
            public void onResponse(Call<RespFolioVenta> call, Response<RespFolioVenta> response) {
                if(response!=null && response.code()==RESP_CODE_WEB_OK){
                    try {
                        System.out.println("");
                        Log.d("CARRITO APP PRESENTER- CREATE VENTA","RESPONSE EXITOSO");
                        System.out.println(response.body().getFolioVenta());
                        Integer folioVenta = response.body().getFolioVenta();
                        // Llamar aqui el metodo createcarrito
                        //agregar la logica para asociar los productoscarritos a una venta a traves de la
                        //tabla poductocarritoventa

                        listProductoCarrito.forEach(productocarrito -> {
                            createCarritoVenta(new CarritoVenta(
                                    productocarrito.getIdProductoCarrito(),
                                    folioVenta
                            ));
                        });
                        /*createCarritoVenta(new CarritoVenta(
                                0,
                                folioVenta
                        ));*/




                    }catch (Exception e){
                        System.out.println("Error al guardar la venta");
                        e.printStackTrace();
                    }
                }else{
                    System.out.println(response.code());
                    System.out.println("Ocurrio un error al crear la venta");
                    Toast.makeText(view,"Ocurrio un error al crear la venta",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RespFolioVenta> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(view,"Ocurrio un error al crear la venta",Toast.LENGTH_SHORT).show();
            }
        });
    }


    //metodo para guardar un registro en la tabla Carritoventa

    public void createCarritoVenta(CarritoVenta carritoVenta){

        log.info("--consumir create carritoventa--");
        RestClientService api = new RestClientServiceImpl();
        Call<RespIdCarritoVenta> call = api.createCarritoVenta(carritoVenta);

        log.info("REQUEST:" + carritoVenta);

        call.enqueue(new Callback<RespIdCarritoVenta>() {
            @Override
            public void onResponse(Call<RespIdCarritoVenta> call, Response<RespIdCarritoVenta> response) {
                if(response!=null && response.code()==RESP_CODE_WEB_OK){
                    try {
                        System.out.println("");
                        Log.d("CARRITO APP PRESENTER- CREATE CARRITO-VENTA","RESPONSE EXITOSO");

                        //Toast.makeText(view,"Venta registrada", Toast.LENGTH_LONG).show();
                        //refreshProductsInCart(idUser);
                    }catch (Exception e){
                        System.err.println("Error al guardar carritoventa");
                        e.printStackTrace();
                    }
                }else{
                    System.err.println(response.code());
                    System.out.println("Ocurrio un error al crear carritoventa");
                    Toast.makeText(view,"Ocurrio un error al crear carritoventa",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RespIdCarritoVenta> call, Throwable t) {
                t.printStackTrace();
                System.err.println("Ocurrio un error al crear el carrito venta");
                Toast.makeText(view,"Ocurrio un error al crear el carritoventa",Toast.LENGTH_SHORT).show();
            }
        });

    }


    //método para actualizar el stock del producto vendido

    public void updateStockProductSelled(ReqUpdateStock reqUpdateStock){

        log.info("--consumir update-stock--");
        RestClientService api = new RestClientServiceImpl();
        Call<RespMensaje> call = api.updateProductStock(reqUpdateStock);

        log.info("REQUEST:" + reqUpdateStock);

        call.enqueue(new Callback<RespMensaje>() {
            @Override
            public void onResponse(Call<RespMensaje> call, Response<RespMensaje> response) {
                if(response!=null && response.code()==RESP_CODE_WEB_OK){
                    try {
                        System.out.println("");
                        Log.d("CARRITO APP PRESENTER- UPDATE-STOCK","RESPONSE EXITOSO");

                        Toast.makeText(view,"Stock actualizado", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        System.err.println("Error al guardar actualizar el stock");
                        e.printStackTrace();
                    }
                }else{
                    System.err.println(response.code());
                    System.out.println("Ocurrio un error al actualizar el stock");
                    Toast.makeText(view,"Ocurrio un error al actualizar el stock",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<RespMensaje> call, Throwable t) {
                t.printStackTrace();
                System.err.println("Ocurrio un error al actualizar el stock");
                Toast.makeText(view,"Ocurrio un error al actualizar el stock",Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * Description: Función encargada de traer los productos comprados por el usuario actual
     */

    public void getMyShopping(String idUser) {
        log.info("--Obteniendo los productos comprados---");


        Call<List<Purchase>> call = restClientService.getMyShopping(idUser);

        Log.d("GET MYSHOPPING IN CART PRESENTER REQUEST: ", "");

        call.enqueue(new Callback<List<Purchase>>() {
            @Override
            public void onResponse(Call<List<Purchase>> call, Response<List<Purchase>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try {
                        System.out.println("");
                        Log.d("GET MYSHOPPING", "RESPONSE EXITOSO");
                        System.out.println(response.body());
                        Toast.makeText(view, "Respuesta exitosa", Toast.LENGTH_SHORT).show();
                        mShopping.postValue(response.body());
                        processFinished();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {


                }
            }

            @Override
            public void onFailure(Call<List<Purchase>> call, Throwable t) {
                System.err.println("Ocurrio un error al obtener mis compras" + t.getMessage());

            }

            public void processFinished(){
                isLoadingMyShopping.setValue(true);
            }
        });
    }



}
