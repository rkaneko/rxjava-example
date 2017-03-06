package com.rkaneko.example.controller.example2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateCorporationCommand {
    private final String corporationName;
    private final String state;
    private final String messageUniqueKey;
}
