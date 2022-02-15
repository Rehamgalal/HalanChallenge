package com.example.halanchallenge.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.halanchallenge.api.ApiService;
import com.example.halanchallenge.model.ListActivityDataItem;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<ListActivityDataItem> result = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String username, String password) {
        compositeDisposable.add(ApiService.getInstance().getMyApi().login(username, password)
                .flatMap(loginResponse ->
                                ApiService.getInstance().getMyApi().getProductsList(loginResponse.getToken())
                        , ListActivityDataItem::new
                ).subscribeOn(Schedulers.io())
                .subscribe(
                        listActivityDataItem -> {
                            result.postValue(listActivityDataItem);
                        }, (io.reactivex.functions.Consumer<? super Throwable>) throwable -> {

                        }

                ));

    }

    public LiveData<ListActivityDataItem> getResult() {
        return this.result;
    }
}
