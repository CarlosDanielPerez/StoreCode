package com.example.storecode_android.entidades;

public class ReqUpdateProduct {

    String nombreProducto;
    String desProducto;
    Double precioUnitarioProducto;
    String imagenProducto;
    Double stockRealProducto;
    Integer idMarca;
    Integer idCategoria;


    public ReqUpdateProduct(){

    }

    public ReqUpdateProduct(String nombreProducto, String desProducto, Double precioUnitarioProducto, String imagenProducto, Double stockRealProducto, Integer idMarca, Integer idCategoria) {
        this.nombreProducto = nombreProducto;
        this.desProducto = desProducto;
        this.precioUnitarioProducto = precioUnitarioProducto;
        this.imagenProducto = imagenProducto;
        this.stockRealProducto = stockRealProducto;
        this.idMarca = idMarca;
        this.idCategoria = idCategoria;
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

    public Double getPrecioUnitarioProducto() {
        return precioUnitarioProducto;
    }

    public void setPrecioUnitarioProducto(Double precioUnitarioProducto) {
        this.precioUnitarioProducto = precioUnitarioProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public Double getStockRealProducto() {
        return stockRealProducto;
    }

    public void setStockRealProducto(Double stockRealProducto) {
        this.stockRealProducto = stockRealProducto;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "{" +
                "nombreProducto='" + nombreProducto + '\'' +
                ", desProducto='" + desProducto + '\'' +
                ", precioUnitarioProducto=" + precioUnitarioProducto +
                ", imagenProducto='" + imagenProducto + '\'' +
                ", stockRealProducto=" + stockRealProducto +
                ", idMarca=" + idMarca +
                ", idCategoria=" + idCategoria +
                '}';
    }


    /*
     {
        "nombreProducto": "Celular Xiaomi",
        "desProducto": "Celular con sistema operativo Android",
        "precioUnitarioProducto": 4500,
        "imagenProducto": "http://192.168.1.66:3000/public/products/celular.jpeg-1617207150442.jpeg",
        "stockRealProducto": 8.00,
        "idMarca": 0,
        "idCategoria": 0
    }
     */
}
