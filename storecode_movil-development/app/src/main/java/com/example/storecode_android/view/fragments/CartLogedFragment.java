package com.example.storecode_android.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.storecode_android.Presenter.CarritoPresenter;
import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ProductInCard;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.adapters.ProductsInCartAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartLogedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartLogedFragment extends Fragment {
    public RecyclerView rvProductsInCart;
    Button btnPayment;
    RecyclerView rvCartLoged;
    public TextView tvResults;
    public List<RespObtenerProducto> respuesta;

    //declarar presenter y adapter
    private ProductsInCartAdapter productsInCartAdapter;
    private ProductPresenter productPresenter;

    public CarritoPresenter carritoPresenter;

    private RelativeLayout rlBaseOnCart;

    public CartLogedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment CartLogedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartLogedFragment newInstance(String param1, String param2) {
        CartLogedFragment fragment = new CartLogedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_cart_loged, container, false);
        rvCartLoged = vista.findViewById(R.id.rvCartLoged);
        btnPayment = vista.findViewById(R.id.btnPayment);

        /*btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Navigation.findNavController(getView()).navigate(CartLogedFragmentDirections.mostrarmodospago());

            }
        });*/


        // Inflate the layout for this fragment


        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rlBaseOnCart = view.findViewById(R.id.rlBaseCartLoged);

        //checar si manda error
        //productPresenter = new ProductPresenter(getContext());
        productPresenter = new ProductPresenter(getContext(), getView());
        carritoPresenter = new CarritoPresenter(getActivity());
        rvProductsInCart= view.findViewById(R.id.rvCartLoged);





        Integer idUser = Integer.parseInt(SharedPref.obtenerIdUsuario(getContext()));

        System.out.println("------Products in cart-------");
        System.out.println(idUser);



        carritoPresenter.refreshProductsInCart(String.valueOf(idUser));
        rvProductsInCart.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
        productsInCartAdapter = new ProductsInCartAdapter( getActivity(), carritoPresenter);
        rvProductsInCart.setHasFixedSize(true);
        rvProductsInCart.setAdapter(productsInCartAdapter);
        observer();
    }

    public void observer(){
        // Create the observer which updates the UI.
        final Observer<List<ProductInCard>> productsObserver = products -> {
            productsInCartAdapter.updateData(products);
            products.forEach(productInCard -> {
                System.out.println("Products in cart");
                System.out.println(productInCard);
            });
        };
        carritoPresenter.listProductsInCart.observe(getViewLifecycleOwner(), productsObserver);


        carritoPresenter.isLoadingProductsInCart.observe(getViewLifecycleOwner(),new Observer<Boolean>(){

            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading!=null){
                    //cambiar la visibilidad del relative layout
                    rlBaseOnCart.setVisibility(View.INVISIBLE);
                }

            }
        });


    }










}