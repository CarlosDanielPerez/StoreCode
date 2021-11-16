package com.example.storecode_android.entidades;

public class Venta {

    Integer idUsuario;
    Integer idPaginaPago;
    String claveTransaccion;
    String paypalDatos;
    String correo;
    Double totalVendido;
    String direccionEntrega;

    public Venta() {
    }

    public Venta( Integer idUsuario, Integer idPaginaPago, String claveTransaccion, String paypalDatos, String correo, Double totalVendido, String direccionEntrega) {

        this.idUsuario = idUsuario;
        this.idPaginaPago = idPaginaPago;
        this.claveTransaccion = claveTransaccion;
        this.paypalDatos = paypalDatos;
        this.correo = correo;
        this.totalVendido = totalVendido;
        this.direccionEntrega = direccionEntrega;
    }



    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPaginaPago() {
        return idPaginaPago;
    }

    public void setIdPaginaPago(Integer idPaginaPago) {
        this.idPaginaPago = idPaginaPago;
    }

    public String getClaveTransaccion() {
        return claveTransaccion;
    }

    public void setClaveTransaccion(String claveTransaccion) {
        this.claveTransaccion = claveTransaccion;
    }

    public String getPaypalDatos() {
        return paypalDatos;
    }

    public void setPaypalDatos(String paypalDatos) {
        this.paypalDatos = paypalDatos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    @Override
    public String toString() {
        return "{" +
                "idUsuario=" + idUsuario +
                ", idPaginaPago=" + idPaginaPago +
                ", claveTransaccion='" + claveTransaccion + '\'' +
                ", paypalDatos='" + paypalDatos + '\'' +
                ", correo='" + correo + '\'' +
                ", totalVendido=" + totalVendido +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                '}';
    }

    /*
    {
    "idProductoCarrito": 127,
    "idUsuario":83,
    "idPaginaPago":1,
    "claveTransaccion": "7364982748",
    "paypalDatos":"Vacio",
    "correo" : "cheche1430@gmail.com",
    "totalVendido" : 200,
    "direccionEntrega":"Calle Anteros 603"
}
     */
}
