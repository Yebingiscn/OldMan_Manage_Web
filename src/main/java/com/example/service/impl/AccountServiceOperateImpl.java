package com.example.service.impl;

import com.example.entity.Account;
import com.example.mapper.AccountMapper;
import com.example.service.AccountServiceOperate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceOperateImpl implements AccountServiceOperate {
    @Resource
    StringRedisTemplate template;

    @Resource
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String from;
    @Resource
    AccountMapper accountMapper;

    @Override
    public int createAccount(Account account) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (doVerify(account.getEmail(), account.getVerifyCode())) {
            boolean insert = accountMapper.insert
                    (account.getName(), encoder.encode(account.getPassWord()), account.getEmail());
            if (!insert) {
                return 2;
            }
        } else return 1;
        return 0;
    }

    @Override
    public void getVerifyCode(String email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("[网站]注册验证码");
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;
        System.out.println(code);
        template.opsForValue().set("verify:code" + email, code + "", 3, TimeUnit.MINUTES);
        simpleMailMessage.setText("注册验证码为：" + code + "三分钟内有效，非本人操作请忽略");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(from);
        mailSender.send(simpleMailMessage);
    }

    @Override
    public int loginAccount(Account account) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            String passwordIsBcInDataBase = accountMapper.findPasswordByUsername(account.getName());
            return encoder.matches(account.getPassWord(), passwordIsBcInDataBase) ? 1 : 0;
        } catch (Exception e) {
            return 2;
        }
    }

    public boolean doVerify(String mail, String verify) {
        String isExitsInRedis = template.opsForValue().get("verify:code" + mail);
        System.out.println(isExitsInRedis + " " + verify);
        if (isExitsInRedis == null) {
            return false;
        }
        if (!isExitsInRedis.equals(verify)) {
            return false;
        }
        template.delete("verify:code" + mail);
        return true;
    }

}
