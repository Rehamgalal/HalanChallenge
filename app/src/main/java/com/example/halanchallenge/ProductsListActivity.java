package com.example.halanchallenge;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.halanchallenge.model.LoginResponse;
import com.example.halanchallenge.model.ProductsList;
import com.google.gson.Gson;

public class ProductsListActivity extends AppCompatActivity {

    LoginResponse response;
    ProductsList productsList;

    TextView userName, phoneNumber, email;
    RecyclerView productsListRV;
    ImageView userIV,logoutIV;

    ProductsAdapter productsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            response = bundle.getParcelable("RESPONSE");
            productsList = bundle.getParcelable("PRODUCTS");
        }

        Gson gson = new Gson();

        userName = findViewById(R.id.username_tv);
        phoneNumber = findViewById(R.id.phone_number_tv);
        email = findViewById(R.id.email_tv);
        userIV= findViewById(R.id.user_iv);
        logoutIV = findViewById(R.id.logoutIV);


        Glide.with(this).load(response.getProfile().getImage()).placeholder(R.drawable.circle).into(userIV);

        productsListRV = findViewById(R.id.products_list_rv);

        logoutIV.setOnClickListener(view -> finish());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        productsListRV.setLayoutManager(mLayoutManager);

        productsListAdapter = new ProductsAdapter(getBaseContext(), productsList.getProducts());
        productsListRV.setAdapter(productsListAdapter);
        productsListAdapter.setClickListener((view, position) -> {
            Bundle myBundle = new Bundle();
            myBundle.putParcelable("ITEM",productsList.getProducts().get(position));
            Intent myIntent = new Intent(this, ProductDetailsActivity.class).putExtra("PARCELABLE",myBundle);
            myIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            startActivity(myIntent);
        });

        userName.setText(response.getProfile().getName());
        phoneNumber.setText(response.getProfile().getPhone());
        email.setText(response.getProfile().getEmail());
    }


}