package com.example.halanchallenge.api;

import com.example.halanchallenge.model.LoginResponse;
import com.example.halanchallenge.model.ProductsList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface NetworkApi {

    @FormUrlEncoded
    @POST("auth")
    Observable<LoginResponse> login(@Field("username")String userName, @Field("password") String password);

    @GET("products")
    Observable<ProductsList> getProductsList(@Header("Authorization") String token);

}
