package com.example.storecode_android.entidades;

public class RespMessage {
    String status;

    public RespMessage(){

    }

    public RespMessage(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                '}';
    }

    /*
    {
    "status": "Los datos del producto se actualizaron correctamente"
}
     */
}
