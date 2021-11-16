package com.example.storecode_android.entidades;

public class ReqUpdateStock {
    Integer idProductoCarrito;
    Integer idProducto;
    Integer cantidadSelled;

    public ReqUpdateStock() {
    }

    public ReqUpdateStock(Integer idProductoCarrito, Integer idProducto, Integer cantidadSelled) {
        this.idProductoCarrito = idProductoCarrito;
        this.idProducto = idProducto;
        this.cantidadSelled = cantidadSelled;
    }

    public Integer getIdProductoCarrito() {
        return idProductoCarrito;
    }

    public void setIdProductoCarrito(Integer idProductoCarrito) {
        this.idProductoCarrito = idProductoCarrito;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidadSelled() {
        return cantidadSelled;
    }

    public void setCantidadSelled(Integer cantidadSelled) {
        this.cantidadSelled = cantidadSelled;
    }

    @Override
    public String toString() {
        return "{" +
                "idProductoCarrito=" + idProductoCarrito +
                ", idProducto=" + idProducto +
                ", cantidadSelled=" + cantidadSelled +
                '}';
    }
}
