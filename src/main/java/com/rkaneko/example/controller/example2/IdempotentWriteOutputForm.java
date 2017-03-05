package com.rkaneko.example.controller.example2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IdempotentWriteOutputForm {
    private boolean success;
}
