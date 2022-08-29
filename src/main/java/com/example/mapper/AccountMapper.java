package com.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountMapper {
    @Insert("insert into account(name, password, mail) values (#{name}, #{password}, #{email})")
    boolean insert(@Param("name") String name, @Param("password") String password, @Param("email") String email);


    @Select("select password from account where name = #{name}")
    String findPasswordByUsername(@Param("name") String name);
}
