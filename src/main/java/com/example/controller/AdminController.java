package com.example.controller;

import com.example.entity.Admin;
import com.example.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;
    @RequestMapping("/admin-login")
    public String login(@RequestParam("username") String adminName,
                        @RequestParam("password") String adminPwd) {
        Admin admin = new Admin(adminName, adminPwd);
        boolean pwdByUserName = adminService.getPwdByUserName(admin);
        System.out.println(pwdByUserName);
        if (pwdByUserName) {
            return "登录成功" + adminName;
        }
        else {
            return "登录失败";
        }
    }
    @RequestMapping("/admin-get-User")
    public List<Admin> showAdmin() {
        return adminService.getAllUser();
    }
}
