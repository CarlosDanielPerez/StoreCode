package com.example.storecode_android.entidades;

public class PurchasedItem {

    String nombreProducto;
    String desProducto;
    String imagenProducto;
    Double precioUnitarioProducto;
    Double cantidadProducto;
    Integer FolioVenta;
    String claveTransaccion;
    Double totalVendido;

    public PurchasedItem() {
    }

    public PurchasedItem(String nombreProducto, String desProducto, String imagenProducto, Double precioUnitarioProducto, Double cantidadProducto, Integer folioVenta, String claveTransaccion, Double totalVendido) {
        this.nombreProducto = nombreProducto;
        this.desProducto = desProducto;
        this.imagenProducto = imagenProducto;
        this.precioUnitarioProducto = precioUnitarioProducto;
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

    public Double getPrecioUnitarioProducto() {
        return precioUnitarioProducto;
    }

    public void setPrecioUnitarioProducto(Double precioUnitarioProducto) {
        this.precioUnitarioProducto = precioUnitarioProducto;
    }

    public Double getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Double cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getFolioVeta() {
        return FolioVenta;
    }

    public void setFolioVeta(Integer folioVeta) {
        FolioVenta = folioVeta;
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
                ", precioUnitarioProducto=" + precioUnitarioProducto +
                ", cantidadProducto=" + cantidadProducto +
                ", FolioVenta=" + FolioVenta +
                ", claveTransaccion='" + claveTransaccion + '\'' +
                ", totalVendido=" + totalVendido +
                '}';
    }

    /*
    {
        "nombreProducto": "Estuche de lentes",
        "desProducto": "Estuche",
        "imagenProducto": "http://192.168.1.72:3000/public/products/image_picker2281895957813068809.jpg-1625784021658.jpg",
        "precioUnitarioProducto": 50,
        "cantidadProducto": 1,
        "FolioVenta": 171,
        "claveTransaccion": "1240199649",
        "totalVendido": 50
    }
     */
}
