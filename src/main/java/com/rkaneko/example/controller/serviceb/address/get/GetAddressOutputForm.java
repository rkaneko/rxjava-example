package com.rkaneko.example.controller.serviceb.address.get;

import com.rkaneko.example.infra.adapter.rdb.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAddressOutputForm {
    private Address address;
}
