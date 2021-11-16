package com.example.storecode_android.entidades;

public class Category {
    Integer idCategoria;
    String desCategoria;
    String statusCategoria;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDesCategoria() {
        return desCategoria;
    }

    public void setDesCategoria(String desCategoria) {
        this.desCategoria = desCategoria;
    }

    public String getStatusCategoria() {
        return statusCategoria;
    }

    public void setStatusCategoria(String statusCategoria) {
        this.statusCategoria = statusCategoria;
    }

    @Override
    public String toString() {
        return "{" +
                "idCategoria=" + idCategoria +
                ", desCategoria='" + desCategoria + '\'' +
                ", statusCategoria='" + statusCategoria + '\'' +
                '}';
    }


    /*
    {
        "idCategoria": 0,
        "desCategoria": "MÓVILES",
        "statusCategoria": "1"
    }
     */
}
