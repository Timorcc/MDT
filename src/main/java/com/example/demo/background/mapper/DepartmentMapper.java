package com.example.demo.background.mapper;

import com.example.demo.background.entity.Department;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    @Select("select * from department")
    List<Department> findAll();

    @Insert("insert into department(depart_name) values(#{departmentName})")
    Boolean AddDepartment(String departmentName);

    @Select("select * from department where id = #{id}")
    Department findById(Long id);

    @Update("update department set depart_name=#{name} where id = #{id}")
    Boolean updateById(Long id, String name);
}
