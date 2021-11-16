package com.example.storecode_android.entidades;

import androidx.annotation.NonNull;

/**
 * Description:
 * Created by EX383473 on 04/01/2019.
 */
@SuppressWarnings("ALL")
public class RespLoginDto {
    private Integer IdUsuario;
    private String Nombre;
    private String Email;
    private String Contra;

    public RespLoginDto(Integer idUsuario, String nombre, String email, String contra) {
        IdUsuario = idUsuario;
        Nombre = nombre;
        Email = email;
        Contra = contra;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContra() {
        return Contra;
    }

    public void setContra(String contra) {
        Contra = contra;
    }

    @Override
    public String toString() {
        return "RespLoginDto{" +
                "IdUsuario=" + IdUsuario +
                ", Nombre='" + Nombre + '\'' +
                ", Email='" + Email + '\'' +
                ", Contra='" + Contra + '\'' +
                '}';
    }

    /*
    {
    "IdUsuario": 0,
    "Nombre": "Rosendo Jiménez",
    "Email": "chend@gmail.com",
    "Contra": "onepiece"
}
    */




}
