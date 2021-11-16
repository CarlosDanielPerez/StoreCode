package com.example.storecode_android.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.storecode_android.Presenter.LoginPresenter;
import com.example.storecode_android.R;
import com.google.android.material.textfield.TextInputEditText;
import com.subhrajyoti.passwordview.PasswordView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAccountFragment extends Fragment {

    private LoginPresenter loginPresenter;
    public Button btnCreateAccount;
    public ImageButton btnReturn;
    public EditText etUserName;
    public EditText etApellidoPaterno;
    public EditText etUserEmail;
    public PasswordView etContrasenia;
    public PasswordView etConfirmaContrasenia;




    public CreateAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateAccountFragment newInstance(String param1, String param2) {
        CreateAccountFragment fragment = new CreateAccountFragment();
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
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnCreateAccount = view.findViewById(R.id.btnCreateAccount);
        btnReturn= view.findViewById(R.id.btnReturn);
        etUserName = view.findViewById(R.id.editTextUserName);
        etApellidoPaterno= view.findViewById(R.id.editTextApellidoPaterno);
        etUserEmail = view.findViewById(R.id.editTextUserEmail);
        etContrasenia = view.findViewById(R.id.editTextPass);
        etConfirmaContrasenia = view.findViewById(R.id.editTextPassConfirm);


        loginPresenter= new LoginPresenter(getActivity());

        btnCreateAccount.setOnClickListener(v->{
            loginPresenter.validaCreateAccount();
        });

        btnReturn.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(CreateAccountFragmentDirections.toLoginFragment());
        });

    }
}