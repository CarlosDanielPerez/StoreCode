package com.example.storecode_android.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.storecode_android.Presenter.CarritoPresenter;
import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.Purchase;
import com.example.storecode_android.entidades.RespMyShopping;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.adapters.MyShoppinRecyclerViewAdapter;
import com.example.storecode_android.view.adapters.ProductsInCartAdapter;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MyShoppinFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    public RecyclerView rvMyShopping;

    //declarar presenter y adapter
    private MyShoppinRecyclerViewAdapter myShoppinRecyclerViewAdapter;
    //private ProductPresenter productPresenter;

    private CarritoPresenter carritoPresenter;

    private RelativeLayout rlBaseMyShopping;

    private ImageView btnReturn;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MyShoppinFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MyShoppinFragment newInstance(int columnCount) {
        MyShoppinFragment fragment = new MyShoppinFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_myshopping_list, container, false);

        // Set the adapter
        /*if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyShoppinRecyclerViewAdapter(DummyContent.ITEMS));
        }*/
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rlBaseMyShopping = view.findViewById(R.id.rlBaseMyShopping);
        //checar si manda error
        //productPresenter = new ProductPresenter(getContext());
        //productPresenter = new ProductPresenter(getContext(),getView());
        carritoPresenter = new CarritoPresenter(getActivity());
        rvMyShopping= view.findViewById(R.id.list);
        btnReturn= view.findViewById(R.id.btnReturn);
        //SearchView searchView = view.findViewById(R.id.activity_preciadorunicomodelo_searchView2);

        Integer idUser = Integer.parseInt(SharedPref.obtenerIdUsuario(getContext()));


        carritoPresenter.refreshMyShopping(String.valueOf(idUser));
        rvMyShopping.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
        myShoppinRecyclerViewAdapter = new MyShoppinRecyclerViewAdapter();
        rvMyShopping.setHasFixedSize(true);
        rvMyShopping.setAdapter(myShoppinRecyclerViewAdapter);

        observer();

        btnReturn.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(MyShoppinFragmentDirections.toProfileLogedFragment());
        });
    }

    public void observer(){

        // Create the observer which updates the UI.
        final Observer<List<Purchase>> myShoppingObserver = new Observer<List<Purchase>>() {
            @Override
            public void onChanged(List<Purchase> myShoppings) {
                myShoppinRecyclerViewAdapter.updateData(myShoppings);
            }

        };
        carritoPresenter.mShopping.observe(getViewLifecycleOwner(), myShoppingObserver);

        carritoPresenter.isLoadingMyShopping.observe(getViewLifecycleOwner(),new Observer<Boolean>(){

            @Override
            public void onChanged(Boolean isLoadingMyShopping) {
                if(isLoadingMyShopping!=null){
                    //cambiar la visibilidad del relative layout
                    //rlBaseHome.setVisibility(View.INVISIBLE);
                    rlBaseMyShopping.setVisibility(View.INVISIBLE);
                }

            }
        });
    }
}