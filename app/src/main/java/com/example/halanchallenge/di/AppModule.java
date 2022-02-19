package com.example.halanchallenge.di;


import com.example.halanchallenge.api.NetworkApi;
import com.example.halanchallenge.other.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class AppModule {
    @Singleton
    @Provides
    public static Retrofit getRetrofitInstance(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient() {
        Interceptor requestInterceptor = chain -> {
            HttpUrl url = chain.request().url().newBuilder().build();
            Request request = chain.request().newBuilder().url(url).build();
            return chain.proceed(request);
        };
        return new OkHttpClient.Builder().addInterceptor(requestInterceptor).connectTimeout(60, TimeUnit.SECONDS).build();
    }

    @Singleton
    @Provides
    public static NetworkApi getRetrofitService(Retrofit retrofit) {
        return retrofit.create(NetworkApi.class);
    }
}
