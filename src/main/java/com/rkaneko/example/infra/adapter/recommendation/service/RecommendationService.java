package com.rkaneko.example.infra.adapter.recommendation.service;

import io.reactivex.Flowable;

import com.rkaneko.example.controller.recommendation.RecommendationOutputForm;

public interface RecommendationService {
    Flowable<RecommendationOutputForm> get(long accountId);
}
