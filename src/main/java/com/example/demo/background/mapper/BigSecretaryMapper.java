package com.example.demo.background.mapper;

import com.example.demo.background.entity.BigSecretary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BigSecretaryMapper {
    @Select("select * from big_secretary")
    List<BigSecretary> findAll();

    @Insert("insert into big_secretary(username,tel_num,wx_num) values(#{username},#{telNum},#{wxNum})")
    Boolean AddBigSecretary(String username, String telNum, String wxNum);

    @Select("select * from big_secretary where id = #{id}")
    BigSecretary findById(Long id);

    @Update("update big_secretary set username=#{username},tel_num=#{telNum},wx_num=#{wxNum} where id=#{id}")
    Boolean updateById(Long id, String username, String telNum, String wxNum);

    //模糊查询
    @Select("select * from big_secretary where username like '%${name}%'  ")
    List<BigSecretary> fuzzyFind(@Param("name") String name);

}
