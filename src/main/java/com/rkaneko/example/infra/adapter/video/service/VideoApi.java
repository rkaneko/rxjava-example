package com.rkaneko.example.infra.adapter.video.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import com.rkaneko.example.controller.video.VideoOutputForm;

import io.reactivex.Flowable;

public interface VideoApi {
    @Headers({ "Content-Type: application/json", "Accept: application/json" })
    @GET("api/video")
    Observable<VideoOutputForm> get();
}
