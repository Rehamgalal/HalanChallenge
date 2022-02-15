package com.example.halanchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.example.halanchallenge.model.LoginResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

public class ProductsListActivity extends AppCompatActivity {

    LoginResponse response;

    TextView userName, phoneNumber, email;
    RecyclerView productsListRV;
    ImageView userIV,logoutIV;
    ProductsList productsList;

    ProductsAdapter productsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            response = bundle.getParcelable("RESPONSE");
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

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.get("https://assessment-sn12.halan.io/products")
                .addHeaders("Authorization", "Bearer " + response.getToken())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        productsList = gson.fromJson(String.valueOf(response), ProductsList.class);
                        productsListAdapter = new ProductsAdapter(getBaseContext(), productsList.products);
                        productsListAdapter.notifyDataSetChanged();
                        productsListRV.setAdapter(productsListAdapter);
                        productsListAdapter.setClickListener((view, position) -> {
                        });
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.e("FastError", error.getMessage());
                    }
                });

        userName.setText(response.getProfile().getName());
        phoneNumber.setText(response.getProfile().getPhone());
        email.setText(response.getProfile().getEmail());
    }


}