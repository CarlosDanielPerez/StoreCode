package com.example.storecode_android.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import org.apache.log4j.Logger;
import java.util.Objects;

import  com.example.storecode_android.R;

/*
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.R;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.presenter.preciador_unico.PreciadorUnicoVistaPreviaPresenter;

import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.URL_BASE;
*/
/**
 * Description: Clase encargada de tener recursos reutilizables como loaders, alertDialogs, piccaso, etc
 * Created by EX383473 on 14/01/2019.
 */

public class AnimacionesGenerales {
    private static final Logger log = LogFile.getLogger(AnimacionesGenerales.class);
    private static ProgressDialog progressDialog;
    @SuppressLint("StaticFieldLeak")
    private static ProgressBar progressBar;
    @SuppressLint("StaticFieldLeak")
    private static TextView tvProgress;
    private static AlertDialog alertDialogDescarga;


    /**
     * Description: Función encargada de mostrar u ocultar un loader mientras se consume el servicio de login
     */
    public static void mostrarLoader(final boolean visibilidad, final Context context, final String title, final String msg) {
        try {
            if (visibilidad) {
                //Mostramos el loader mientras se realiza la consulta en un hilo secundario
                progressDialog = new ProgressDialog(context);
                progressDialog.setTitle(title);
                progressDialog.setMessage(msg);
                progressDialog.setIndeterminate(true);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();
            } else {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        } catch (Exception e) {
            log.error("ERROR_mostrarLoader:" + e.getMessage());
        }

    }

    /*
    public static void mostrarAlertDialogDescarga(final Activity view, final String title, final String msg, final boolean visibilidad) {
        try {
            if (visibilidad) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view);
                LayoutInflater inflater = view.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.alert_dialog_descargando_apk, null);
                dialogBuilder.setView(dialogView);
                TextView tvTitle = dialogView.findViewById(R.id.tv_title_descarga);
                TextView tvMsg = dialogView.findViewById(R.id.tv_msg_descarga);
                tvTitle.setText(title);
                tvMsg.setText(msg);
                progressBar = dialogView.findViewById(R.id.progress_bar);
                tvProgress = dialogView.findViewById(R.id.tv_progress);
                progressBar.setProgress(0);
                alertDialogDescarga = dialogBuilder.create();
                Objects.requireNonNull(alertDialogDescarga.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialogDescarga.show();
                alertDialogDescarga.setCancelable(false);
            } else {
                if (alertDialogDescarga != null && alertDialogDescarga.isShowing()) {
                    alertDialogDescarga.dismiss();
                    alertDialogDescarga.cancel();
                }
            }
        } catch (Exception e) {
            log.error("ERROR_mostrarAlertDialogDescarga:" + e.getMessage());
        }
    }*/

    public static void actualizaProgressBar(final int progress) {
        if (progressBar != null) {
            progressBar.setProgress(progress);
            tvProgress.setText(String.valueOf(progress));
        }
    }

    /**
     * Mostramos un cuadro de diálogo indicando que ocurrió un error en el servidor
     *
     * @param view Recibe el activity desde el cual se invoca
     */
    public static void mostrarAlertDialogErrorServer(Activity view) {
        log.info("--mostrarAlertDialogErrorServer--");
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view);
            LayoutInflater inflater = view.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog_error_red, null);
            dialogBuilder.setView(dialogView);
            Button btnQuitar = dialogView.findViewById(R.id.alert_dialog_error_red_btn_entendido);
            final AlertDialog alertDialogErrorServer = dialogBuilder.create();
            Objects.requireNonNull(alertDialogErrorServer.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialogErrorServer.show();
            alertDialogErrorServer.setCancelable(false);
            btnQuitar.setOnClickListener(v -> {
                alertDialogErrorServer.dismiss();
                alertDialogErrorServer.cancel();
            });
        } catch (Exception e) {
            log.error("ERROR_mostrarAlertDialogErrorServer:" + e.getMessage());
        }
    }

    /**
     * Mostramos un cuadro de diálogo indicando que ocurrió un error en el servidor
     *
     * @param view Recibe el activity desde el cual se invoca
     * @param asyncTaskRunner
     */
    /*Eliminar
    public static void mostrarAlertDialogErrorServer2(Activity view,  PreciadorUnicoVistaPreviaPresenter.AsyncTaskRunner asyncTaskRunner) {
        log.info("--mostrarAlertDialogErrorServer--");
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view);
            LayoutInflater inflater = view.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog_error_red, null);
            dialogBuilder.setView(dialogView);
            Button btnQuitar = dialogView.findViewById(R.id.alert_dialog_error_red_btn_entendido);
            final AlertDialog alertDialogErrorServer = dialogBuilder.create();
            Objects.requireNonNull(alertDialogErrorServer.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialogErrorServer.show();
            alertDialogErrorServer.setCancelable(false);
            btnQuitar.setOnClickListener(v -> {
                alertDialogErrorServer.dismiss();
                alertDialogErrorServer.cancel();

                asyncTaskRunner.cancel(true);

            });
        } catch (Exception e) {
            log.error("ERROR_mostrarAlertDialogErrorServer:" + e.getMessage());
        }
    }
    */

    /**
     * Mostramos un cuadro de diálogo indicando que ocurrió un error de catálogo
     */
    @SuppressLint("SetTextI18n")
    public static void mostrarAlertDialogError(final Activity view, final String errorMsj, String errorCode) {
        log.info("--mostrarAlertDialogError--");
        log.error("ErrorCode:" + errorCode + "" + errorMsj);
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view);
            LayoutInflater inflater = view.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog_error, null);
            dialogBuilder.setView(dialogView);
            TextView tvMensaje = dialogView.findViewById(R.id.tv_msg_error);
            tvMensaje.setText("[" + errorCode + "] " + errorMsj);
            Button btnQuitar = dialogView.findViewById(R.id.alert_dialog_error_red_btn_entendido);
            final AlertDialog alertDialogErrorServer = dialogBuilder.create();
            Objects.requireNonNull(alertDialogErrorServer.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialogErrorServer.show();
            alertDialogErrorServer.setCancelable(false);
            btnQuitar.setOnClickListener(v -> {
                alertDialogErrorServer.dismiss();
                alertDialogErrorServer.cancel();

            });
        } catch (Exception e) {
            log.error("mostrarAlertDialogError:" + e.getMessage());
        }
    }


    /**
     * Mostramos un cuadro de diálogo indicando una alerta
     */
    /*@SuppressLint("SetTextI18n")
    public static void mostrarAlertDialog(final Activity view, final String errorMsj) {
        try {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view);
            LayoutInflater inflater = view.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.alert_dialog_sin_planes, null);
            dialogBuilder.setView(dialogView);
            TextView tvMensaje = dialogView.findViewById(R.id.tv_msg_error);
            tvMensaje.setText("" + errorMsj);
            Button btnQuitar = dialogView.findViewById(R.id.alert_dialog_error_red_btn_entendido);
            final AlertDialog alertDialogErrorServer = dialogBuilder.create();
            Objects.requireNonNull(alertDialogErrorServer.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialogErrorServer.show();
            alertDialogErrorServer.setCancelable(false);
            btnQuitar.setOnClickListener(v -> {
                alertDialogErrorServer.dismiss();
                alertDialogErrorServer.cancel();

            });
        } catch (Exception e) {
            log.error("ERROR_mostrarAlertDialogErrorServer:" + e.getMessage());
        }
    }*/

    /*public static void mostrarImagenPicasso(final Context context, final String nombreImagen, final int width, final int height, final ImageView imageView) {
        try {
            Picasso.with(context)
                    .load(URL_BASE + "img/".concat(nombreImagen))
                    .resize(width, height)
                    .centerInside()
                    .placeholder(R.drawable.img_cargando_foto)
                    .error(R.drawable.img_sinfoto)
                    .priority(Picasso.Priority.HIGH)
                    .into(imageView);
        } catch (Exception e) {
            log.info("ERROR_mostrarImagenPicasso:" + e.getMessage());
        }
    }*/

    /*public static void mostrarImagenPicassoWithOutRezise(final Context context, final String nombreImagen, final ImageView imageView) {
        try {
            Picasso.with(context)
                    .load(URL_BASE + "img/".concat(nombreImagen))
                    .fit()
                    .centerInside()
                    .placeholder(R.drawable.img_cargando_foto)
                    .error(R.drawable.img_sinfoto)
                    .priority(Picasso.Priority.HIGH)
                    .into(imageView);
        } catch (Exception e) {
            log.error("ERROR_mostrarImagenPicassoWithOutRezise:" + e.getMessage());
        }
    }*/


}
