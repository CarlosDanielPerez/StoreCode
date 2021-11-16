package com.example.storecode_android.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.storecode_android.Presenter.UserPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.entidades.TokenFCM;
import com.example.storecode_android.utils.Constantes;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.SplashScreenActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Description: Controlador de las Notificaciones via GCM Firebase o de Tapps WEB
 * Created by EX440831 on 14/02/2020.
 */

public class NotificacionFirebaseMessagingService extends FirebaseMessagingService {

    private static final Logger log = LogFile.getLogger(NotificacionFirebaseMessagingService.class);

    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {
            log.info("Message data payload: " + remoteMessage.getData());
            System.out.println("Message data payload: " + remoteMessage.getData());
            //Verificacion parametros enviados desde el Postman o Tapps WEB
            try {

                //Primero verifica si los datos enviados son un ID y un AppID
                switch (remoteMessage.getData().get("message")) {
                    case "Notificacion":
                        String claveTransaccion= remoteMessage.getData().get("claveTransaccion");
                        String idVendedor= remoteMessage.getData().get("idVendedor");
                        Double totalPagado = Double.parseDouble(remoteMessage.getData().get("totalVendido"));
                        String body = remoteMessage.getData().get("body");
                        String title = remoteMessage.getData().get("title");
                        log.info("Pruebas Notificación Recibida: " + remoteMessage.getData().get("claveTransaccion") + " - " + remoteMessage.getData().get("totalVendido"));

                        System.out.println("Pruebas Notificación Recibida: " + remoteMessage.getData().get("claveTransaccion") + " - " + remoteMessage.getData().get("totalVendido"));

                        String items= remoteMessage.getData().get("items");

                        System.out.println("Imprimiendo items---------------");
                        String productosComprados= items.substring(1,(items.length()-1));
                        System.out.println(items);
                        System.out.println("productos comprados:"+ productosComprados);

                        /*Type listType = new TypeToken<List<ReqItemProduct>>(){}.getType();
                        List<ReqItemProduct> listItem  = new Gson().fromJson(productosComprados, listType);

                        System.out.println("Clave"+claveTransaccion);
                        System.out.println("Tus productos");
                        listItem.forEach(product ->{
                            System.out.println(product.getDescription());
                            System.out.println(product.getPrice());
                        });*/

                        String idUsuario= SharedPref.obtenerIdUsuario(getBaseContext());
                        NotificationToDevice notificationToDevice = new NotificationToDevice(
                                Integer.parseInt(idUsuario),
                                null,
                                Integer.parseInt(idVendedor),
                                claveTransaccion,
                                totalPagado,
                                //items o productos comprados: este es un string
                                productosComprados
                        );

                        //ArrayList<NotificationToDevice> notificationToDeviceList= new ArrayList<>();

                        String currentNotification= SharedPref.obtenerNotificacionDescartada(getBaseContext());
                        System.out.println("currentNotification:"+currentNotification);

                        if(!currentNotification.equals("Vacio")){
                            Type listType2 = new TypeToken<List<NotificationToDevice>>(){}.getType();
                            ArrayList<NotificationToDevice> listNotifications = new Gson().fromJson(currentNotification, listType2);
                            listNotifications.add(notificationToDevice);

                            SharedPref.guardarNotificacionDescartada(getBaseContext(),listNotifications);
                        }else{
                            ArrayList<NotificationToDevice> listNotification = new ArrayList<>();
                            listNotification.add(notificationToDevice);
                            SharedPref.guardarNotificacionDescartada(getBaseContext(),listNotification);
                        }




                        Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                        /*intent.putExtra("claveTransaccion", app_name);
                        intent.putExtra("totalVendido", app_id);*/

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), Constantes.NOTIFICATION_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_RIFAO");
                        notificationBuilder.setAutoCancel(true)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setTicker(Constantes.NOTIFICATION_DESCRIPTION)
                                .setContentTitle(Constantes.APLICATION_NAME)
                                .setContentText(title)
                                .setContentInfo(Constantes.NOTIFICATION_DESCRIPTION)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setContentIntent(pendingIntent);


                        CharSequence name = Constantes.NOTIFICATION_CHANNEL;
                        String description = Constantes.NOTIFICATION_CHANNEL;
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationChannel channel = new NotificationChannel(Constantes.NOTIFICATION_CHANNEL, name, importance);
                        channel.setDescription(description);

                        NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
                        notificationManager.createNotificationChannel(channel);
                        notificationManager.notify(Constantes.NOTIFICATION_ID, notificationBuilder.build());
                        break;

                    //Segundo verifica si el dato enviado es Prueba que indica que abra SMA
                    case "NotificacionVendedor":

                        String titleVendedor= remoteMessage.getData().get("title");
                        String descriptionVendedor = remoteMessage.getData().get("description");
                        Intent intentVendedor = new Intent(getApplicationContext(), SplashScreenActivity.class);
                        /*intent.putExtra("claveTransaccion", app_name);
                        intent.putExtra("totalVendido", app_id);*/

                        intentVendedor.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        PendingIntent pendingIntentVendedor = PendingIntent.getActivity(getApplicationContext(), Constantes.NOTIFICATION_REQUEST_CODE, intentVendedor, PendingIntent.FLAG_UPDATE_CURRENT);

                        NotificationCompat.Builder notificationBuilderVendedor = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_RIFAO");
                        notificationBuilderVendedor.setAutoCancel(true)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setTicker(Constantes.NOTIFICATION_DESCRIPTION)
                                .setContentTitle(Constantes.APLICATION_NAME)
                                .setContentText(titleVendedor)
                                .setContentInfo(Constantes.NOTIFICATION_DESCRIPTION)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setContentIntent(pendingIntentVendedor);


                        CharSequence name2 = Constantes.NOTIFICATION_CHANNEL;
                        String description2 = Constantes.NOTIFICATION_CHANNEL;
                        int importance2 = NotificationManager.IMPORTANCE_HIGH;
                        NotificationChannel channel2 = new NotificationChannel(Constantes.NOTIFICATION_CHANNEL, name2, importance2);
                        channel2.setDescription(descriptionVendedor);

                        NotificationManager notificationManager2 = getApplicationContext().getSystemService(NotificationManager.class);
                        notificationManager2.createNotificationChannel(channel2);
                        notificationManager2.notify(Constantes.NOTIFICATION_ID, notificationBuilderVendedor.build());



                        /*try {
                            Intent intent2 = new Intent(getApplicationContext(), SplashScreenActivity.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/

                        break;
                    //Tercero verifica si el dato enviador es Remote que indica que abra el Servicio de Remote Config para otras aplicaciones diferentes a Precio Inteligente
                    case "Remote":
                        /*RemoteConfigService2 remoteConfigService2 = new RemoteConfigService2();
                        remoteConfigService2.consultamosFireBaseConfig(getApplicationContext(), 2);*/

                        //Guardo una preferencia en caso de que el usuario haya recibido una notificación por este medio
                        //Y la uso en caso de que el usuario le de clic le redirija a Detalle de Aplicaciones y cuando de regresar
                        //lo saque de la aplicación
                        //SharedPref.guardarNotificacionGCM("notificacion_gcm_activa");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            log.info("Message Notification Body: " + remoteMessage.getNotification().getTitle() + " / " + remoteMessage.getNotification().getBody());
        }

    }

    @Override
    public void onNewToken(String token) {
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        String idUsuario= SharedPref.obtenerIdUsuario(getBaseContext());
        UserPresenter userPresenter= new UserPresenter();
        SharedPref.guardarTokenFCM(this,token);

        if(!idUsuario.equals("null")){
            userPresenter.guardarUsuarioTokenFCM(new TokenFCM(
                    Integer.parseInt(idUsuario),
                    token
            ));
        }
    }

}
