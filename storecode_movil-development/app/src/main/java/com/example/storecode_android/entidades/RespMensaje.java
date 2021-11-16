package com.example.storecode_android.entidades;

public class RespMensaje {
    String Mensaje;

    public RespMensaje(){}

    public RespMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "{" +
                "Mensaje='" + Mensaje + '\'' +
                '}';
    }

    /*
    {
    "Mensaje": "Carrito registrado con exito"
}
     */
}
