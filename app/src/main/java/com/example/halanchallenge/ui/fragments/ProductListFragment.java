package com.example.halanchallenge.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.halanchallenge.adapter.ProductsAdapter;
import com.example.halanchallenge.R;
import com.example.halanchallenge.databinding.FragmentProductListBinding;
import com.example.halanchallenge.model.LoginResponse;
import com.example.halanchallenge.model.Product;
import com.example.halanchallenge.model.ProductsList;

import java.util.List;
import java.util.Objects;


public class ProductListFragment extends Fragment {

    private FragmentProductListBinding binding;
    private LoginResponse response;
    private ProductsList productsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            response = getArguments().getParcelable("RESPONSE");
            productsList = getArguments().getParcelable("PRODUCTS");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.setItem(response.getProfile());
        binding.setProducts(productsList);
    }

    @BindingAdapter("recyclerProducts")
    public static void recyclerViewBinder(RecyclerView recyclerView, List<Product> products) {
        ProductsAdapter productsListAdapter = new ProductsAdapter(products);
        recyclerView.setAdapter(productsListAdapter);
        productsListAdapter.setClickListener(product -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("ITEM", product);
            NavController navController = Navigation.findNavController(recyclerView);
            navController.navigate(R.id.action_productListFragment_to_productDetailsFragment, bundle);
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
    }

}