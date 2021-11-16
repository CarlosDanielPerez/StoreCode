package com.example.storecode_android.entidades;

public class ProductoCarrito {
    Integer idProducto;
    Integer idUsuario;
    Integer cantidadProducto;
    public ProductoCarrito(){

    }
    public ProductoCarrito(Integer idProducto, Integer idUsuario, Integer cantidadProducto) {
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
        this.cantidadProducto = cantidadProducto;
    }



    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario= idUsuario;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    @Override
    public String toString() {
        return "{" +
                "idProducto=" + idProducto +
                ", idUsuario=" + idUsuario +
                ", cantidadProducto=" + cantidadProducto +
                '}';
    }




    /*
    {
    "idProducto":36,
    "idUsuario": 3,
    "cantidadProducto":2
}
     */

}
