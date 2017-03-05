package com.rkaneko.example.infra.adapter.serviceb.service;

import com.rkaneko.example.controller.serviceb.address.create.CreateAddressInputForm;
import com.rkaneko.example.controller.serviceb.address.create.CreateAddressOutputForm;
import com.rkaneko.example.controller.serviceb.address.get.GetAddressOutputForm;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.*;

public interface AddressApi {
    @Headers({"Accept:application/json"})
    @GET("api/service-b/address/{key}")
    Observable<Response<GetAddressOutputForm>> get(@Path("key") String key);

    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("api/service-b/address/create")
    Observable<Response<CreateAddressOutputForm>> create(@Body CreateAddressInputForm inputForm);
}
