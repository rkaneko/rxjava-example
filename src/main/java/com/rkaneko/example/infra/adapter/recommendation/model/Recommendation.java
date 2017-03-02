package com.rkaneko.example.infra.adapter.recommendation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Recommendation {
    private Long videoId;
    private String title;
    private int point;
}
