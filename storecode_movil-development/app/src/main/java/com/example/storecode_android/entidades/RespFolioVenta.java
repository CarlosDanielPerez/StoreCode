package com.example.storecode_android.entidades;

public class RespFolioVenta {

    Integer FolioVenta;

    public RespFolioVenta(){

    }

    public RespFolioVenta(Integer folioVenta) {
        FolioVenta = folioVenta;
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
                "FolioVenta=" + FolioVenta +
                '}';
    }
}
