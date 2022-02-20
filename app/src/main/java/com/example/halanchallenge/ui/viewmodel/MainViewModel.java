package com.example.halanchallenge.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.halanchallenge.HalanChallenge;
import com.example.halanchallenge.api.NetworkApi;
import com.example.halanchallenge.model.ListActivityDataItem;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<ListActivityDataItem> result = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public NetworkApi service;

    public MainViewModel(@NonNull Application application) {
        super(application);
        ((HalanChallenge) application).getAppComponent().inject(this);
    }
    public void login(String username, String password) {
        compositeDisposable.add(service.login(username, password)
                .flatMap(loginResponse ->
                                service.getProductsList(loginResponse.getToken())
                        , ListActivityDataItem::new
                ).subscribeOn(Schedulers.io())
                .subscribe(
                        result::postValue,
                        (io.reactivex.functions.Consumer<? super Throwable>) throwable -> error.postValue(throwable.getMessage())

                ));

    }

    public boolean validate(String username,String password) {
        return username.length() >= 6 && username.length() <= 15&& !password.isEmpty();
    }

    public LiveData<ListActivityDataItem> getResult() {
        return this.result;
    }

    public LiveData<String> getError() {
        return this.error;
    }
}
