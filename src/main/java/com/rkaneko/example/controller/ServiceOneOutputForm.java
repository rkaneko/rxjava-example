package com.rkaneko.example.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServiceOneOutputForm {
    private final String name;
    private final boolean status;
}
