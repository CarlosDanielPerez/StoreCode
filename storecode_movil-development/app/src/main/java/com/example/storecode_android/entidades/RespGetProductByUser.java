package com.example.storecode_android.entidades;

import java.io.Serializable;

public class RespGetProductByUser implements Serializable {
    private Integer idProducto;
    private String nombreProducto;
    private String desProducto;
    private double precioUnitarioProducto;
    private String imagenProducto;
    //private Date fechaAlojadoProducto;
    private double stockRealProducto;
    private String statusProducto;
    private String idMarca;
    private String idCategoria;
    private Integer idUsuario;

    /*
     {
        "idProducto": 55,
        "nombreProducto": "Estuche de lentes",
        "desProducto": "Estuche",
        "precioUnitarioProducto": 50,
        "imagenProducto": "http://192.168.1.72:3000/public/products/image_picker2281895957813068809.jpg-1625784021658.jpg",
        "stockRealProducto": "2.00",
        "statusProducto": "Activo",
        "idMarca": "HUAWEI",
        "idCategoria": "MUEBLES",
        "idUsuario": 100
    }
     */

    public RespGetProductByUser(){

    }

    public RespGetProductByUser(Integer idProducto, String nombreProducto, String desProducto, double precioUnitarioProducto, String imagenProducto, double stockRealProducto, String statusProducto, String idMarca, String idCategoria, Integer idUsuario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.desProducto = desProducto;
        this.precioUnitarioProducto = precioUnitarioProducto;
        this.imagenProducto = imagenProducto;
        this.stockRealProducto = stockRealProducto;
        this.statusProducto = statusProducto;
        this.idMarca = idMarca;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
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

    public double getPrecioUnitarioProducto() {
        return precioUnitarioProducto;
    }

    public void setPrecioUnitarioProducto(double precioUnitarioProducto) {
        this.precioUnitarioProducto = precioUnitarioProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public double getStockRealProducto() {
        return stockRealProducto;
    }

    public void setStockRealProducto(double stockRealProducto) {
        this.stockRealProducto = stockRealProducto;
    }

    public String getStatusProducto() {
        return statusProducto;
    }

    public void setStatusProducto(String statusProducto) {
        this.statusProducto = statusProducto;
    }

    public String getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(String idMarca) {
        this.idMarca = idMarca;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", desProducto='" + desProducto + '\'' +
                ", precioUnitarioProducto=" + precioUnitarioProducto +
                ", imagenProducto='" + imagenProducto + '\'' +
                ", stockRealProducto=" + stockRealProducto +
                ", statusProducto='" + statusProducto + '\'' +
                ", idMarca='" + idMarca + '\'' +
                ", idCategoria='" + idCategoria + '\'' +
                ", idUsuario=" + idUsuario +
                '}';
    }

    /*
    {
        "idProducto": 50,
        "nombreProducto": "Laptop HP",
        "desProducto": "Laptop HP de ultima generación",
        "precioUnitarioProducto": 3500,
        "imagenProducto": "http://192.168.1.72:3000/public/products/laptop_hp.jpg-1617843523662.jpg",
        "stockRealProducto": "6.00",
        "statusProducto": "Activo",
        "idMarca": "HP",
        "idCategoria": "ELECTRÓNICOS",
        "idUsuario": 49
    }
     */
}
