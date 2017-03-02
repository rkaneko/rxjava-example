package com.rkaneko.example.controller.video;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.rkaneko.example.infra.adapter.video.model.Video;

@RestController
public class VideoController {
    @RequestMapping(path = "/api/video", method = RequestMethod.GET)
    public VideoOutputForm run() {
        Video video1 = new Video(1L, "24", ZonedDateTime.of(2005, 7, 1, 22, 0, 0, 0, ZoneOffset.UTC),
                ZonedDateTime.now(ZoneOffset.UTC));
        Video video2 = new Video(2L, "Prison Break", ZonedDateTime.of(2007, 10, 1, 22, 0, 0, 0, ZoneOffset.UTC),
                ZonedDateTime.now(ZoneOffset.UTC));
        List<Video> videos = Lists.newArrayList(video1, video2);
        return new VideoOutputForm(videos);
    }
}
