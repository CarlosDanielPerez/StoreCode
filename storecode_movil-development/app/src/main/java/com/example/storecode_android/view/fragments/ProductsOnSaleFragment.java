package com.example.storecode_android.view.fragments;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.adapters.ModeloAdapterInstaladas;
import com.example.storecode_android.view.adapters.ProductsOnSaleAdapter;
import com.example.storecode_android.view.adapters.ProductsOnSaleListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsOnSaleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsOnSaleFragment extends Fragment implements ProductsOnSaleListener {

    public RecyclerView recyclerView;
    public List<RespObtenerProducto> respuesta;

    ImageButton btnEdit, btnDelete, btnAddImages;

    //declarar presenter y adapter
    private ProductsOnSaleAdapter productsOnSaleAdapter;
    private ProductPresenter productPresenter;

    private RelativeLayout rlBaseOnSale;

    ImageButton btnReturn;



    public ProductsOnSaleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     */
    // TODO: Rename and change types and number of parameters
    public static ProductsOnSaleFragment newInstance(String param1, String param2) {
        ProductsOnSaleFragment fragment = new ProductsOnSaleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products_on_sale, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rlBaseOnSale = view.findViewById(R.id.rlBaseProductsOnSale);
        //checar si manda error
        //productPresenter = new ProductPresenter(getContext());
        productPresenter = new ProductPresenter(getContext(), getView());
        recyclerView= view.findViewById(R.id.rv_products_onSale);
        btnReturn = view.findViewById(R.id.btnReturn);

       /* btnEdit = view.findViewById(R.id.btnEdit);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnAddImages = view.findViewById(R.id.btnAddImages);*/
        //SearchView searchView = view.findViewById(R.id.activity_preciadorunicomodelo_searchView2);

        Integer idUser = Integer.parseInt(SharedPref.obtenerIdUsuario(getContext()));

        System.out.println("------Products on sale-------");
        System.out.println(idUser);

        productPresenter.refreshProductsOnSale(idUser.toString());



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
        productsOnSaleAdapter = new ProductsOnSaleAdapter(this, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(productsOnSaleAdapter);


        btnReturn.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(ProductsOnSaleFragmentDirections.actionProductsOnSaleFragmentToNavProfileLogedFragment());
        });

        observePresenter();


    }

    public void observePresenter(){

        // Create the observer which updates the UI.
        final Observer<List<RespGetProductByUser>> productsObserver = new Observer<List<RespGetProductByUser>>() {
            @Override
            public void onChanged(List<RespGetProductByUser> products) {
                productsOnSaleAdapter.updateData(products);
            }

        };
        productPresenter.listProductsOnSale.observe(getViewLifecycleOwner(), productsObserver);

        productPresenter.isLoadingOnSale.observe(getViewLifecycleOwner(),new Observer<Boolean>(){

            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading!=null){
                    //cambiar la visibilidad del relative layout
                    //rlBaseHome.setVisibility(View.INVISIBLE);
                    rlBaseOnSale.setVisibility(View.INVISIBLE);
                }

            }
        });
    }
}