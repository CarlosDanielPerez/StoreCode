package com.example.storecode_android.view.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.storecode_android.BuildConfig;
import com.example.storecode_android.Presenter.LoginPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.view.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.log4j.Logger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final Logger log = LogFile.getLogger(LoginFragment.class);
    private LoginPresenter loginPresenter;
    public Switch mSwitch;
    public EditText etIdUsuario, etContrasenia;
    private TextView tvVersion;
    public Button btnEntrar;
    public Button btnCreateAccount;

    public TextView textInicio, textSingle;
    public TextInputLayout textLayout;


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        log.info("--onCreate--");
        super.onCreate(savedInstanceState);
        //Asignamos la orientación del disposito


        //Construimos el presentador
        loginPresenter = new LoginPresenter(getActivity());
        //Inicializamos los componentes
        mSwitch = view.findViewById(R.id.inicio_sesion_switch);
        etIdUsuario = view.findViewById(R.id.editTextUser);
        etContrasenia = view.findViewById(R.id.editTextPass);

        textInicio = view.findViewById(R.id.TextInicio);
        Typeface typeface0 = ResourcesCompat.getFont(getContext(), R.font.telcel_nova_media_otf);
        textInicio.setTypeface(typeface0);

        textSingle = view.findViewById(R.id.textSingle);
        Typeface typefaceX = ResourcesCompat.getFont(getContext(), R.font.telcel_nova_media_otf);
        textSingle.setTypeface(typefaceX);

        textLayout = view.findViewById(R.id.textLayout);
        Typeface typeface2 = ResourcesCompat.getFont(getContext(), R.font.telcel_nova_media_otf);
        textLayout.setTypeface(typeface2);

        etContrasenia = view.findViewById(R.id.editTextPass);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.telcel_nova_media_otf);
        etContrasenia.setTypeface(typeface);

        mSwitch = view.findViewById(R.id.inicio_sesion_switch);
        Typeface typeface3 = ResourcesCompat.getFont(getContext(), R.font.telcel_nova_media_otf);
        mSwitch.setTypeface(typeface3);

        tvVersion = view.findViewById(R.id.version_app);
        String versionApp = "V " + BuildConfig.VERSION_NAME;
        tvVersion.setText(versionApp);

        etContrasenia.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                loginPresenter.cambiarColorBoton();
            }
        });

        etIdUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                loginPresenter.cambiarColorBoton();
            }
        });
        etContrasenia.setOnClickListener(v -> loginPresenter.cambiarColorBoton());

        //Agregamos un listener al botón validaUsuarioYPassword
        btnEntrar = view.findViewById(R.id.buttonLogin);
        btnCreateAccount = view.findViewById(R.id.btnCreateAccount);

        btnEntrar.setOnClickListener(v ->
                loginPresenter.validaUsuarioYContrasenia()
        );

        btnCreateAccount.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(LoginFragmentDirections.toCreateAccountFragment());
        });

        etContrasenia.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE && btnEntrar.isEnabled()) {
                    btnEntrar.performClick();
                    return true;
                }
                return false;
            }
        });
    }
}