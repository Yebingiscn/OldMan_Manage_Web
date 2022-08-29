package com.example.service;

import com.example.entity.Account;

public interface AccountServiceOperate {
    int createAccount(Account account);

    void getVerifyCode(String email);

    int loginAccount(Account account);
}
