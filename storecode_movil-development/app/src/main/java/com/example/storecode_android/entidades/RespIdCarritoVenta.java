package com.example.storecode_android.entidades;

public class RespIdCarritoVenta {
    Integer idCarritoVenta;

    public RespIdCarritoVenta() {
    }

    public RespIdCarritoVenta(Integer idCarritoVenta) {
        this.idCarritoVenta = idCarritoVenta;
    }

    public Integer getIdCarritoVenta() {
        return idCarritoVenta;
    }

    public void setIdCarritoVenta(Integer idCarritoVenta) {
        this.idCarritoVenta = idCarritoVenta;
    }

    @Override
    public String toString() {
        return "{" +
                "idCarritoVenta=" + idCarritoVenta +
                '}';
    }
}
