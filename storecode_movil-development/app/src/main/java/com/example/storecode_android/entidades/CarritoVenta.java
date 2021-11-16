package com.example.storecode_android.entidades;

public class CarritoVenta {
    Integer idProductoCarrito;
    Integer FolioVenta;

    public CarritoVenta() {
    }

    public CarritoVenta(Integer idProductoCarrito, Integer folioVenta) {
        this.idProductoCarrito = idProductoCarrito;
        FolioVenta = folioVenta;
    }

    public Integer getIdProductoCarrito() {
        return idProductoCarrito;
    }

    public void setIdProductoCarrito(Integer idProductoCarrito) {
        this.idProductoCarrito = idProductoCarrito;
    }

    public Integer getFolioVenta() {
        return FolioVenta;
    }

    public void setFolioVenta(Integer folioVenta) {
        FolioVenta = folioVenta;
    }

    @Override
    public String toString() {
        return "{" +
                "idProductoCarrito=" + idProductoCarrito +
                ", FolioVenta=" + FolioVenta +
                '}';
    }

    /*
    {
    "idProductoCarrito": 8,
    "FolioVenta": 84
}
     */
}
