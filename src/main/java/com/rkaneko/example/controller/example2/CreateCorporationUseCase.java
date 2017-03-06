package com.rkaneko.example.controller.example2;

import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rkaneko.example.infra.adapter.rdb.model.Address;
import com.rkaneko.example.infra.adapter.rdb.model.Corporation;
import com.rkaneko.example.infra.adapter.rdb.service.CorporationService;
import com.rkaneko.example.infra.adapter.serviceb.service.AddressService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateCorporationUseCase {
    private final CorporationService corporationService;
    private final AddressService addressService;

    public Observable<Corporation> execute(CreateCorporationCommand aCommand) {
        Corporation anonymousCorporation = Corporation.anonymous(aCommand.getCorporationName(),
                aCommand.getMessageUniqueKey());
        // @formatter:off
        return corporationService.save(anonymousCorporation).flatMap(
                corporation -> {
                    Address anonymousAddress = Address.anonymous(
                            corporation.getId()
                            , aCommand.getState(),
                            aCommand.getMessageUniqueKey()
                    );
                    return addressService.save(anonymousAddress)
                            .map(address -> Corporation.aggregate(corporation, address));
                });
        // @formatter:on
    }
}
