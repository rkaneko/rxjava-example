package com.rkaneko.example.infra.adapter.account.service;

import io.reactivex.Flowable;

import com.rkaneko.example.controller.account.LoginInputForm;
import com.rkaneko.example.controller.account.LoginOutputForm;

public interface AccountService {
    Flowable<LoginOutputForm> login(LoginInputForm inputForm);
}
