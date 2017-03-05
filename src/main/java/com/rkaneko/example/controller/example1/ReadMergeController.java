package com.rkaneko.example.controller.example1;

import com.rkaneko.example.controller.recommendation.RecommendationOutputForm;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheOperationInvoker;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rkaneko.example.controller.account.LoginInputForm;
import com.rkaneko.example.infra.adapter.account.service.AccountService;
import com.rkaneko.example.infra.adapter.recommendation.service.RecommendationService;
import com.rkaneko.example.infra.adapter.video.service.VideoService;

import java.util.Collections;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadMergeController {
    private final AccountService accountService;
    private final VideoService videoService;
    private final RecommendationService recommendationService;

    private static final RecommendationOutputForm RECOMMENDATIONS_ON_ERROR = new RecommendationOutputForm(Collections.emptyList());

    @RequestMapping(path = "/api/example1", method = RequestMethod.POST)
    public ReadMergeOutputForm run(@Validated @RequestBody ReadMergeInputForm inputForm) {
        // @formatter:off
        return accountService
                .login(inputForm.getAccount(), inputForm.getPassword())
                .doOnError(throwable -> log.error("Failed to login."))
                .doOnError(Throwable::printStackTrace)
                .doOnNext(loginOutputForm -> {
                    final String threadName = Thread.currentThread().getName();
                    log.info(String.format("Account service - %s", threadName));
                })
                .map(loginOutputForm -> {
                    if (!loginOutputForm.isSuccess()) {
                        throw new RuntimeException();
                    }
                    return loginOutputForm;
                })
                .flatMap(
                        loginOutputForm ->
                            recommendationService
                                    .get(loginOutputForm.accountId())
                                    .doOnError(throwable -> log.error("Error occurred on the Recommendation service."))
                                    .doOnError(Throwable::printStackTrace)
                                    .doOnNext(recommendationOutputForm -> {
                                        final String threadName = Thread.currentThread().getName();
                                        log.info(String.format("Recommendation service - %s", threadName));
                                    })
                                    .onErrorReturnItem(RECOMMENDATIONS_ON_ERROR)
                )
                .zipWith(
                        videoService.get()
                                .doOnError(throwable -> log.error("Error occurred on the Video service."))
                                .doOnError(Throwable::printStackTrace)
                                .doOnNext(videoOutputForm -> {
                                    final String threadName = Thread.currentThread().getName();
                                    log.info(String.format("Video service - %s", threadName));
                                })
                                .observeOn(Schedulers.computation())
                        , (recommendationOutputForm, videoOutputForm) ->
                                        new ReadMergeOutputForm(
                                                videoOutputForm.getVideos()
                                                , recommendationOutputForm.getRecommendations()
                                        )
                )
                .blockingSingle();
        // @formatter:on
    }
}
