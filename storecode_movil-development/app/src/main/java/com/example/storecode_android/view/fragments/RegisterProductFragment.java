package com.example.storecode_android.view.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.Brand;
import com.example.storecode_android.entidades.Category;
import com.example.storecode_android.entidades.Producto;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.adapters.SpinnerAdapter;
import com.example.storecode_android.view.adapters.SpinnerBrandAdapter;
import com.example.storecode_android.view.adapters.SpinnerCategoryAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterProductFragment extends Fragment {

    ImageButton btnReturn;
    int SELECT_PHOTO= 1;
    Uri uri;
    String pathAbsolute;

    ImageView ivPhoto;
    Button btnChooseImage;
    Button btnSave;
    EditText editTxtNameProduct;
    EditText editTxtDescription;
    EditText editTxtStock;
    EditText editTxtPriceProduct;
    ProductPresenter productPresenter;

    Spinner spinner ;
    Spinner spinnerBrand;

    Category category;
    Brand brand;




    public RegisterProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterProductFragment newInstance(String param1, String param2) {
        RegisterProductFragment fragment = new RegisterProductFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //Verifica permisos para Android 6.0+
            int permissionCheck = ContextCompat.checkSelfPermission(
                    getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                Log.i("Mensaje", "No se tiene permiso para leer.");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
            } else {
                Log.i("Mensaje", "Se tiene permiso para leer!");
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnReturn= view.findViewById(R.id.btnReturn);
        ivPhoto= view.findViewById(R.id.ivPhotoProduct);
        //
        editTxtNameProduct = view.findViewById(R.id.editTxtNameProduct);
        editTxtDescription = view.findViewById(R.id.editTxtDescription);
        editTxtPriceProduct = view.findViewById(R.id.editTxtPriceProduct);
        editTxtStock = view.findViewById(R.id.editTxtStock);
        //
        btnChooseImage = view.findViewById(R.id.btnChooseImage);
        btnSave = view.findViewById(R.id.btnSave);

        spinner = view.findViewById(R.id.category_spinner);
        spinnerBrand = view.findViewById(R.id.brand_spinner);

        productPresenter= new ProductPresenter(getContext(), getView());

        btnChooseImage.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, SELECT_PHOTO);
        });

        productPresenter.getAllCategories();
        productPresenter.getAllBrands();


        observer();

        btnSave.setOnClickListener(v ->{

            System.out.println("---------------------Item del spinner-------------------");
            System.out.println(spinner.getSelectedItem());
            //obtener el id de la categoria al parsear el json

            try{
                Gson gson = new Gson();
                category = gson.fromJson(spinner.getSelectedItem().toString(), Category.class);
                if( category!= null ){
                    //impresion de todo el objeto
                    System.out.println("Id categoria"+category.getIdCategoria());
                }
            }catch(JsonSyntaxException e){
                System.err.println("JsonSyntaxException: " + e.getMessage());
            }

            System.out.println(spinnerBrand.getSelectedItem());
            //obtener el id de la categoria al parsear el json

            try{
                Gson gson = new Gson();
                brand = gson.fromJson(spinnerBrand.getSelectedItem().toString(), Brand.class);
                if( brand!= null ){
                    //impresion de todo el objeto
                    System.out.println("Id marca: "+brand.getIdMarca());
                }
            }catch(JsonSyntaxException e){
                System.err.println("JsonSyntaxException: " + e.getMessage());
            }

            Producto producto= new Producto(
                    pathAbsolute,
                    editTxtNameProduct.getText().toString(),
                    editTxtDescription.getText().toString(),
                    Double.parseDouble(editTxtPriceProduct.getText().toString()),
                    Double.parseDouble(editTxtStock.getText().toString()),
                    brand.getIdMarca(),
                    category.getIdCategoria(),
                    Integer.parseInt(SharedPref.obtenerIdUsuario(getContext()))

            );
            if(pathAbsolute!=null){
                productPresenter.uploadProduct(pathAbsolute,producto);

            }else{
                Toast.makeText(getContext(),"No has seleccionado una imagen",Toast.LENGTH_SHORT).show();
            }

        });

        //


        btnReturn.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(RegisterProductFragmentDirections.toProfileLogedFragment());
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data!= null && data.getData()!= null){
            uri = data.getData();

            /*String fileUri = uri.toString();
            String filePath = Uri.parse(fileUri).getPath();*/
            //Converting from file path to file uri:
            File imageFile = new File(getRealPathFromURI(uri));
            pathAbsolute= imageFile.getAbsolutePath();
            System.out.println("Image file");
            System.out.println(imageFile.getAbsolutePath());


            System.out.println("Ruta de la imagen");

            try {

                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                //file = new File(path);
                //ivPhoto.setImageURI(Uri.parse(path));
                ivPhoto.setImageBitmap(bitmap);
                //ivPhoto.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    //metodos para obtener el uri
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    /*public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor =getActivity().getContentResolver().query(uri, projection, null,null,null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }*/
    //end get uri

    //observer
    public void observer(){

        final Observer<List<Category>> categoryObserver = categories -> {
            ArrayList<Category> itemCategories= new ArrayList<>();
            itemCategories.addAll(categories);
            //adapter del spinner
            SpinnerCategoryAdapter customAdapter = new SpinnerCategoryAdapter(getContext(),R.layout.category_spinner_adapter,itemCategories);
            spinner.setAdapter(customAdapter);
        };
        productPresenter.listCategories.observe(getViewLifecycleOwner(), categoryObserver);


        final Observer<List<Brand>> brandObserver = brands -> {
            ArrayList<Brand> itemBrands= new ArrayList<>();
            itemBrands.addAll(brands);
            //adapter del spinner
            SpinnerBrandAdapter customAdapter = new SpinnerBrandAdapter(getContext(),R.layout.category_spinner_adapter,itemBrands);
            spinnerBrand.setAdapter(customAdapter);
        };
        productPresenter.listBrands.observe(getViewLifecycleOwner(), brandObserver);
    }
    //observer
}