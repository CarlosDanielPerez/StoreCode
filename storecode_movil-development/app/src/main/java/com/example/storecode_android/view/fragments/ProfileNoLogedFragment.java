package com.example.storecode_android.view.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.storecode_android.R;
import com.example.storecode_android.view.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileNoLogedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileNoLogedFragment extends Fragment {
    Button btnLogin;

    public ProfileNoLogedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileNoLogedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileNoLogedFragment newInstance(String param1, String param2) {
        ProfileNoLogedFragment fragment = new ProfileNoLogedFragment();
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
        return inflater.inflate(R.layout.fragment_profile_no_loged, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogin= view.findViewById(R.id.btnLogin);



        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //NavDirections navDirection = ProfileNoLogedFragmentDirections
               //Intent intent = new Intent(getContext(), LoginActivity.class);
                //startActivity(intent);
               //Navegacion hacia la pantalla de Login
               /*NavController navController = Navigation.findNavController(getActivity(), R.id.fragContent);
               navController.navigate(R.id.toLoginActivity);*/
               Navigation.findNavController(getView()).navigate(ProfileNoLogedFragmentDirections.toLoginActivity());
               /*Intent intent = new Intent(getContext(), LoginActivity.class);
               startActivity(intent);*/



            }
        });
    }
}