package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountServiceOperate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class AccountController {

    @Resource
    AccountServiceOperate accountServiceOperate;

    @RequestMapping("/send-verify-code")
    public String doRegister(@RequestParam("email") String mail) {
        try {
            System.out.printf(mail);
            accountServiceOperate.getVerifyCode(mail);
            return "成功发送邮件";
        } catch (Exception e) {
            return "发生了异常，邮件发送失败";
        }
    }

    @RequestMapping("/register-verify")
    public String register(@RequestParam("username") String userName,
                           @RequestParam("password") String passWord,
                           @RequestParam("email") String email,
                           @RequestParam("verifyCode") String verifyCode) {
        Account accountForRegister = new Account(userName, passWord, email, verifyCode);
        int checkCode = accountServiceOperate.createAccount(accountForRegister);
        if (checkCode == 0) {
            return "注册成功";
        } else if (checkCode == 1) {
            return "注册失败，验证码错误";
        } else if (checkCode == 2) {
            return "注册失败，账户已存在";
        } else {
            return "注册失败";
        }
    }

    @RequestMapping("/login-verify")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String passWord,
                        HttpSession session) {
        Account accountForLogin = new Account(userName, passWord);
        int isAccountExist = accountServiceOperate.loginAccount(accountForLogin);
        if (isAccountExist == 1) {
            session.setAttribute("user", userName);
            return "登录成功," + userName;
        } else if (isAccountExist == 0) {
            return "不存在此账户";
        } else return "账号或者密码错误";
    }
}
