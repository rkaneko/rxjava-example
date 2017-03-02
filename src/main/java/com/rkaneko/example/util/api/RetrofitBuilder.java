package com.rkaneko.example.util.api;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.rkaneko.example.util.json.ObjectMapperBuilder;

public class RetrofitBuilder {

    private String baseUrl;
    private HttpLoggingInterceptor.Level logLevel;

    public RetrofitBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RetrofitBuilder logLevel(HttpLoggingInterceptor.Level level) {
        this.logLevel = level;
        return this;
    }

    public Retrofit build() {
        Preconditions.checkNotNull(!Strings.isNullOrEmpty(baseUrl));

        if (logLevel == null) {
            logLevel = HttpLoggingInterceptor.Level.NONE;
        }

        ObjectMapper mapper = new ObjectMapperBuilder().configure().withJavaTimeModule().withOptionalModule().build();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor(logLevel)).build();
        JacksonConverterFactory factory = JacksonConverterFactory.create(mapper);
        return new Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    private Interceptor loggingInterceptor(HttpLoggingInterceptor.Level logLevel) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(logLevel);
        return interceptor;
    }
}
