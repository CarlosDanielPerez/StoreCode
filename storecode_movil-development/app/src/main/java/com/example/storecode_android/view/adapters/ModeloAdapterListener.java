package com.example.storecode_android.view.adapters;


import com.example.storecode_android.entidades.ProductoTenis;
import com.example.storecode_android.entidades.RespObtenerProducto;

public interface ModeloAdapterListener {
    public void OnProductClicked(RespObtenerProducto producto, int position);
    public void OnProductClicked(ProductoTenis producto, int position);
}
