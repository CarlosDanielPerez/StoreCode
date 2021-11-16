package com.example.storecode_android.entidades;

public class RespGetCarrito {
    Integer idCarrito;

    public RespGetCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }


    @Override
    public String toString() {
        return "{" +
                "idCarrito=" + idCarrito +
                '}';
    }
}
