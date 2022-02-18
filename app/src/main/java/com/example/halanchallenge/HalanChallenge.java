package com.example.halanchallenge;

import android.app.Application;

import com.example.halanchallenge.di.AppComponent;
import com.example.halanchallenge.di.DaggerAppComponent;

public class HalanChallenge extends Application {

    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        appComponent =  DaggerAppComponent.create();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
