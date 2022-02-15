package com.example.halanchallenge;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;

import com.example.halanchallenge.api.ApiService;
import com.example.halanchallenge.model.ListActivityDataItem;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Login{

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void login(String username, String password,Context context) {
        compositeDisposable.add(ApiService.getInstance().getMyApi().login(username,password)
                .flatMap(loginResponse ->
                                ApiService.getInstance().getMyApi().getProductsList(loginResponse.getToken())
                , ListActivityDataItem::new
                ).subscribeOn(Schedulers.io())
        .subscribe(
                listActivityDataItem -> {
                    Intent myIntent = new Intent(context, ProductsListActivity.class);
                    myIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    myIntent.putExtra("RESPONSE",listActivityDataItem.getLoginResponse());
                    myIntent.putExtra("PRODUCTS",listActivityDataItem.getProductsList());
                    context.startActivity(myIntent);
                }, (io.reactivex.functions.Consumer<? super Throwable>) throwable -> {

                    }

        ));

}}
