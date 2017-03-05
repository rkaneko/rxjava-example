package com.rkaneko.example.infra.adapter.video.service.impl;

import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rkaneko.example.controller.video.VideoOutputForm;
import com.rkaneko.example.infra.adapter.video.service.VideoApi;
import com.rkaneko.example.infra.adapter.video.service.VideoService;
import com.rkaneko.example.util.api.ApiRegistry;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VideoServiceImpl implements VideoService {
    private final ApiRegistry apiRegistry;

    @Override
    public Observable<VideoOutputForm> get() {
        VideoApi videoApi = apiRegistry.of(VideoApi.class);
        return videoApi.get();
    }
}
