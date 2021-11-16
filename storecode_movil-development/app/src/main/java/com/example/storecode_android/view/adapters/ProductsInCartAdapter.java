package com.example.storecode_android.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.Presenter.CarritoPresenter;
import com.example.storecode_android.Presenter.LoginPresenter;
import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ProductInCard;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.entidades.RespUserData;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;

import com.example.storecode_android.view.fragments.CartLogedFragmentDirections;
import com.example.storecode_android.view.fragments.ProductDetailFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductsInCartAdapter extends RecyclerView.Adapter<HolderProductsInCart> implements Filterable {

    private static final Logger log = LogFile.getLogger(ModeloAdapterInstaladas.class);


    private final ArrayList<ProductInCard> modeloList = new ArrayList<ProductInCard>();
    private  final ArrayList<ReqItemProduct> reqItemProductList = new ArrayList<>();

    private Double subtotal=0.0;
    private Double total= 0.0;
    public static final int REQUEST_CODE = 1;
    //public key del vendedor
    public static final String PUBLIC_KEY="TEST-3aa335c4-d833-47aa-b31b-638832e27e2a";

    public FragmentActivity context;

    private CarritoPresenter carritoPresenter;
    private LoginPresenter loginPresenter;
    //private List<ProductInCard> mFilteredList = modeloList;

    ProductPresenter productPresenter;

    String userDataString ;
    RespUserData userData ;
    RespUserData vendedor;



    public ProductsInCartAdapter(FragmentActivity context, CarritoPresenter carritoPresenter) {

        this.context = context;
        this.carritoPresenter = carritoPresenter;
        this.loginPresenter= new LoginPresenter(context);

        this.userDataString =SharedPref.obtenerUsuario(context);

        this.userData = new Gson().fromJson(userDataString,RespUserData.class);
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public HolderProductsInCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_producs_in_cart, parent, false);

        //filtrarModelos();
        return new HolderProductsInCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductsInCart holder, int position) {
        productPresenter = new ProductPresenter();


        ProductInCard producto = modeloList.get(position);

        Picasso.get().load(producto.getImagenProducto()).into(holder.ivProductOnCart);
        holder.tvNameOnCart.setText(producto.getNombreProducto());
        holder.tvDescriptionOnCart.setText(producto.getDesProducto());
        holder.tvPriceOnCart.setText("$ "+producto.getPrecioUnitarioProducto().toString());
        holder.tvStockOnCart.setText("Cantidad: ");

        ArrayList<Double> items= ProductDetailFragment.getArrayItems(producto.getStockRealProducto());
        //adapter
        SpinnerAdapter customAdapter = new SpinnerAdapter(context,R.layout.custom_spinner_adapter,items);

        holder.spinner_cantidad.setAdapter(customAdapter);
        Double cantidad = Double.parseDouble(holder.spinner_cantidad.getSelectedItem().toString());

        Double quantity = Double.parseDouble(holder.spinner_cantidad.getSelectedItem().toString());

        reqItemProductList.add(new ReqItemProduct(
                producto.getIdProducto(),
                producto.getIdproductocarrito(),
                producto.getIdCarrito(),
                producto.getIdUsuario(),
                producto.getNombreProducto(),
                producto.getDesProducto(),
                producto.getImagenProducto(),
                producto.getPrecioUnitarioProducto(),
                quantity.intValue(),
                "test_user_91638065@testuser.com",
                //reemplazar por el user(comprador) email
                //userData.getEmailUsuario(),
                "Vacio"
                //userData.getEmailUsuario()

        ));



        /*System.out.println("Encargaste"+ cantidad+ producto.getNombreProducto());
        subtotal= producto.getPrecioUnitarioProducto()*cantidad;
        total =total+ subtotal;*/

        holder.tvSubtotal.setText("Subtotal: $"+subtotal.toString());

/*
        System.out.println("Total:"+ total);

        System.out.println("---------EN stock---------------");
        System.out.println("El stock es:"+producto.getStockRealProducto());*/


        //Manejar la lógica de visibilidad del boton para pagar
        //holder.btnPayment.setVisibility(View.VISIBLE);
        try{
            if(producto.getIdUsuario()!=modeloList.get(position+1).getIdUsuario()){

                System.out.println("----Subtotal-----");
                System.out.println(total);
                holder.btnPayment.setVisibility(View.VISIBLE);
                total=0.0;
            }

        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println("----TOTAL-----");
            System.out.println(total);
            holder.btnPayment.setVisibility(View.VISIBLE);

        }



        //Eliminar un producto del carrito
        holder.btnDeleteFromCart.setOnClickListener(v->{
            deleteFromCard(producto);
        });


        //
        holder.btnPayment.setOnClickListener(v->{


            Navigation.findNavController(v).navigate(CartLogedFragmentDirections.mostrarmodospago());




            /*ArrayList<ReqItemProduct> reqItemProducts = new ArrayList<>();

            //Obtener el email del cliente
            String response = SharedPref.obtenerUsuario(context);
            RespUserData resp = new Gson().fromJson(response, RespUserData.class);
            System.out.println("Email: "+resp.getEmailUsuario());
            //Obtener el email del cliente

            reqItemProductList.forEach(reqItemProduct -> {
                if(reqItemProduct.getIdVendedor()==producto.getIdUsuario()){
                    reqItemProducts.add(reqItemProduct);
                }
            });

            Integer idVendedor= reqItemProducts.get(0).getIdVendedor();

            loginPresenter.getUserById(idVendedor.toString());

            //asignar vendedor
            vendedor= getCurrentUser();

            reqItemProducts.forEach(reqItemProduct -> {
                reqItemProduct.setAccessToken(vendedor.getAccessTokenMpago());
            });

            System.out.println("Request enviado al createIdPreference: "+reqItemProducts.toString());
            carritoPresenter.createIdPreference(context,reqItemProducts);



            //carritoPresenter.refreshProductsInCart(userData.getIdUsuario().toString());

            */
        });

        holder.spinner_cantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Double cantidad = Double.parseDouble(holder.spinner_cantidad.getSelectedItem().toString());
                System.out.println("---------------OnItemSelectedItem-----------------");
                System.out.println("Position: "+position);
                System.out.println("Cantidad: "+cantidad);

                Double total = Double.parseDouble(holder.spinner_cantidad.getSelectedItem().toString())*producto.getPrecioUnitarioProducto();

                String value = String.valueOf(total);
                holder.tvSubtotal.setText("Subtotal: $"+value);

                //AtomicReference<Double> totalAux= new AtomicReference<>(0.0);
                reqItemProductList.forEach(reqItemProduct -> {
                    if(reqItemProduct.getIdProducto()==producto.getIdProducto()){
                        reqItemProduct.setQuantity(cantidad.intValue());
                    }

                });


                

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }




    @Override
    public int getItemCount() {
        System.out.println("---------getItemCount de products in cart------------");
        System.out.println(modeloList.size());
        return modeloList.size();
    }


    public void updateData(List<ProductInCard> data) {
        System.out.println("------------en el update data de ProductInCart---------");

        modeloList.clear();
        modeloList.addAll(data);
        System.out.println(modeloList);
        notifyDataSetChanged();
    }

    public void deleteFromCard(ProductInCard producto){
        Integer idUser = Integer.parseInt(SharedPref.obtenerIdUsuario(context));
        carritoPresenter.deleteProductFromCart(producto.getIdproductocarrito().toString());
        carritoPresenter.refreshProductsInCart(idUser.toString());
    }

    public RespUserData getCurrentUser(){
        String userString = SharedPref.obtenerVendedor(context);
        return new Gson().fromJson(userString,RespUserData.class);
    }


}
