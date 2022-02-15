package com.example.halanchallenge.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arindicatorview.ARIndicatorView;
import com.example.halanchallenge.adapter.ImagesAdapter;
import com.example.halanchallenge.R;
import com.example.halanchallenge.model.Product;

public class ProductDetailsFragment extends Fragment {

    Product product;

    TextView description,title,price;
    Button back;
    ARIndicatorView indicatorView;

    RecyclerView imagesListRV;

    ImagesAdapter imagesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = getArguments().getParcelable("ITEM");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        description = view.findViewById(R.id.product_description_tv);
        title = view.findViewById(R.id.product_title_tv);
        back = view.findViewById(R.id.materialButton);
        price = view.findViewById(R.id.product_price_tv);
        imagesListRV = view.findViewById(R.id.product_images_banner);
        indicatorView = view.findViewById(R.id.ar_indicator);


        back.setOnClickListener(v -> requireActivity().onBackPressed());
        description.setText(product.getDeal_description());
        title.setText(product.getName_ar());
        description.setMovementMethod(new ScrollingMovementMethod());
        price.setText("كاش"+"           "+product.getPrice()+"جنيه");

        imagesAdapter = new ImagesAdapter(requireContext(),product.getImages());
        imagesListRV.setAdapter(imagesAdapter);

        indicatorView.attachTo(imagesListRV,true);

    }
}