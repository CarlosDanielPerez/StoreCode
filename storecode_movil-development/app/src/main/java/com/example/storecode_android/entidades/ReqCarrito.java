package com.example.storecode_android.entidades;

public class ReqCarrito {
    Integer idUsuario;
    Double precioUnitario;
    Double cantidadProducto;
    String statusCarrito;

    public ReqCarrito(){

    }

    public ReqCarrito(Integer idUsuario, Double precioUnitario, Double cantidadProducto, String statusCarrito) {
        this.idUsuario = idUsuario;
        this.precioUnitario = precioUnitario;
        this.cantidadProducto = cantidadProducto;
        this.statusCarrito = statusCarrito;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Double cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getStatusCarrito() {
        return statusCarrito;
    }

    public void setStatusCarrito(String statusCarrito) {
        this.statusCarrito = statusCarrito;
    }

    @Override
    public String toString() {
        return "{" +
                "idUsuario=" + idUsuario +
                ", precioUnitario=" + precioUnitario +
                ", cantidadProducto=" + cantidadProducto +
                ", statusCarrito='" + statusCarrito + '\'' +
                '}';
    }


    /*
    {
    "idUsuario": 47,
    "precioUnitario": 0.0,
    "cantidadProducto": 0.0,
    "statusCarrito":"1"
}
     */
}
