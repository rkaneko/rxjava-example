package com.rkaneko.example.controller.serviceb.address.get;

import com.rkaneko.example.infra.adapter.rdb.model.Address;
import com.rkaneko.example.infra.adapter.rdb.repository.AddressRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetAddressController {
    private final AddressRepository addressRepository;

    @RequestMapping(path = "/api/service-b/address/{key}", method = RequestMethod.GET)
    public ResponseEntity<GetAddressOutputForm> run(@PathVariable("key") String key) {
        Address address = addressRepository.findByMessageUniqueId(key);
        GetAddressOutputForm outputForm = new GetAddressOutputForm(address);
        if (address == null) {
            return new ResponseEntity<>(outputForm, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(outputForm, HttpStatus.OK);
    }
}
