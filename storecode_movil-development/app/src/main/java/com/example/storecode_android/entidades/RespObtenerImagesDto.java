package com.example.storecode_android.entidades;

public class RespObtenerImagesDto {

    Integer idProducto;
    String img1;
    String img2;
    String img3;
    String img4;
    String nombreProducto;
    String imagenProducto;

     /*
    {
        "idProducto": 48,
        "img1": "http://192.168.1.72:3000/public/products/imagen2.jpg-1618104655045.jpg",
        "img2": "http://192.168.1.72:3000/public/products/imagen3.jpg-1618104655049.jpg",
        "img3": "http://192.168.1.72:3000/public/products/imagen4.jpg-1618104655052.jpg",
        "img4": "http://192.168.1.72:3000/public/products/laptopdell_front.jpg-1618104655053.jpg",
        "nombreProducto": "Laptop Dell",
        "imagenProducto": "http://192.168.1.72:3000/public/products/IMG-20210310-WA0004.jpeg-1617939165921.jpeg"
    }
     */

    public RespObtenerImagesDto(){

    }

    public RespObtenerImagesDto(Integer idProducto, String img1, String img2, String img3, String img4, String nombreProducto, String imagenProducto) {
        this.idProducto = idProducto;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.nombreProducto = nombreProducto;
        this.imagenProducto = imagenProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    @Override
    public String toString() {
        return "{" +
                "idProducto=" + idProducto +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", img3='" + img3 + '\'' +
                ", img4='" + img4 + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", imagenProducto='" + imagenProducto + '\'' +
                '}';
    }



}
