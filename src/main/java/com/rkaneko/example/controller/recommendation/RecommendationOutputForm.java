package com.rkaneko.example.controller.recommendation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.rkaneko.example.infra.adapter.recommendation.model.Recommendation;

@AllArgsConstructor
@Getter
public class RecommendationOutputForm {
    private List<Recommendation> recommendations;
}
