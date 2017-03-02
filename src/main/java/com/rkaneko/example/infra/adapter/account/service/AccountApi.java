package com.rkaneko.example.infra.adapter.account.service;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import com.rkaneko.example.controller.account.LoginInputForm;
import com.rkaneko.example.controller.account.LoginOutputForm;

public interface AccountApi {
    @Headers({ "Content-Type: application/json", "Accept: application/json" })
    @POST("api/account/login")
    Flowable<LoginOutputForm> login(@Body LoginInputForm inputForm);
}
