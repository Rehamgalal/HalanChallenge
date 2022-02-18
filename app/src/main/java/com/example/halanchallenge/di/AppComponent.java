package com.example.halanchallenge.di;


import com.example.halanchallenge.HalanChallenge;
import com.example.halanchallenge.ui.viewmodel.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent extends AndroidInjector<HalanChallenge> {

    void inject(MainViewModel viewModel);
}
