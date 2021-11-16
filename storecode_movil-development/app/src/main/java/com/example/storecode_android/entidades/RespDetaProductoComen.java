package com.example.storecode_android.entidades;

public class RespDetaProductoComen {

    String nombre;
    String comentario;
    Integer estrellas;
    String imagenUsuario;
    String fecha;

    public RespDetaProductoComen(){

    }

    public RespDetaProductoComen(String nombre, String comentario, Integer estrellas, String imagenUsuario, String fecha) {
        this.nombre = nombre;
        this.comentario = comentario;
        this.estrellas = estrellas;
        this.imagenUsuario = imagenUsuario;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public String getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(String imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "{" +
                "nombre='" + nombre + '\'' +
                ", comentario='" + comentario + '\'' +
                ", estrellas=" + estrellas +
                ", imagenUsuario='" + imagenUsuario + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }


    /*
    {
        "nombre": "José Andrés Jiménez",
        "comentario": "El producto es bueno",
        "estrellas": null,
        "imagenUsuario": null,
        "fecha": "2021-04-18T05:00:00.000Z"
    }

    //comentarios

    {
        "nombre": "José Andrés Jiménez",
        "comentariocliente": "El producto es muy bueno, te dan lo que ofrecen",
        "estrellas": 4,
        "imagenUsuario": null,
        "fecha": "2021-04-20T05:00:00.000Z"
    }
    * */
}
