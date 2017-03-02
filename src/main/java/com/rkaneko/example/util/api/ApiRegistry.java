package com.rkaneko.example.util.api;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class ApiRegistry {

    private final Retrofit retrofit;

    public ApiRegistry(String baseUrl, HttpLoggingInterceptor.Level logLevel) {
        Preconditions.checkNotNull(!Strings.isNullOrEmpty(baseUrl));
        Preconditions.checkNotNull(logLevel);

        this.retrofit = new RetrofitBuilder().baseUrl(baseUrl).logLevel(logLevel).build();
    }

    public <T> T of(Class<T> apiClass) {
        Preconditions.checkNotNull(apiClass);
        return retrofit.create(apiClass);
    }
}
