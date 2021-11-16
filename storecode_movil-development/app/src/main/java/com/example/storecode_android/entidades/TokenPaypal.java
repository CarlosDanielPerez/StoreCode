package com.example.storecode_android.entidades;

import java.io.Serializable;

public class TokenPaypal implements Serializable {
    private String scope;
    private String access_token;
    private String token_type;
    private String app_id;
    private Integer expires_in;
    private String nonce;


    public TokenPaypal(){

    }

    public TokenPaypal(String scope, String access_token, String token_type, String app_id, Integer expires_in, String nonce) {
        this.scope = scope;
        this.access_token = access_token;
        this.token_type = token_type;
        this.app_id = app_id;
        this.expires_in = expires_in;
        this.nonce = nonce;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @Override
    public String toString(){
        return "{" +
                "scope=" + scope +
                ", access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", app_id='" + app_id + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", nonce='" + nonce +
                '}';
    }

}
