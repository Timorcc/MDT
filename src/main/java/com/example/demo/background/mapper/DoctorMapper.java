package com.example.demo.background.mapper;

import com.example.demo.background.entity.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DoctorMapper {

    @Select("select * from doctor")
    List<Doctor> findAll();
    @Insert("insert into doctor(username,tel_num,wx_num,depart) values(#{username},#{telNum},#{wxNum},#{depart})")
    Boolean AddDoctor(String username,String telNum,String wxNum,String depart);

}
