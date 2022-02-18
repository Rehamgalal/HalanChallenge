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

import com.example.halanchallenge.R;
import com.example.halanchallenge.databinding.FragmentLoginBinding;
import com.example.halanchallenge.ui.viewmodel.MainViewModel;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel model = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.loginButton.setOnClickListener(v -> model.login(binding.usernameEt.getText().toString(),binding.passwordEt.getText().toString()));
        model.getResult().observe(getViewLifecycleOwner(), listActivityDataItem -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("RESPONSE",listActivityDataItem.getLoginResponse());
            bundle.putParcelable("PRODUCTS",listActivityDataItem.getProductsList());
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_productListFragment,bundle);
        });
    }
}