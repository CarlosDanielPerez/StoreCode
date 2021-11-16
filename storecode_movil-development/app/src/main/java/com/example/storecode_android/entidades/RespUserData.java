package com.example.storecode_android.entidades;




public class RespUserData {
    private Integer idUsuario;
    private String nombreUsuario;
    private String apellido1Usuario;
    private String emailUsuario;
    private String contraUsuario;
    private String confirmaContraUsuario;
    private String apellido2Usuario;
    private String imagenUsuario;
    private String telefonoUsuario;
    private String statusUsuario;
    private String rfeUsuario;
    private String codeActive;
    private String clientidpaypal;
    private String pk_mercadopago;
    private String accessTokenMpago;
    private String tokenFCM;

    /*
    {
    "idUsuario": 0,
    "nombreUsuario": "Rosendo",
    "apellido1Usuario": "Jiménez",
    "emailUsuario": "chend@gmail.com",
    "contraUsuario": "onepiece",
    "confirmaContraUsuario": "onepiece",
    "apellido2Usuario": "Cervantes",
    "imagenUsuario": null,
    "telefonoUsuario": "null",
    "statusUsuario": "0",
    "rfeUsuario": "null",
    "codeActive": "111000",
    "clienidpaypal": null
}
     */

    public RespUserData(){

    }

    public RespUserData(Integer idUsuario, String nombreUsuario, String apellido1Usuario, String emailUsuario, String contraUsuario, String confirmaContraUsuario, String apellido2Usuario, String imagenUsuario, String telefonoUsuario,
                        String statusUsuario, String rfeUsuario, String codeActive, String clientidpaypal, String pk_mercadopago, String accessTokenMpago, String tokenFCM) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellido1Usuario = apellido1Usuario;
        this.emailUsuario = emailUsuario;
        this.contraUsuario = contraUsuario;
        this.confirmaContraUsuario = confirmaContraUsuario;
        this.apellido2Usuario = apellido2Usuario;
        this.imagenUsuario = imagenUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.statusUsuario = statusUsuario;
        this.rfeUsuario = rfeUsuario;
        this.codeActive = codeActive;
        this.clientidpaypal = clientidpaypal;
        this.pk_mercadopago = pk_mercadopago;
        this.accessTokenMpago = accessTokenMpago;
        this.tokenFCM = tokenFCM;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellido1Usuario() {
        return apellido1Usuario;
    }

    public void setApellido1Usuario(String apellido1Usuario) {
        this.apellido1Usuario = apellido1Usuario;
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

    public String getConfirmaContraUsuario() {
        return confirmaContraUsuario;
    }

    public void setConfirmaContraUsuario(String confirmaContraUsuario) {
        this.confirmaContraUsuario = confirmaContraUsuario;
    }

    public String getApellido2Usuario() {
        return apellido2Usuario;
    }

    public void setApellido2Usuario(String apellido2Usuario) {
        this.apellido2Usuario = apellido2Usuario;
    }

    public String getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(String imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }



    public String getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(String statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public String getRfeUsuario() {
        return rfeUsuario;
    }

    public void setRfeUsuario(String rfeUsuario) {
        this.rfeUsuario = rfeUsuario;
    }

    public String getCodeActive() {
        return codeActive;
    }

    public void setCodeActive(String codeActive) {
        this.codeActive = codeActive;
    }

    public String getClientidpaypal() {
        return clientidpaypal;
    }

    public void setClientidpaypal(String clientidpaypal) {
        this.clientidpaypal = clientidpaypal;
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

    public String getTokenFCM() {
        return tokenFCM;
    }

    public void setTokenFCM(String tokenFCM) {
        this.tokenFCM = tokenFCM;
    }

    @Override
    public String toString() {
        return "{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", apellido1Usuario='" + apellido1Usuario + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", contraUsuario='" + contraUsuario + '\'' +
                ", confirmaContraUsuario='" + confirmaContraUsuario + '\'' +
                ", apellido2Usuario='" + apellido2Usuario + '\'' +
                ", imagenUsuario='" + imagenUsuario + '\'' +
                ", telefonoUsuario='" + telefonoUsuario + '\'' +
                ", statusUsuario='" + statusUsuario + '\'' +
                ", rfeUsuario='" + rfeUsuario + '\'' +
                ", codeActive='" + codeActive + '\'' +
                ", clientidpaypal='" + clientidpaypal + '\'' +
                ", pk_mercadopago='" + pk_mercadopago + '\'' +
                ", accessTokenMpago='" + accessTokenMpago + '\'' +
                ", tokenFCM='" + tokenFCM + '\'' +
                '}';
    }

   /*@Override
    public String toString() {
        return '{' +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", apellido1Usuario='" + apellido1Usuario + '\'' +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", contraUsuario='" + contraUsuario + '\'' +
                ", confirmaContraUsuario='" + confirmaContraUsuario + '\'' +
                ", apellido2Usuario='" + apellido2Usuario + '\'' +
                ", imagenUsuario='" + imagenUsuario + '\'' +
                ", telefonoUsuario='" + telefonoUsuario + '\'' +
                ", statusUsuario='" + statusUsuario + '\'' +
                ", rfeUsuario='" + rfeUsuario + '\'' +
                ", codeActive='" + codeActive + '\'' +
                ", clientidpaypal='" + clientidpaypal + '\'' +
                '}';
    }*/
}
