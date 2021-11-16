package com.example.storecode_android.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.view.fragments.ProductsOnSaleFragmentDirections;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductsOnSaleAdapter extends RecyclerView.Adapter<HolderProductsOnSale> implements Filterable {

    private static final Logger log = LogFile.getLogger(ModeloAdapterInstaladas.class);
    /*private final Activity context;
    private final List<RespObtenerDatosAplicaciones> modeloList;
    private List<RespObtenerDatosAplicaciones> mFilteredList;*/

    private final ArrayList<RespGetProductByUser> modeloList = new ArrayList<RespGetProductByUser>();
    private int aux;

    //private ImageButton btn_buscar;
    private int bandera_search = 0;
    //private SearchView msearchView;
    private Context context;
    private List<RespGetProductByUser> mFilteredList = modeloList;

    private ProductsOnSaleListener modeloAdapterListener;
    ProductPresenter productPresenter;

    String version;

    Drawable d;

    public ProductsOnSaleAdapter(ProductsOnSaleListener modeloAdapterListener, Activity context) {
        this.context = context;
        //this.modeloList = modeloList;
        //this.mFilteredList = modeloList;
        this.aux = aux;
        this.modeloAdapterListener = modeloAdapterListener;
    }


    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public HolderProductsOnSale onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items_products_onsale, parent, false);

        //filtrarModelos();
        return new HolderProductsOnSale(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductsOnSale holder, int position) {
        RespGetProductByUser producto = modeloList.get(position);
        System.out.println("----------------");
        System.out.printf("OnBindViewHolder");
        System.out.println(producto.getNombreProducto());
        holder.tvNameOnSale.setText(producto.getNombreProducto());
        holder.tvPriceOnSale.setText("$ " + producto.getPrecioUnitarioProducto());
        holder.tvDescriptionOnSale.setText(producto.getDesProducto());
        holder.tvStockOnSale.setText("Cantidad: "+producto.getStockRealProducto());
        holder.tvMarcaOnSale.setText("Marca: "+producto.getIdMarca());
        holder.tvCategoryOnSale.setText("Categoria: "+producto.getIdCategoria());

        //holder.ivModelo.setImageURI(Uri.parse(producto.getImagenProducto()));
        /*Picasso.with(context)
                .load(Uri.parse(producto.getImagenProducto()))
                .into(holder.ivModelo);*/
        Picasso.get().load(Uri.parse(producto.getImagenProducto()))
                .into(holder.ivModeloOnSale);


        System.out.println("Click en:" + producto.getNombreProducto());

        //Metodo para mostrar el detalle del producto
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("producto:"+producto.getNombreProducto());
                //modeloAdapterListener.OnProductClicked(modeloList.get(position),position);
            }
        });


        holder.btnEdit.setOnClickListener(v->{
            ProductsOnSaleFragmentDirections.ToUpdateProduct action = ProductsOnSaleFragmentDirections.toUpdateProduct(producto);
            Navigation.findNavController(v).navigate(action);
        });

        holder.btnDelete.setOnClickListener(v->{
            productPresenter = new ProductPresenter(context, null);
            productPresenter.deleteProduct(producto.getIdProducto().toString());
        });


        //Toast.makeText(context,"EL producto que seleccionaste es:"+producto.getNombreProducto()+"y su id es:"+producto.getIdProducto(),Toast.LENGTH_SHORT).show();

        //ENVIAR SIG SERVICIO
        //CONSULTAR PRODUCTO POR ID
        //guardar en prefs
        //consultarlo en la otra pantalla
        //reemplazar prefs


    }

    @Override
    public int getItemCount() {
        System.out.println("---------getItemCount de modelo instaladas------------");
        System.out.println(modeloList.size());
        return modeloList.size();
    }

    public void updateData(List<RespGetProductByUser> data) {
        System.out.println("en el update data");

        modeloList.clear();
        modeloList.addAll(data);
        System.out.println(modeloList);
        notifyDataSetChanged();
    }
}
