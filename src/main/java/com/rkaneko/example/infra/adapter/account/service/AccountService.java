package com.rkaneko.example.infra.adapter.account.service;

import com.rkaneko.example.controller.account.LoginOutputForm;
import io.reactivex.Observable;

public interface AccountService {
    Observable<LoginOutputForm> login(String account, String password);
}
