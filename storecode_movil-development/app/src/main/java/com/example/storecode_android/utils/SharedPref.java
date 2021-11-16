package com.example.storecode_android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.storecode_android.entidades.NotificationToDevice;

import org.apache.log4j.Logger;

import java.util.List;

import static com.example.storecode_android.utils.Constantes.ID_PREFERENCE;
import static com.example.storecode_android.utils.Constantes.TOKEN_FCM;

/**
 * Description: Clase encargada de extraer las preferencias de usario desde SharedPreferences
 * Created by EX440831 on 14/02/2020.
 */

public class SharedPref {
    private static final Logger log = LogFile.getLogger(SharedPref.class);
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEdit;

    @SuppressLint("CommitPrefEdits")
    public static void inicializaPreferencias(Context context) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(Constantes.SHAR_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferencesEdit == null)
            sharedPreferencesEdit = sharedPreferences.edit();
    }

    public static String getString(final String nameSharedPreference) {
        return sharedPreferences.getString(nameSharedPreference, null);
    }

    public static void setString(final String nameSharedPreference, final String value) {
        sharedPreferencesEdit.putString(nameSharedPreference, value);
        sharedPreferencesEdit.commit();
    }

    public static boolean getBoolean(final String nameSharedPreference) {
        return sharedPreferences.getBoolean(nameSharedPreference, false);
    }

    public static void setBoolean(final String nameSharedPreference, final boolean value) {
        sharedPreferencesEdit.putBoolean(nameSharedPreference, value);
        sharedPreferencesEdit.commit();
    }

    public static String getTutorial(final String nameSharedPreference) {
        return sharedPreferences.getString(nameSharedPreference, "Vacio");
    }

    public static void setTutorial(final String nameSharedPreference, final String value) {
        sharedPreferencesEdit.putString(nameSharedPreference, value);
        sharedPreferencesEdit.commit();
    }

    public static String getPermitirNotificaciones(final String nameSharedPreference) {
        return sharedPreferences.getString(nameSharedPreference, "Vacio");
    }

    public static void setPermitirNotificaciones(final String nameSharedPreference, final String value) {
        sharedPreferencesEdit.putString(nameSharedPreference, value);
        sharedPreferencesEdit.commit();
    }

    public static void guardarAplicaciones(final Context context, String datos) {
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.PRODUCTOS, datos);
        sharedPreferencesEdit.commit();
    }

    public static String obtenerAplicaciones(final Context context) {
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.PRODUCTOS, "Vacio");
    }
    //guardar datos del usuario

    public static void guardarUsuario(final Context context, String user){
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.USER, user);
        sharedPreferencesEdit.commit();
    }

    public static String obtenerUsuario(final Context context){
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.USER,"Vacio");
    }

    //guardar datos del usuario

    public static void guardarVendedor(final Context context, String vendedor){
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.VENDEDOR, vendedor);
        sharedPreferencesEdit.commit();
    }

    public static String obtenerVendedor(final Context context){
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.VENDEDOR,"Vacio");
    }

    //Guardar el id preference de mercado pago
    public static void guardarIdPreference(final Context context, String id){
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(ID_PREFERENCE, id);
        sharedPreferencesEdit.commit();
    }

    public static String obtenerIdPreference(final Context context){
        inicializaPreferencias(context);
        return sharedPreferences.getString(ID_PREFERENCE,"Vacio");
    }

    public static void guardarIdUsuario(final Context context, Integer idUsuario){
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.ID_USER,idUsuario.toString());
        sharedPreferencesEdit.commit();
    }
    //obtener el tokenFCM
    public static String obtenerTokenFCM(final Context context){
        inicializaPreferencias(context);
        return sharedPreferences.getString(TOKEN_FCM,"Vacio");
    }

    public static void guardarTokenFCM(final Context context, String tokenFCM){
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.TOKEN_FCM, tokenFCM);
        sharedPreferencesEdit.commit();
    }
    //Guardar productInCard
    public static void guardarListProductInCard(final Context context, String listProductInCard){
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.PRODUCTO_IN_CARD, listProductInCard);
        sharedPreferencesEdit.commit();
    }

    //obtener lista de productInCard
    public static String obtenerListProductInCard(final Context context){
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.PRODUCTO_IN_CARD,"null");
    }

    //obtener id del Usuario
    public static String obtenerIdUsuario(final Context context){
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.ID_USER,"null");
        
        
    }


    //ELiminar datos del usuario
    public static void deleteUserData(final Context context) {
        log.info("Usuario Eliminado");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.USER);
        sharedPreferencesEdit.commit();
    }

    public static void deleteIdUser(final Context context){
        log.info("Id usuario eliminado");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.ID_USER);
        sharedPreferencesEdit.commit();
    }

    //ELiminar todos los productos
    public static void deleteProducts(final Context context) {
        log.info("Productos Eliminados");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.PRODUCTOS);
        sharedPreferencesEdit.commit();
    }



    public static void guardarNotificacionDescartada(final Context context, List<NotificationToDevice> notificacions ) {
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.SHAR_PREF_NOTIFICACIONES_DESCARTADAS, notificacions.toString());
        sharedPreferencesEdit.commit();
    }

    public static String obtenerNotificacionDescartada(final Context context) {
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.SHAR_PREF_NOTIFICACIONES_DESCARTADAS, "Vacio");
    }

    public static void deleteNotificacionDescartada(final Context context) {
        log.info("Notificacion Eliminada");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.SHAR_PREF_NOTIFICACIONES_DESCARTADAS);
        sharedPreferencesEdit.commit();
    }

    //Bandera de Notificaciones Activas
    public static void guardarNotificacionActiva(final Context context, String bandera_notificacion) {
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.SHAR_PREF_NOTIFICACIONES_ACTIVA, bandera_notificacion);
        sharedPreferencesEdit.commit();
    }

    public static String obtenerguardarNotificacionActiva(final Context context) {
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.SHAR_PREF_NOTIFICACIONES_ACTIVA, "Vacio");
    }

    public static void deleteNotificacionActiva(final Context context) {
        log.info("Notificacion Eliminada");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.SHAR_PREF_NOTIFICACIONES_ACTIVA);
        sharedPreferencesEdit.commit();
    }

    //Bandera de Notificaciones Activas

    //Shared para guardar y consultar detalle de la compra

    public static void guardarNotificacionCompra(final Context context, NotificationToDevice notificacions) {
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.SHAR_PREF_NOTIFICATION_COMPRA, notificacions.toString());
        sharedPreferencesEdit.commit();
    }

    public static String obtenerNotificacionCompra(final Context context) {
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.SHAR_PREF_NOTIFICATION_COMPRA, "Vacio");
    }

    public static void deleteNotificacionCompra(final Context context) {
        log.info("Notificacion Eliminada");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.SHAR_PREF_NOTIFICATION_COMPRA);
        sharedPreferencesEdit.commit();
    }





   /* public static void guardarVersionPrevActualizacion(final Context context, final String versionAnterior) {
        log.info( "--guardarPrecColocPosActualizacion--");
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.SHAR_PREF_COLOC_PREC, versionAnterior);
        sharedPreferencesEdit.commit();
    }

    public static void guardarNotificacionDescartada(final Context context, Notificacion_Descartada notificacion_descartada ) {
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.SHAR_PREF_NOTIFICACIONES_DESCARTADAS, notificacion_descartada.toString());
        sharedPreferencesEdit.commit();
    }

    public static String obtenerNotificacionDescartada(final Context context) {
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.SHAR_PREF_NOTIFICACIONES_DESCARTADAS, "Vacio");
    }

    public static void deleteNotificacionDescartada(final Context context) {
        log.info("Notificacion Eliminada");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.SHAR_PREF_NOTIFICACIONES_DESCARTADAS);
        sharedPreferencesEdit.commit();
    }

    //Bandera de Notificaciones Activas
    public static void guardarNotificacionActiva(final Context context, String bandera_notificacion) {
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.SHAR_PREF_NOTIFICACIONES_ACTIVA, bandera_notificacion);
        sharedPreferencesEdit.commit();
    }

    public static String obtenerguardarNotificacionActiva(final Context context) {
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.SHAR_PREF_NOTIFICACIONES_ACTIVA, "Vacio");
    }

    public static void deleteNotificacionActiva(final Context context) {
        log.info("Notificacion Eliminada");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.SHAR_PREF_NOTIFICACIONES_ACTIVA);
        sharedPreferencesEdit.commit();
    }

    //Bandera de Notificaciones Activas
    public static void guardarAplicaciones(final Context context, String datos) {
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.SHAR_PREF_APLICACIONES_DISPONIBLES, datos);
        sharedPreferencesEdit.commit();
    }

    public static String obtenerAplicaciones(final Context context) {
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.SHAR_PREF_APLICACIONES_DISPONIBLES, "Vacio");
    }

    public static void deleteAplicaciones(final Context context) {
        log.info("Aplicacaciones Eliminada");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.SHAR_PREF_APLICACIONES_DISPONIBLES);
        sharedPreferencesEdit.commit();
    }*/

    //Preference de Descripcion de Aplicaciones
   /* public static void guardarDescripcionAplicaciones(final Context context, String datos) {
        inicializaPreferencias(context);
        sharedPreferencesEdit.putString(Constantes.SHAR_PREF_DESCRIPCION_APLICACIONES, datos);
        sharedPreferencesEdit.commit();
    }

    public static String obtenerDescripcionAplicaciones(final Context context) {
        inicializaPreferencias(context);
        return sharedPreferences.getString(Constantes.SHAR_PREF_DESCRIPCION_APLICACIONES, "Vacio");
    }

    public static void deleteDescripcionAplicaciones(final Context context) {
        log.info("Aplicacaciones Eliminada");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.SHAR_PREF_DESCRIPCION_APLICACIONES);
        sharedPreferencesEdit.commit();
    }

    //Guarda Nombre del Usuario
    public static String getNombreEmpleado(final String nameSharedPreference) {
        return sharedPreferences.getString(nameSharedPreference, null);
    }

    public static void setNombreEmpleado(final String nameSharedPreference, final String value) {
        sharedPreferencesEdit.putString(nameSharedPreference, value);
        sharedPreferencesEdit.commit();
    }

    //Preference de PackageName
    public static void guardarPackageName(String datos) {
        log.info("Paquete Creado");
        sharedPreferencesEdit.putString(Constantes.SHAR_PREF_PACKAGE_NAME, datos);
        sharedPreferencesEdit.commit();
    }

    public static String obtenerPackageName() {
        return sharedPreferences.getString(Constantes.SHAR_PREF_PACKAGE_NAME, "Vacio");
    }

    SharedPref.setString(Constantes.SHAR_PREF_USUARIO, aSwitch.isChecked() ? user : null);
    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("user", user);
                            editor.putString("pass", pass);
                            editor.apply();

    public static void deletePackageName(final Context context) {
        log.info("Paquete Eliminado");
        inicializaPreferencias(context);
        sharedPreferencesEdit.remove(Constantes.SHAR_PREF_PACKAGE_NAME);
        sharedPreferencesEdit.commit();
    }*/


}
