package com.example.storecode_android.entidades;

public class ProcesamientoPaypal {

private String id;
private String status;
private String links;

    public ProcesamientoPaypal(String id, String status, String links) {
        this.id = id;
        this.status = status;
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }


    @Override
    public String toString(){
        return "{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", links='" + links + '\'' +
                '}';
    }
}
