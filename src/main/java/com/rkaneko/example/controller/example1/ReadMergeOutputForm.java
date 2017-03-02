package com.rkaneko.example.controller.example1;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.rkaneko.example.infra.adapter.recommendation.model.Recommendation;
import com.rkaneko.example.infra.adapter.video.model.Video;

@AllArgsConstructor
@Getter
public class ReadMergeOutputForm {
    private List<Video> videos;
    private List<Recommendation> recommendations;
}
