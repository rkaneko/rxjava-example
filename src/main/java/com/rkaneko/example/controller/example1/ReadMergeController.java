package com.rkaneko.example.controller.example1;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rkaneko.example.controller.account.LoginInputForm;
import com.rkaneko.example.infra.adapter.account.service.AccountService;
import com.rkaneko.example.infra.adapter.recommendation.service.RecommendationService;
import com.rkaneko.example.infra.adapter.video.service.VideoService;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReadMergeController {
    private final AccountService accountService;
    private final VideoService videoService;
    private final RecommendationService recommendationService;

    @RequestMapping(path = "/api/example1", method = RequestMethod.POST)
    public ReadMergeOutputForm run(@Validated @RequestBody ReadMergeInputForm inputForm) {
        LoginInputForm loginInputForm = new LoginInputForm(inputForm.getAccount(), inputForm.getPassword());
        // @formatter:off
        return accountService
                .login(loginInputForm)
                .flatMap(
                        loginOutputForm -> recommendationService.get(loginOutputForm.accountId())
                )
                .zipWith(
                        videoService.get()
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
