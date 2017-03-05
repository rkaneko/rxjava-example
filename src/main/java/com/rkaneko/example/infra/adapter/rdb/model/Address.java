package com.rkaneko.example.infra.adapter.rdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Address {
    private Long id;
    private Long corporationId;
    private String state;
    private String messageUniqueKey;
}
