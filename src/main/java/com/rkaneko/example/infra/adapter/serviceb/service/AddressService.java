package com.rkaneko.example.infra.adapter.serviceb.service;

import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import retrofit2.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.rkaneko.example.controller.serviceb.address.create.CreateAddressInputForm;
import com.rkaneko.example.controller.serviceb.address.get.GetAddressOutputForm;
import com.rkaneko.example.infra.adapter.rdb.model.Address;
import com.rkaneko.example.util.api.ApiRegistry;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressService {
    private final ApiRegistry apiRegistry;

    public Observable<Address> save(Address anonymousAddress) {
        Preconditions.checkNotNull(anonymousAddress);

        AddressApi addressApi = apiRegistry.of(AddressApi.class);

        Observable<Response<GetAddressOutputForm>> getAddressResp$ = addressApi.get(anonymousAddress
                .getMessageUniqueKey());
        // @formatter:off
        return getAddressResp$.flatMap(getAddressResp -> {
            if (getAddressResp.isSuccessful()) {
                return Observable.just(getAddressResp.body().getAddress());
            }
            if (getAddressResp.code() != 404) {
                new UnsupportedOperationException(getAddressResp.errorBody().string());
            }
            return addressApi.create(
                    new CreateAddressInputForm(
                            anonymousAddress.getCorporationId()
                            , anonymousAddress.getState()
                            , anonymousAddress.getMessageUniqueKey()
                    )
            ).map(createAddressOutputFormResp -> {
                Long addressId = createAddressOutputFormResp.body().getAddressId();
                return Address.of(addressId, anonymousAddress);
            });
        });
        // @formatter:on
    }
}
