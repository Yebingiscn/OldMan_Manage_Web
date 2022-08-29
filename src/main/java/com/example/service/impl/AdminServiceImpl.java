package com.example.service.impl;

import com.example.entity.Admin;
import com.example.mapper.AdminMapper;
import com.example.service.AdminService;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@CacheConfig(cacheNames = "caffeineCacheManager")
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;

    private final HashMap<Integer, List<Admin>> adminInfoMap = new HashMap<>();

    @Resource
    Cache<Integer, List<Admin>> caffeineCache;
    @Override
    public List<Admin> getAllUser() {
//        return adminMapper.getAllUser();
        caffeineCache.getIfPresent(1);
        List<Admin> objects = caffeineCache.asMap().get(1);
        if (objects == null) {
            List<Admin> allUser = adminMapper.getAllUser();
            caffeineCache.put(1, allUser);
            return allUser;
        }
       else {
            return objects;
        }
    }

    @Override
    public boolean getPwdByUserName(Admin admin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(admin.getAdminPwd(),
                adminMapper.getPwdByName(admin.getAdminName()));
    }

}
