package com.rkaneko.example.infra.adapter.video.model;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Video {
    private long id;
    private String title;
    private ZonedDateTime publishedAt;
    private ZonedDateTime startDate;
}
