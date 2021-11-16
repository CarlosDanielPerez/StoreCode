package com.example.storecode_android.entidades;

public class ReqMercadoPago {

    String idUsuario;
    String pk_mercadopago;
    String accessTokenMpago;

    public ReqMercadoPago() {
    }

    public ReqMercadoPago(String idUsuario, String pk_mercadopago, String accessTokenMpago) {
        this.idUsuario = idUsuario;
        this.pk_mercadopago = pk_mercadopago;
        this.accessTokenMpago = accessTokenMpago;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPk_mercadopago() {
        return pk_mercadopago;
    }

    public void setPk_mercadopago(String pk_mercadopago) {
        this.pk_mercadopago = pk_mercadopago;
    }

    public String getAccessTokenMpago() {
        return accessTokenMpago;
    }

    public void setAccessTokenMpago(String accessTokenMpago) {
        this.accessTokenMpago = accessTokenMpago;
    }

    @Override
    public String toString() {
        return "{" +
                "idUsuario=" + idUsuario +
                ", pk_mercadopago='" + pk_mercadopago + '\'' +
                ", accessTokenMpago='" + accessTokenMpago + '\'' +
                '}';
    }
}
