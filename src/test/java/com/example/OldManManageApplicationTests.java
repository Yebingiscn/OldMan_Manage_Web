package com.example;

import com.example.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class OldManManageApplicationTests {
    @Resource
    AdminMapper adminMapper;
    @Test
    public void getNotificationInfo() {
        System.out.println(adminMapper.getPwdByName("test"));
    }
}

