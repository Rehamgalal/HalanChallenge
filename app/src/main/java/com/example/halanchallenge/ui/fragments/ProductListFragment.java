package com.example.halanchallenge.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.halanchallenge.adapter.ProductsAdapter;
import com.example.halanchallenge.R;
import com.example.halanchallenge.model.LoginResponse;
import com.example.halanchallenge.model.ProductsList;
import com.example.halanchallenge.ui.MainActivity;


public class ProductListFragment extends Fragment {

    LoginResponse response;
    ProductsList productsList;

    TextView userName, phoneNumber, email;
    RecyclerView productsListRV;
    ImageView userIV,logoutIV;

    ProductsAdapter productsListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            response = getArguments().getParcelable("RESPONSE");
            productsList = getArguments().getParcelable("PRODUCTS");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userName = view.findViewById(R.id.username_tv);
        phoneNumber = view.findViewById(R.id.phone_number_tv);
        email = view.findViewById(R.id.email_tv);
        userIV= view.findViewById(R.id.user_iv);
        logoutIV = view.findViewById(R.id.logoutIV);

        Glide.with(this).load(response.getProfile().getImage()).placeholder(R.drawable.circle).into(userIV);

        productsListRV = view.findViewById(R.id.products_list_rv);

        logoutIV.setOnClickListener(v -> requireActivity().onBackPressed());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(requireContext());
        productsListRV.setLayoutManager(mLayoutManager);

        productsListAdapter = new ProductsAdapter(requireContext(), productsList.getProducts());
        productsListRV.setAdapter(productsListAdapter);
        productsListAdapter.setClickListener((v, position) -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("ITEM",productsList.getProducts().get(position));
            Navigation.findNavController(view).navigate(R.id.action_productListFragment_to_productDetailsFragment,bundle);

        });

        userName.setText(response.getProfile().getName());
        phoneNumber.setText(response.getProfile().getPhone());
        email.setText(response.getProfile().getEmail());
    }
}