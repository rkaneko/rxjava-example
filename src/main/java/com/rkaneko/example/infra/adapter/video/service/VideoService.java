package com.rkaneko.example.infra.adapter.video.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import com.rkaneko.example.controller.video.VideoOutputForm;

public interface VideoService {
    @Headers({ "Accept: application/json" })
    @GET("api/video")
    Observable<VideoOutputForm> get();
}
