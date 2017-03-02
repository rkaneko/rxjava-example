package com.rkaneko.example.controller.recommendation;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.rkaneko.example.infra.adapter.recommendation.model.Recommendation;

@RestController
public class RecommendationController {
    @RequestMapping(path = "/api/recommendation", method = RequestMethod.POST)
    public RecommendationOutputForm run(@Validated @RequestBody RecommendationInputForm inputForm) {
        Recommendation recommendation1 = new Recommendation(3L, "Badman begins", 5);
        Recommendation recommendation2 = new Recommendation(4L, "Iron man", 4);
        List<Recommendation> recommendations = Lists.newArrayList(recommendation1, recommendation2);
        return new RecommendationOutputForm(recommendations);
    }
}
