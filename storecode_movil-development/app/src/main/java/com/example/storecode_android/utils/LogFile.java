package com.example.storecode_android.utils;

import android.os.Environment;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

import static com.example.storecode_android.utils.Constantes.LOG_SIZE_MB;
//import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.LOG_SIZE_MB;

/**
 * Description: Se metió LOG4J por la transparencia para crear y administrar archivos de logs a diferencia del log nativo
 * Created by EX383473 on 16/10/2019.
 */
public class LogFile {

    /**
     * Función que tiene las configuraciones del LOG4J
     */
    public static Logger getLogger(Class clazz) {

        Logger log = Logger.getLogger(clazz.getName());
        try {
            final LogConfigurator logConfigurator = new LogConfigurator();
            logConfigurator.setFileName(Environment.getExternalStorageDirectory().toString() + File.separator + Constantes.LOG_FILE_NAME);
            logConfigurator.setRootLevel(Level.INFO);
            logConfigurator.setLevel("org.apache", Level.INFO);
            logConfigurator.setUseFileAppender(true);
            logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
            logConfigurator.setMaxFileSize(LOG_SIZE_MB);
            logConfigurator.setImmediateFlush(true);
            logConfigurator.configure();
            log = Logger.getLogger(clazz);
        } catch (RuntimeException ignored) {//Esta excepción ocurre en ocasiones y es por que no alcanza a construir el log en la primer clase
        } catch (Exception e) {
            log.error( e.getMessage());
            e.printStackTrace();
        }
        return log;
    }


}
