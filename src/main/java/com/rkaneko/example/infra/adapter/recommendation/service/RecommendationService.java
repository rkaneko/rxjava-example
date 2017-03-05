package com.rkaneko.example.infra.adapter.recommendation.service;

import com.rkaneko.example.controller.recommendation.RecommendationOutputForm;
import io.reactivex.Observable;

public interface RecommendationService {
    Observable<RecommendationOutputForm> get(long accountId);
}
