package com.example.storecode_android.entidades;

public class TokenFCM {
    Integer idUsuario;
    String tokenFCM;

    public TokenFCM() {
    }

    public TokenFCM(Integer idUsuario, String tokenFCM) {
        this.idUsuario= idUsuario;
        this.tokenFCM = tokenFCM;
    }

    public String getTokenFCM() {
        return tokenFCM;
    }

    public void setTokenFCM(String tokenFCM) {
        this.tokenFCM = tokenFCM;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "{" +
                "idUsuario=" + idUsuario +
                ", tokenFCM='" + tokenFCM + '\'' +
                '}';
    }
}
