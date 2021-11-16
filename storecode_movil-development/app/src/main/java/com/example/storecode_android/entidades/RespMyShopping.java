package com.example.storecode_android.entidades;

public class RespMyShopping {
    String nombreProducto;
    String desProducto;
    String imagenProducto;
    Integer cantidadProducto;
    Integer FolioVenta;
    String claveTransaccion;
    Double totalVendido;

    public RespMyShopping() {
    }

    public RespMyShopping(String nombreProducto, String desProducto, String imagenProducto, Integer cantidadProducto, Integer folioVenta, String claveTransaccion, Double totalVendido) {
        this.nombreProducto = nombreProducto;
        this.desProducto = desProducto;
        this.imagenProducto = imagenProducto;
        this.cantidadProducto = cantidadProducto;
        FolioVenta = folioVenta;
        this.claveTransaccion = claveTransaccion;
        this.totalVendido = totalVendido;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDesProducto() {
        return desProducto;
    }

    public void setDesProducto(String desProducto) {
        this.desProducto = desProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getFolioVenta() {
        return FolioVenta;
    }

    public void setFolioVenta(Integer folioVenta) {
        FolioVenta = folioVenta;
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

    @Override
    public String toString() {
        return "{" +
                "nombreProducto='" + nombreProducto + '\'' +
                ", desProducto='" + desProducto + '\'' +
                ", imagenProducto='" + imagenProducto + '\'' +
                ", cantidadProducto=" + cantidadProducto +
                ", FolioVenta=" + FolioVenta +
                ", claveTransaccion='" + claveTransaccion + '\'' +
                ", totalVendido=" + totalVendido +
                '}';
    }
}
