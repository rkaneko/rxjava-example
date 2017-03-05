package com.rkaneko.example.infra.adapter.recommendation.service;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import com.rkaneko.example.controller.recommendation.RecommendationInputForm;
import com.rkaneko.example.controller.recommendation.RecommendationOutputForm;

public interface RecommendationApi {
    @Headers({ "Content-Type: application/json", "Accept: application/json" })
    @POST("api/recommendation")
    Observable<RecommendationOutputForm> get(@Body RecommendationInputForm recommendationInputForm);
}
