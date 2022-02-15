package com.example.halanchallenge;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;

import com.example.halanchallenge.api.ApiService;
import com.example.halanchallenge.model.LoginResponse;

import java.util.function.Consumer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

// Call async LoginTask
public class Login{

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void login(String username, String password,Context context) {
        compositeDisposable.add(ApiService.getInstance().getMyApi().login(username,password).subscribeOn(Schedulers.io())
        .subscribe(new io.reactivex.functions.Consumer<LoginResponse>() {
            @Override
            public void accept(LoginResponse loginResponse) throws Exception {
                Intent myIntent = new Intent(context, ProductsListActivity.class);
                myIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                myIntent.putExtra("RESPONSE",loginResponse);
                context.startActivity(myIntent);
            }
        }));
    }
}
