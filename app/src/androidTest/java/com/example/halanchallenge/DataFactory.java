package com.example.halanchallenge;

import com.example.halanchallenge.model.ListActivityDataItem;
import com.example.halanchallenge.model.LoginResponse;
import com.example.halanchallenge.model.Product;
import com.example.halanchallenge.model.ProductsList;
import com.example.halanchallenge.model.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DataFactory {


    private final AtomicInteger counter = new AtomicInteger(0);

    public ListActivityDataItem createItem() {
        return new ListActivityDataItem(createLoginResponse(),createProductList());
    }

    private LoginResponse createLoginResponse() {
        return new LoginResponse("ok","dfsdff",new Profile("name","","name","mail","09987765532"));
    }

    private ProductsList createProductList() {
        return new ProductsList("ok", Arrays.asList(createProduct(),createProduct(),createProduct()));
    }

    private Product createProduct() {
        int id = counter.incrementAndGet();
        return new Product(Integer.toString(id),"اسم", "des","brand","","name",10,new ArrayList<>());
    }
}
