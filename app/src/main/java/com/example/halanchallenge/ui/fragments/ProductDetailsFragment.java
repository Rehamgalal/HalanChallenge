package com.example.halanchallenge.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arindicatorview.ARIndicatorView;
import com.example.halanchallenge.adapter.ImagesAdapter;
import com.example.halanchallenge.databinding.FragmentProductDetailsBinding;
import com.example.halanchallenge.model.Product;

import java.util.List;

public class ProductDetailsFragment extends Fragment {

    private Product product;

    private FragmentProductDetailsBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = getArguments().getParcelable("ITEM");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setItem(product);
        binding.materialButton.setOnClickListener(v -> requireActivity().onBackPressed());
        binding.productDescriptionTv.setMovementMethod(new ScrollingMovementMethod());
    }

    @BindingAdapter({"recyclerImages","indicator"})
    public static void recyclerViewBinder(RecyclerView recyclerView, List<String> images,ARIndicatorView indicatorView){
        ImagesAdapter imagesAdapter = new ImagesAdapter(images);
        recyclerView.setAdapter(imagesAdapter);
        indicatorView.attachTo(recyclerView,true);
    }
}