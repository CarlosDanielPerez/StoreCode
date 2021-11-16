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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.storecode_android.Presenter.UserPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.Purchase;
import com.example.storecode_android.entidades.PurchasedItem;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.view.adapters.ModeloAdapterPurchasedItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PurchasedDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PurchasedDetailFragment extends Fragment {

    Purchase purchase;
    ImageView btnReturn;
    TextView tvClaveTransaccion;
    TextView tvPurchaseDate;
    TextView tvMonto;

    RelativeLayout rlBasePurchaseDetail;
    RecyclerView listItems;

    //presenter
    UserPresenter userPresenter;
    ModeloAdapterPurchasedItem modeloAdapterPurchasedItem;


    public PurchasedDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PurchasedDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PurchasedDetailFragment newInstance(String param1, String param2) {
        PurchasedDetailFragment fragment = new PurchasedDetailFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_purchased_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReturn=view.findViewById(R.id.btnReturn);
        tvClaveTransaccion=view.findViewById(R.id.tvClaveTransaccion);
        tvPurchaseDate=view.findViewById(R.id.tvPurchaseDate);
        tvMonto=view.findViewById(R.id.tvMonto);

        rlBasePurchaseDetail = view.findViewById(R.id.rlBasePurchaseDetail);
        listItems = view.findViewById(R.id.listItems);

        purchase= PurchasedDetailFragmentArgs.fromBundle(getArguments()).getPurchase();

        userPresenter= new UserPresenter(getActivity());

        tvClaveTransaccion.setText("Clave Transacción: "+purchase.getClaveTransaccion());
        tvPurchaseDate.setText("Fecha: "+purchase.getFechaVenta());
        tvMonto.setText("Monto Total: $"+purchase.getTotalVendido());

        userPresenter.getPurchasedItem(purchase.getFolioVenta().toString());

        listItems.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL, false));
        modeloAdapterPurchasedItem = new ModeloAdapterPurchasedItem(getActivity());
        listItems.setAdapter(modeloAdapterPurchasedItem);
        listItems.setHasFixedSize(true);

        observer();

        //Navega hacia la pantalla MyShopping
        btnReturn.setOnClickListener(v-> Navigation.findNavController(getView()).navigate(PurchasedDetailFragmentDirections.toMyShoppingFragment()));


    }

    public void observer(){

        // Create the observer which updates the UI.
        final Observer<List<PurchasedItem>> itemsObserver = items -> modeloAdapterPurchasedItem.updateData(items);

        userPresenter.purchasedItemList.observe(getViewLifecycleOwner(), itemsObserver);

        userPresenter.isLoading.observe(getViewLifecycleOwner(), isLoading -> {
            if(isLoading!=null){
                //cambiar la visibilidad del relative layout
                //rlBaseHome.setVisibility(View.INVISIBLE);
                rlBasePurchaseDetail.setVisibility(View.INVISIBLE);
            }

        });
    }
}