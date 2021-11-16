package com.example.storecode_android.entidades;

import java.io.Serializable;
import java.sql.Date;

public class Purchase  implements Serializable {
    Integer FolioVenta;
    Integer idUsuario;
    Integer idpaginaPago;
    String claveTransaccion;
    String paypalDatos;
    Date fechaVenta;
    String correo;
    Double totalVendido;
    String direccionEntrega;

    public Purchase() {
    }

    public Purchase(Integer folioVenta, Integer idUsuario, Integer idpaginaPago, String claveTransaccion, String paypalDatos, Date fechaVenta, String correo, Double totalVendido, String direccionEntrega) {
        FolioVenta = folioVenta;
        this.idUsuario = idUsuario;
        this.idpaginaPago = idpaginaPago;
        this.claveTransaccion = claveTransaccion;
        this.paypalDatos = paypalDatos;
        this.fechaVenta = fechaVenta;
        this.correo = correo;
        this.totalVendido = totalVendido;
        this.direccionEntrega = direccionEntrega;
    }

    public Integer getFolioVenta() {
        return FolioVenta;
    }

    public void setFolioVenta(Integer folioVenta) {
        FolioVenta = folioVenta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPaginaPago() {
        return idpaginaPago;
    }

    public void setIdPaginaPago(Integer idPaginaPago) {
        this.idpaginaPago = idPaginaPago;
    }

    public String getClaveTransaccion() {
        return claveTransaccion;
    }

    public void setClaveTransaccion(String claveTransaccion) {
        this.claveTransaccion = claveTransaccion;
    }

    public String getPaypalDatos() {
        return paypalDatos;
    }

    public void setPaypalDatos(String paypalDatos) {
        this.paypalDatos = paypalDatos;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }


    @Override
    public String toString() {
        return "{" +
                "FolioVenta=" + FolioVenta +
                ", idUsuario=" + idUsuario +
                ", idpaginaPago=" + idpaginaPago +
                ", claveTransaccion='" + claveTransaccion + '\'' +
                ", paypalDatos='" + paypalDatos + '\'' +
                ", fechaVenta=" + fechaVenta +
                ", correo='" + correo + '\'' +
                ", totalVendido=" + totalVendido +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                '}';
    }
}
