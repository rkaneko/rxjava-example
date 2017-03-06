package com.rkaneko.example.controller.example2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class IdempotentWriteInputForm {
    private String corporationName;
    private String state;
    private String messageUniqueKey;
}
