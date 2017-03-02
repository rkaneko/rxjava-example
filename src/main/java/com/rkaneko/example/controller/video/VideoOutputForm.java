package com.rkaneko.example.controller.video;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.rkaneko.example.infra.adapter.video.model.Video;

@AllArgsConstructor
@Getter
public class VideoOutputForm {
    private List<Video> videos;
}
