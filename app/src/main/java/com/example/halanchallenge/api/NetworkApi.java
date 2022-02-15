package com.example.halanchallenge.api;

import com.example.halanchallenge.model.LoginResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkApi {

    @FormUrlEncoded
    @POST("auth")
    Single<LoginResponse> login(@Field("username")String userName, @Field("password") String password);
}
