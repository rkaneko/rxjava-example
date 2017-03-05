package com.rkaneko.example.controller.serviceb.address.create;

import com.rkaneko.example.infra.adapter.rdb.model.Address;
import com.rkaneko.example.infra.adapter.rdb.repository.AddressRepository;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateAddressController {
    private final AddressRepository addressRepository;

    @RequestMapping(path = "/api/service-b/address/create", method = RequestMethod.POST)
    public ResponseEntity<CreateAddressOutputForm> run(@Validated @RequestBody CreateAddressInputForm inputForm) {
        Address address = Address.builder().corporationId(inputForm.getCorporationId())
                .state(inputForm.getState())
                .messageUniqueKey(inputForm.getMessageUniqueKey())
                .build();
        try {
            Address saved = addressRepository.insert(address);
            return new ResponseEntity<>(new CreateAddressOutputForm(saved.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
