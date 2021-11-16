package com.example.storecode_android.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NotificationToDevice extends TokenFCM implements Serializable {
    private Integer idVendedor;
    private String claveTransaccion;
    private Double totalVendido;
    private String items;

    public NotificationToDevice(Integer idUsuario, String tokenFCM,Integer idVendedor, String claveTransaccion, Double totalVendido,String items) {
        super(idUsuario, tokenFCM);
        this.idVendedor=idVendedor;
        this.claveTransaccion = claveTransaccion;
        this.totalVendido = totalVendido;
        this.items = items;

    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getClaveTransaccion() {
        return claveTransaccion;
    }

    public void setClaveTransaccion(String claveTransaccion) {
        this.claveTransaccion = claveTransaccion;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }


    /*public String toStringv1() {

        return "{" +
                "claveTransaccion='" + claveTransaccion + '\'' +
                ", totalVendido=" + totalVendido +
                ", items='" + items +""+ '\'' +
                ", idUsuario=" + idUsuario +
                ", tokenFCM='" + tokenFCM + '\'' +
                '}';
    }*/


    @Override
    public String toString() {
        return "{" +
                "idVendedor=" + idVendedor +
                ", claveTransaccion='" + claveTransaccion + '\'' +
                ", totalVendido=" + totalVendido +
                ", items='" + items + '\'' +
                ", idUsuario=" + idUsuario +
                ", tokenFCM='" + tokenFCM + '\'' +
                '}';
    }
}
