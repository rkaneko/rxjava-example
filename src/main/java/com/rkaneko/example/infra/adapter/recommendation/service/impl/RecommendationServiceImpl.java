package com.rkaneko.example.infra.adapter.recommendation.service.impl;

import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.rkaneko.example.controller.recommendation.RecommendationInputForm;
import com.rkaneko.example.controller.recommendation.RecommendationOutputForm;
import com.rkaneko.example.infra.adapter.recommendation.service.RecommendationApi;
import com.rkaneko.example.infra.adapter.recommendation.service.RecommendationService;
import com.rkaneko.example.util.api.ApiRegistry;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecommendationServiceImpl implements RecommendationService {
    private final ApiRegistry apiRegistry;

    @Override
    public Observable<RecommendationOutputForm> get(long accountId) {
        Preconditions.checkState(accountId > 0);
        RecommendationInputForm inputForm = new RecommendationInputForm(accountId);
        RecommendationApi recommendationApi = apiRegistry.of(RecommendationApi.class);
        return recommendationApi.get(inputForm);
    }
}
