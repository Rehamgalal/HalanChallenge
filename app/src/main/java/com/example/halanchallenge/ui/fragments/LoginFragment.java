package com.example.halanchallenge.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.halanchallenge.R;
import com.example.halanchallenge.ui.viewmodel.MainViewModel;


public class LoginFragment extends Fragment {

    EditText userNameEt, passwordEt;
    Button loginBtn;

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
        MainViewModel model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        userNameEt = view.findViewById(R.id.username_et);
        passwordEt = view.findViewById(R.id.password_et);
        loginBtn = view.findViewById(R.id.login_button);
        loginBtn.setOnClickListener(v -> model.login(userNameEt.getText().toString(),passwordEt.getText().toString()));
        model.getResult().observe(getViewLifecycleOwner(), listActivityDataItem -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("RESPONSE",listActivityDataItem.getLoginResponse());
            bundle.putParcelable("PRODUCTS",listActivityDataItem.getProductsList());
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_productListFragment,bundle);
        });
    }
}