package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select password from accounts where username = #{username}")
    String getPwdByName(@Param("username") String username);

    @Select("select role from accounts where username = #{username};")
    String getRoleByName(@Param("username") String username);

    @Select("select id, username, role from accounts")
    List<Admin> getAllUser();
}
