package com.rkaneko.example.infra.adapter.rdb.service;

import com.google.common.base.Preconditions;
import com.rkaneko.example.infra.adapter.rdb.model.Address;
import com.rkaneko.example.infra.adapter.rdb.model.Corporation;
import com.rkaneko.example.infra.adapter.rdb.repository.AddressRepository;
import com.rkaneko.example.infra.adapter.rdb.repository.CorporationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateCorporationService {
    private final CorporationRepository corporationRepository;
    private final AddressRepository addressRepository;

    @Transactional(rollbackFor = {Exception.class})
    public Corporation create(Corporation anonymousCorporation, Address anonymousAddress) {
        Preconditions.checkNotNull(anonymousCorporation);
        Preconditions.checkNotNull(anonymousAddress);

        Corporation corporation = corporationRepository.save(anonymousCorporation);
        Address unidentifiedAddress = Address.builder()
                .corporationId(corporation.getId())
                .state(anonymousAddress.getState())
                .phone(anonymousAddress.getPhone())
                .build();
        Address address = addressRepository.save(unidentifiedAddress);
        return Corporation.aggregate(corporation, address);
    }
}
