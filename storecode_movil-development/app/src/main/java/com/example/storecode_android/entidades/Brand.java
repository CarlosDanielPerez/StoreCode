package com.example.storecode_android.entidades;

public class Brand {

    Integer idMarca;
    String desMarca;
    String statusMarca;

    public Brand(){

    }

    public Brand(Integer idMarca, String desMarca, String statusMarca) {
        this.idMarca = idMarca;
        this.desMarca = desMarca;
        this.statusMarca = statusMarca;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getDesMarca() {
        return desMarca;
    }

    public void setDesMarca(String desMarca) {
        this.desMarca = desMarca;
    }

    public String getStatusMarca() {
        return statusMarca;
    }

    public void setStatusMarca(String statusMarca) {
        this.statusMarca = statusMarca;
    }

    @Override
    public String toString() {
        return "{" +
                "idMarca=" + idMarca +
                ", desMarca='" + desMarca + '\'' +
                ", statusMarca='" + statusMarca + '\'' +
                '}';
    }

    /*
    {
        "idMarca": 0,
        "desMarca": "HUAWEI",
        "statusMarca": "1"
    }
     */
}
