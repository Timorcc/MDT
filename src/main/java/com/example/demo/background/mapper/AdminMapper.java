package com.example.demo.background.mapper;

import com.example.demo.background.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where username =#{username}")
    Admin findByUsername(String username);
}
