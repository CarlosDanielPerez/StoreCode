package com.example.storecode_android.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.Brand;
import com.example.storecode_android.entidades.Category;
import com.example.storecode_android.entidades.ProductInCard;
import com.example.storecode_android.entidades.Producto;
import com.example.storecode_android.entidades.ReqUpdateProduct;
import com.example.storecode_android.entidades.RespDetaProductoComen;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.entidades.RespMessage;
import com.example.storecode_android.entidades.RespObtenerImagesDto;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.view.fragments.RegisterProductFragmentDirections;
import com.example.storecode_android.view.fragments.UpdateProductFragmentDirections;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;

public class ProductPresenter {

    RestClientServiceImpl restClientService= new RestClientServiceImpl();

    private final Context view;
    private final View view2;
    //RegisterProductFragment view;

    public MutableLiveData<List<RespObtenerProducto>> listProducts = new MutableLiveData();
    public MutableLiveData<Boolean> isLoading= new MutableLiveData();

    //variables para obtener productos en venta

    public MutableLiveData<List<RespGetProductByUser>> listProductsOnSale = new MutableLiveData();
    public MutableLiveData<Boolean> isLoadingOnSale= new MutableLiveData();
    //Variables para el home no loged
    public MutableLiveData<List<RespObtenerProducto>> listAllProducts = new MutableLiveData();
    public MutableLiveData<Boolean> isLoadingAll= new MutableLiveData();
    //Datos para las imagenes complementarias
    public MutableLiveData<RespObtenerImagesDto> imagesCompl= new MutableLiveData();

    //Variables para los comentarios de productos

    public MutableLiveData<List<RespDetaProductoComen>> comentarios= new MutableLiveData();
    public MutableLiveData<Boolean> isLoadingComents= new MutableLiveData();

    //Variables para los comentarios de productos

    public MutableLiveData<List<RespDetaProductoComen>> comentariosClient= new MutableLiveData();
    public MutableLiveData<Boolean> isLoadingComentsClient= new MutableLiveData();

    //variables para observar las categorias
    public MutableLiveData<List<Category>> listCategories= new MutableLiveData<>();

    //variables para observar los productos en el carrito

    //variables para observar las marcas
    public MutableLiveData<List<Brand>> listBrands = new MutableLiveData<>();

    private static final Logger log = LogFile.getLogger(LoginPresenter.class);
    //private final Context view;

    public ProductPresenter(Context view, View view2) {

        this.view = view;
        this.view2 = view2;
    }
    public ProductPresenter(){
        this.view= null;
        this.view2 =null;
    }

    public void refreshAllProducts(){
        getAllProducts();
    }

    public void refreshProductsByUser(String id){
        getProductsByUser(id);
    }

    public void refreshProductsOnSale(String id){
        getProductsOnSale(id);
    }



    public void refreshImagesComple(String id){
        getImagesCompl(id);
    }

    //refrescar comentarios generales
    public void refreshComents(String id){getComentsGen(id);}

    //refrescar comentarios de clientes
    public void refreshComentsClient(String id){getComentsClient(id);}

    public void getAllProducts(){
        log.info("--Obteniendo los productos por el id usuario---");
        AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.load_products), view.getString(R.string.waiting_products));

        Call<List<RespObtenerProducto>> call =restClientService.cargarAllProductos();
        //log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("GET PRODUCTS APP PRESENTER REQUEST: ","");

        call.enqueue(new Callback<List<RespObtenerProducto>>() {
            @Override
            public void onResponse(Call<List<RespObtenerProducto>> call, Response<List<RespObtenerProducto>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try{
                        System.out.println("");
                        Log.d("GET ALL PRODUCTS APP PRESENTER","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //SharedPref.guardarIdUsuario(view,response.body().getIdUsuario());
                        //Toast.makeText(view,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        listAllProducts.postValue(response.body());
                        processFinished();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    AnimacionesGenerales.mostrarLoader(false, view, null, null);

                }
            }

            @Override
            public void onFailure(Call<List<RespObtenerProducto>> call, Throwable t) {
                processFinished();
            }

            public void processFinished(){
                isLoadingAll.setValue(true);
            }
        });

    }


    /**
     * Description: Función encargada de traer todas las categorias
     */

    public void getAllCategories(){
        log.info("--Obteniendo todas las categorias---");
        //AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.load_products), view.getString(R.string.waiting_products));

        Call<List<Category>> call =restClientService.getAllCategories();
        //log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("GET CATEGORIES APP PRESENTER REQUEST: ","");

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try{
                        System.out.println("");
                        Log.d("GET ALL CATEGORIES APP PRESENTER","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //SharedPref.guardarIdUsuario(view,response.body().getIdUsuario());
                        //Toast.makeText(view,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        listCategories.postValue(response.body());
                        //processFinished();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);
                    System.out.println("Codigo de respuesta"+response.code());

                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                //processFinished();
                System.out.println("Ocurrio un error al obtener las categorias");
            }

            /*public void processFinished(){
                isLoadingAll.setValue(true);
            }*/
        });

    }


    /**
     * Description: Función encargada de traer todas las categorias
     */

    public void getAllBrands(){
        log.info("--Obteniendo todas las marcas---");
        //AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.load_products), view.getString(R.string.waiting_products));

        Call<List<Brand>> call =restClientService.getAllBrands();
        //log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("GET BRANDS APP PRESENTER REQUEST: ","");

        call.enqueue(new Callback<List<Brand>>() {
            @Override
            public void onResponse(Call<List<Brand>> call, Response<List<Brand>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try{
                        System.out.println("");
                        Log.d("GET ALL BRANDS APP PRESENTER","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //SharedPref.guardarIdUsuario(view,response.body().getIdUsuario());
                        //Toast.makeText(view,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        listBrands.postValue(response.body());
                        //processFinished();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);
                    System.out.println("Codigo de respuesta"+response.code());

                }
            }

            @Override
            public void onFailure(Call<List<Brand>> call, Throwable t) {
                //processFinished();
                System.out.println("Ocurrio un error al obtener las MARCAS");
            }

            /*public void processFinished(){
                isLoadingAll.setValue(true);
            }*/
        });

    }

    /**
     * Description: Función encargada de traer todos los productos
     */

    public void getProductsByUser(String id){
        log.info("--Obteniendo los productos por el id usuario---");
        //AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.load_products), view.getString(R.string.waiting_products));

        Call<List<RespObtenerProducto>> call =restClientService.cargarProductos(id);
        //log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("GET PRODUCTS BY USER APP PRESENTER REQUEST: ","");

        call.enqueue(new Callback<List<RespObtenerProducto>>() {
            @Override
            public void onResponse(Call<List<RespObtenerProducto>> call, Response<List<RespObtenerProducto>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try{
                        System.out.println("");
                        Log.d("GET PRODUCTS BY USER APP PRESENTER","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //SharedPref.guardarIdUsuario(view,response.body().getIdUsuario());
                        //Toast.makeText(view,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        listProducts.postValue(response.body());
                        processFinished();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    AnimacionesGenerales.mostrarLoader(false, view, null, null);

                }
            }

            @Override
            public void onFailure(Call<List<RespObtenerProducto>> call, Throwable t) {
                System.err.println("Ocurrio un error al obtener los productos"+t.getMessage());
                processFinished();
            }

            public void processFinished(){
                isLoading.setValue(true);
            }
        });

    }


    /**
     * Description: Función encargada de traer los productos que estan en venta
     */

    public void getProductsOnSale(String id){
        log.info("--Obteniendo los productos que estan en venta---");
        //AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.load_products), view.getString(R.string.waiting_products));

        Call<List<RespGetProductByUser>> call =restClientService.getProductsOnSale(id);
        //log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("GET PRODUCTS ON SALE APP PRESENTER REQUEST: ","");

        call.enqueue(new Callback<List<RespGetProductByUser>>() {
            @Override
            public void onResponse(Call<List<RespGetProductByUser>> call, Response<List<RespGetProductByUser>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try{
                        System.out.println("");
                        Log.d("GET PRODUCTS BY USER APP PRESENTER","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //SharedPref.guardarIdUsuario(view,response.body().getIdUsuario());
                        //Toast.makeText(view,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        listProductsOnSale.postValue(response.body());
                        processFinished();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    AnimacionesGenerales.mostrarLoader(false, view, null, null);

                }
            }

            @Override
            public void onFailure(Call<List<RespGetProductByUser>> call, Throwable t) {
                System.err.println("Ocurrio un error al obtener los productos"+t.getMessage());
                processFinished();
            }

            public void processFinished(){
                isLoadingOnSale.setValue(true);
            }
        });

    }




    /**
     * Description: Función encargada de traer todos los productos
     */

    public void getImagesCompl(String id) {
        log.info("--Obteniendo las imagenes complementarias---");
        //AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.load_products), view.getString(R.string.waiting_products));

        Call<RespObtenerImagesDto> call = restClientService.obtenerImages(id);
        //log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("GET IMAGES COMPLE APP PRESENTER REQUEST: ", "");

        call.enqueue(new Callback<RespObtenerImagesDto>() {
            @Override
            public void onResponse(Call<RespObtenerImagesDto> call, Response<RespObtenerImagesDto> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try {
                        System.out.println("");
                        Log.d("GET IMAGES PRODUCT APP PRESENTER", "RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //SharedPref.guardarIdUsuario(view,response.body().getIdUsuario());
                        //Toast.makeText(view, "Respuesta exitosa", Toast.LENGTH_SHORT).show();
                        imagesCompl.postValue(response.body());
                        //processFinished();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);

                }
            }

            @Override
            public void onFailure(Call<RespObtenerImagesDto> call, Throwable t) {
                System.err.println("Ocurrio un error al obtener las imagenes de los productos" + t.getMessage());

            }

            /*public void processFinished(){
                isLoading.setValue(true);
            }*/
        });
    }

    /**
     * Description: Función encargada de traer los comentarios generales
     */

    public void getComentsGen(String id) {
        log.info("--Obteniendo los comentarios generales---");


        Call<List<RespDetaProductoComen>> call = restClientService.getComentsGeneral(id);

        Log.d("GET COMENTS PRESENTER REQUEST: ", "");

        call.enqueue(new Callback<List<RespDetaProductoComen>>() {
            @Override
            public void onResponse(Call<List<RespDetaProductoComen>> call, Response<List<RespDetaProductoComen>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try {
                        System.out.println("");
                        Log.d("GET COMENTS PRESENTER", "RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //Toast.makeText(view, "Respuesta exitosa", Toast.LENGTH_SHORT).show();
                        comentarios.postValue(response.body());
                        processFinished();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);

                }
            }

            @Override
            public void onFailure(Call<List<RespDetaProductoComen>> call, Throwable t) {
                System.err.println("Ocurrio un error al obtener los comentarios" + t.getMessage());

            }

            public void processFinished(){
                isLoadingComents.setValue(true);
            }
        });
    }

    /**
     * Description: Función encargada de traer los comentarios generales
     */

    public void getComentsClient(String id) {
        log.info("--Obteniendo los comentarios generales---");


        Call<List<RespDetaProductoComen>> call = restClientService.getComentsClient(id);

        Log.d("GET COMENTS PRESENTER REQUEST: ", "");

        call.enqueue(new Callback<List<RespDetaProductoComen>>() {
            @Override
            public void onResponse(Call<List<RespDetaProductoComen>> call, Response<List<RespDetaProductoComen>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try {
                        System.out.println("");
                        Log.d("GET COMENTS PRESENTER", "RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //Toast.makeText(view, "Respuesta exitosa", Toast.LENGTH_SHORT).show();
                        comentariosClient.postValue(response.body());
                        processFinished();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {


                }
            }

            @Override
            public void onFailure(Call<List<RespDetaProductoComen>> call, Throwable t) {
                System.err.println("Ocurrio un error al obtener los comentarios" + t.getMessage());

            }

            public void processFinished(){
                isLoadingComentsClient.setValue(true);
            }
        });
    }

    /**
     * Description: Función encargada de registrar un producto
     */

    public void uploadProduct(String path, Producto producto) {
        RequestBody nombreProducto= RequestBody.create(MediaType.parse("multipart/form-data"), producto.getNombreProducto());
        RequestBody desProducto= RequestBody.create(MediaType.parse("multipart/form-data"), producto.getDesProducto());
        RequestBody precioUnitario= RequestBody.create(MediaType.parse("multipart/form-data"), producto.getPrecioUnitario().toString());
        RequestBody cantidadProducto= RequestBody.create(MediaType.parse("multipart/form-data"), producto.getCantidadProducto().toString());
        RequestBody marca= RequestBody.create(MediaType.parse("multipart/form-data"), producto.getMarca().toString());
        RequestBody categoria= RequestBody.create(MediaType.parse("multipart/form-data"), producto.getCategoria().toString());
        RequestBody idUsuario= RequestBody.create(MediaType.parse("multipart/form-data"), producto.getIdUsuario().toString());

        //Prepare image
        MultipartBody.Part image=null;

        if(path!=null){
            File file = new File(path);

            String fe = "";
            int i = file.getAbsolutePath().lastIndexOf('.');
            if (i > 0) {
                fe = file.getAbsolutePath().substring(i+1);
            }
            System.out.println("File extension is : "+fe);

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/"+fe), file);


            image = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            System.out.println(file.getName());
        }
        //image prepared
        if(image!=null){
            Call<ResponseBody> call= restClientService.uploadProduct(nombreProducto, desProducto,precioUnitario, cantidadProducto, marca, categoria,idUsuario, image);



            call.enqueue(new Callback<ResponseBody>(){

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("SAVE FILE","GUARDANDO PRODUCTO");
                    if(response!=null && response.code() == RESP_CODE_WEB_OK) {
                        System.out.println(response.body().toString());
                        System.out.println(response.code());
                        System.out.println("Producto saved");
                        /*UserPresenter userPresenter = new UserPresenter();
                        
                        RespObtenerProducto producto1  =  new RespObtenerProducto(

                        );
                        userPresenter.sendNotificationToTopics();*/
                        Toast.makeText(view, "Producto registrado correctamente", Toast.LENGTH_SHORT).show();
                        if(view2!=null){
                            Navigation.findNavController(view2).navigate(RegisterProductFragmentDirections.toProfileLogedFragment());
                        }
                        //Navigation.findNavController().navigate(RegisterProductFragmentDirections.toProfileLogedFragment());
                        //Navigation.findNavController(view.getView()).navigate(RegisterProductFragmentDirections.toProfileLogedFragment());

                        //Toast.makeText(view, "Producto registrado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.err.println("Ocurrio un error al registrar el producto: " + t.getMessage());
                    System.out.println("Fallo al registrar el producto");
                }
            });
        }


    }

    /*
    Método para actualizar productos
     */

    public void updateProducts(String id, ReqUpdateProduct product){

        Call<RespMessage> call = restClientService.updateProduct(id, product);

        call.enqueue(new Callback<RespMessage>() {
            @Override
            public void onResponse(Call<RespMessage> call, Response<RespMessage> response) {
                System.out.println("codigo de respuesta: "+response.code());
                if(response!=null && response.code() == RESP_CODE_WEB_OK){
                    Log.d("SAVE FILE","ACTUALIZANDO PRODUCTO");

                    Toast.makeText(view, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show();
                    if(view2!=null){
                        Navigation.findNavController(view2).navigate(UpdateProductFragmentDirections.actionUpdateProductFragmentToProductsOnSaleFragment());
                    }


                }


            }

            @Override
            public void onFailure(Call<RespMessage> call, Throwable t) {
                System.out.println("HA OCURRIODO UN ERROR AL ACTUALIZAR EL PRODUCTO");
            }
        });

    }

     /*
    Método para actualizar productos
     */

    public void deleteProduct(String id){

        Call<RespMessage> call = restClientService.deleteProduct(id);

        call.enqueue(new Callback<RespMessage>() {
            @Override
            public void onResponse(Call<RespMessage> call, Response<RespMessage> response) {

                if(response!=null && response.code() == RESP_CODE_WEB_OK){
                    Log.d("SAVE FILE","ELIMINADO LOGICO DE UN PRODUCTO");

                    Toast.makeText(view, "Producto eliminado correctamente", Toast.LENGTH_SHORT).show();

                }else{
                    System.out.println("codigo de respuesta: "+response.code());
                }


            }

            @Override
            public void onFailure(Call<RespMessage> call, Throwable t) {
                System.out.println("HA OCURRIODO UN ERROR AL ACTUALIZAR EL PRODUCTO");
                Toast.makeText(view, "Ha ocurrido un error inesperado", Toast.LENGTH_SHORT).show();
            }
        });

    }








}
