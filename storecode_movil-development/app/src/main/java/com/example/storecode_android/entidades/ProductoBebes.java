package com.example.storecode_android.entidades;

import java.io.Serializable;
import java.util.Date;

public class ProductoBebes implements Serializable {
    private Integer idProducto;
    private String nombreProducto;
    private String desProducto;
    private double precioUnitarioProducto;
    private String imagenProducto;
    //private Date fechaAlojadoProducto;
    private double stockRealProducto;
    private String statusProducto;
    private Integer idMarca;
    private Integer idCategoria;
    private Integer idUsuario;





     /*

     {
        "idProducto": 0,
        "nombreProducto": "Manguera",
        "desProducto": "Manguera para carro",
        "precioUnitarioProducto": 998,
        "imagenProducto": "http://192.168.1.72:3000/public/products/image-1616627584744.jpg",
        "fechaAlojadoProducto": "2021-03-06T06:00:00.000Z",
        "stockRealProducto": 8,
        "statusProducto": "1",
        "idMarca": 6,
        "idCategoria": 8,
        "idUsuario": 40
    }

     {idProducto=34,
     nombreProducto='Pantalla plana',
      desProducto='Televisión inteligente',
      precioUnitarioProducto=9500.0,
      imagenProducto='http://192.168.1.72:3000/public/products/image-1616629033631.jpg',
      stockRealProducto=6.0,
      statusProducto='1',
      idMarca=6,
      idCategoria=1,
      idUsuario=39},


     {
        "idProducto": 0,
        "nombreProducto": "Manguera",
        "desProducto": "Manguera para carro",
        "precioUnitarioProducto": 998,
        "imagenProducto": "http://192.168.1.72:3000/public/products/image-1616627584744.jpg",
        "stockRealProducto": 8,
        "idUsuario": 40
    }


     {
        "idProducto": 48,
        "Nombrw": "Laptop Dell",
        "Descripcion": "Laptop Dell nuevo",
        "$ Unitario": 4200,
        "Imagen": "http://192.168.1.72:3000/public/products/IMG-20210310-WA0004.jpeg-1617939165921.jpeg",
        "Fecha": "2021-04-06T05:00:00.000Z",
        "Stock": "3.00",
        "Estatus": "Activo",
        "Marca": "SONY",
        "Categoria": "ELECTRÓNICOS"
    }
    * */


    public ProductoBebes(){

    }

    public ProductoBebes(Integer idProducto, String nombreProducto, String desProducto, double precioUnitarioProducto, String imagenProducto, double stockRealProducto, String statusProducto, Integer idMarca, Integer idCategoria, Integer idUsuario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.desProducto = desProducto;
        this.precioUnitarioProducto = precioUnitarioProducto;
        this.imagenProducto = imagenProducto;
        //this.fechaAlojadoProducto = fechaAlojadoProducto;
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

    /*public Date getFechaAlojadoProducto() {
        return fechaAlojadoProducto;
    }*/

    /*public void setFechaAlojadoProducto(Date fechaAlojadoProducto) {
        this.fechaAlojadoProducto = fechaAlojadoProducto;
    }*/

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
                //", fechaAlojadoProducto=" + fechaAlojadoProducto.toString() +
                ", stockRealProducto=" + stockRealProducto +
                ", statusProducto='" + statusProducto + '\'' +
                ", idMarca=" + idMarca +
                ", idCategoria=" + idCategoria +
                ", idUsuario=" + idUsuario +
                '}';
    }
}

