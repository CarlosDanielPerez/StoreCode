package com.example.storecode_android.service;

import com.example.storecode_android.Proceso;
import com.example.storecode_android.entidades.Brand;
import com.example.storecode_android.entidades.CarritoVenta;
import com.example.storecode_android.entidades.Category;
import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.entidades.ProcesamientoPaypal;
import com.example.storecode_android.entidades.ProductInCard;
import com.example.storecode_android.entidades.ProductoBebes;
import com.example.storecode_android.entidades.ProductoBotas;
import com.example.storecode_android.entidades.ProductoCarrito;
import com.example.storecode_android.entidades.ProductoCasual;
import com.example.storecode_android.entidades.ProductoSandalias;
import com.example.storecode_android.entidades.ProductoTenis;
import com.example.storecode_android.entidades.Purchase;
import com.example.storecode_android.entidades.PurchasedItem;
import com.example.storecode_android.entidades.ReqCarrito;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.entidades.ReqLoginDto;
import com.example.storecode_android.entidades.ReqMercadoPago;
import com.example.storecode_android.entidades.ReqUpdateProduct;
import com.example.storecode_android.entidades.ReqUpdateStock;
import com.example.storecode_android.entidades.RespDetaProductoComen;
import com.example.storecode_android.entidades.RespFolioVenta;
import com.example.storecode_android.entidades.RespGetCarrito;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.entidades.RespIdCarritoVenta;
import com.example.storecode_android.entidades.RespIdPreference;
import com.example.storecode_android.entidades.RespLoginDto;
import com.example.storecode_android.entidades.RespMensaje;
import com.example.storecode_android.entidades.RespMessage;
import com.example.storecode_android.entidades.RespObtenerImagesDto;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.entidades.RespUserData;
import com.example.storecode_android.entidades.TokenFCM;
import com.example.storecode_android.entidades.TokenPaypal;
import com.example.storecode_android.entidades.Venta;
import com.example.storecode_android.utils.LogFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.storecode_android.utils.Constantes.TIME_OUT_RETROFIT;
import static com.example.storecode_android.utils.Constantes.URL_BASE;
import static com.example.storecode_android.utils.Constantes.URL_BASE_PAYPAL;


/**
 * Description: Implementación que contiene los métodos/servicios a los que se conectará la aplicación,
 * así como las configuraciones de retrofit
 * Created by EX383473 on 04/01/2019.
 */
public class RestClientServiceImpl implements RestClientService {
    private static final Logger log = LogFile.getLogger(RestClientServiceImpl.class);
    private RestClientService restClient;

    /**
     * En el constructor de la implementación metemos las configuraciones del retrofit
     */
    public RestClientServiceImpl() {
            try {

                /*// Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                final S

                SLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                */

                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                        .create();

                OkHttpClient client = new OkHttpClient.Builder()
                //.sslSocketFactory(sslSocketFactory)
                //.hostnameVerifier((hostname, session) -> true)
                .connectTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        restClient = retrofit.create(RestClientService.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public RestClientServiceImpl(String paypal, String user, String password) {

        try {

                /*// Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                final S

                SLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                */

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                    .create();

            OkHttpClient client = new OkHttpClient.Builder()
                    //.sslSocketFactory(sslSocketFactory)
                    //.hostnameVerifier((hostname, session) -> true)
                    .connectTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS)
                    .addInterceptor(new BasicAuthInterceptor(user, password))
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE_PAYPAL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            restClient = retrofit.create(RestClientService.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public RestClientServiceImpl(String proceso) {
        try {

                /*// Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                final S

                SLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                */

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
                    .create();

            OkHttpClient client = new OkHttpClient.Builder()
                    //.sslSocketFactory(sslSocketFactory)
                    //.hostnameVerifier((hostname, session) -> true)
                    .connectTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE_PAYPAL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            restClient = retrofit.create(RestClientService.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
















    /**
     * Construye Retrofit con listener de descarga
     */
    /*public RestClientServiceImpl(RetrofitDownloadListener listener) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .client(getOkHttpDownloadClientBuilder(listener).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            restClient = retrofit.create(RestClientService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    /*public RestClientServiceImpl(RxJava2CallAdapterFactory adapter) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30*//*TIME_OUT_RETROFIT*//*, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30*//*TIME_OUT_RETROFIT*//*, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(adapter)
                .build();
        restClient = retrofit.create(RestClientService.class);
    }*/

    /*private OkHttpClient.Builder getOkHttpDownloadClientBuilder(final RetrofitDownloadListener progressListener) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        try {
            // You might want to increase the timeout
            httpClientBuilder.connectTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS);
            httpClientBuilder.writeTimeout(0, TimeUnit.SECONDS);
            httpClientBuilder.readTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS);
            httpClientBuilder.addInterceptor(chain -> {
                if (progressListener == null)
                    return chain.proceed(chain.request());
                Response response = chain.proceed(chain.request());
                ResponseBody body = response.body();
                String bodyString = body.string();
                MediaType contentType = body.contentType();
                return response.newBuilder().body(new ProgressResponseBody(ResponseBody.create(contentType, bodyString), progressListener, bodyString.getBytes().length)).build();
            });

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return httpClientBuilder;
    }*/

    @Override
    public Call<RespLoginDto> login(ReqLoginDto request) {
        return restClient.login(request);
    }

    @Override
    public Call<RespMensaje> createAccount(RespUserData respUserData) {
        return restClient.createAccount(respUserData);
    }

    @Override
    public Call<List<RespObtenerProducto>> cargarProductos(String id) {
        return restClient.cargarProductos(id);
    }


    @Override
    public Call<RespUserData> getUserById(String id) {
        return restClient.getUserById(id);
    }

    @Override
    public Call<List<RespObtenerProducto>> cargarAllProductos() {
        return restClient.cargarAllProductos();
    }

    @Override
    public Call<List<RespGetProductByUser>> getProductsOnSale(String id) {
        return restClient.getProductsOnSale(id);
    }

    @Override
    public Call<RespObtenerImagesDto> obtenerImages(String id) {
        return restClient.obtenerImages(id);
    }

    @Override
    public Call<List<RespDetaProductoComen>> getComentsGeneral(String id) {
        return restClient.getComentsGeneral(id);
    }

    @Override
    public Call<List<RespDetaProductoComen>> getComentsClient(String id) {
        return restClient.getComentsClient(id);
    }

    @Override
    public Call<ResponseBody> uploadProduct(RequestBody nombreProducto, RequestBody desProducto, RequestBody precioUnitario, RequestBody cantidadProducto, RequestBody marca, RequestBody categoria, RequestBody idUsuario, MultipartBody.Part file) {
        return restClient.uploadProduct(nombreProducto,desProducto,precioUnitario,cantidadProducto,marca,categoria,idUsuario, file);
    }

    @Override
    public Call<List<Category>> getAllCategories() {
        return restClient.getAllCategories();
    }

    @Override
    public Call<List<Brand>> getAllBrands() {
        return restClient.getAllBrands();
    }

    @Override
    public Call<RespMessage> updateProduct(String id, ReqUpdateProduct product) {
        return restClient.updateProduct(id, product);
    }

    @Override
    public Call<RespMessage> deleteProduct(String id) {
        return restClient.deleteProduct(id);
    }

    @Override
    public Call<List<ProductInCard>> getProductsInCart(String id) {
        return restClient.getProductsInCart(id);
    }

    @Override
    public Call<RespMensaje> insertCarrito(ReqCarrito reqCarrito) {
        return restClient.insertCarrito(reqCarrito);
    }

    @Override
    public Call<RespGetCarrito> getCarritoByIdUser(String idUser) {
        return restClient.getCarritoByIdUser(idUser);
    }

    @Override
    public Call<RespMensaje> insertProductInCart(ProductoCarrito productoCarrito) {
        return restClient.insertProductInCart(productoCarrito);
    }

    @Override
    public Call<RespMensaje> deleteProductFromCart(String idProductoCarrito) {
        return restClient.deleteProductFromCart(idProductoCarrito);
    }

    @Override
    public Call<RespIdPreference> createIdPreference(List<ReqItemProduct> reqItemProduct) {
        return restClient.createIdPreference(reqItemProduct);
    }

    @Override
    public Call<RespFolioVenta> createVenta(Venta venta) {
        return restClient.createVenta(venta);
    }

    @Override
    public Call<RespIdCarritoVenta> createCarritoVenta(CarritoVenta carritoVenta) {
        return restClient.createCarritoVenta(carritoVenta);
    }

    @Override
    public Call<RespMensaje> updateProductStock(ReqUpdateStock reqUpdateStock) {
        return restClient.updateProductStock(reqUpdateStock);
    }

    @Override
    public Call<List<Purchase>> getMyShopping(String idUser) {
        return restClient.getMyShopping(idUser);
    }

    @Override
    public Call<RespMensaje> guardarDatosMercadoPago(ReqMercadoPago reqMercadoPago) {
        return restClient.guardarDatosMercadoPago(reqMercadoPago);
    }

    @Override
    public Call<RespMensaje> guardarUsuarioTokenFCM(TokenFCM tokenFCM) {
        return restClient.guardarUsuarioTokenFCM(tokenFCM);
    }

    @Override
    public Call<String> sendNotificationTODevice(NotificationToDevice notificationToDevice) {
        return restClient.sendNotificationTODevice(notificationToDevice);
    }

    @Override
    public Call<String> sendNotificationToTopics(RespObtenerProducto producto) {
        return restClient.sendNotificationToTopics(producto);
    }

    @Override
    public Call<List<PurchasedItem>> getPurchasedItem(String folioVenta) {
        return restClient.getPurchasedItem(folioVenta);
    }

    @Override
    public Call<List<ProductoTenis>> getTenis() {
        return restClient.getTenis();
    }

    @Override
    public Call<List<ProductoSandalias>> getSandalias() {
        return restClient.getSandalias();
    }

    @Override
    public Call<List<ProductoBotas>> getBotas() {
        return restClient.getBotas();
    }

    @Override
    public Call<List<ProductoCasual>> getCasual() {
        return restClient.getCasual();
    }

    @Override
    public Call<List<ProductoBebes>> getBebes() {
        return restClient.getBebes();
    }



    @Override
    public Call<TokenPaypal> obtenerToken(String grantType) {
        return restClient.obtenerToken(grantType);
    }

    @Override
    public Call<List<ProcesamientoPaypal>> procesamientoPaypal(String auth, Proceso body) {
        return restClient.procesamientoPaypal(auth, body);
    }


























    /*@Override
    public Call<ResponseMasterDto<RespLogin_AutorizadoDto>> login_autorizado(ReqLogin_AutorizadoDto request) {
        return restClient.login_autorizado(request);
    }

    @Override
    public Call<ResponseMasterDto<RespLoginDto>> login(ReqLoginDto request) {
        return restClient.login(request);
    }

    @Override
    public Call<ResponseMasterDto<List<ResObtenerMarcaDto>>> obtenerMarcas(ReqObtenerMarcaDto request) {
        return restClient.obtenerMarcas(request);
    }

    @Override
    public Call<ResponseMasterDto<List<RespObtenerModelosDto>>> obtenerModelos(ReqObtenerModelosDto request) {
        return restClient.obtenerModelos(request);
    }

    @Override
    public Call<ResponseMasterDto<List<RespColor>>> obtenerColores(ReqObtenerColoresDto request) {
        return restClient.obtenerColores(request);
    }

    @Override
    public Call<ResponseMasterDto<List<RespObtenerPlanDto>>> obtenerPlanes(ReqObtenerPlanDto request) {
        return restClient.obtenerPlanes(request);
    }

    @Override
    public Call<ResponseMasterDto<PreciadorDispositivoDto>> construyePreciador(ReqPreciadorDispositivoDto request) {
        return restClient.construyePreciador(request);
    }

    @Override
    public Call<ResponseMasterDto<List<PreciadorDispositivoDto>>> construyePreciadores(List<ReqPreciadorDispositivoDto> request) {
        return restClient.construyePreciadores(request);
    }

    *//*@Override
    public Call<ResponseMasterDto<RespConsultaVideosDto>> consultaVideosPorModelo(ReqConsultaVideosDto request) {
        return restClient.consultaVideosPorModelo(request);
    }*//*

    @Override
    public Call<ResponseMasterDto<RespConsultaVideosDto2>> consultaVideosPorModelo(ReqConsultaVideosDto2 request) {
        return restClient.consultaVideosPorModelo(request);
    }

    @Override
    public Call<ResponseMasterDto<RespConsultaVideosPorMod>> consultaInformacionVideos(RequestConsultaVideosPorMod request) {
        return restClient.consultaInformacionVideos(request);
    }

    @Override
    public Observable<ResponseMasterDto<RespConsultaVideosDto2>> consultaVideosPorModeloObserver(@Body ReqConsultaVideosDto2 request) {
        return restClient.consultaVideosPorModeloObserver(request);
    }

    @Override
    public Call<ResponseMasterDto<RespBitacoraLoginDto>> guardaLoginApp(ReqBitacoraLoginAppDto request) {
        return restClient.guardaLoginApp(request);
    }

    @Override
    public Call<ResponseMasterDto<RespBitacoraLoginDto>> guardaColocacionApp(ReqBitacoraColocacionDto request) {
        return restClient.guardaColocacionApp(request);
    }

    @Override
    public Call<ResponseMasterDto<RespFlagStock>> getFlagStock(ReqFlagStock request) {
        return restClient.getFlagStock(request);
    }*/
}

class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    public BasicAuthInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }



    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }









}




