package com.example.storecode_android.entidades;

import androidx.annotation.NonNull;

/**
 * Description:
 * Created by EX383473 on 04/01/2019.
 */
@SuppressWarnings("ALL")
public class ReqLoginDto {
    private String emailUsuario;
    private String contraUsuario;

    public ReqLoginDto(String emailUsuario, String contraUsuario) {
        this.emailUsuario = emailUsuario;
        this.contraUsuario = contraUsuario;
    }

    public ReqLoginDto(){

    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContraUsuario() {
        return contraUsuario;
    }

    public void setContraUsuario(String contraUsuario) {
        this.contraUsuario = contraUsuario;
    }

    @Override
    public String toString() {
        return "ReqLoginDto{" +
                "emailUsuario='" + emailUsuario + '\'' +
                ", contraUsuario='" + contraUsuario + '\'' +
                '}';
    }

    /*public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/

    /*@NonNull
    @Override
    public String toString() {
        return "ReqLoginDto{" +
                "idUsuario='" + idUsuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }*/
}
