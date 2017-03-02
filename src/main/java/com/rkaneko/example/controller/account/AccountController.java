package com.rkaneko.example.controller.account;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rkaneko.example.infra.adapter.account.model.Account;

@RestController
public class AccountController {
    @RequestMapping(path = "/api/account/login", method = RequestMethod.POST)
    public LoginOutputForm login(@Validated @RequestBody LoginInputForm inputForm) {
        return new LoginOutputForm(true, new Account(1L, "Ryota"));
    }
}
