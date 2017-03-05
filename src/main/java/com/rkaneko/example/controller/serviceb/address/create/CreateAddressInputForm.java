package com.rkaneko.example.controller.serviceb.address.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateAddressInputForm {
    private Long corporationId;
    private String state;
    private String messageUniqueKey;
}
