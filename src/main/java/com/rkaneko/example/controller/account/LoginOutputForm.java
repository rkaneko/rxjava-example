package com.rkaneko.example.controller.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.google.common.base.Preconditions;
import com.rkaneko.example.infra.adapter.account.model.Account;

@AllArgsConstructor
@Getter
public class LoginOutputForm {
    private boolean success;
    private Account account;

    public long accountId() {
        Preconditions.checkNotNull(account);
        return account.getId();
    }
}
