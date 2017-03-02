package com.rkaneko.example.infra.adapter.account.service.impl;

import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.rkaneko.example.controller.account.LoginInputForm;
import com.rkaneko.example.controller.account.LoginOutputForm;
import com.rkaneko.example.infra.adapter.account.service.AccountApi;
import com.rkaneko.example.infra.adapter.account.service.AccountService;
import com.rkaneko.example.util.api.ApiRegistry;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements AccountService {
    private final ApiRegistry apiRegistry;

    @Override
    public Flowable<LoginOutputForm> login(String account, String password) {
        Preconditions.checkNotNull(account);
        Preconditions.checkNotNull(password);

        AccountApi accountApi = apiRegistry.of(AccountApi.class);
        return accountApi.login(new LoginInputForm(account, password));
    }
}
