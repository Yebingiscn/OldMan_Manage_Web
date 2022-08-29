package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    String name;
    String passWord;
    String email;
    String verifyCode;

    public Account(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }
}
