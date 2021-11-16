package com.example.storecode_android.view.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

/*import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;*/
import com.example.storecode_android.Presenter.CarritoPresenter;
import com.example.storecode_android.Presenter.LoginPresenter;
import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ProductoCarrito;
import com.example.storecode_android.entidades.ReqCarrito;
import com.example.storecode_android.entidades.RespObtenerImagesDto;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.MainDrawerActivity;
import com.example.storecode_android.view.adapters.ComentariosAdapter;
import com.example.storecode_android.view.adapters.ModeloAdapterInstaladas;
import com.example.storecode_android.view.adapters.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailFragment extends Fragment {


    ImageButton btnSalir;
    //ImageSlider sliderImageProducts;
    private ProductPresenter productPresenter;
    private LoginPresenter loginPresenter;
    private CarritoPresenter carritoPresenter;
    RespObtenerProducto producto;
    TextView tvName;
    TextView tvDescription;
    TextView tvPrice;
    TextView tvStock;
    TextView tvNameVendor;
    TextView tvEmailVendor;
    Spinner spinner ;

    Button btnAddTOCart;

    //adapter
    private ComentariosAdapter comentariosAdapter;
    private RelativeLayout rlBaseComentario;
    RecyclerView rvComents;
    //para comentarios de clientes
    private ComentariosAdapter comentariosAdapterClient;
    private RelativeLayout rlBaseComentarioClient;
    RecyclerView rvComentsClient;






    public ProductDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetailFragment newInstance(String param1, String param2) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productPresenter = new ProductPresenter(getContext(), getView());
        loginPresenter = new LoginPresenter();
        carritoPresenter = new CarritoPresenter(getActivity());
        //sliderImageProducts = view.findViewById(R.id.sliderImageProducts);
        //asignacion de elementos de la interfaz grafica
        spinner= view.findViewById(R.id.stock_spinner);
        btnSalir= view.findViewById(R.id.btnSalir);
        tvName = view.findViewById(R.id.tvTitleProduct);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvPrice = view.findViewById(R.id.tvPrecio);
        tvStock = view.findViewById(R.id.tvStock);
        // Para mostrar datos del vendedor
        tvNameVendor = view.findViewById(R.id.tvNameVendor);
        tvEmailVendor = view.findViewById(R.id.tvEmailVendor);
        //para mostrar comentarios
        comentariosAdapter= new ComentariosAdapter(getActivity());
        rvComents = view.findViewById(R.id.rv_coments);
        rlBaseComentario= view.findViewById(R.id.rlBaseComents);

        btnAddTOCart = view.findViewById(R.id.btnAddToCart);

        //para mostrar comentarios de clientes
        comentariosAdapterClient= new ComentariosAdapter(getActivity());
        rvComentsClient = view.findViewById(R.id.rv_coments_clients);
        rlBaseComentarioClient= view.findViewById(R.id.rlBaseComentsClient);

        producto = ProductDetailFragmentArgs.fromBundle(getArguments()).getRespObtenerProducto();
        tvName.setText("Nombre del producto: "+producto.getNombreProducto());
        tvDescription.setText("Descripción: "+producto.getDesProducto());
        tvPrice.setText("Precio: $"+ producto.getPrecioUnitarioProducto());
        tvStock.setText("Productos existentes: "+ producto.getStockRealProducto());

        productPresenter.getImagesCompl(producto.getIdProducto().toString());
        loginPresenter.getUserById(producto.getIdUsuario().toString());
        //mostrar comentarios
        productPresenter.getComentsGen(producto.getIdProducto().toString());
        productPresenter.getComentsClient(producto.getIdProducto().toString());

        rvComents.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));

        rvComents.setHasFixedSize(true);
        rvComents.setAdapter(comentariosAdapter);

        rvComentsClient.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));

        rvComentsClient.setHasFixedSize(true);
        rvComentsClient.setAdapter(comentariosAdapterClient);

        //
        ArrayList<Double> items= getArrayItems(producto.getStockRealProducto());

        //adapter
        SpinnerAdapter customAdapter = new SpinnerAdapter(getContext(),R.layout.custom_spinner_adapter,items);
        spinner.setAdapter(customAdapter);


        observePresenter();


        btnAddTOCart.setOnClickListener(v -> {
            //Primero consultar el id del vendedor del producto
            //mandamos a preguntar si el carrito no esta vacio?
            //al click en agregar al carrito
            //pregunta si la lista esta vacia
            //si esta vacia agrega un nuevo carrito
            //se crea nuevo id carrito
            //luego capturo id del carrito
            //agregar el productocarrito
            //!si es lcarrito no esta vacio?
            //se captura el id del vendedor y preguntar si ese vendedor coincide con el elemento del carrito que ya esta creado
            //preguntar si es el mismo vendedor, si no es el mismo se crea nuevo carrito

            String idUsuario = SharedPref.obtenerIdUsuario(getContext());
            Double cantidad = Double.parseDouble(String.valueOf(spinner.getSelectedItem()));

            carritoPresenter.insertProductInCart(new ProductoCarrito(
                    producto.getIdProducto(),
                    Integer.parseInt(idUsuario),
                    cantidad.intValue()
            ));
            /*carritoPresenter.insertCarrito(new ReqCarrito(
                    Integer.parseInt(idUsuario),
                    producto.getPrecioUnitarioProducto(),
                    0.0,
                    "1"
            ));



            carritoPresenter.getIdCarrito(idUsuario,producto.getIdProducto(), cantidad.intValue());*/


            /*if(carritoPresenter.idUsuario!=null){
                System.out.println("EL id del carrito es: "+carritoPresenter.idUsuario);
                System.out.println("YA SE PUEDE PROCEDER CON EL SIGUIENTE PASO");
            }*/
        });

        btnSalir.setOnClickListener(v -> {

            Navigation.findNavController(getView()).navigate(ProductDetailFragmentDirections.productDetailToHomeLoged());
        });

    }

    public static ArrayList<Double> getArrayItems(Double max){
        ArrayList<Double> items= new ArrayList();
        for (double i=1.0; i<=max; i++){
            items.add(i);
        }
        return items;
    }


    /*public ArrayAdapter<double> getItemSpinner(double max){

    }*/

    public void observePresenter(){

        productPresenter.imagesCompl.observe(getViewLifecycleOwner(), respObtenerImagesDto -> {
            //List<SlideModel> slideModels = new ArrayList();


            /*if(respObtenerImagesDto!=null){
                slideModels.add(new SlideModel(respObtenerImagesDto.getImagenProducto(),"Imagen 1"));
                slideModels.add(new SlideModel(respObtenerImagesDto.getImg1(),"Imagen 2"));
                slideModels.add(new SlideModel(respObtenerImagesDto.getImg2(),"Imagen 3"));
                slideModels.add(new SlideModel(respObtenerImagesDto.getImg3(),"Imagen 4"));
                slideModels.add(new SlideModel(respObtenerImagesDto.getImg4(),"Imagen 5"));
            }else{
                slideModels.add(new SlideModel(producto.getImagenProducto(),"Imagen 1"));
                slideModels.add(new SlideModel("no image","Imagen 2"));
                slideModels.add(new SlideModel("no image","Imagen 3"));
                slideModels.add(new SlideModel("no image","Imagen 4"));
                slideModels.add(new SlideModel("no image","Imagen 5"));
            }*/



            //sliderImageProducts.setImageList(slideModels, true);
        });

        loginPresenter.vendedor.observe(getViewLifecycleOwner(), vendedor -> {
            tvNameVendor.setText("Nombre: "+vendedor.getNombreUsuario());
            tvEmailVendor.setText("Email: "+vendedor.getEmailUsuario());
        });

        productPresenter.comentarios.observe(getViewLifecycleOwner(), comentarios ->{
            comentariosAdapter.updateData(comentarios);
        });

        productPresenter.comentariosClient.observe(getViewLifecycleOwner(), comentarios ->{
            comentariosAdapterClient.updateData(comentarios);
        });

        //ocultar el progress bar

        productPresenter.isLoadingComentsClient.observe(getViewLifecycleOwner(), isLoading -> {
            if(isLoading!=null){
                //cambiar la visibilidad del relative layout
                rlBaseComentarioClient.setVisibility(View.INVISIBLE);
            }

        });

        productPresenter.isLoadingComents.observe(getViewLifecycleOwner(),isLoading ->{
            rlBaseComentario.setVisibility(View.INVISIBLE);
        });





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }
}