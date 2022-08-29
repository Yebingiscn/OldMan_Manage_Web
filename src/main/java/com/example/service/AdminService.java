package com.example.service;

import com.example.entity.Admin;

import java.util.List;

public interface AdminService {
     List<Admin> getAllUser();

     boolean getPwdByUserName(Admin admin);
}
