package com.example.storecode_android.view.fragments;

import android.Manifest;
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
import com.example.storecode_android.entidades.ReqUpdateProduct;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.adapters.SpinnerBrandAdapter;
import com.example.storecode_android.view.adapters.SpinnerCategoryAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateProductFragment extends Fragment {

   RespGetProductByUser producto;

   ImageView ivPhotoProduct;
   Button btnChooseImage;
   Button btnSave;
   EditText editTextNameProduct;

   EditText editTextDescriptionProduct;
   EditText editTextPriceProduct;
   EditText editTextQuantityProduct;
   ImageButton btnReturn;


   Spinner categorySpinner;
   Spinner brandSpinner;

   ProductPresenter productPresenter;

   Category category;
   Brand brand;

    int SELECT_PHOTO= 1;
    Uri uri;
    String pathAbsolute;





    public UpdateProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateProductFragment newInstance(String param1, String param2) {
        UpdateProductFragment fragment = new UpdateProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_update_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        producto = UpdateProductFragmentArgs.fromBundle(getArguments()).getProduct();
        ivPhotoProduct = view.findViewById(R.id.ivPhotoProduct);
        btnChooseImage = view.findViewById(R.id.btnChooseImage);
        btnSave = view.findViewById(R.id.btnSave);
        editTextNameProduct = view.findViewById(R.id.editTextNameProduct);
        editTextDescriptionProduct = view.findViewById(R.id.editTextDescriptionProduct);
        editTextPriceProduct = view.findViewById(R.id.editTextPriceProduct);
        editTextQuantityProduct = view.findViewById(R.id.editTextQuantityProduct);
        categorySpinner = view.findViewById(R.id.category_spinner);
        brandSpinner = view.findViewById(R.id.brand_spinner);
        producto= UpdateProductFragmentArgs.fromBundle(getArguments()).getProduct();
        btnReturn = view.findViewById(R.id.btnReturn);

        productPresenter= new ProductPresenter(getContext(), getView());

        productPresenter.getAllCategories();
        productPresenter.getAllBrands();

        observer();

        if(producto!=null){
            Picasso.get().load(producto.getImagenProducto()).into(ivPhotoProduct);
            editTextNameProduct.setText(producto.getNombreProducto());
            editTextDescriptionProduct.setText(producto.getDesProducto());
            editTextPriceProduct.setText(String.valueOf(producto.getPrecioUnitarioProducto()));
            editTextQuantityProduct.setText(String.valueOf(producto.getStockRealProducto()));
            Picasso.get().load(Uri.parse(producto.getImagenProducto())).into(ivPhotoProduct);
        }

        btnChooseImage.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, SELECT_PHOTO);
        });


        btnSave.setOnClickListener(v ->{

            System.out.println("---------------------Item del spinner-------------------");
            System.out.println(categorySpinner.getSelectedItem());
            //obtener el id de la categoria al parsear el json

            try{
                Gson gson = new Gson();
                category = gson.fromJson(categorySpinner.getSelectedItem().toString(), Category.class);
                if( category!= null ){
                    //impresion de todo el objeto
                    System.out.println("Id categoria"+category.getIdCategoria());
                }
            }catch(JsonSyntaxException e){
                System.err.println("JsonSyntaxException: " + e.getMessage());
            }

            System.out.println(brandSpinner.getSelectedItem());
            //obtener el id de la categoria al parsear el json

            try{
                Gson gson = new Gson();
                brand = gson.fromJson(brandSpinner.getSelectedItem().toString(), Brand.class);
                if( brand!= null ){
                    //impresion de todo el objeto
                    System.out.println("Id marca: "+brand.getIdMarca());
                }
            }catch(JsonSyntaxException e){
                System.err.println("JsonSyntaxException: " + e.getMessage());
            }

            ReqUpdateProduct productoReq= new ReqUpdateProduct(
                    editTextNameProduct.getText().toString(),
                    editTextDescriptionProduct.getText().toString(),
                    Double.parseDouble(editTextPriceProduct.getText().toString()),
                    producto.getImagenProducto(),
                    Double.parseDouble(editTextQuantityProduct.getText().toString()),
                    0,
                    0


            );


            productPresenter.updateProducts(producto.getIdProducto().toString(),productoReq);

            /*if(pathAbsolute!=null){


            }else{
                Toast.makeText(getContext(),"No has seleccionado una imagen",Toast.LENGTH_SHORT).show();
            }*/

        });
          /*
     {
        "nombreProducto": "Celular Xiaomi",
        "desProducto": "Celular con sistema operativo Android",
        "precioUnitarioProducto": 4500,
        "imagenProducto": "http://192.168.1.66:3000/public/products/celular.jpeg-1617207150442.jpeg",
        "stockRealProducto": 8.00,
        "idMarca": 0,
        "idCategoria": 0
    }
     */

        btnReturn.setOnClickListener(v->{
            //Navigation.findNavController(getView()).navigate();
            Navigation.findNavController(getView()).navigate(UpdateProductFragmentDirections.actionUpdateProductFragmentToProductsOnSaleFragment());
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
                ivPhotoProduct.setImageBitmap(bitmap);
                //ivPhoto.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


    //observer
    public void observer(){

        final Observer<List<Category>> categoryObserver = categories -> {
            ArrayList<Category> itemCategories= new ArrayList<>();
            itemCategories.addAll(categories);
            //adapter del spinner
            SpinnerCategoryAdapter customAdapter = new SpinnerCategoryAdapter(getContext(),R.layout.category_spinner_adapter,itemCategories);
            categorySpinner.setAdapter(customAdapter);
        };
        productPresenter.listCategories.observe(getViewLifecycleOwner(), categoryObserver);


        final Observer<List<Brand>> brandObserver = brands -> {
            ArrayList<Brand> itemBrands= new ArrayList<>();
            itemBrands.addAll(brands);
            //adapter del spinner
            SpinnerBrandAdapter customAdapter = new SpinnerBrandAdapter(getContext(),R.layout.category_spinner_adapter,itemBrands);
            brandSpinner.setAdapter(customAdapter);
        };
        productPresenter.listBrands.observe(getViewLifecycleOwner(), brandObserver);
    }
    //observer

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
}